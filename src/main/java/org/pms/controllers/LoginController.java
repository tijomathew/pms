package org.pms.controllers;

import org.apache.commons.lang3.StringUtils;
import org.pms.constants.PageNames;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.*;
import org.pms.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    @RequestMapping(method = RequestMethod.GET)
    public String indexPageDisplay(Model model) {
        return "index";
    }

    @RequestMapping(value = "/login.action", method = RequestMethod.GET)
    public String loginPageDisplay(Model model) {
        model.addAttribute("loginUser", new User());
        return "login";
    }

    @RequestMapping(value = "/loggedin.action", method = RequestMethod.POST)
    public String verifyUser(@ModelAttribute("loginUser") @Valid User user, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
        String redirectedPage = "parish";
        String redirectPageName = StringUtils.EMPTY;

        /*Priest formDisplayPriest = new Priest();
        formDisplayPriest.setPriestID("PR" + priestService.getHighestAutoIDSM());*/


        requestResponseHolder.getCurrentSession().setAttribute("showlinks", Boolean.TRUE.booleanValue());

        try {
            redirectPageName = loginService.verifyUserAndGetRedirectPageSM(user.getUserName(), user.getPassword());

        } catch (IllegalArgumentException ex) {
            logger.error("Exception thrown from login functionality");
        }

        if (result.hasErrors()) {
            redirectedPage = "login";
        }
        createFormBackObjectForRedirectPage(model, redirectPageName);
        // mailService.sendUserCredentials(new User());
       /* if (redirectPageName) {
            model.addAttribute("parish", formBackParish);
        } else {
            result.addError(new ObjectError("loginErrorDisplay", new String[]{"LoginError"}, new String[]{}, "default message"));
            redirectedPage = "login";
            //model.addAttribute("error", "please errorrrrr");
            model.addAttribute("loginUser", user);
        }*/

        return redirectPageName;
    }

    private void createFormBackObjectForRedirectPage(Model model, String redirectPageName) {
        switch (redirectPageName) {
            case PageNames.LOGIN:
                model.addAttribute("loginUser", new User());
                break;
            case PageNames.PARISH:
                Parish parishFormBackObject = createParishFormBackObject(model);
                model.addAttribute("parish", parishFormBackObject);
                break;
            case PageNames.MASSCENTER:
                MassCenter formBackMassCenter = massCenterService.createMassCenterFormBackObject(model);
                model.addAttribute("massCenter", formBackMassCenter);
                break;
            case PageNames.PRAYERUNIT:
                PrayerUnit formBackPrayerUnit = prayerUnitService.createPrayerUnitFormBackObject(model);
                model.addAttribute("prayerUnit", formBackPrayerUnit);
                break;
            case PageNames.FAMILY:
                model.addAttribute("family", new Family());
                break;
            case PageNames.MEMBER:
                model.addAttribute("member", new Member());
                break;
        }
    }

    private Parish createParishFormBackObject(Model model) {
        Long parishCounter = parishService.getParishCount();
        Parish formBackParish = new Parish();
        formBackParish.setParishID("PA" + (++parishCounter));
        model.addAttribute("parish", formBackParish);
        return formBackParish;
    }


}
