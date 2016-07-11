package org.pms.controllers;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pms.custompropertyeditors.FamilyCustomPropertyEditor;
import org.pms.custompropertyeditors.MassCentreCustomPropertyEditor;
import org.pms.custompropertyeditors.PrayerUnitCustomPropertyEditor;
import org.pms.enums.*;
import org.pms.displaywrappers.UserWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.*;
import org.pms.services.*;
import org.pms.sessionmanager.PMSSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.lang.Object;
import java.util.*;
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
    private MailService mailService;

    @Autowired
    private MassCentreService massCentreService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewusers.action", method = RequestMethod.GET)
    public String viewUsersPageDisplay(Model model) {

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        userService.createUserFormBackObject(model, currentUser);
        return PageName.USER.toString();
    }

    @RequestMapping(value = "/adduser.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addUser(@ModelAttribute("user") User user) {
        boolean insertUser = false;
        boolean userEmailAlreadyExists = true;
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        if (user.getId() == null) {
            user.setCreatedBy(currentUser.getEmail());
            user.setUpdatedBy(currentUser.getEmail());
            user.setCreatedOn(getCurrentDate());
            user.setUpdatedOn(getCurrentDate());
            String generatedPassword = RandomStringUtils.random(8, PMSSessionManager.keySpace);
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
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
                if (user.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
                    if (user.getUsersOfMassCentres() != null) {
                        insertUser = true;
                    }
                } else if (user.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
                    if (user.getUsersOfPrayerUnits() != null) {
                        insertUser = true;
                    }
                } else if (user.getSystemRole() == SystemRole.FAMILY_USER) {
                    insertUser = true;
                }
            }

            //Insert a User Role only if he is assigned with a single role from UI.
            if (insertUser && !userEmailAlreadyExists) {
                userService.addUserSM(user);
                user.setPassword(passwordBeforeHashing);
                if (user.getSendMailFlag()) {
                    mailService.sendUserCredentials(user);
                }
                customResponse = createSuccessMessage(StatusCode.SUCCESS, user.getEmail(), "loaded to the system");
            }

            //Error message when user is having multiple roles in the system.
            if (!insertUser) {
                //show the error message.
                customResponse = createErrorMessage(StatusCode.FAILURE, user.getEmail(), "could not loaded to the system");
            }

            //Error message when entered username is already exists from the database.
            if (userEmailAlreadyExists) {
                customResponse = createErrorMessage(StatusCode.FAILURE, user.getEmail(), "could not loaded to the system");
                //result.addError(new ObjectError("multipleEmailErrorDisplay", new String[]{"cannot have multiple emailID"}, new String[]{}, "cannot have duplicate emailID for different user!!.."));
            }
        } else {
            User userFromDB = userService.getUserByEmail(user.getEmail());
            if (userFromDB.getEmail().equals(user.getEmail())) {
                user.setPassword(userFromDB.getPassword());
                user.setCreatedBy(userFromDB.getCreatedBy());
                user.setCreatedOn(userFromDB.getCreatedOn());
                user.setUpdatedBy(currentUser.getEmail());
                user.setUpdatedOn(getCurrentDate());
                user.setAlreadyLoggedIn(userFromDB.getAlreadyLoggedIn());
                user.setValidated(userFromDB.getIsValidated());
                user.setSendMailFlag(userFromDB.getSendMailFlag());
                userService.updateUser(user);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, user.getEmail(), "updated successfully");
            } else {
                customResponse = createErrorMessage(StatusCode.FAILURE, user.getEmail(), "could not edit the email");
            }
        }

        return customResponse;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(MassCentre.class, new MassCentreCustomPropertyEditor(massCentreService));
        binder.registerCustomEditor(PrayerUnit.class, new PrayerUnitCustomPropertyEditor(prayerUnitService));
        binder.registerCustomEditor(Family.class, new FamilyCustomPropertyEditor(familyService));
    }

    private String getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        DateTime currentDate = new DateTime();
        return dateTimeFormatter.print(currentDate);
    }

}
