package org.pms.controllers;

import org.pms.constants.Roles;
import org.pms.constants.SystemRoles;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.Parish;
import org.pms.models.Priest;
import org.pms.models.User;
import org.pms.services.LoginService;
import org.pms.services.MailService;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
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


    @RequestMapping(method = RequestMethod.GET)
    public String indexPageDisplay(Model model) {
        return "index";
    }

    @RequestMapping(value = "/login.action", method = RequestMethod.GET)
    public String loginPageDisplay(Model model) {
        model.addAttribute("loginUser", new User());
        return "login";
    }

    @RequestMapping(value = "/loggedIn.action", method = RequestMethod.POST)
    public String verifyUser(@ModelAttribute("loginUser") @Valid User user, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
        String redirectedPage = "parish";
        boolean permittedUser = false;

        /*Priest formDisplayPriest = new Priest();
        formDisplayPriest.setPriestID("PR" + priestService.getHighestAutoIDSM());*/

        Long parishCounter = parishService.getParishCount();
        Parish formBackParish = new Parish();
        formBackParish.setParishID("PA" + (++parishCounter));
        model.addAttribute("parish", formBackParish);


        requestResponseHolder.getCurrentSession().setAttribute("showlinks", Boolean.TRUE.booleanValue());

        try {
            permittedUser = loginService.verifyUserSM(user.getUserName(), user.getPassword());

        } catch (IllegalArgumentException ex) {
            logger.error("Exception thrown from login functionality");
        }

        if (result.hasErrors()) {
            redirectedPage = "login";
        }

        // mailService.sendUserCredentials(new User());
        if (permittedUser) {
            model.addAttribute("parish", formBackParish);
        } else {
            result.addError(new ObjectError("loginErrorDisplay", new String[]{"LoginError"}, new String[]{}, "default message"));
            redirectedPage = "login";
            //model.addAttribute("error", "please errorrrrr");
            model.addAttribute("loginUser", user);
        }

        return redirectedPage;
    }


}
