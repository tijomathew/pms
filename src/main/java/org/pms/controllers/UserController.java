package org.pms.controllers;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
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
import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.pms.models.PrayerUnit;
import org.pms.models.User;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.pms.services.UserService;
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
 * AdminController description
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
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewusers.action", method = RequestMethod.GET)
    public String usersPageDisplay(Model model) {
        model.addAttribute("user", new User());

        createModelSelectBoxes(model);

        return "users";
    }

    @RequestMapping(value = "/addUser.action", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model) {
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRoles.PMS_CURRENT_USER);
        user.setCreatedBy(currentUser.getUserName());
        user.setUpdatedBy(currentUser.getUserName());

        user.setPassword(DigestUtils.shaHex(user.getPassword()));
        userService.addUserSM(user);

        model.addAttribute("user", new User());
        createModelSelectBoxes(model);
        return "users";
    }

    @RequestMapping(value = "/viewUsers.action", method = RequestMethod.GET)
    public String viewAllUsers() {
        return "viewAdmin";
    }

    private Model createModelSelectBoxes(Model model) {
        Map<String, String> parishMap = new HashMap<String, String>();
        List<Parish> addedParishes = parishService.getAllParish();
        parishMap.put(String.valueOf(0), "--Please Select--");
        for (Parish parish : addedParishes)
            parishMap.put(parish.getParishID(), parish.getName());

        Map<String, String> massCenterMap = new HashMap<String, String>();
        List<MassCenter> massCenterList = massCenterService.getAllMassCenter();
        massCenterMap.put(String.valueOf(0), "--Please Select--");
        for (MassCenter massCenter : massCenterList)
            massCenterMap.put(massCenter.getMassCenterID(), massCenter.getName());

        Map<String, String> prayerUnitMap = new HashMap<String, String>();
        List<PrayerUnit> prayerUnitList = prayerUnitService.getAllPrayerUnits();
        prayerUnitMap.put(String.valueOf(0), "--Please Select--");
        for (PrayerUnit prayerUnit : prayerUnitList)
            prayerUnitMap.put(prayerUnit.getPrayerUnitCode(), prayerUnit.getPrayerUnitName());

        model.addAttribute("parishList", parishMap);
        model.addAttribute("massCenterList", massCenterMap);
        model.addAttribute("prayerUnitList", prayerUnitMap);

        return model;
    }

    @RequestMapping(value = "displayUserGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWard() {
        List<User> allUsers = userService.getAllUsers();
        List<UserDto> userDtoList = userService.createUserDtos(allUsers);
        Integer totalRows = userService.getAllUserCount().intValue();
        List<GridRow> userGridRows = new ArrayList<GridRow>(userDtoList.size());
        for (UserDto userDto : userDtoList) {
            userGridRows.add(new UserWrapper(userDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(10, 2, 20, userGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }

}
