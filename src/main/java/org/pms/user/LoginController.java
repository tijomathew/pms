package org.pms.user;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.common.PageName;
import org.pms.email.MailService;
import org.pms.common.error.AbstractErrorAndGridHandler;
import org.pms.common.error.CustomResponse;
import org.pms.common.FactorySelectBox;
import org.pms.common.RequestResponseHolder;
import org.pms.domain.CashFlow;
import org.pms.domain.Parish;
import org.pms.domain.User;
import org.pms.parish.ParishService;
import org.pms.prayerunit.PrayerUnitService;
import org.pms.common.PMSSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private PrayerUnitService prayerUnitService;

    @Autowired
    private FactorySelectBox factorySelectBox;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    /**
     * This method redirects to the login page after creating login user's domain object to backup as domain attribute object in the UI display.
     *
     * @param model this parameter used to show the login user domain object in the spring's form tag domain attribute.
     * @return the name of the page to which application has to redirect which will be resolved with the help of spring's internal view resolver.
     */
    @RequestMapping(value = "login.action", method = RequestMethod.GET)
    public String loginPageDisplay(Model model) {
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

        String redirectedActionName;
        if (redirectPageName == PageName.PARISH) {
            redirectedActionName = "redirect:/viewparish.action";
        } else if (redirectPageName == PageName.PRAYERUNIT) {
            redirectedActionName = "redirect:/viewprayerunit.action";
        } else if (redirectPageName == PageName.FAMILY) {
            redirectedActionName = "redirect:/viewfamilywelcome.action";
        } else if (redirectPageName == PageName.RECEIPT) {
            redirectedActionName = "redirect:/viewreceipt.action";
        } else {
            redirectedActionName = redirectPageName.toString();
        }

        return redirectedActionName;
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
            userService.updateUser(currentUserToUpdate);
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
            userService.updateUser(currentUserToUpdate);

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
     * @param model            domain object of the redirecting page.
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
                parishService.createParishFormBackObject(model);
                break;
            case PRAYERUNIT:
                prayerUnitService.createPrayerUnitFormBackObject(model);
                break;
            case FAMILY:
                factorySelectBox.generateSelectBoxInModel(model, requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class));
                break;
            case RECEIPT:
                Map<Long, String> parishMap = new HashMap<>();
                List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);
                if (!parishList.isEmpty()) {
                    parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParsihName));
                }
                CashFlow cashFlowReceipt = new CashFlow();
                cashFlowReceipt.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
                model.addAttribute("cashFlowReceipt", cashFlowReceipt);
                model.addAttribute("parishMap", parishMap);
                break;
        }
    }

}
