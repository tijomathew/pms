package org.pms.services;

import org.pms.models.User;

/**
 * LoginService description
 * User: tijo
 */
public interface LoginService {

    String verifyUserAndGetRedirectPageSM(String loginUserName, String loginUserPassword);
}
