package org.pms.daoImpls;

import org.pms.daos.UserSessionBasedURLLoggerDao;
import org.pms.models.UserSessionBasedURLLogger;
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
