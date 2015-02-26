package org.pms.serviceImpls;

import org.pms.models.User;
import org.pms.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by tijo on 6/11/14.
 */

@Component
public class MailServiceImpl implements MailService {

    @Autowired(required = true)
    private MailSender mailSender;


    @Override
    public void sendUserCredentials(User registeredUser) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("tijopower888@gmail.com");
        simpleMailMessage.setText("Dear Tijo Mathew");

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("tijomathew1988@gmail.com", "tijojohn");
                    }
                });

        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }
}
