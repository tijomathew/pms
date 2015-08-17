package org.pms.daos;

import org.pms.models.User;

import java.util.List;

/**
 * UserDao description
 * User: tijo
 */
public interface UserDao {

    boolean addOrUpdateUserDM(User user);

    User getUserByEmail(String email);

    Long verifyEmailIsPresent(String mailID);

    List<User> getAllUsers();

    Long getAllUserCount();

    List<User> getAllUsersForParishIds(List<Long> parishIds);

    List<User> getAllUsersForMassCentreIds(List<Long> massCentreIds);

    List<User> getAllUsersForPrayerUnitIds(List<Long> prayerUnitIds);

    List<User> getAllUsersForFamilyIds(List<Long> familyIds);
}
