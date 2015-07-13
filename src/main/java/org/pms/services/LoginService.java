package org.pms.services;

import org.pms.enums.PageName;
import org.pms.models.User;

/**
 * LoginService description
 * User: tijo
 */
public interface LoginService {

    PageName verifyUserAndGetRedirectPageSM(String loginUserEmail, String loginUserPassword);
}
