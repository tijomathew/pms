package org.pms.services;

import org.pms.models.User;

/**
 * UserService description
 * User: tijo
 */
public interface UserService {

    boolean addUserSM(User user);

    User getUserByUserName(String userName);
}
