package org.pms.controllers;

import org.apache.commons.lang3.StringUtils;
import org.pms.custompropertyeditors.MassCentreCustomPropertyEditor;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.displaywrappers.PrayerUnitWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.MassCentreService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private MassCentreService massCentreService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewprayerunit.action", method = RequestMethod.GET)
    public String prayerUnitPageDisplay(Model modelMap) {

        prayerUnitService.createPrayerUnitFormBackObject(modelMap);

        return PageName.PRAYERUNIT.toString();
    }


    @RequestMapping(value = "/addprayerunit.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addPrayerUnit(@ModelAttribute("prayerUnit") @Valid PrayerUnit prayerUnit, BindingResult result) {

        if (!result.hasErrors()) {

            if (prayerUnit.getId() == null && prayerUnit.getPrayerUnitNo() == null) {

                prayerUnit.getMappedMassCentre().addPrayerUnitsForMassCentre(prayerUnit);

                prayerUnitService.setPrayerUnitNumber(prayerUnit);

                User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

                if (currentUser.getSystemRole() != SystemRole.PRAYER_UNIT_ADMIN) {
                    prayerUnitService.addPrayerUnitSM(prayerUnit);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, prayerUnit.getPrayerUnitName(), SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add prayer unit as a PU admin in the system.");
                }
            } else {
                PrayerUnit retrievedPrayerUnit = prayerUnitService.getPrayerUnitForIDSM(prayerUnit.getId());
                if (!prayerUnit.getMappedMassCentre().getMappedParish().equals(retrievedPrayerUnit.getMappedMassCentre().getMappedParish())) {
                    prayerUnitService.setPrayerUnitNumber(prayerUnit);
                }
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
    Object generateJsonDisplayForPrayerUnit(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<PrayerUnit> allPrayerUnits = prayerUnitService.getAllPrayerUnitsForUserRole(currentUser);
        Integer totalRows = allPrayerUnits.size();

        List<PrayerUnit> allPrayerUnitSubList = new ArrayList<>();

        if (totalRows > 0) {
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
    String generatePrayerUnitSelectBox(@RequestParam(value = "selectedMassCentreId", required = true) Long selectedMassCentreId) {
        String returnObject = StringUtils.EMPTY;
        if (selectedMassCentreId != 0l) {
            List<PrayerUnit> prayerUnitList = prayerUnitService.getAllPrayerUnitsForMassCentreID(selectedMassCentreId);
            List<SelectBox<String, Long>> prayerUnitSelectBoxList = prayerUnitList.stream().map(prayerUnit -> new SelectBox<>(prayerUnit.getPrayerUnitName(), prayerUnit.getId())).collect(Collectors.toList());
            returnObject = SelectBox.getJsonForSelectBoxCreation(prayerUnitSelectBoxList);
        }
        return returnObject;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
        binder.registerCustomEditor(MassCentre.class, new MassCentreCustomPropertyEditor(massCentreService));
    }

}
