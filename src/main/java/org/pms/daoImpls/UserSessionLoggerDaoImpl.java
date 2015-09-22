package org.pms.daoImpls;

import org.pms.daos.UserSessionLoggerDao;
import org.pms.models.UserSessionLogger;
import org.springframework.stereotype.Repository;

/**
 * Created by tijo on 22/9/15.
 */

@Repository
public class UserSessionLoggerDaoImpl extends GenericDaoImpl<UserSessionLogger> implements UserSessionLoggerDao {

    public UserSessionLoggerDaoImpl() {
        setType(UserSessionLogger.class);
    }

    @Override
    public Boolean addUserSessionLogger(UserSessionLogger userSessionLogger) {
        createAndSave(userSessionLogger);
        return true;
    }

    @Override
    public Boolean updateUserSessionLogger(UserSessionLogger userSessionLogger) {
        updateInstance(userSessionLogger);
        return true;
    }
}
