package org.pms.serviceImpls;

import org.pms.daos.UserSessionLoggerDao;
import org.pms.models.UserSessionLogger;
import org.pms.services.UserSessionLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tijo on 22/9/15.
 */

@Service
@Transactional
public class UserSessionLoggerServiceImpl implements UserSessionLoggerService {

    @Autowired
    UserSessionLoggerDao userSessionLoggerDao;

    @Override
    public Boolean addUserSessionLogger(UserSessionLogger userSessionLogger) {
        userSessionLoggerDao.addUserSessionLogger(userSessionLogger);
        return true;
    }

    @Override
    public Boolean updateUserSessionLogger(UserSessionLogger userSessionLogger) {
        userSessionLoggerDao.updateUserSessionLogger(userSessionLogger);
        return true;
    }
}
