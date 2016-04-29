package org.pms.controllers;

import com.google.gson.Gson;
import org.pms.enums.PageName;
import org.pms.models.User;
import org.pms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 28/4/16.
 */
@Controller
public class EmailNotificationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/viewemailnotification.action", method = RequestMethod.GET)
    public String viewEmailNotificationPageDisplay() {
        return PageName.EMAILNOTIFICATION.toString();
    }

    @RequestMapping(value = "/createmailidlist.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateMailIdSelectBox() {
        List<User> allUsers = userService.getAllUsers();
        List<Email> emailList = new ArrayList<>();
        for (User user : allUsers) {
            String[] nameFromMailId = user.getEmail().split("@");
            emailList.add(new Email(user.getEmail(), nameFromMailId[0]));
        }
        Gson gson = new Gson();
        return gson.toJson(emailList);
    }

    class Email {
        private String email;
        private String name;

        Email(String email, String name) {
            this.email = email;
            this.name = name;
        }
    }
}
