package org.pms.user;

import org.pms.domain.UserSessionBasedURLLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tijo on 22/9/15.
 */

@Service
@Transactional
public class UserSessionBasedURLLoggerServiceImpl implements UserSessionBasedURLLoggerService {

    @Autowired
    UserSessionBasedURLLoggerDao userSessionBasedURLLoggerDao;

    @Override
    public Boolean addUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger) {
        userSessionBasedURLLoggerDao.addUserSessionBasedURLLog(userSessionBasedURLLogger);
        return true;
    }

    @Override
    public Boolean updateUserSessionBasedURLLog(UserSessionBasedURLLogger userSessionBasedURLLogger) {
        userSessionBasedURLLoggerDao.updateUserSessionBasedURLLog(userSessionBasedURLLogger);
        return true;
    }
}
