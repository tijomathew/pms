package org.pms.controllers;

import org.pms.displaywrappers.WardWrapper;
import org.pms.dtos.PrayerUnitDto;
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

    @RequestMapping(value = "/viewWard.action", method = RequestMethod.GET)
    public String wardPageDisplay(Model modelMap) {

        createPrayerUnitFormBackObject(modelMap);

        return "prayerunit";
    }


    @RequestMapping(value = "/addprayerunit.action", method = RequestMethod.POST)
    public String addWard(@ModelAttribute("prayerUnit") PrayerUnit prayerUnit, Model modelMap) {

        MassCenter massCenter = massCenterService.getMassCenterForIDSM(prayerUnit.getMassCenterId());
        prayerUnit.setMappedMassCenter(massCenter);
        massCenter.addPrayerUnitsForMassCenter(prayerUnit);
        prayerUnitService.addPrayerUnitSM(prayerUnit);

        createPrayerUnitFormBackObject(modelMap);

        return "prayerunit";
    }

    @RequestMapping(value = "displayWardGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWard(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<PrayerUnit> allPrayerUnits = prayerUnitService.getAllPrayerUnits();
        List<PrayerUnitDto> prayerUnitDtoList = prayerUnitService.createPrayerUnitDtos(allPrayerUnits);
        Integer totalRows = prayerUnitService.getPrayerUnitCount().intValue();
        List<PrayerUnitDto> allPrayerUnitSubList = new ArrayList<>();

        if(totalRows > 0){
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

    private void createPrayerUnitFormBackObject(Model modelMap) {
        Long prayerUnitCounter = prayerUnitService.getPrayerUnitCount();
        PrayerUnit formBackPrayerUnit = new PrayerUnit();
        formBackPrayerUnit.setPrayerUnitCode("PU" + (++prayerUnitCounter));
        modelMap.addAttribute("prayerUnit", formBackPrayerUnit);
        Map<Long, String> massCenterMap = new HashMap<Long, String>();
        List<MassCenter> massCenterList = massCenterService.getAllMassCenter();
        massCenterMap.put(0l, "--Please Select--");
        if (!massCenterList.isEmpty()) {
            for (MassCenter massCenter : massCenterList)
                massCenterMap.put(massCenter.getId(), massCenter.getName());
        }
        modelMap.addAttribute("massCenterMap", massCenterMap);
    }
}
