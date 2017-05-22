package org.pms.user;

import org.pms.common.dao.GenericDaoImpl;
import org.pms.domain.UserSessionLogger;
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
