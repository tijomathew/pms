package org.pms.controllers;

import org.pms.constants.PageNames;
import org.pms.constants.SystemRoles;
import org.pms.displaywrappers.FamilyWrapper;
import org.pms.dtos.FamilyDto;
import org.pms.helpers.*;
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

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewfamily.action", method = RequestMethod.GET)
    public String familyPageDisplay(Model model) {
        model.addAttribute("family", new Family());
        return PageNames.FAMILY;
    }

    @RequestMapping(value = "/addfamily.action", method = RequestMethod.POST)
    public String addFamily(@ModelAttribute("family") Family family, Model model) {
        model.addAttribute("family", new Family());
        Parish parishForFamily = parishService.getParishForIDSM(family.getParishId());
        MassCenter massCenterForFamily = massCenterService.getMassCenterForIDSM(family.getMassCenterId());
        PrayerUnit prayerUnitForFamily = prayerUnitService.getPrayerUnitForIDSM(family.getPrayerUnitId());
        family.setFamilyParish(parishForFamily);
        parishForFamily.addFamilyForParish(family);
        family.setFamilyMassCenter(massCenterForFamily);
        massCenterForFamily.addFamilyForMassCenter(family);
        family.setFamilyPrayerUnit(prayerUnitForFamily);
        prayerUnitForFamily.addFamilyForWard(family);

        String attachedStringToID = prayerUnitForFamily.getPrayerUnitCode() + "-FA";
        Long familyCounterForParish = familyService.getFamilyCountForParish(parishForFamily.getId());
        if (familyCounterForParish < 10) {
            attachedStringToID += "0";
        }

        family.setFamilyID(attachedStringToID + (++familyCounterForParish));

        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRoles.PMS_CURRENT_USER);
        boolean permissionDenied = false;

        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_ADMIN)) {
            permissionDenied = true;
        }

        if (!permissionDenied) {
            familyService.addFamilySM(family);
        } else {
            //show the error that family cannot be add by family admin. He can edit only his information.
        }
        return PageNames.FAMILY;
    }

    @RequestMapping(value = "createparishselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateParishSelectBox() {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        List<Parish> parishList = new ArrayList<>();
        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            parishList.add(parishService.getParishForIDSM(currentUser.getParishId()));
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            MassCenter massCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId());
            parishList.add(massCenter.getMappedParish());
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            PrayerUnit prayerUnit = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
            parishList.add(prayerUnit.getMappedMassCenter().getMappedParish());
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_ADMIN)) {
            Family family = familyService.getFamilyForID(currentUser.getFamilyId());
            parishList.add(family.getFamilyParish());
        } else {
            parishList = parishService.getAllParish();
        }
        List<SelectBox<String>> selectBoxList = new ArrayList<SelectBox<String>>();
        for (Parish parish : parishList) {
            SelectBox<String> selectBox = new SelectBox<String>(String.valueOf(parish.getId()), parish.getName());
            selectBoxList.add(selectBox);
        }
        SelectBox<String> selectBox = new SelectBox<String>(null, null);
        return selectBox.getJsonForSelectBoxCreation(selectBoxList);
    }

    @RequestMapping(value = "/createmasscenterselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateMassCenterSelectBox(@RequestParam(value = "selectedParishId", required = true) Long selectedParishID) {
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRoles.PMS_CURRENT_USER);
        if (selectedParishID != 0l) {
            List<MassCenter> massCenterListForParishID = new ArrayList<>();
            if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
                massCenterListForParishID.add(massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId()));
            } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
                PrayerUnit prayerUnitOfCurrentUser = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
                massCenterListForParishID.add(prayerUnitOfCurrentUser.getMappedMassCenter());
            } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_ADMIN)) {
                Family familyOfCurrentUser = familyService.getFamilyForID(currentUser.getFamilyId());
                massCenterListForParishID.add(familyOfCurrentUser.getFamilyMassCenter());
            } else {
                massCenterListForParishID = massCenterService.getMassCenterForParishID(selectedParishID);
            }
            List<SelectBox<String>> selectBoxList = new ArrayList<SelectBox<String>>(massCenterListForParishID.size());
            for (MassCenter massCenter : massCenterListForParishID)
                selectBoxList.add(new SelectBox<String>(String.valueOf(massCenter.getId()), massCenter.getName()));
            return new SelectBox<String>().getJsonForSelectBoxCreation(selectBoxList);
        }
        return null;
    }

    @RequestMapping(value = "/createprayerunitselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateWardSelectBox(@RequestParam(value = "selectedMassCenterId", required = true) Long selectedMassCenterId) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        List<PrayerUnit> prayerUnitList = new ArrayList<>();
        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            PrayerUnit prayerUnitOfCurrentUser = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
            prayerUnitList.add(prayerUnitOfCurrentUser);
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_ADMIN)) {
            Family familyOfCurrentUser = familyService.getFamilyForID(currentUser.getFamilyId());
            prayerUnitList.add(familyOfCurrentUser.getFamilyPrayerUnit());
        } else {
            prayerUnitList = prayerUnitService.getPrayerUnitForMassCenterIDSM(selectedMassCenterId);
        }
        List<SelectBox<String>> prayerUnitSelectBoxList = new ArrayList<SelectBox<String>>();
        for (PrayerUnit prayerUnit : prayerUnitList)
            prayerUnitSelectBoxList.add(new SelectBox<String>(prayerUnit.getPrayerUnitName(), String.valueOf(prayerUnit.getId())));
        return new SelectBox<String>().getJsonForSelectBoxCreation(prayerUnitSelectBoxList);
    }

    @RequestMapping(value = "/displayfamilygrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForFamily(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        List<Family> allFamilies = new ArrayList<>();
        Integer totalFamilyCount = 0;

        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.ADMIN)) {
            allFamilies = familyService.getAllFamilySM();
            totalFamilyCount = familyService.getFamilyTotalCount().intValue();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            List<Family> allFamiliesUnderParish = parishService.getParishForIDSM(currentUser.getParishId()).getMappedFamilies();
            allFamilies.addAll(allFamiliesUnderParish);
            totalFamilyCount = allFamilies.size();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            List<Family> allFamiliesUnderMassCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId()).getMappedFamilies();
            allFamilies.addAll(allFamiliesUnderMassCenter);
            totalFamilyCount = allFamilies.size();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            List<Family> allFamiliesUnderPrayerUnit = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId()).getMappedFamilies();
            allFamilies.addAll(allFamiliesUnderPrayerUnit);
            totalFamilyCount = allFamilies.size();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_ADMIN)) {
            allFamilies.add(familyService.getFamilyForID(currentUser.getFamilyId()));
            totalFamilyCount = 1;
        }

        List<FamilyDto> familyDtoList = familyService.createFamilyDto(allFamilies);
        List<GridRow> familyGridRows = new ArrayList<GridRow>(familyDtoList.size());

        List<FamilyDto> allFamilySublist = new ArrayList<>();

        if (totalFamilyCount > 0) {
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
