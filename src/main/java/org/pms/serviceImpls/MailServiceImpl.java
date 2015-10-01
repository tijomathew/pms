package org.pms.serviceImpls;

import org.pms.models.User;
import org.pms.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by tijo on 6/11/14.
 */

@Component
public class MailServiceImpl implements MailService {

    @Autowired(required = true)
    private JavaMailSender mailSender;


    @Override
    public void sendUserCredentials(User registeredUser) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        try {
            mailMsg.setTo(registeredUser.getEmail());
            mailMsg.setSubject("Username & Password to login to the Parish Management Software System in Ireland!!..");
            mailMsg.setText("Link to login :- http://pms.heart4needy.com \n\nUsername:- " + registeredUser.getEmail() + "\n\nPassword:- " + registeredUser.getPassword() + "\n\nThanking you," + "\n\nPin2");
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("---Errorrr from Mail Sending---");
        }
    }

    @Override
    public void sendForgotPassword(String mailID, String generatedPassword) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        try {
            mailMsg.setTo(mailID);
            mailMsg.setSubject("Reset Password to login to the Parish Management Software System in Ireland!!..");
            mailMsg.setText("Password:- " + generatedPassword);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("---Errorrr from Mail Sending---");
        }
    }
}
