package org.pms.user;

import org.pms.domain.User;

import java.util.List;

/**
 * UserDao description
 * User: tijo
 */
public interface UserDao {

    Boolean addUserDM(User user);

    User getUserByEmail(String email);

    Long verifyEmailIsPresent(String mailID);

    List<User> getAllUsers();

    List<User> getAllUsersForParishIds(List<Long> parishIds);

    List<User> getAllUsersForPrayerUnitIds(List<Long> prayerUnitIds);

    List<User> getAllUsersForCurrentUser(User currentUser, Boolean isLoggedIn);

    Boolean updateUser(User user);
}
