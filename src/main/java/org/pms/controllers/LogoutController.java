package org.pms.controllers;

import org.pms.constants.PageNames;
import org.pms.constants.SystemRoles;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * LogoutController description
 * User: tijo
 */

@Controller
@RequestMapping("/")
public class LogoutController {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/loggedout.action", method = RequestMethod.GET)
    public String loginPageDisplay(Model model) {
        model.addAttribute("loginUser", new User());
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        if (currentUser != null) {
            requestResponseHolder.removeAttributeFromSession(SystemRoles.PMS_CURRENT_USER);
            requestResponseHolder.getCurrentSession().invalidate();
        }
        return PageNames.LOGIN;
    }
}
