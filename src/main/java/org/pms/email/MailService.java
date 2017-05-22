package org.pms.email;

import org.pms.domain.User;

/**
 * Created by tijo on 6/11/14.
 */
public interface MailService {

    void sendUserCredentials(User registeredUser);

    void sendForgotPassword(String mailID, String generatedPassword);
}
