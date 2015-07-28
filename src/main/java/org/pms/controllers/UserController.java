package org.pms.controllers;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.pms.enums.*;
import org.pms.displaywrappers.UserWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
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
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * UserController description
 * User: tijo
 */

@Controller
public class UserController extends AbstractErrorAndGridHandler {

    @Autowired
    private UserService userService;

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

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        userService.createUserFormBackObject(model, currentUser);
        return PageName.USER.toString();
    }

    @RequestMapping(value = "/adduser.action", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model, BindingResult result) {
        boolean insertUser = false;
        boolean userEmailAlreadyExists = true;
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
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
            if (user.getSystemRole() == SystemRole.PARISH_ADMIN) {
                if (user.getParishId() != 0) {
                    user.setMassCenterId(0l);
                    user.setPrayerUnitId(0l);
                    user.setFamilyId(0l);
                    user.setEmail(user.getEmail());
                    insertUser = true;
                }
            } else if (user.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
                if (user.getParishId() != 0 && user.getMassCenterId() != 0) {
                    user.setParishId(0l);
                    user.setPrayerUnitId(0l);
                    user.setFamilyId(0l);
                    user.setEmail(user.getEmail());
                    insertUser = true;
                }
            } else if (user.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
                if (user.getParishId() != 0 && user.getMassCenterId() != 0 && user.getPrayerUnitId() != 0) {
                    user.setParishId(0l);
                    user.setMassCenterId(0l);
                    user.setFamilyId(0l);
                    user.setEmail(user.getEmail());
                    insertUser = true;
                }
            } else if (user.getSystemRole() == SystemRole.FAMILY_USER) {
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
            userService.addOrUpdateUserSM(user);
            user.setPassword(passwordBeforeHashing);
            if (user.getSendMailFlag()) {
                mailService.sendUserCredentials(user);
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

        return PageName.USER.toString();
    }

    @RequestMapping(value = "displayusergrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForPrayerUnit(@RequestParam(value = "rows", required = false) Integer
                                                    rows, @RequestParam(value = "page", required = false) Integer page) {

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<User> allUsers = userService.getAllUsersForUserRole(currentUser);

        Integer totalUsersRows = allUsers.size();

        List<GridRow> userGridRows = new ArrayList<GridRow>(allUsers.size());
        List<User> allUsersSubList = new ArrayList<>();

        if (totalUsersRows > 0) {
            allUsersSubList = JsonBuilder.generateSubList(page, rows, totalUsersRows, allUsers);
        }

        if (!allUsersSubList.isEmpty()) {
            userGridRows = allUsersSubList.stream().map(user -> new UserWrapper(user)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalUsersRows, page, rows, userGridRows));
    }

    @RequestMapping(value = "/createfamilyselectboxofusers.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateFamilyNamesSelectBox(@RequestParam(value = "selectedPrayerUnitId", required = true) Long
                                                selectedPrayerUnitId) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Family> familyList = new ArrayList<>();
        if (currentUser.getSystemRole() == SystemRole.PARISH_ADMIN) {
            familyList.addAll(familyService.getAllFamilyForParishID(currentUser.getParishId()));
        } else if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
            MassCenter massCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId());
            familyList.addAll(massCenter.getMappedFamilies());
        } else if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
            PrayerUnit prayerUnit = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
            familyList.addAll(prayerUnit.getMappedFamilies());
        } else {
            familyList = familyService.getAllFamilySM();
        }
        List<SelectBox<String, Long>> selectBoxList = new ArrayList<>();
        for (Family family : familyList) {
            SelectBox<String, Long> selectBox = new SelectBox<>(family.getFamilyName(), family.getId());
            selectBoxList.add(selectBox);
        }

        return SelectBox.getJsonForSelectBoxCreation(selectBoxList);
    }


}
