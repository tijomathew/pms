package org.pms.services;

import org.pms.models.UserSessionBasedURLLogger;

/**
 * Created by tijo on 22/9/15.
 */
public interface UserSessionBasedURLLoggerService {

    Boolean addUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger);

    Boolean updateUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger);
}
