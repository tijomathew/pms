package org.pms.serviceImpls;

import org.pms.constants.SystemRoles;
import org.pms.daos.LoginDao;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.User;
import org.pms.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * LoginServiceImpl description
 * User: tijo
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    private static final String[] differentRolesInSessionValues = new String[]{"adminRole", "parishAdminRole", "massCenterAdminRole", "prayerUnitAdminRole"};

    @Override
    public boolean verifyUserSM(String loginUserName, String loginUserPassword) throws IllegalArgumentException {
        boolean authenticatedUser = false;
        User loggedInUser = loginDao.getUserByUsernameDM(loginUserName);

        if (loginUserPassword.equalsIgnoreCase(loggedInUser.getPassword()) && (loginUserName.equalsIgnoreCase(loggedInUser.getUserName()))) {
            authenticatedUser = true;
            createUserRoleInSession(loggedInUser);
        }
        return authenticatedUser;
    }

    private void createUserRoleInSession(User loggedInUser) {
        switch (loggedInUser.getSystemRole().toString()) {
            case SystemRoles.ADMIN:
                createUserRolesInSessionScope(differentRolesInSessionValues, "adminRole");
                break;
            case SystemRoles.PARISH_ADMIN:
                createUserRolesInSessionScope(differentRolesInSessionValues, "parishAdminRole");
                break;
            case SystemRoles.MASS_CENTER_ADMIN:
                createUserRolesInSessionScope(differentRolesInSessionValues, "massCenterAdminRole");
                break;
            case SystemRoles.PRAYER_UNIT_ADMIN:
                createUserRolesInSessionScope(differentRolesInSessionValues, "prayerUnitAdminRole");
                break;
        }

    }

    private void createUserRolesInSessionScope(String[] passedRoleNames, String assignedRoleInSession) {
        for (String rolesInSession : passedRoleNames) {
            if (!rolesInSession.equalsIgnoreCase(assignedRoleInSession)) {
                requestResponseHolder.getCurrentSession().setAttribute(rolesInSession, Boolean.FALSE.booleanValue());
            } else {
                requestResponseHolder.getCurrentSession().setAttribute(rolesInSession, Boolean.TRUE.booleanValue());
            }
        }
    }
}
