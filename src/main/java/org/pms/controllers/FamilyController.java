package org.pms.controllers;

import org.pms.enums.PageName;
import org.pms.displaywrappers.FamilyWrapper;
import org.pms.dtos.FamilyDto;
import org.pms.enums.SystemRole;
import org.pms.error.AbstractErrorHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * This class is the controller of the Family module.
 * User: tijo
 */

@Controller
public class FamilyController extends AbstractErrorHandler {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private UserService userService;

    @Autowired
    private FactorySelectBox factorySelectBox;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewfamily.action", method = RequestMethod.GET)
    public String familyPageDisplay(Model model) {
        model.addAttribute("family", new Family());
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN || currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
            factorySelectBox.generateSelectBoxInModel(model, currentUser);
        }
        return PageName.FAMILY.toString();
    }

    @RequestMapping(value = "/addfamily.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addFamily(Model model, @ModelAttribute("family") @Valid Family family, BindingResult result) {

        if (!result.hasErrors()) {
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

            Long familyCounterForParish = familyService.getFamilyCountForParish(parishForFamily.getId());

            family.setFamilyID(++familyCounterForParish);

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            familyService.addFamilySM(family);
            if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN || currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
                factorySelectBox.generateSelectBoxInModel(model, currentUser);
            }

            //TODO check whether the user is already assigned with a family.

            currentUser.setFamilyId(family.getId());
            userService.addOrUpdateUserSM(currentUser);

            customResponse = createSuccessMessage(StatusCode.SUCCESS, family.getFamilyName(), "added in to the system");

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());

        }

        return customResponse;
    }

    @RequestMapping(value = "createparishselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateParishSelectBox() {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Parish> parishList = new ArrayList<>();
        if (currentUser.getSystemRole() == SystemRole.PARISH_ADMIN || currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
            parishList.add(parishService.getParishForIDSM(currentUser.getParishId()));
        } else if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
            MassCenter massCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId());
            parishList.add(massCenter.getMappedParish());
        } else if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
            PrayerUnit prayerUnit = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
            parishList.add(prayerUnit.getMappedMassCenter().getMappedParish());
        } else if (currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
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
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRole.PMS_CURRENT_USER.toString());
        if (selectedParishID != 0l) {
            List<MassCenter> massCenterListForParishID = new ArrayList<>();
            if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN || currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
                massCenterListForParishID.add(massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId()));
            } else if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
                PrayerUnit prayerUnitOfCurrentUser = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
                massCenterListForParishID.add(prayerUnitOfCurrentUser.getMappedMassCenter());
            } else if (currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
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
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<PrayerUnit> prayerUnitList = new ArrayList<>();
        if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
            PrayerUnit prayerUnitOfCurrentUser = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
            prayerUnitList.add(prayerUnitOfCurrentUser);
        } else if (currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
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
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Family> allFamilies = new ArrayList<>();
        Integer totalFamilyCount = 0;

        if (currentUser.getSystemRole() == SystemRole.ADMIN) {
            allFamilies = familyService.getAllFamilySM();
            totalFamilyCount = familyService.getFamilyTotalCount().intValue();
        } else if (currentUser.getSystemRole() == SystemRole.PARISH_ADMIN) {
            List<Family> allFamiliesUnderParish = parishService.getParishForIDSM(currentUser.getParishId()).getMappedFamilies();
            allFamilies.addAll(allFamiliesUnderParish);
            totalFamilyCount = allFamilies.size();
        } else if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
            List<Family> allFamiliesUnderMassCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId()).getMappedFamilies();
            allFamilies.addAll(allFamiliesUnderMassCenter);
            totalFamilyCount = allFamilies.size();
        } else if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
            List<Family> allFamiliesUnderPrayerUnit = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId()).getMappedFamilies();
            allFamilies.addAll(allFamiliesUnderPrayerUnit);
            totalFamilyCount = allFamilies.size();
        } else if (currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
            allFamilies.add(familyService.getFamilyForID(currentUser.getFamilyId()));
            totalFamilyCount = 1;
        }

        List<FamilyDto> familyDtoList = familyService.createFamilyDto(allFamilies);
        List<GridRow> familyGridRows = new ArrayList<GridRow>(familyDtoList.size());

        List<FamilyDto> allFamilySubList = new ArrayList<>();

        if (totalFamilyCount > 0) {
            allFamilySubList = JsonBuilder.generateSubList(page, rows, totalFamilyCount, familyDtoList);
        }

        for (FamilyDto familyDto : allFamilySubList) {
            familyGridRows.add(new FamilyWrapper(familyDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalFamilyCount, page, rows, familyGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }
}
