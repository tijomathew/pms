package org.pms.daos;

import org.pms.models.UserSessionLogger;

/**
 * Created by tijo on 22/9/15.
 */
public interface UserSessionLoggerDao {

    Boolean addUserSessionLogger(UserSessionLogger userSessionLogger);

    Boolean updateUserSessionLogger(UserSessionLogger userSessionLogger);
}
