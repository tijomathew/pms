package org.pms.daos;

import org.pms.models.User;

/**
 * LoginDao description
 * User: tijo
 */
public interface LoginDao {

    public User getUserByUserEmail(String loginUserEmail);
}
