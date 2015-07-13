package org.pms.controllers;

import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.displaywrappers.PrayerUnitWrapper;
import org.pms.dtos.PrayerUnitDto;
import org.pms.error.CustomErrorMessage;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.MassCenter;
import org.pms.models.PrayerUnit;
import org.pms.models.User;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the controller of the PrayerUnit module.
 * User: tijo
 */

@Controller
public class PrayerUnitController {

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private ParishService parishService;

    @RequestMapping(value = "/viewprayerunit.action", method = RequestMethod.GET)
    public String wardPageDisplay(Model modelMap) {

        prayerUnitService.createPrayerUnitFormBackObject(modelMap);

        return PageName.PRAYERUNIT.toString();
    }


    @RequestMapping(value = "/addprayerunit.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addWard(Model modelMap, @ModelAttribute("prayerUnit") @Valid PrayerUnit prayerUnit, BindingResult result) {

        CustomResponse res = null;
        List<CustomErrorMessage> customErrorMessages = new ArrayList<CustomErrorMessage>();

        if (!result.hasErrors()) {
            MassCenter massCenter = massCenterService.getMassCenterForIDSM(prayerUnit.getMassCenterId());
            prayerUnit.setMappedMassCenter(massCenter);
            massCenter.addPrayerUnitsForMassCenter(prayerUnit);

            Long prayerUnitCounter = prayerUnitService.getPrayerUnitCountForMassCenter(prayerUnit.getMappedMassCenter().getId());

            prayerUnit.setPrayerUnitCode(++prayerUnitCounter);

            User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRole.PMS_CURRENT_USER.toString());
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
        } else {
            List<FieldError> allErrors = result.getFieldErrors();
            for (FieldError objectError : allErrors) {
                customErrorMessages.add(new CustomErrorMessage(objectError.getField(), objectError.getField() + "  " + objectError.getDefaultMessage()));
            }
            res = new CustomResponse("FAIL", customErrorMessages);
        }

        return res;
    }

    @RequestMapping(value = "displayprayerunitgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWard(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<PrayerUnit> allPrayerUnits = new ArrayList<>();
        Integer totalRows = 0;
        if (currentUser.getSystemRole() == SystemRole.ADMIN) {
            allPrayerUnits = prayerUnitService.getAllPrayerUnits();
            totalRows = prayerUnitService.getPrayerUnitCount().intValue();
        } else if (currentUser.getSystemRole() == SystemRole.PARISH_ADMIN) {
            List<MassCenter> massCentersUnderParish = parishService.getParishForIDSM(currentUser.getParishId()).getMassCenterList();
            for (MassCenter massCenter : massCentersUnderParish) {
                allPrayerUnits.addAll(massCenter.getPrayerUnits());
            }
            totalRows = allPrayerUnits.size();
        } else if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
            MassCenter massCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId());
            allPrayerUnits.addAll(massCenter.getPrayerUnits());
            totalRows = allPrayerUnits.size();
        } else if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
            allPrayerUnits.add(prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId()));
            totalRows = 1;
        }

        List<PrayerUnitDto> prayerUnitDtoList = prayerUnitService.createPrayerUnitDtos(allPrayerUnits);

        List<PrayerUnitDto> allPrayerUnitSubList = new ArrayList<>();

        if (totalRows > 0) {
            allPrayerUnitSubList = JsonBuilder.generateSubList(page, rows, totalRows, prayerUnitDtoList);
        }

        List<GridRow> prayerUnitGridRows = new ArrayList<GridRow>(prayerUnitDtoList.size());
        for (PrayerUnitDto prayerUnitDto : allPrayerUnitSubList) {
            prayerUnitGridRows.add(new PrayerUnitWrapper(prayerUnitDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalRows, page, rows, prayerUnitGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }


}
