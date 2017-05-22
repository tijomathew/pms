package org.pms.prayerunit;

import org.apache.commons.lang3.StringUtils;
import org.pms.parish.ParishCustomPropertyEditor;
import org.pms.common.PageName;
import org.pms.user.StatusCode;
import org.pms.user.SystemRole;
import org.pms.common.error.AbstractErrorAndGridHandler;
import org.pms.common.error.CustomResponse;
import org.pms.common.GridRow;
import org.pms.common.JsonBuilder;
import org.pms.common.QueryFormat;
import org.pms.common.RequestResponseHolder;
import org.pms.domain.Parish;
import org.pms.domain.PrayerUnit;
import org.pms.domain.SelectBox;
import org.pms.domain.User;
import org.pms.parish.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the controller of the PrayerUnit module.
 * User: tijo
 */

@Controller
public class PrayerUnitController extends AbstractErrorAndGridHandler {

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewprayerunit.action", method = RequestMethod.GET)
    public String viewPrayerUnitPageDisplay(Model modelMap) {

        prayerUnitService.createPrayerUnitFormBackObject(modelMap);

        return PageName.PRAYERUNIT.toString();
    }


    @RequestMapping(value = "/addprayerunit.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addPrayerUnit(@ModelAttribute("prayerUnit") @Valid PrayerUnit prayerUnit, BindingResult result) {

        if (!result.hasErrors()) {

            if (prayerUnit.getId() == null && prayerUnit.getPrayerUnitNo() == null) {

                User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
                synchronized (this) {
                    if (currentUser.getSystemRole() != SystemRole.PRAYER_UNIT_ADMIN) {
                        prayerUnitService.setPrayerUnitNumber(prayerUnit);
                        prayerUnit.getMappedParish().addPrayerUnitsForParish(prayerUnit);
                        prayerUnitService.addPrayerUnitSM(prayerUnit);
                        customResponse = createSuccessMessage(StatusCode.SUCCESS, prayerUnit.getPrayerUnitName(), SUCCESS_MESSAGE_DISPLAY);
                    } else {
                        customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add prayer unit as a PU admin in the system.");
                    }
                }
            } else {
                PrayerUnit retrievedPrayerUnit = prayerUnitService.getPrayerUnitForIDSM(prayerUnit.getId());
                prayerUnit.setPrayerUnitNo(retrievedPrayerUnit.getPrayerUnitNo());
                prayerUnitService.updatePrayerUnit(prayerUnit);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, prayerUnit.getPrayerUnitName(), "updated successfully.");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displayprayerunitgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForPrayerUnit(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<PrayerUnit> allPrayerUnits = prayerUnitService.getAllPrayerUnitsForUserRole(currentUser);
        Integer totalRows = allPrayerUnits.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<PrayerUnit> allPrayerUnitSubList = new ArrayList<>();

        if (totalRows > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allPrayerUnits, formatter.by(sortIndexColumnName, PrayerUnit.class));
            }
            allPrayerUnitSubList = JsonBuilder.generateSubList(page, rows, totalRows, allPrayerUnits);
        }

        List<GridRow> prayerUnitGridRows = new ArrayList<GridRow>(allPrayerUnits.size());
        if (!allPrayerUnitSubList.isEmpty()) {
            prayerUnitGridRows = allPrayerUnitSubList.stream().map(prayerUnit -> new PrayerUnitWrapper(prayerUnit)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalRows, page, rows, prayerUnitGridRows));
    }

    @RequestMapping(value = "/createprayerunitselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generatePrayerUnitSelectBox(@RequestParam(value = "selectedParishId", required = true) Long selectedParishId) {
        String returnObject = StringUtils.EMPTY;
        if (selectedParishId != 0l) {
            List<PrayerUnit> prayerUnitList = prayerUnitService.getAllPrayerUnitsForParishID(selectedParishId);
            List<SelectBox<String, Long>> prayerUnitSelectBoxList = prayerUnitList.stream().map(prayerUnit -> new SelectBox<>(prayerUnit.getPrayerUnitName(), prayerUnit.getId())).collect(Collectors.toList());
            returnObject = SelectBox.getJsonForSelectBoxCreation(prayerUnitSelectBoxList);
        }
        return returnObject;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }

}
