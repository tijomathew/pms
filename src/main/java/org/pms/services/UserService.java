package org.pms.services;

import org.pms.dtos.UserDto;
import org.pms.models.User;

import java.util.List;

/**
 * UserService description
 * User: tijo
 */
public interface UserService {

    boolean addUserSM(User user);

    User getUserByUserName(String userName);

    List<User> getAllUsers();

    List<UserDto> createUserDtos(List<User> userList) throws IllegalArgumentException;

    Long getAllUserCount();
}
