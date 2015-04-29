package org.pms.controllers;

import org.pms.displaywrappers.FamilyWrapper;
import org.pms.dtos.FamilyDto;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.models.*;
import org.pms.services.FamilyService;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * This class is the controller of the Family module.
 * User: tijo
 */

@Controller
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @RequestMapping(value = "/viewfamily.action", method = RequestMethod.GET)
    public String familyPageDisplay(Model model) {
        model.addAttribute("family", new Family());
        return "family";
    }

    @RequestMapping(value = "/addFamily.action", method = RequestMethod.POST)
    public String addFamily(@ModelAttribute("family") Family family, Model model) {
        model.addAttribute("family", new Family());
        Parish parishForFamily = parishService.getParishForIDSM(family.getParishId());
        MassCenter massCenterForFamily = massCenterService.getMassCenterForIDSM(family.getMassCenterId());
        PrayerUnit wardForFamily = prayerUnitService.getPrayerUnitForIDSM(family.getPrayerUnitId());
        family.setFamilyParish(parishForFamily);
        parishForFamily.addFamilyForParish(family);
        family.setFamilyMassCenter(massCenterForFamily);
        massCenterForFamily.addFamilyForMassCenter(family);
        family.setFamilyPrayerUnit(wardForFamily);
        wardForFamily.addFamilyForWard(family);
        familyService.addFamilySM(family);
        return "family";
    }

    @RequestMapping(value = "createParishSelectBox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateParishSelectBox() {
        List<Parish> parishList = parishService.getAllParish();
        List<SelectBox<String>> selectBoxList = new ArrayList<SelectBox<String>>();
        for (Parish parish : parishList) {
            SelectBox<String> selectBox = new SelectBox<String>(String.valueOf(parish.getId()), parish.getName());
            selectBoxList.add(selectBox);
        }
        SelectBox<String> selectBox = new SelectBox<String>(null, null);
        return selectBox.getJsonForSelectBoxCreation(selectBoxList);
    }

    @RequestMapping(value = "/createMassCenterSelectBox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateMassCenterSelectBox(@RequestParam(value = "selectedParishId", required = true) Long selectedParishID) {
        if (selectedParishID != 0l) {
            List<MassCenter> massCenterListForParishID = massCenterService.getMassCenterForParishID(selectedParishID);
            List<SelectBox<String>> selectBoxList = new ArrayList<SelectBox<String>>(massCenterListForParishID.size());
            for (MassCenter massCenter : massCenterListForParishID)
                selectBoxList.add(new SelectBox<String>(String.valueOf(massCenter.getId()), massCenter.getName()));
            return new SelectBox<String>().getJsonForSelectBoxCreation(selectBoxList);
        }
        return null;
    }

    @RequestMapping(value = "/createWardSelectBox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateWardSelectBox(@RequestParam(value = "selectedMassCenterId", required = true) Long selectedMassCenterId) {
        List<PrayerUnit> wardList = prayerUnitService.getWardsForMassCenterIDSM(selectedMassCenterId);
        List<SelectBox<String>> wardSelectBoxList = new ArrayList<SelectBox<String>>();
        for (PrayerUnit ward : wardList)
            wardSelectBoxList.add(new SelectBox<String>(ward.getPrayerUnitName(), String.valueOf(ward.getId())));
        return new SelectBox<String>().getJsonForSelectBoxCreation(wardSelectBoxList);
    }

    @RequestMapping(value = "/displayFamilyGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForFamily(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<Family> allFamilies = familyService.getAllFamilySM();
        List<FamilyDto> familyDtoList = familyService.createFamilyDto(allFamilies);
        List<GridRow> familyGridRows = new ArrayList<GridRow>(familyDtoList.size());
        Integer totalFamilyCount = familyService.getFamilyTotalCount().intValue();
        List<FamilyDto> allFamilySublist = new ArrayList<>();

        if(totalFamilyCount > 0){
            allFamilySublist = JsonBuilder.generateSubList(page, rows, totalFamilyCount, familyDtoList);
        }

        for (FamilyDto familyDto : allFamilySublist) {
            familyGridRows.add(new FamilyWrapper(familyDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalFamilyCount, page, rows, familyGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }
}
