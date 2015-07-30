package org.pms.serviceImpls;

import org.pms.daos.UserDao;
import org.pms.enums.SystemRole;
import org.pms.enums.SystemRolesStatus;
import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.pms.models.PrayerUnit;
import org.pms.models.User;
import org.pms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * UserServiceImpl description
 * User: tijo
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private FamilyService familyService;

    @Override
    public boolean addOrUpdateUserSM(User user) {
        userDao.addOrUpdateUserDM(user);
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

    @Override
    public void createUserFormBackObject(Model model, User currentUser) {
        model.addAttribute("user", new User());
        if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
            createUserModelSelectBoxes(model, currentUser);
        }

        Predicate<SystemRole> excludePMSCurrentUser = p -> !(p.name().equalsIgnoreCase(SystemRole.PMS_CURRENT_USER.toString()));
        Predicate<SystemRole> excludeAdmin = p -> !(p.name().equalsIgnoreCase(SystemRole.ADMIN.toString()));
        Predicate<SystemRole> excludeAdminAndCurrentUser = excludePMSCurrentUser.and(excludeAdmin);

        model.addAttribute("systemRoles", Arrays.stream(SystemRole.values()).filter(excludeAdminAndCurrentUser).collect(Collectors.toMap(SystemRole::name, SystemRole::getUIDisplayValue)));
        model.addAttribute("systemRoleStatus", Arrays.stream(SystemRolesStatus.values()).collect(Collectors.toMap(SystemRolesStatus::name, SystemRolesStatus::getUIDisplayValue)));

    }

    @Override
    public List<User> getAllUsersForUserRole(User currentUser) {
        List<User> allUsers = new ArrayList<>();

        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allUsers = getAllUsers();
                break;
            case PARISH_ADMIN:
                List<Long> currentUserParishIdAsList = new ArrayList<>();
                currentUserParishIdAsList.add(currentUser.getParishId());

                allUsers.addAll(getAllUsersForParishIds(currentUserParishIdAsList));
                break;
            case MASS_CENTER_ADMIN:
                List<Long> currentUserMassCenterIdAsList = new ArrayList<>();
                currentUserMassCenterIdAsList.add(currentUser.getMassCenterId());

                allUsers.addAll(getAllUsersForMassCenterIds(currentUserMassCenterIdAsList));
                break;
            case PRAYER_UNIT_ADMIN:
                List<Long> currentUserPrayerUnitIdAsList = new ArrayList<>();
                currentUserPrayerUnitIdAsList.add(currentUser.getPrayerUnitId());

                allUsers.addAll(getAllUsersForPrayerUnitIds(currentUserPrayerUnitIdAsList));
                break;
            case FAMILY_USER:
                //No Op
                break;
        }

        return allUsers;
    }

    private void createUserModelSelectBoxes(Model model, User currentUser) {

        Map<Long, String> parishMap = new HashMap<Long, String>();
        List<Parish> addedParishes = parishService.getAllParishForUserRole(currentUser);
        if (!addedParishes.isEmpty()) {
            parishMap = addedParishes.stream().collect(Collectors.toMap(Parish::getId, Parish::getParishName));
        }

        Map<Long, String> massCenterMap = new HashMap<Long, String>();
        List<MassCenter> massCenterList = massCenterService.getAllMassCentersForUserRole(currentUser);
        if (!massCenterList.isEmpty()) {
            massCenterMap = massCenterList.stream().collect(Collectors.toMap(MassCenter::getId, MassCenter::getMassCenterName));
        }

        Map<Long, String> prayerUnitMap = new HashMap<Long, String>();
        List<PrayerUnit> prayerUnitList = prayerUnitService.getAllPrayerUnitsForUserRole(currentUser);
        if (!prayerUnitList.isEmpty()) {
            prayerUnitMap = prayerUnitList.stream().collect(Collectors.toMap(PrayerUnit::getId, PrayerUnit::getPrayerUnitName));
        }

        model.addAttribute("parishList", parishMap);
        model.addAttribute("massCenterList", massCenterMap);
        model.addAttribute("prayerUnitList", prayerUnitMap);

    }
}
