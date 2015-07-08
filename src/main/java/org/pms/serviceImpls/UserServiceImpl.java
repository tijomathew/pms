package org.pms.serviceImpls;

import org.pms.daos.UserDao;
import org.pms.dtos.UserDto;
import org.pms.models.User;
import org.pms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * UserServiceImpl description
 * User: tijo
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUserSM(User user) {
        userDao.addUserDM(user);
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<UserDto> createUserDtos(List<User> userList) throws IllegalArgumentException {
        List<UserDto> userDtoList = new ArrayList<>(userList.size());
        if (!userList.isEmpty()) {
            Integer uniqueId = 0;
            for (User user : userList) {
                UserDto userDto = new UserDto();
                userDto.setId(uniqueId);
                userDto.setEmail(user.getEmail());
                userDto.setIsActive(user.getIsActive());
                userDto.setFamilyId(user.getMassCenterId());
                userDto.setParishId(user.getParishId());
                userDto.setSystemRole(user.getSystemRole());
                userDtoList.add(userDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("UserList Cannot be null");
        }

        return userDtoList;
    }

    @Override
    public Long getAllUserCount() {
        return userDao.getAllUserCount();
    }

    @Override
    public List<User> getAllUsersForParishIds(List<Long> parishIds) {
        return userDao.getAllUsersForParishIds(parishIds);
    }

    @Override
    public List<User> getAllUsersForMassCenterIds(List<Long> massCenterIds) {
        return userDao.getAllUsersForMassCenterIds(massCenterIds);
    }

    @Override
    public List<User> getAllUsersForPrayerUnitIds(List<Long> prayerUnitIds) {
        return userDao.getAllUsersForPrayerUnitIds(prayerUnitIds);
    }

    @Override
    public List<User> getAllUsersForFamilyIds(List<Long> familyIds) {
        return userDao.getAllUsersForFamilyIds(familyIds);
    }
}
