package org.pms.serviceImpls;

import org.pms.daos.UserDao;
import org.pms.enums.SystemRole;
import org.pms.enums.SystemRolesStatus;
import org.pms.models.MassCentre;
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
    private MassCentreService massCentreService;

    @Autowired
    private PrayerUnitService prayerUnitService;

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

        Predicate<SystemRole> excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser = p -> !(p.name().equalsIgnoreCase(SystemRole.PMS_CURRENT_USER.toString())) && !(p.name().equalsIgnoreCase(SystemRole.ADMIN.toString()));

        switch (currentUser.getSystemRole()) {
            case PARISH_ADMIN:
                Predicate<SystemRole> excludeParishAdmin = p -> !(p.name().equalsIgnoreCase(SystemRole.PARISH_ADMIN.toString()));
                excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser = excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser.and(excludeParishAdmin);
                break;
            case MASS_CENTER_ADMIN:
                Predicate<SystemRole> excludeParishAdminAndMassCenterAdmin = p -> !(p.name().equalsIgnoreCase(SystemRole.MASS_CENTER_ADMIN.toString())) && !(p.name().equalsIgnoreCase(SystemRole.PARISH_ADMIN.toString()));
                excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser = excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser.and(excludeParishAdminAndMassCenterAdmin);
                break;
            case PRAYER_UNIT_ADMIN:
                Predicate<SystemRole> excludeParishAdminAndMassCenterAdminAndPrayerUnitAdmin = p -> !(p.name().equalsIgnoreCase(SystemRole.PRAYER_UNIT_ADMIN.toString())) && !(p.name().equalsIgnoreCase(SystemRole.MASS_CENTER_ADMIN.toString())) && !(p.name().equalsIgnoreCase(SystemRole.PARISH_ADMIN.toString()));
                excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser = excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser.and(excludeParishAdminAndMassCenterAdminAndPrayerUnitAdmin);
                createUserModelSelectBoxes(model, currentUser);
                break;
        }
        Map<String, String> systemRoleMap = Arrays.stream(SystemRole.values()).filter(excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser).collect(Collectors.toMap(SystemRole::name, SystemRole::getUIDisplayValue));
        systemRoleMap.put("0", "-Please Select--");
        model.addAttribute("systemRoles", systemRoleMap);
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
                currentUserParishIdAsList.add(currentUser.getUsersOfParishes().getId());

                allUsers.addAll(getAllUsersForParishIds(currentUserParishIdAsList));
                break;
            case MASS_CENTER_ADMIN:
                List<Long> currentUserMassCenterIdAsList = new ArrayList<>();
                currentUserMassCenterIdAsList.add(currentUser.getUsersOfMassCenters().getId());

                allUsers.addAll(getAllUsersForMassCenterIds(currentUserMassCenterIdAsList));
                break;
            case PRAYER_UNIT_ADMIN:
                List<Long> currentUserPrayerUnitIdAsList = new ArrayList<>();
                currentUserPrayerUnitIdAsList.add(currentUser.getUsersOfPrayerUnits().getId());

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
        List<MassCentre> massCentreList = massCentreService.getAllMassCentersForUserRole(currentUser);
        if (!massCentreList.isEmpty()) {
            massCenterMap = massCentreList.stream().collect(Collectors.toMap(MassCentre::getId, MassCentre::getMassCenterName));
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
