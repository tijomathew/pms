package org.pms.services;

import org.pms.models.User;

/**
 * LoginService description
 * User: tijo
 */
public interface LoginService {

    boolean verifyUserSM(String loginUserName, String loginUserPassword);
}
