package org.pms.sessionmanager;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.chrono.ISOChronology;
import org.pms.applicationbuilder.PMSApplicationBuilder;
import org.pms.applicationbuilder.PMSApplicationBuilderImpl;
import org.pms.models.UserSessionBasedURLLogger;
import org.pms.models.UserSessionLogger;
import org.pms.serviceImpls.UserSessionBasedURLLoggerServiceImpl;
import org.pms.serviceImpls.UserSessionLoggerServiceImpl;
import org.pms.services.UserSessionBasedURLLoggerService;
import org.pms.services.UserSessionLoggerService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * User: tijo.
 */
public class PMSSessionManager implements HttpSessionListener {

    public static final String keySpace = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ123456789";
    public static final String PMS_APPLICATION_SESSION = "pmsContextKey";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        WebApplicationContext springContext = getSpringWebAppContext(se);
        if (springContext != null) {
            String sessionContextKey = RandomStringUtils.random(20, keySpace);
            synchronized (sessionContextKey) {
                se.getSession().setAttribute(PMS_APPLICATION_SESSION, sessionContextKey);
            }
            System.out.println("Registered PMS application context session key: {}" + sessionContextKey);

        } else {
            System.out.println("Unable to register a PMS application context session key!");
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        WebApplicationContext springContext = getSpringWebAppContext(se);
        if (springContext != null) {
            PMSApplicationBuilder pmsApplicationBuilder = springContext.getBean(PMSApplicationBuilderImpl.class);
            if (pmsApplicationBuilder != null) {
                if (se.getSession().getAttribute("userSessionLog") != null && se.getSession().getAttribute("userSessionBasedURLLog") != null) {
                    ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(se.getSession().getServletContext());

                    UserSessionLogger userSessionLogger = (UserSessionLogger) se.getSession().getAttribute("userSessionLog");
                    userSessionLogger.setLogoutTime(new DateTime().getMillis());
                    userSessionLogger.setRemark("Session Expired");
                    UserSessionLoggerService userSessionLoggerService = (UserSessionLoggerService) applicationContext.getBean("userSessionLoggerServiceImpl");
                    userSessionLoggerService.updateUserSessionLogger(userSessionLogger);

                    UserSessionBasedURLLogger userSessionBasedURLLogger = (UserSessionBasedURLLogger) se.getSession().getAttribute("userSessionBasedURLLog");
                    userSessionBasedURLLogger.setUrlVisitEndTime(new DateTime().getMillis());

                    UserSessionBasedURLLoggerService userSessionBasedURLLoggerService = (UserSessionBasedURLLoggerService) applicationContext.getBean("userSessionBasedURLLoggerServiceImpl");
                    userSessionBasedURLLoggerService.updateUserSessionBasedURLLog(userSessionBasedURLLogger);
                }
                String sessionContextKey = (String) se.getSession().getAttribute(PMS_APPLICATION_SESSION);
                if (StringUtils.isNotBlank(sessionContextKey)) {
                    try {
                        if (pmsApplicationBuilder.isUserSessionActive(sessionContextKey)) {
                            pmsApplicationBuilder.destroyUserSessionFromSessionMap(sessionContextKey);
                            System.out.println("session context key is destroyed!!..");
                        }
                    } catch (Exception e) {
                        System.out.println("Logger has to be created to log the errors in entire system!!..");
                    }
                }
            }
        }
    }

    private final WebApplicationContext getSpringWebAppContext(final HttpSessionEvent se) {
        WebApplicationContext springContext = null;
        try {
            springContext = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(se.getSession()
                            .getServletContext());
        } catch (IllegalStateException e) {
            System.out.println("Spring's web application context(bean factory named as applicationContext.xml) required for accessing PMS application is not available at this time!");
        }
        return springContext;
    }

    private DateTime getDateTime(long dateInMillis) {
        Chronology calendar = ISOChronology.getInstance();
        return new DateTime(dateInMillis, calendar);
    }

    private Double getTimeInHoursAndMinutes(DateTime fromDate, DateTime toDate) {
        Duration duration = new Duration(fromDate, toDate);
        Long totalMinutes = duration.getStandardMinutes();
        return Double.valueOf((double) totalMinutes / 60);
    }
}
