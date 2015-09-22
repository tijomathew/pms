package org.pms.services;

import org.pms.models.UserSessionLogger;

/**
 * Created by tijo on 22/9/15.
 */
public interface UserSessionLoggerService {

    Boolean addUserSessionLogger(UserSessionLogger userSessionLogger);

    Boolean updateUserSessionLogger(UserSessionLogger userSessionLogger);
}
