package org.pms.email;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.pms.common.PageName;
import org.pms.user.StatusCode;
import org.pms.user.SystemRole;
import org.pms.common.error.AbstractErrorAndGridHandler;
import org.pms.common.error.CustomResponse;
import org.pms.common.DateUtils;
import org.pms.common.RequestResponseHolder;
import org.pms.domain.User;
import org.pms.user.UserService;
import org.pms.common.PMSSessionManager;
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
public class EmailNotificationController extends AbstractErrorAndGridHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/viewemailnotification.action", method = RequestMethod.GET)
    public String viewEmailNotificationPageDisplay() {
        return PageName.EMAILNOTIFICATION.toString();
    }

    @RequestMapping(value = "/createmailidlist.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateMailIdSelectBox(@RequestParam(value = "all", required = true) Boolean allUsersFlag) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<User> allUsers = userService.getAllUsersNotLoggedInForUser(currentUser, allUsersFlag);

        List<Email> emailList = new ArrayList<>();
        for (User user : allUsers) {
            String[] nameFromMailId = user.getEmail().split("@");
            emailList.add(new Email(user.getEmail(), nameFromMailId[0]));
        }
        Gson gson = new Gson();
        return gson.toJson(emailList);
    }

    @RequestMapping(value = "/sendcustomregistrationemail.action", method = RequestMethod.GET)
    public
    @ResponseBody
    CustomResponse sendMailForRegisteredUser(@RequestParam(value = "email", required = true) String registeredEmail) {
        User registeredUser = userService.getUserByEmail(registeredEmail);
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        if (registeredUser != null) {
            registeredUser.setUpdatedBy(currentUser.getEmail());
            registeredUser.setUpdatedOn(DateUtils.getCurrentDate());
            String generatedPassword = RandomStringUtils.random(8, PMSSessionManager.keySpace);
            registeredUser.setPassword(DigestUtils.shaHex(generatedPassword));
            userService.updateUser(registeredUser);
            if (registeredUser.getSendMailFlag()) {
                registeredUser.setPassword(generatedPassword);
                mailService.sendUserCredentials(registeredUser);
            }
            return createStatusCodeResponse(StatusCode.SUCCESS);
        } else {
            return createStatusCodeResponse(StatusCode.FAIL);
        }
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