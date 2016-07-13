package org.pms.services;

import org.pms.enums.PageName;
import org.pms.models.User;

/**
 * LoginService description
 * User: tijo
 */
public interface LoginService {

    User verifyLoggedInUser(String loginUserEmail, String loginUserPassword);

    PageName getRedirectPageForLoggedInUser(User currentUser);

    User getUserByEmail(String userEmail);

    Boolean verifyEmailIsPresent(String mail);
}
