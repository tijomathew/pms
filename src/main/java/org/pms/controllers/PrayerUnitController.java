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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        prayerUnitService.addPrayerUnitSM(prayerUnit);

        prayerUnitService.createPrayerUnitFormBackObject(modelMap);

        return PageNames.PRAYERUNIT;
    }

    @RequestMapping(value = "displayWardGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWard() {
        List<PrayerUnit> allPrayerUnits = prayerUnitService.getAllPrayerUnits();
        List<PrayerUnitDto> prayerUnitDtoList = prayerUnitService.createPrayerUnitDtos(allPrayerUnits);
        List<GridRow> prayerUnitGridRows = new ArrayList<GridRow>(prayerUnitDtoList.size());
        for (PrayerUnitDto prayerUnitDto : prayerUnitDtoList) {
            prayerUnitGridRows.add(new WardWrapper(prayerUnitDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(10, 2, 20, prayerUnitGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }



}
