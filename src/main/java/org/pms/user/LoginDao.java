package org.pms.user;

import org.pms.domain.User;

/**
 * LoginDao description
 * User: tijo
 */
public interface LoginDao {

    public User getUserByUserEmail(String loginUserEmail);
}
