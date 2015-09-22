package org.pms.daos;

import org.pms.models.UserSessionBasedURLLogger;

/**
 * Created by tijo on 22/9/15.
 */
public interface UserSessionBasedURLLoggerDao {

    Boolean addUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger);

    Boolean updateUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger);
}
