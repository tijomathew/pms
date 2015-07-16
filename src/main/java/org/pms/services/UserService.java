package org.pms.services;

import org.pms.dtos.UserDto;
import org.pms.models.User;

import java.util.List;

/**
 * UserService description
 * User: tijo
 */
public interface UserService {

    boolean addOrUpdateUserSM(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    List<UserDto> createUserDtos(List<User> userList) throws IllegalArgumentException;

    Long getAllUserCount();

    List<User> getAllUsersForParishIds(List<Long> parishIds);

    List<User> getAllUsersForMassCenterIds(List<Long> massCenterIds);

    List<User> getAllUsersForPrayerUnitIds(List<Long> prayerUnitIds);

    List<User> getAllUsersForFamilyIds(List<Long> familyIds);
}
