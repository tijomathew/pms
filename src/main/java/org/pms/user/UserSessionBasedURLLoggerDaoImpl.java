package org.pms.user;

import org.pms.common.dao.GenericDaoImpl;
import org.pms.domain.UserSessionBasedURLLogger;
import org.springframework.stereotype.Repository;

/**
 * Created by tijo on 22/9/15.
 */

@Repository
public class UserSessionBasedURLLoggerDaoImpl extends GenericDaoImpl<UserSessionBasedURLLogger> implements UserSessionBasedURLLoggerDao {

    public UserSessionBasedURLLoggerDaoImpl() {
        setType(UserSessionBasedURLLogger.class);
    }

    @Override
    public Boolean addUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger) {
        createAndSave(userSessionBasedURLLogger);
        return true;
    }

    @Override
    public Boolean updateUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger) {
        updateInstance(userSessionBasedURLLogger);
        return true;
    }
}
