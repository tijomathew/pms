package org.pms.user;

import org.pms.common.PageName;
import org.pms.common.RequestResponseHolder;
import org.pms.domain.User;
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
public class LogoutController {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/loggedout.action", method = RequestMethod.POST)
    public String loginPageDisplay(Model model) {
        model.addAttribute("loginUser", new User());
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        if (currentUser != null) {
            requestResponseHolder.removeAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString());
            requestResponseHolder.getCurrentSession().invalidate();
        }
        return PageName.LOGIN.toString();
    }
}
