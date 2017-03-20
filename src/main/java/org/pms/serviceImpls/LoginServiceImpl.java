package org.pms.serviceImpls;

import org.apache.commons.codec.digest.DigestUtils;
import org.pms.applicationbuilder.PMSApplicationBuilder;
import org.pms.daos.UserDao;
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
    private UserDao userDao;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Qualifier("PMSApplicationBuilderImpl")
    @Autowired(required = true)
    private PMSApplicationBuilder pmsApplicationBuilder;

    private static final String[] differentRolesInSessionValues = new String[]{"adminRole", "parishAdminRole", "prayerUnitAdminRole", "familyUserRole", "showForFinanceAdmin"};

    @Override
    public User verifyLoggedInUser(String loginUserEmail, String loginUserPassword) {
        User loggedInUser = loginDao.getUserByUserEmail(loginUserEmail);
        if (loggedInUser != null) {
            if (!DigestUtils.shaHex(loginUserPassword).equalsIgnoreCase(loggedInUser.getPassword()) && (loginUserEmail.equalsIgnoreCase(loggedInUser.getEmail()))) {
                loggedInUser = null;
            }
        }
        return loggedInUser;
    }

    @Override
    public PageName getRedirectPageForLoggedInUser(User currentUser) throws IllegalArgumentException {
        PageName redirectPageName;
        if (currentUser.getAlreadyLoggedIn()) {
            createUserRoleInSession(currentUser);
            redirectPageName = getRedirectedPageName(currentUser);
        } else {
            redirectPageName = PageName.CHANGEPASSWORD;
        }
        return redirectPageName;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return loginDao.getUserByUserEmail(userEmail);
    }

    @Override
    public Boolean verifyEmailIsPresent(String mail) {
        Long mailCount = userDao.verifyEmailIsPresent(mail);
        return mailCount.longValue() > 0;
    }

    private PageName getRedirectedPageName(User loggedInUser) {
        PageName redirectPageName = PageName.LOGIN;
        switch (loggedInUser.getSystemRole()) {
            case ADMIN:
                redirectPageName = PageName.PARISH;
                break;
            case PARISH_ADMIN:
                redirectPageName = PageName.PARISH;
                break;
            case PRAYER_UNIT_ADMIN:
                redirectPageName = PageName.PRAYERUNIT;
                break;
            case FAMILY_USER:
                redirectPageName = PageName.FAMILY;
                break;
            case FINANCE_USER:
                redirectPageName = PageName.RECEIPT;
                break;
        }
        return redirectPageName;
    }

    private void createUserRoleInSession(User loggedInUser) {
        switch (loggedInUser.getSystemRole()) {
            case ADMIN:
                createUserAndRolesInSessionScope("adminRole", loggedInUser);
                break;
            case PARISH_ADMIN:
                createUserAndRolesInSessionScope("parishAdminRole", loggedInUser);
                break;
            case PRAYER_UNIT_ADMIN:
                createUserAndRolesInSessionScope("prayerUnitAdminRole", loggedInUser);
                break;
            case FAMILY_USER:
                createUserAndRolesInSessionScope("familyUserRole", loggedInUser);
                break;
            case FINANCE_USER:
                createUserAndRolesInSessionScope("showForFinanceAdmin", loggedInUser);
                break;
        }

    }

    private void createUserAndRolesInSessionScope(String assignedRoleInSession, User loggedInUser) {
        for (String rolesInSession : differentRolesInSessionValues) {
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
