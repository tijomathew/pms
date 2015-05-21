package org.pms.controllers;

import org.pms.models.User;
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

    @RequestMapping(value = "/loggedout.action", method = RequestMethod.GET)
    public String loginPageDisplay(Model model) {
        model.addAttribute("loginUser", new User());
        return "login";
    }
}
