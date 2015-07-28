package org.pms.services;

import org.pms.models.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * UserService description
 * User: tijo
 */
public interface UserService {

    boolean addOrUpdateUserSM(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    Long getAllUserCount();

    List<User> getAllUsersForParishIds(List<Long> parishIds);

    List<User> getAllUsersForMassCenterIds(List<Long> massCenterIds);

    List<User> getAllUsersForPrayerUnitIds(List<Long> prayerUnitIds);

    List<User> getAllUsersForFamilyIds(List<Long> familyIds);

    void createUserFormBackObject(Model model, User currentUser);

    List<User> getAllUsersForUserRole(User currentUser);
}
