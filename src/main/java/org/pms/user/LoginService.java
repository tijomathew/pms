package org.pms.user;

import org.pms.common.PageName;
import org.pms.domain.User;

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
