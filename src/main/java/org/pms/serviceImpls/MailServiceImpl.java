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
            mailMsg.setSubject("Welcome to Parish Management System (PMS) of Syromalabar Church in Ireland");
            mailMsg.setText("Your family’s unique login details to the PMS are given below." + "\n\nClick on the link and enter your user credentials for gaining access to PMS \n\nURL:http://pms.heart4needy.com \n\nLogin Email: " + registeredUser.getEmail() + "\n\nPassword: " + registeredUser.getPassword() + "\n\nNote:\n1. Please keep your login credentials for future access. \n\n2. You can also access PMS from www.syromalabar.ie \n\nThanking you," + "\n\nRev. Fr. Jose Bharanikulangara\nRev. Fr. Antony Cheeramvelil\n\nTechnical Contact: Pintu Jacob (0873214964), pintujacob@gmail.com");
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
