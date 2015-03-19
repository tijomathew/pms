package org.pms.serviceImpls;

import org.apache.commons.lang3.StringUtils;
import org.pms.constants.PageNames;
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

    private static final String[] differentRolesInSessionValues = new String[]{"adminRole", "parishAdminRole", "massCenterAdminRole", "prayerUnitAdminRole", "familyHeadRole"};

    @Override
    public String verifyUserAndGetRedirectPageSM(String loginUserName, String loginUserPassword) throws IllegalArgumentException {
        String redirectPageName = StringUtils.EMPTY;
        User loggedInUser = loginDao.getUserByUsernameDM(loginUserName);
        if (loginUserPassword.equalsIgnoreCase(loggedInUser.getPassword()) && (loginUserName.equalsIgnoreCase(loggedInUser.getUserName()))) {
            createUserRoleInSession(loggedInUser);
            redirectPageName = getRedirectedPageName(loggedInUser);
        }
        return redirectPageName;
    }

    private String getRedirectedPageName(User loggedInUser) {
        String redirectPageName = StringUtils.EMPTY;
        switch (loggedInUser.getSystemRole().toString()) {
            case SystemRoles.ADMIN:
            case SystemRoles.PARISH_ADMIN:
                redirectPageName = PageNames.PARISH;
                break;
            case SystemRoles.MASS_CENTER_ADMIN:
                redirectPageName = PageNames.MASSCENTER;
                break;
            case SystemRoles.PRAYER_UNIT_ADMIN:
                redirectPageName = PageNames.PRAYERUNIT;
                break;
            case SystemRoles.FAMILY_ADMIN:
                redirectPageName = PageNames.FAMILY;
                break;
        }
        return redirectPageName;
    }

    private void createUserRoleInSession(User loggedInUser) {
        switch (loggedInUser.getSystemRole().toString()) {
            case SystemRoles.ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "adminRole", loggedInUser);
                break;
            case SystemRoles.PARISH_ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "parishAdminRole", loggedInUser);
                break;
            case SystemRoles.MASS_CENTER_ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "massCenterAdminRole", loggedInUser);
                break;
            case SystemRoles.PRAYER_UNIT_ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "prayerUnitAdminRole", loggedInUser);
                break;
            case SystemRoles.FAMILY_ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "familyHeadRole", loggedInUser);
                break;
        }

    }

    private void createUserAndRolesInSessionScope(String[] passedRoleNames, String assignedRoleInSession, User loggedInUser) {
        for (String rolesInSession : passedRoleNames) {
            if (!rolesInSession.equalsIgnoreCase(assignedRoleInSession)) {
                requestResponseHolder.getCurrentSession().setAttribute(rolesInSession, Boolean.FALSE.booleanValue());
            } else {
                requestResponseHolder.getCurrentSession().setAttribute(rolesInSession, Boolean.TRUE.booleanValue());
            }
        }
        requestResponseHolder.getCurrentSession().setAttribute(SystemRoles.PMS_CURRENT_USER, loggedInUser);
    }
}
