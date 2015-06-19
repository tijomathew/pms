package org.pms.interceptor;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.pms.applicationbuilder.PMSApplicationBuilder;
import org.pms.constants.SystemRoles;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.User;
import org.pms.services.PriestService;
import org.pms.sessionmanager.PMSSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: tijo.
 */
public class PMSInterceptor implements HandlerInterceptor {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private PMSApplicationBuilder pmsApplicationBuilder;

    @Resource(name = "adminLinks")
    private List<String> adminLinks;

    @Resource(name = "parishAdminLinks")
    private List<String> parishAdminLinks;

    @Resource(name = "massCenterAdminLinks")
    private List<String> massCenterAdminLinks;

    @Resource(name = "prayerUnitAdminLinks")
    private List<String> prayerUnitAdminLinks;

    @Resource(name = "familyHeadLinks")
    private List<String> familyHeadLinks;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean indicatorToProceed = false;
        boolean indicatorToShowSessionTimedOut = false;
        String urlAction = httpServletRequest.getRequestURI().replace(httpServletRequest.getContextPath() + "/", "");
        if (urlAction.contains("resources")) {
            indicatorToProceed = true;
        }
        if (!indicatorToProceed) {
            if (urlAction.equalsIgnoreCase("login.action") || urlAction.equalsIgnoreCase("loggedout.action") || urlAction.equalsIgnoreCase("loggedin.action")) {
                indicatorToProceed = true;
            } else {
                String sessionContextKey = requestResponseHolder.getAttributeFromSession(PMSSessionManager.PMS_APPLICATION_SESSION, String.class);
                User currentUser = null;
                if (pmsApplicationBuilder.isUserSessionActive(sessionContextKey)) {
                    User userFromSessionMap = pmsApplicationBuilder.getUserFromUserSessionMap(sessionContextKey);
                    User userFromCurrentSession = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
                    if (userFromSessionMap.equals(userFromCurrentSession)) {
                        currentUser = userFromCurrentSession;
                    }
                } else {
                    indicatorToShowSessionTimedOut = true;
                }
                if (currentUser != null && !indicatorToShowSessionTimedOut) {
                    switch (currentUser.getSystemRole()) {
                        case SystemRoles.ADMIN:
                            indicatorToProceed = checkURLIsAllowedForCurrentUser(urlAction, adminLinks);
                            break;
                        case SystemRoles.PARISH_ADMIN:
                            indicatorToProceed = checkURLIsAllowedForCurrentUser(urlAction, parishAdminLinks);
                            break;
                        case SystemRoles.MASS_CENTER_ADMIN:
                            indicatorToProceed = checkURLIsAllowedForCurrentUser(urlAction, massCenterAdminLinks);
                            break;
                        case SystemRoles.PRAYER_UNIT_ADMIN:
                            indicatorToProceed = checkURLIsAllowedForCurrentUser(urlAction, prayerUnitAdminLinks);
                            break;
                        case SystemRoles.FAMILY_ADMIN:
                        case SystemRoles.FAMILY_USER:
                            indicatorToProceed = checkURLIsAllowedForCurrentUser(urlAction, familyHeadLinks);
                            break;
                    }
                }
            }
            if (!indicatorToProceed && !indicatorToShowSessionTimedOut) {
                requestResponseHolder.setAttributeToSession("showURLAccessDenied", new String("This URL cannot be accessed by this User Role"));
                httpServletResponse.sendRedirect("login.action");
                httpServletResponse.flushBuffer();
            } else if (!indicatorToProceed && indicatorToShowSessionTimedOut) {
                requestResponseHolder.setAttributeToSession("showURLAccessDenied", new String("Your session is timed out. Please login here to continue your software"));
                httpServletResponse.sendRedirect("login.action");
                httpServletResponse.flushBuffer();
            }
        }
        return indicatorToProceed;
    }

    private boolean checkURLIsAllowedForCurrentUser(String urlAction, List<String> urlList) {
        return urlList.contains(urlAction);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // No-op in this scenario
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //No-op in this scenario
    }
}
