package org.pms.daos;

import org.pms.models.User;

import java.util.List;

/**
 * UserDao description
 * User: tijo
 */
public interface UserDao {

    boolean addUserDM(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    Long getAllUserCount();
}
