package org.pms.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.enums.SystemRolesStatus;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.FactorySelectBox;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.*;
import org.pms.services.*;
import org.pms.sessionmanager.PMSSessionManager;
import org.pms.validators.LoginValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * LoginController description
 * User: tijo
 */

@Controller
@RequestMapping("/")
public class LoginController extends AbstractErrorAndGridHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassCentreService massCentreService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private FactorySelectBox factorySelectBox;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    /**
     * This method redirects to the login page after creating login user's model object to backup as model attribute object in the UI display.
     *
     * @param model this parameter used to show the login user model object in the spring's form tag model attribute.
     * @return the name of the page to which application has to redirect which will be resolved with the help of spring's internal view resolver.
     */
    @RequestMapping(value = "login.action", method = RequestMethod.GET)
    public String loginPageDisplay(Model model) {
        logger.debug("application creates the login user form back object and redirects to the login page");
        model.addAttribute("loginUser", new User());
        return PageName.LOGIN.toString();
    }

    /**
     * This method verifies the logged in user and redirects to the respective page as per the logged in user's role in the system.
     *
     * @param user   this parameter possess the user's credentials from the UI form fields.
     * @param result this parameter helps to show the validation error in the UI if there is any error in the form fields.
     * @param model  this parameter used to store the form back object which can be used in the redirected page.
     * @return the name of the page to which application has to redirect which will be resolved with the help of spring's internal view resolver.
     */
    @RequestMapping(value = "loggedin.action", method = RequestMethod.POST)
    public String verifyUser(@ModelAttribute("loginUser") @Valid User user, BindingResult result, Model model) {
        logger.debug("authenticating and authorizing the user in the system");
        PageName redirectPageName = PageName.LOGIN;
        User loggedInUser;

        try {
            loggedInUser = loginService.verifyLoggedInUser(user.getEmail(), user.getPassword());
            if (loggedInUser != null) {
                if (loggedInUser.getIsActive() == SystemRolesStatus.ACTIVE) {
                    redirectPageName = loginService.getRedirectPageForLoggedInUser(loggedInUser);
                    createFormBackObjectForRedirectPage(model, redirectPageName, loggedInUser);
                } else {
                    result.addError(new ObjectError("loginErrorDisplay", new String[]{"BlockedUserError"}, new String[]{}, "You are blocked to login to the system.Please contact your admin."));
                }

            } else {
                result.addError(new ObjectError("loginErrorDisplay", new String[]{"LoginCredentialsError"}, new String[]{}, "Username or Password is invalid in our system. Please re-enter correct one."));
            }

        } catch (IllegalArgumentException ex) {
            logger.error("The authentication and authorization of the user is failed in the system");
        }

        return redirectPageName.toString();
    }

    /**
     * This method change the password of the user who login to the system for the first time.
     *
     * @param user current user logged in to the system.
     * @return custom response with the status code of the response.
     */
    @RequestMapping(value = "changepassword.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse changePassword(@ModelAttribute("changePasswordUser") @Valid User user) {
        if (user.getNewPassword() != null && user.getConfirmPassword() != null && user.getNewPassword().equals(user.getConfirmPassword())) {
            User currentUserToUpdate = loginService.getUserByEmail(user.getEmail());
            currentUserToUpdate.setAlreadyLoggedIn(Boolean.TRUE);
            currentUserToUpdate.setPassword(DigestUtils.shaHex(user.getNewPassword()));
            userService.addOrUpdateUserSM(currentUserToUpdate);
            customResponse = createStatusCodeResponse(StatusCode.SUCCESS);
        } else {
            customResponse = createStatusCodeResponse(StatusCode.FAIL);
        }
        return customResponse;
    }

    /**
     * This method reset the password of the user who clicks on the forgot password menu and enters the registered mail ID in our system.
     *
     * @param user the user who has valid mail ID registered in our system.
     * @return custom response with the status code of the response.
     */
    @RequestMapping(value = "forgotpassword.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse forgotPassword(@ModelAttribute("loginUser") @Valid User user) {
        boolean isEmailIDPresent = loginService.verifyEmailIsPresent(user.getEmail());
        if (isEmailIDPresent) {
            String generatedPassword = RandomStringUtils.random(8, PMSSessionManager.keySpace);

            User currentUserToUpdate = loginService.getUserByEmail(user.getEmail());
            currentUserToUpdate.setAlreadyLoggedIn(Boolean.FALSE);
            currentUserToUpdate.setPassword(DigestUtils.shaHex(generatedPassword));
            userService.addOrUpdateUserSM(currentUserToUpdate);

            mailService.sendForgotPassword(user.getEmail(), generatedPassword);

            customResponse = createStatusCodeResponse(StatusCode.SUCCESS);
        } else {
            customResponse = createStatusCodeResponse(StatusCode.FAIL);
        }
        return customResponse;
    }

    /**
     * This method creates the form back object for page wrt to the user's role in the system.
     *
     * @param model            model object of the redirecting page.
     * @param redirectPageName the redirect page name in the system.
     * @param currentUser      current logged in user.
     */
    private void createFormBackObjectForRedirectPage(Model model, PageName redirectPageName, User currentUser) {
        switch (redirectPageName) {
            case CHANGEPASSWORD:
                model.addAttribute("changePasswordUser", currentUser);
                break;
            case LOGIN:
                model.addAttribute("loginUser", new User());
                break;
            case PARISH:
                parishService.createFormBackObject(model);
                break;
            case MASSCENTRE:
                massCentreService.createMassCentreFormBackObject(model);
                break;
            case PRAYERUNIT:
                prayerUnitService.createPrayerUnitFormBackObject(model);
                break;
            case FAMILY:
                factorySelectBox.generateSelectBoxInModel(model, requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class));
                break;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new LoginValidator());
    }

}
