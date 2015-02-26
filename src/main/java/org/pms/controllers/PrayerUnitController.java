package org.pms.controllers;

import org.pms.displaywrappers.WardWrapper;
import org.pms.dtos.WardDto;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.models.MassCenter;
import org.pms.models.PrayerUnit;
import org.pms.services.MassCenterService;
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

    @RequestMapping(value = "/viewWard.action", method = RequestMethod.GET)
    public String wardPageDisplay(Model modelMap) {
        Long prayerUnitCounter = prayerUnitService.getPrayerUnitCount();
        PrayerUnit formBackPrayerUnit = new PrayerUnit();
        formBackPrayerUnit.setPrayerUnitCode("PU" + (++prayerUnitCounter));
        modelMap.addAttribute("ward", formBackPrayerUnit);
        Map<Long, String> massCenterMap = new HashMap<Long, String>();
        List<MassCenter> massCenterList = massCenterService.getAllMassCenter();
        massCenterMap.put(0l, "--Please Select--");
        if (!massCenterList.isEmpty()) {
            for (MassCenter massCenter : massCenterList)
                massCenterMap.put(massCenter.getId(), massCenter.getName());
        }
        modelMap.addAttribute("massCenterMap", massCenterMap);
        return "prayerunit";
    }

    @RequestMapping(value = "/addWard.action", method = RequestMethod.POST)
    public String addWard(@ModelAttribute("ward") PrayerUnit ward, Model modelMap) {
        modelMap.addAttribute("ward", new PrayerUnit());
        MassCenter massCenter = massCenterService.getMassCenterForIDSM(ward.getMassCenterId());
        ward.setMappedMassCenter(massCenter);
        massCenter.addWardsForMassCenter(ward);
        prayerUnitService.addPrayerUnitSM(ward);
        return "prayerunit";
    }

    @RequestMapping(value = "displayWardGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWard() {
        List<PrayerUnit> allWards = prayerUnitService.getAllPrayerUnits();
        List<WardDto> wardDtoList = prayerUnitService.createWardDtos(allWards);
        List<GridRow> wardGridRows = new ArrayList<GridRow>(wardDtoList.size());
        for (WardDto wardDto : wardDtoList) {
            wardGridRows.add(new WardWrapper(wardDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(10, 2, 20, wardGridRows);

        JsonBuilder jsonBuilder = new JsonBuilder();
        return jsonBuilder.convertToJson(resultContainer);
    }
}
