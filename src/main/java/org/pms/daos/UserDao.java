package org.pms.daos;

import org.pms.models.User;

/**
 * UserDao description
 * User: tijo
 */
public interface UserDao {

    boolean addUserDM(User user);

    User getUserByUserName(String userName);
}
