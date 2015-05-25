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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

        createModelSelectBoxes(model);

        return "users";
    }

    @RequestMapping(value = "/adduser.action", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model) {
        boolean insertUser = false;
        boolean userNameAlreadyExists = true;
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRoles.PMS_CURRENT_USER);
        user.setCreatedBy(currentUser.getUserName());
        user.setUpdatedBy(currentUser.getUserName());

        user.setPassword(DigestUtils.shaHex(user.getPassword()));

        User userFromDB = userService.getUserByUserName(user.getUserName());

        if (userFromDB == null) {
            userNameAlreadyExists = false;
        }

        // A single user must have single role in the system. A single user cannot act as multiple role in the system.

        if (user.getParishId() != 0 && user.getMassCenterId() == 0 && user.getPrayerUnitId() == 0 && user.getFamilyId() == 0) {
            insertUser = true;
        } else if (user.getMassCenterId() != 0 && user.getParishId() == 0 && user.getPrayerUnitId() == 0 && user.getFamilyId() == 0) {
            insertUser = true;
        } else if (user.getPrayerUnitId() != 0 && user.getParishId() == 0 && user.getMassCenterId() == 0 && user.getFamilyId() == 0) {
            insertUser = true;
        } else if (user.getFamilyId() != 0 && user.getParishId() == 0 && user.getMassCenterId() == 0 && user.getPrayerUnitId() == 0) {
            insertUser = true;
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
        createModelSelectBoxes(model);
        return PageNames.USERS;
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

        Map<Long, String> familyMap = new HashMap<>();
        List<Family> familyList = familyService.getAllFamilySM();
        for (Family family : familyList)
            familyMap.put(family.getId(), family.getFamilyName());

        model.addAttribute("parishList", parishMap);
        model.addAttribute("massCenterList", massCenterMap);
        model.addAttribute("prayerUnitList", prayerUnitMap);
        model.addAttribute("familyList", familyMap);

        return model;
    }

    @RequestMapping(value = "displayusergrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWard() {
        List<User> allUsers = userService.getAllUsers();
        Integer totalRows = userService.getAllUserCount().intValue();

        List<UserDto> userDtoList = userService.createUserDtos(allUsers);

        List<GridRow> userGridRows = new ArrayList<GridRow>(userDtoList.size());
        for (UserDto userDto : userDtoList) {
            userGridRows.add(new UserWrapper(userDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(10, 2, 20, userGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }

}
