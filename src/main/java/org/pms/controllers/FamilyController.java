package org.pms.controllers;

import org.apache.commons.lang3.StringUtils;
import org.pms.custompropertyeditors.FamilyCustomPropertyEditor;
import org.pms.custompropertyeditors.MassCenterCustomPropertyEditor;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.custompropertyeditors.PrayerUnitCustomPropertyEditor;
import org.pms.enums.PageName;
import org.pms.displaywrappers.FamilyWrapper;
import org.pms.enums.SystemRole;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is the controller of the Family module.
 * User: tijo
 */

@Controller
public class FamilyController extends AbstractErrorAndGridHandler {

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
    CustomResponse addFamily(@ModelAttribute("family") @Valid Family family, BindingResult result) {

        if (!result.hasErrors()) {

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            if (currentUser.getUserOfFamily() == null) {
                family.getFamilyParish().addFamilyForParish(family);
                family.getFamilyMassCenter().addFamilyForMassCenter(family);
                family.getFamilyPrayerUnit().addFamilyForWard(family);

                Long familyCounterForParish = familyService.getFamilyCountForParish(family.getFamilyParish().getId());

                family.setFamilyNo(++familyCounterForParish);

                familyService.addFamilySM(family);

                currentUser.setUserOfFamily(family);
                userService.addOrUpdateUserSM(currentUser);

                customResponse = createSuccessMessage(StatusCode.SUCCESS, family.getFamilyName(), SUCCESS_MESSAGE_DISPLAY);
            } else {
                customResponse = createErrorMessage(StatusCode.FAILURE, family.getFamilyName(), "cannot add to the system by a family user named " + currentUser.getEmail());
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());

        }

        return customResponse;
    }

    @RequestMapping(value = "/displayfamilygrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForFamily(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Family> allFamilies = familyService.getAllFamiliesForUserRole(currentUser);
        Integer totalFamilyCount = allFamilies.size();

        List<GridRow> familyGridRows = new ArrayList<GridRow>(allFamilies.size());

        List<Family> allFamilySubList = new ArrayList<>();

        if (totalFamilyCount > 0) {
            allFamilySubList = JsonBuilder.generateSubList(page, rows, totalFamilyCount, allFamilies);
        }

        if (!allFamilySubList.isEmpty()) {
            familyGridRows = allFamilySubList.stream().map(family -> new FamilyWrapper(family)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalFamilyCount, page, rows, familyGridRows));
    }

    @RequestMapping(value = "/createfamilyselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateFamilyNamesSelectBox(@RequestParam(value = "selectedPrayerUnitId", required = false) Long
                                                selectedPrayerUnitId) {
        List<Family> familyList = new ArrayList<>();
        if (selectedPrayerUnitId == null || selectedPrayerUnitId == 0) {
            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
            familyList = familyService.getAllFamiliesForUserRole(currentUser);
        } else {
            familyList.addAll(familyService.getAllFamilyForPrayerUnitID(selectedPrayerUnitId));
        }
        List<SelectBox<String, Long>> selectBoxList = familyList.stream().map(family -> new SelectBox<>(family.getFamilyName(), family.getId())).collect(Collectors.toList());
        return SelectBox.getJsonForSelectBoxCreation(selectBoxList);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
        binder.registerCustomEditor(MassCenter.class, new MassCenterCustomPropertyEditor(massCenterService));
        binder.registerCustomEditor(PrayerUnit.class, new PrayerUnitCustomPropertyEditor(prayerUnitService));
        binder.registerCustomEditor(Family.class, new FamilyCustomPropertyEditor(familyService));
    }

}
