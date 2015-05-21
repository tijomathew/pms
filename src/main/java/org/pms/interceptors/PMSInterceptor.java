package org.pms.interceptors;

import org.pms.constants.SystemRoles;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.User;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    @Autowired(required = true)
    private PriestService priestService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

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
        String urlAction = httpServletRequest.getRequestURI().replace(httpServletRequest.getContextPath() + "/", "");
        if (urlAction.equalsIgnoreCase("login.action") || urlAction.equalsIgnoreCase("loggedout.action") || urlAction.equalsIgnoreCase("loggedin.action")) {
            indicatorToProceed = true;
        } else {
            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
            if (currentUser != null) {
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
                        indicatorToProceed = checkURLIsAllowedForCurrentUser(urlAction, familyHeadLinks);
                        break;
                }
            }
        }
        if (!indicatorToProceed) {
            requestResponseHolder.setAttributeToSession("showURLAccessDenied", new String("This URL cannot be accessed by this User Role"));
            httpServletResponse.sendRedirect("login.action");
            httpServletResponse.flushBuffer();
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
