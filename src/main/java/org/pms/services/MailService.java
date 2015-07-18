package org.pms.services;

import org.pms.models.User;

/**
 * Created by tijo on 6/11/14.
 */
public interface MailService {

    void sendUserCredentials(User registeredUser);

    void sendForgotPassword(String mailID, String generatedPassword);
}
