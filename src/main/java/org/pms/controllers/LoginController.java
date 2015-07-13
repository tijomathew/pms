package org.pms.controllers;

import org.apache.commons.lang3.StringUtils;
import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.helpers.FactorySelectBox;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.*;
import org.pms.services.*;
import org.pms.validators.LoginValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * LoginController description
 * User: tijo
 */

@Controller
@RequestMapping("/")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private PriestService priestService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private FactorySelectBox factorySelectBox;

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
        requestResponseHolder.getCurrentSession().setAttribute("showlinks", Boolean.TRUE.booleanValue());

        try {
            redirectPageName = loginService.verifyUserAndGetRedirectPageSM(user.getEmail(), user.getPassword());

        } catch (IllegalArgumentException ex) {
            logger.error("The authentication and authorization of the user is failed in the system");
        }

        if (result.hasErrors()) {
            //redirectedPage = "login";
        }
        createFormBackObjectForRedirectPage(model, redirectPageName);

       /* if (redirectPageName) {
            model.addAttribute("parish", formBackParish);
        } else {
            result.addError(new ObjectError("loginErrorDisplay", new String[]{"LoginError"}, new String[]{}, "default message"));
            redirectedPage = "login";
            //model.addAttribute("error", "please errorrrrr");
            model.addAttribute("loginUser", user);
        }*/


        return redirectPageName.toString();
    }

    private void createFormBackObjectForRedirectPage(Model model, PageName redirectPageName) {
        switch (redirectPageName) {
            case LOGIN:
                model.addAttribute("loginUser", new User());
                break;
            case PARISH:
                model.addAttribute("showAddButton", true);
                model.addAttribute("parish", new Parish());
                break;
            case MASSCENTER:
                MassCenter formBackMassCenter = massCenterService.createMassCenterFormBackObject(model);
                model.addAttribute("massCenter", formBackMassCenter);
                break;
            case PRAYERUNIT:
                PrayerUnit formBackPrayerUnit = prayerUnitService.createPrayerUnitFormBackObject(model);
                model.addAttribute("prayerUnit", formBackPrayerUnit);
                break;
            case FAMILY:
                model.addAttribute("family", new Family());
                factorySelectBox.generateSelectBoxInModel(model, requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class));
                break;
            case MEMBER:
                model.addAttribute("member", new Member());
                factorySelectBox.createSelectBox(model);
                break;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new LoginValidator());
    }

}
