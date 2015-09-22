package org.pms.serviceImpls;

import org.pms.daos.UserSessionBasedURLLoggerDao;
import org.pms.models.UserSessionBasedURLLogger;
import org.pms.services.UserSessionBasedURLLoggerService;
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
