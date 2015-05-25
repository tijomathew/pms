package org.pms.controllers;

import org.pms.constants.PageNames;
import org.pms.constants.SystemRoles;
import org.pms.displaywrappers.WardWrapper;
import org.pms.dtos.PrayerUnitDto;
import org.pms.helpers.*;
import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.pms.models.PrayerUnit;
import org.pms.models.User;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        return PageNames.PRAYERUNIT;
    }


    @RequestMapping(value = "/addprayerunit.action", method = RequestMethod.POST)
    public String addWard(@ModelAttribute("prayerUnit") PrayerUnit prayerUnit, Model modelMap) {

        MassCenter massCenter = massCenterService.getMassCenterForIDSM(prayerUnit.getMassCenterId());
        prayerUnit.setMappedMassCenter(massCenter);
        massCenter.addPrayerUnitsForMassCenter(prayerUnit);

        String attachedStringToID = massCenter.getMassCenterID() + "-PU";
        Long prayerUnitCounter = prayerUnitService.getPrayerUnitCountForMassCenter(prayerUnit.getMappedMassCenter().getId());
        if (prayerUnitCounter < 10) {
            attachedStringToID += "0";
        }

        prayerUnit.setPrayerUnitCode(attachedStringToID + (++prayerUnitCounter));

        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRoles.PMS_CURRENT_USER);
        boolean permissionDenied = false;

        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            permissionDenied = true;
        }
        if (!permissionDenied) {
            prayerUnitService.addPrayerUnitSM(prayerUnit);
        } else {
            // show the error that prayer unit cannot be add by Prayer Unit admin. He can edit only its information.
        }

        prayerUnitService.createPrayerUnitFormBackObject(modelMap);

        return PageNames.PRAYERUNIT;
    }

    @RequestMapping(value = "displayprayerunitgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWard(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        List<PrayerUnit> allPrayerUnits = new ArrayList<>();
        Integer totalRows = 0;
        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.ADMIN)) {
            allPrayerUnits = prayerUnitService.getAllPrayerUnits();
            totalRows = prayerUnitService.getPrayerUnitCount().intValue();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            List<MassCenter> massCentersUnderParish = parishService.getParishForIDSM(currentUser.getParishId()).getMassCenterList();
            for (MassCenter massCenter : massCentersUnderParish) {
                allPrayerUnits.addAll(massCenter.getPrayerUnits());
            }
            totalRows = allPrayerUnits.size();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            MassCenter massCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId());
            allPrayerUnits.addAll(massCenter.getPrayerUnits());
            totalRows = allPrayerUnits.size();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
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
            prayerUnitGridRows.add(new WardWrapper(prayerUnitDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalRows, page, rows, prayerUnitGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }


}
