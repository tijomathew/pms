package org.pms.controllers;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.pms.enums.PageNames;
import org.pms.displaywrappers.UserWrapper;
import org.pms.dtos.UserDto;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.enums.SystemRoles;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.*;
import org.pms.services.*;
import org.pms.sessionmanager.PMSSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserController description
 * User: tijo
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewusers.action", method = RequestMethod.GET)
    public String usersPageDisplay(Model model) {
        model.addAttribute("user", new User());

        if (requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class).getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            createModelSelectBoxes(model);
        }
        return PageNames.USER;
    }

    @RequestMapping(value = "/adduser.action", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model, BindingResult result) {
        boolean insertUser = false;
        boolean userEmailAlreadyExists = true;
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        user.setCreatedBy(currentUser.getEmail());
        user.setUpdatedBy(currentUser.getEmail());
        String generatedPassword = RandomStringUtils.random(8, PMSSessionManager.keySpace);
        if (user.getPassword() == null) {
            user.setPassword(generatedPassword);
        }
        String passwordBeforeHashing = user.getPassword();
        user.setPassword(DigestUtils.shaHex(user.getPassword()));

        User userFromDB = userService.getUserByEmail(user.getEmail());

        if (userFromDB == null) {
            userEmailAlreadyExists = false;
        }

        // A single user must have single role in the system. A single user cannot act as multiple role in the system.

        if (!userEmailAlreadyExists) {
            if (user.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
                if (user.getParishId() != 0) {
                    user.setMassCenterId(0l);
                    user.setPrayerUnitId(0l);
                    user.setFamilyId(0l);
                    user.setEmail(user.getEmail() + user.getExtensionOfEmail());
                    insertUser = true;
                }
            } else if (user.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
                if (user.getParishId() != 0 && user.getMassCenterId() != 0) {
                    user.setParishId(0l);
                    user.setPrayerUnitId(0l);
                    user.setFamilyId(0l);
                    user.setEmail(user.getEmail() + user.getExtensionOfEmail());
                    insertUser = true;
                }
            } else if (user.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
                if (user.getParishId() != 0 && user.getMassCenterId() != 0 && user.getPrayerUnitId() != 0) {
                    user.setParishId(0l);
                    user.setMassCenterId(0l);
                    user.setFamilyId(0l);
                    user.setEmail(user.getEmail() + user.getExtensionOfEmail());
                    insertUser = true;
                }
            } else if (user.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_USER)) {
                if (user.getParishId() != 0 && user.getMassCenterId() != 0 && user.getPrayerUnitId() != 0) {
                /*user.setParishId(0l);
                user.setMassCenterId(0l);
                user.setPrayerUnitId(0l);*/
                    user.setFamilyId(0l);
                    //throw an error message that those values must be valid for Family User Role.

                    insertUser = true;
                }
            }
        }

        //Insert a User Role only if he is assigned with a single role from UI.
        if (insertUser && !userEmailAlreadyExists) {
            model.addAttribute("user", new User());
            userService.addUserSM(user);
            user.setPassword(passwordBeforeHashing);
            if (user.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_USER)) {
                if (user.getSendMailFlag().equalsIgnoreCase("true")) {
                    mailService.sendUserCredentials(user);
                }
            }

            if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
                createModelSelectBoxes(model);
            }
        }

        //Error message when user is having multiple roles in the system.
        if (!insertUser) {
            //show the error message.
        }

        //Error message when entered username is already exists from the database.
        if (userEmailAlreadyExists) {
            result.addError(new ObjectError("multipleEmailErrorDisplay", new String[]{"cannot have multiple emailID"}, new String[]{}, "cannot have duplicate emailID for different user!!.."));
        }

        return PageNames.USER;
    }

    @RequestMapping(value = "displayusergrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForPrayerUnit(@RequestParam(value = "rows", required = false) Integer
                                                    rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        List<User> allUsers = new ArrayList<>();

        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            List<Long> massCenterIdsUnderParishAdmin = massCenterService.getAllMassCenterIdsForParish(currentUser.getParishId());
            allUsers.addAll(userService.getAllUsersForMassCenterIds(massCenterIdsUnderParishAdmin));

            List<Long> prayerUnitIdsUnderParishAdmin = prayerUnitService.getAllPrayerUnitIdsForMassCenterIds(massCenterIdsUnderParishAdmin);
            allUsers.addAll(userService.getAllUsersForPrayerUnitIds(prayerUnitIdsUnderParishAdmin));

            List<Long> familyIdsUnderParishAdmin = familyService.getAllFamilyIdsForPrayerUnitId(prayerUnitIdsUnderParishAdmin);
            allUsers.addAll(userService.getAllUsersForFamilyIds(familyIdsUnderParishAdmin));
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            List<Long> massCenterIdAsList = new ArrayList<>();
            massCenterIdAsList.add(currentUser.getMassCenterId());
            List<Long> prayerUnitIdsUnderMassCenterAdmin = prayerUnitService.getAllPrayerUnitIdsForMassCenterIds(massCenterIdAsList);
            allUsers.addAll(userService.getAllUsersForPrayerUnitIds(prayerUnitIdsUnderMassCenterAdmin));

            List<Long> familyIdsUnderMassCenterAdmin = familyService.getAllFamilyIdsForPrayerUnitId(prayerUnitIdsUnderMassCenterAdmin);
            allUsers.addAll(userService.getAllUsersForFamilyIds(familyIdsUnderMassCenterAdmin));
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            List<Long> prayerUnitIdAsList = new ArrayList<>();
            prayerUnitIdAsList.add(currentUser.getPrayerUnitId());
            List<Long> familyIdsUnderPrayerUnitAdmin = familyService.getAllFamilyIdsForPrayerUnitId(prayerUnitIdAsList);
            allUsers.addAll(userService.getAllUsersForFamilyIds(familyIdsUnderPrayerUnitAdmin));
        } else {
            allUsers = userService.getAllUsers();
        }
        Integer totalRows = allUsers.size();

        List<UserDto> userDtoList = userService.createUserDtos(allUsers);

        List<GridRow> userGridRows = new ArrayList<GridRow>(userDtoList.size());
        for (UserDto userDto : userDtoList) {
            userGridRows.add(new UserWrapper(userDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalRows, page, rows, userGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }

    @RequestMapping(value = "/createfamilyselectboxofusers.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateFamilyNamesSelectBox(@RequestParam(value = "selectedPrayerUnitId", required = true) Long
                                                selectedPrayerUnitId) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        List<Family> familyList = new ArrayList<>();
        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            familyList.addAll(familyService.getAllFamilyForParishID(currentUser.getParishId()));
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            MassCenter massCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId());
            familyList.addAll(massCenter.getMappedFamilies());
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            PrayerUnit prayerUnit = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
            familyList.addAll(prayerUnit.getMappedFamilies());
        } else {
            familyList = familyService.getAllFamilySM();
        }
        List<SelectBox<String>> selectBoxList = new ArrayList<SelectBox<String>>();
        for (Family family : familyList) {
            SelectBox<String> selectBox = new SelectBox<String>(family.getFamilyName(), String.valueOf(family.getId()));
            selectBoxList.add(selectBox);
        }
        SelectBox<String> selectBox = new SelectBox<String>(null, null);
        return selectBox.getJsonForSelectBoxCreation(selectBoxList);
    }

    private Model createModelSelectBoxes(Model model) {
        Map<Long, String> parishMap = new HashMap<Long, String>();
        List<Parish> addedParishes = parishService.getAllParish();
        for (Parish parish : addedParishes)
            parishMap.put(parish.getId(), parish.getName());

        Map<Long, String> massCenterMap = new HashMap<Long, String>();
        List<MassCenter> massCenterList = massCenterService.getAllMassCenter();
        for (MassCenter massCenter : massCenterList)
            massCenterMap.put(massCenter.getId(), massCenter.getName());

        Map<Long, String> prayerUnitMap = new HashMap<Long, String>();
        List<PrayerUnit> prayerUnitList = prayerUnitService.getAllPrayerUnits();

        for (PrayerUnit prayerUnit : prayerUnitList)
            prayerUnitMap.put(prayerUnit.getId(), prayerUnit.getPrayerUnitName());

        model.addAttribute("parishList", parishMap);
        model.addAttribute("massCenterList", massCenterMap);
        model.addAttribute("prayerUnitList", prayerUnitMap);

        return model;
    }

}
