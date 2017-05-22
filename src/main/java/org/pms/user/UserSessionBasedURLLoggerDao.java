package org.pms.user;

import org.pms.domain.UserSessionBasedURLLogger;

/**
 * Created by tijo on 22/9/15.
 */
public interface UserSessionBasedURLLoggerDao {

    Boolean addUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger);

    Boolean updateUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger);
}
