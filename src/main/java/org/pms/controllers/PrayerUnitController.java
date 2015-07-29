package org.pms.controllers;

import org.apache.commons.lang3.StringUtils;
import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.displaywrappers.PrayerUnitWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.*;
import org.pms.models.MassCenter;
import org.pms.models.PrayerUnit;
import org.pms.models.SelectBox;
import org.pms.models.User;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private MassCenterService massCenterService;

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
    CustomResponse addPrayerUnit(Model modelMap, @ModelAttribute("prayerUnit") @Valid PrayerUnit prayerUnit, BindingResult result) {

        if (!result.hasErrors()) {
            MassCenter massCenter = massCenterService.getMassCenterForIDSM(prayerUnit.getMassCenterNo());
            prayerUnit.setMappedMassCenter(massCenter);
            massCenter.addPrayerUnitsForMassCenter(prayerUnit);

            Long prayerUnitCounter = prayerUnitService.getPrayerUnitCountUnderParish(prayerUnit.getMappedMassCenter().getMappedParish().getId());

            prayerUnit.setPrayerUnitNo(++prayerUnitCounter);

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
            boolean permissionDenied = false;

            if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
                permissionDenied = true;
            }
            if (!permissionDenied) {
                prayerUnitService.addPrayerUnitSM(prayerUnit);
            } else {
                // show the error that prayer unit cannot be add by Prayer Unit admin. He can edit only its information.
            }

            prayerUnitService.createPrayerUnitFormBackObject(modelMap);
            customResponse = createSuccessMessage(StatusCode.SUCCESS, prayerUnit.getPrayerUnitName(), SUCCESS_MESSAGE_DISPLAY);
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
    String generatePrayerUnitSelectBox(@RequestParam(value = "selectedMassCenterId", required = true) Long selectedMassCenterId) {
        String returnObject = StringUtils.EMPTY;
        if (selectedMassCenterId != 0l) {
            List<PrayerUnit> prayerUnitList = prayerUnitService.getPrayerUnitForMassCenterIDSM(selectedMassCenterId);
            List<SelectBox<String, Long>> prayerUnitSelectBoxList = prayerUnitList.stream().map(prayerUnit -> new SelectBox<>(prayerUnit.getPrayerUnitName(), prayerUnit.getId())).collect(Collectors.toList());
            returnObject = SelectBox.getJsonForSelectBoxCreation(prayerUnitSelectBoxList);
        }
        return returnObject;
    }


}
