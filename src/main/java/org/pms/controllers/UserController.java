package org.pms.controllers;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.pms.constants.PageNames;
import org.pms.constants.Roles;
import org.pms.constants.RolesStatus;
import org.pms.displaywrappers.UserWrapper;
import org.pms.displaywrappers.WardWrapper;
import org.pms.dtos.PrayerUnitDto;
import org.pms.dtos.UserDto;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.constants.SystemRoles;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.*;
import org.pms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewusers.action", method = RequestMethod.GET)
    public String usersPageDisplay(Model model) {
        model.addAttribute("user", new User());

        return PageNames.USERS;
    }

    @RequestMapping(value = "/adduser.action", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model) {
        boolean insertUser = false;
        boolean userNameAlreadyExists = true;
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        user.setCreatedBy(currentUser.getUserName());
        user.setUpdatedBy(currentUser.getUserName());

        user.setPassword(DigestUtils.shaHex(user.getPassword()));

        User userFromDB = userService.getUserByUserName(user.getUserName());

        if (userFromDB == null) {
            userNameAlreadyExists = false;
        }

        // A single user must have single role in the system. A single user cannot act as multiple role in the system.

        if (user.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            if (user.getParishId() != 0) {
                user.setMassCenterId(0l);
                user.setPrayerUnitId(0l);
                user.setFamilyId(0l);
                insertUser = true;
            }
        } else if (user.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            if (user.getParishId() != 0 && user.getMassCenterId() != 0) {
                user.setParishId(0l);
                user.setPrayerUnitId(0l);
                user.setFamilyId(0l);
                insertUser = true;
            }
        } else if (user.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            if (user.getParishId() != 0 && user.getMassCenterId() != 0 && user.getPrayerUnitId() != 0) {
                user.setParishId(0l);
                user.setMassCenterId(0l);
                user.setFamilyId(0l);
                insertUser = true;
            }
        } else if (user.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_ADMIN)) {
            if (user.getParishId() != 0 && user.getMassCenterId() != 0 && user.getPrayerUnitId() != 0 && user.getFamilyId() != 0) {
                user.setParishId(0l);
                user.setMassCenterId(0l);
                user.setPrayerUnitId(0l);
                insertUser = true;
            }
        }

        //Insert a User Role only if he is assigned with a single role from UI.
        if (insertUser && !userNameAlreadyExists) {
            model.addAttribute("user", new User());
            userService.addUserSM(user);
        }

        //Error message when user is having multiple roles in the system.
        if (!insertUser) {
            //show the error message.
        }

        //Error message when entered username is already exists from the database.
        if (userNameAlreadyExists) {
            //show the error message.
        }

        return PageNames.USERS;
    }

    @RequestMapping(value = "displayusergrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWard(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        List<User> allUsers = new ArrayList<>();
        Integer totalRows = 0;

        /*if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            List<User> allUsersUnderParishAdmin = parishService.getParishForIDSM(currentUser.getParishId()).getMappedFamilies();
            for (Family family : allFamiliesUnderParish) {
                allUsers.addAll(family.getMemberList());
            }
            totalRows = allUsers.size();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            List<Family> allFamiliesUnderMassCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId()).getMappedFamilies();
            for (Family family : allFamiliesUnderMassCenter) {
                allUsers.addAll(family.getMemberList());
            }
            totalRows = allUsers.size();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            List<Family> allFamiliesUnderPrayerUnit = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId()).getMappedFamilies();
            for (Family family : allFamiliesUnderPrayerUnit) {
                allUsers.addAll(family.getMemberList());
            }
            totalRows = allUsers.size();
        } else {*/
            allUsers = userService.getAllUsers();
            totalRows = allUsers.size();
       /* }*/

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
    Object generateFamilyNamesSelectBox(@RequestParam(value = "selectedPrayerUnitId", required = true) Long selectedPrayerUnitId) {
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

}
