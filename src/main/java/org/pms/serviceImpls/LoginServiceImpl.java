package org.pms.serviceImpls;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.pms.applicationbuilder.PMSApplicationBuilder;
import org.pms.enums.PageName;
import org.pms.daos.LoginDao;
import org.pms.enums.SystemRole;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.User;
import org.pms.services.LoginService;
import org.pms.sessionmanager.PMSSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("PMSApplicationBuilderImpl")
    @Autowired(required = true)
    private PMSApplicationBuilder pmsApplicationBuilder;

    private static final String[] differentRolesInSessionValues = new String[]{"adminRole", "parishAdminRole", "massCenterAdminRole", "prayerUnitAdminRole", "familyUserRole"};

    @Override
    public PageName verifyUserAndGetRedirectPageSM(String loginUserEmail, String loginUserPassword) throws IllegalArgumentException {
        PageName redirectPageName = PageName.LOGIN;
        User loggedInUser = loginDao.getUserByUserEmail(loginUserEmail);
        if (loggedInUser != null) {
            if (DigestUtils.shaHex(loginUserPassword).equalsIgnoreCase(loggedInUser.getPassword()) && (loginUserEmail.equalsIgnoreCase(loggedInUser.getEmail()))) {
                createUserRoleInSession(loggedInUser);
                redirectPageName = getRedirectedPageName(loggedInUser);
            }
        }
        return redirectPageName;
    }

    private PageName getRedirectedPageName(User loggedInUser) {
        PageName redirectPageName = PageName.LOGIN;
        switch (loggedInUser.getSystemRole()) {
            case ADMIN:
            case PARISH_ADMIN:
                redirectPageName = PageName.PARISH;
                break;
            case MASS_CENTER_ADMIN:
                redirectPageName = PageName.MASSCENTER;
                break;
            case PRAYER_UNIT_ADMIN:
                redirectPageName = PageName.PRAYERUNIT;
                break;
            case FAMILY_USER:
                redirectPageName = PageName.FAMILY;
                break;
        }
        return redirectPageName;
    }

    private void createUserRoleInSession(User loggedInUser) {
        switch (loggedInUser.getSystemRole()) {
            case ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "adminRole", loggedInUser);
                break;
            case PARISH_ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "parishAdminRole", loggedInUser);
                break;
            case MASS_CENTER_ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "massCenterAdminRole", loggedInUser);
                break;
            case PRAYER_UNIT_ADMIN:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "prayerUnitAdminRole", loggedInUser);
                break;
            case FAMILY_USER:
                createUserAndRolesInSessionScope(differentRolesInSessionValues, "familyUserRole", loggedInUser);
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
        String sessionContextKey = requestResponseHolder.getAttributeFromSession(PMSSessionManager.PMS_APPLICATION_SESSION, String.class);
        pmsApplicationBuilder.addUserInSessionMap(loggedInUser, sessionContextKey);
        requestResponseHolder.setAttributeToSession(SystemRole.PMS_CURRENT_USER.toString(), loggedInUser);
        requestResponseHolder.setAttributeToSession("currentUserEmail", loggedInUser.getEmail());
    }
}
