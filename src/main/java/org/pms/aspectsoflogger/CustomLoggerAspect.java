package org.pms.aspectsoflogger;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.chrono.ISOChronology;
import org.pms.applicationbuilder.PMSApplicationBuilder;
import org.pms.enums.SystemRole;
import org.pms.helpers.PMSRequestResponseHolder;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.User;
import org.pms.models.UserSessionBasedURLLogger;
import org.pms.models.UserSessionLogger;
import org.pms.services.UserSessionBasedURLLoggerService;
import org.pms.services.UserSessionLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import java.io.File;

/**
 * Created by tijo on 21/9/15.
 */

@Aspect
public class CustomLoggerAspect {

    @Autowired
    RequestResponseHolder requestResponseHolder;

    @Autowired
    PMSApplicationBuilder pmsApplicationBuilder;

    @Autowired
    UserSessionLoggerService userSessionLoggerService;

    @Autowired
    UserSessionBasedURLLoggerService userSessionBasedURLLoggerService;

    @AfterReturning("execution(* org.pms.controllers.LoginController.verifyUser(..))")
    public void getAdviceForLogin() {
        if (requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class) != null) {
            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
            Resource resource = pmsApplicationBuilder.getResource("classpath:GeoLiteCity.dat");
            try {
                File geoGraphicFile = new File(resource.getFile().getAbsolutePath());
                LookupService lookup = new LookupService(geoGraphicFile, LookupService.GEOIP_MEMORY_CACHE);
                Location locationServices = lookup.getLocation(requestResponseHolder.getCurrentRequest().getRemoteAddr());

                UserSessionLogger userSessionLogger = new UserSessionLogger();
                userSessionLogger.setAccessedIP(requestResponseHolder.getCurrentRequest().getHeader("X-Forwarded-For"));
                userSessionLogger.setUserAgent(requestResponseHolder.getCurrentRequest().getHeader("User-Agent"));
                userSessionLogger.setLoginTime(new DateTime(ISOChronology.getInstance(DateTimeZone.UTC)).getMillis());
                userSessionLogger.setSessionLogMappedUser(currentUser);
                if (locationServices != null) {
                    userSessionLogger.setCity(locationServices.city);
                    userSessionLogger.setCountryCode(locationServices.countryCode);
                    userSessionLogger.setCountryName(locationServices.countryName);
                    userSessionLogger.setLatitude(String.valueOf(locationServices.latitude));
                    userSessionLogger.setLongitude(String.valueOf(locationServices.longitude));
                    userSessionLogger.setPostalCode(locationServices.postalCode);
                    userSessionLogger.setRegion(locationServices.region);
                    userSessionLogger.setRegionName(regionName.regionNameByCode(locationServices.countryCode, locationServices.region));
                }

                userSessionLoggerService.addUserSessionLogger(userSessionLogger);
                requestResponseHolder.setAttributeToSession("userSessionLog", userSessionLogger);
            } catch (Exception e) {
                //TODO please add a logger here.
                System.out.println("Error from login advice aspect" + e);
            }
        }
    }

    @Before("execution(* org.pms.controllers.LogoutController.loginPageDisplay(..))")
    public void getAdviceForLogout() {
        if (requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class) != null) {
            UserSessionLogger userSessionLogger = requestResponseHolder.getAttributeFromSession("userSessionLog", UserSessionLogger.class);
            userSessionLogger.setLogoutTime(new DateTime(ISOChronology.getInstance(DateTimeZone.UTC)).getMillis());
            userSessionLogger.setRemark("Logout");
            userSessionLoggerService.updateUserSessionLogger(userSessionLogger);
            requestResponseHolder.removeAttributeFromSession("userSessionLog");
            requestResponseHolder.removeAttributeFromSession("userSessionBasedURLLog");
        }
    }

    @Before("execution(* view*(..))")
    public void getAdviceForAllViewURLS() {
        if (requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class) != null) {
            UserSessionLogger userSessionLogger = requestResponseHolder.getAttributeFromSession("userSessionLog", UserSessionLogger.class);
            UserSessionBasedURLLogger userSessionBasedURLLogger = null;

            if (requestResponseHolder.getAttributeFromSession("userSessionBasedURLLog", UserSessionBasedURLLogger.class) != null) {
                userSessionBasedURLLogger = requestResponseHolder.getAttributeFromSession("userSessionBasedURLLog", UserSessionBasedURLLogger.class);
            }

            if (userSessionBasedURLLogger == null) {
                userSessionBasedURLLogger = getAndSaveNewInstanceForUserSessionBasedURLLogger(userSessionLogger);
            } else {
                if (!userSessionBasedURLLogger.getVisitedURL().equals(requestResponseHolder.getCurrentRequest().getRequestURI())) {
                    userSessionBasedURLLogger.setUrlVisitEndTime(new DateTime(ISOChronology.getInstance(DateTimeZone.UTC)).getMillis());
                    userSessionBasedURLLoggerService.updateUserSessionBasedURLLog(userSessionBasedURLLogger);

                    userSessionBasedURLLogger = getAndSaveNewInstanceForUserSessionBasedURLLogger(userSessionLogger);
                }
            }

            requestResponseHolder.setAttributeToSession("userSessionBasedURLLog", userSessionBasedURLLogger);


        }
    }

    private DateTime getDateTime(long dateInMillis) {
        Chronology calendar = ISOChronology.getInstance(DateTimeZone.UTC);
        return new DateTime(dateInMillis, calendar);
    }

    private Double getTimeInHoursAndMinutes(DateTime fromDate, DateTime toDate) {
        Duration duration = new Duration(fromDate, toDate);
        Long totalMinutes = duration.getStandardMinutes();
        return Double.valueOf((double) totalMinutes / 60);
    }

    private UserSessionBasedURLLogger getAndSaveNewInstanceForUserSessionBasedURLLogger(UserSessionLogger userSessionLogger) {
        UserSessionBasedURLLogger newInstance = new UserSessionBasedURLLogger();
        newInstance.setUrlVisitInitTime(new DateTime(ISOChronology.getInstance(DateTimeZone.UTC)).getMillis());
        newInstance.setVisitedURL(requestResponseHolder.getCurrentRequest().getRequestURI());
        newInstance.setUserSessionLogger(userSessionLogger);
        userSessionBasedURLLoggerService.addUserSessionBasedURLLog(newInstance);
        return newInstance;
    }
}

