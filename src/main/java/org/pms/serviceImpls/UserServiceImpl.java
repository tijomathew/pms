package org.pms.serviceImpls;

import org.pms.daos.UserDao;
import org.pms.enums.SystemRole;
import org.pms.enums.SystemRolesStatus;
import org.pms.models.*;
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
    private MassCentreService massCentreService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private FamilyService familyService;

    @Override
    public Boolean addUserSM(User user) {
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
    public Long getAllUserCount() {
        return userDao.getAllUserCount();
    }

    @Override
    public List<User> getAllUsersForParishIds(List<Long> parishIds) {
        return userDao.getAllUsersForParishIds(parishIds);
    }

    @Override
    public List<User> getAllUsersForMassCentreIds(List<Long> massCentreIds) {
        return userDao.getAllUsersForMassCentreIds(massCentreIds);
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
    public Boolean updateUser(User user) {
        userDao.updateUser(user);
        return true;
    }

    @Override
    public void createUserFormBackObject(Model model, User currentUser) {
        model.addAttribute("user", new User());

        Predicate<SystemRole> excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser = p -> !(p.name().equalsIgnoreCase(SystemRole.PMS_CURRENT_USER.toString())) && !(p.name().equalsIgnoreCase(SystemRole.ADMIN.toString()));

        switch (currentUser.getSystemRole()) {
            case ADMIN:
                createUserModelSelectBoxes(model, currentUser);
                break;
            case MASS_CENTER_ADMIN:
                Predicate<SystemRole> excludeMassCentreAdmin = p -> !(p.name().equalsIgnoreCase(SystemRole.MASS_CENTER_ADMIN.toString()));
                excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser = excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser.and(excludeMassCentreAdmin);
                createUserModelSelectBoxes(model, currentUser);
                break;
            case PRAYER_UNIT_ADMIN:
                Predicate<SystemRole> excludeMassCentreAdminAndPrayerUnitAdmin = p -> !(p.name().equalsIgnoreCase(SystemRole.PRAYER_UNIT_ADMIN.toString())) && !(p.name().equalsIgnoreCase(SystemRole.MASS_CENTER_ADMIN.toString()));
                excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser = excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser.and(excludeMassCentreAdminAndPrayerUnitAdmin);
                createUserModelSelectBoxes(model, currentUser);
                break;
        }

        model.addAttribute("systemRoles", Arrays.stream(SystemRole.values()).filter(excludeUserBasedAdminRoleAfterRemovingAdminAndPMSCurrentUser).collect(Collectors.toMap(SystemRole::name, SystemRole::getUIDisplayValue)));
        model.addAttribute("systemRoleStatus", Arrays.stream(SystemRolesStatus.values()).collect(Collectors.toMap(SystemRolesStatus::name, SystemRolesStatus::getUIDisplayValue)));

    }

    @Override
    public List<User> getAllUsersForUserRole(User currentUser) {
        final List<User> allUsers = new ArrayList<>();

        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allUsers.addAll(getAllUsers());
                break;
            case MASS_CENTER_ADMIN:
                MassCentre massCentreOfMassCentreAdmin = currentUser.getUsersOfMassCentres();
                List<Long> currentUserMassCentreIdAsList = new ArrayList<>();
                currentUserMassCentreIdAsList.add(massCentreOfMassCentreAdmin.getId());
                allUsers.addAll(getAllUsersForMassCentreIds(currentUserMassCentreIdAsList));
                break;
            case PRAYER_UNIT_ADMIN:
                PrayerUnit prayerUnitOfPrayerUnitAdmin = currentUser.getUsersOfPrayerUnits();
                List<Long> currentUserPrayerUnitIdAsList = new ArrayList<>();
                currentUserPrayerUnitIdAsList.add(prayerUnitOfPrayerUnitAdmin.getId());
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

        Map<Long, String> massCentreMap = new HashMap<Long, String>();
        List<MassCentre> massCentreList = massCentreService.getAllMassCentresForUserRole(currentUser);
        if (!massCentreList.isEmpty()) {
            massCentreMap = massCentreList.stream().collect(Collectors.toMap(MassCentre::getId, MassCentre::getMassCentreName));
        }

        Map<Long, String> prayerUnitMap = new HashMap<Long, String>();
        List<PrayerUnit> prayerUnitList = prayerUnitService.getAllPrayerUnitsForUserRole(currentUser);
        if (!prayerUnitList.isEmpty()) {
            prayerUnitMap = prayerUnitList.stream().collect(Collectors.toMap(PrayerUnit::getId, PrayerUnit::getPrayerUnitName));
        }

        Map<Long, String> familyMap = new HashMap<>();
        List<Family> familyList = familyService.getAllFamiliesForUserRole(currentUser);
        if (!familyList.isEmpty()) {
            familyMap = familyList.stream().collect(Collectors.toMap(Family::getId, Family::getFamilyName));
        }

        model.addAttribute("massCentreList", massCentreMap);
        model.addAttribute("prayerUnitList", prayerUnitMap);
        model.addAttribute("familyList", familyMap);

    }
}
