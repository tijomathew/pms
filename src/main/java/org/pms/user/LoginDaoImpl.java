package org.pms.user;

import org.hibernate.criterion.Restrictions;
import org.pms.common.dao.GenericDaoImpl;
import org.pms.domain.User;
import org.springframework.stereotype.Repository;

/**
 * LoginDaoImpl description
 * User: tijo
 */
@Repository
public class LoginDaoImpl extends GenericDaoImpl<User> implements LoginDao {

    public LoginDaoImpl() {
        setType(User.class);
    }

    @Override
    public User getUserByUserEmail(String loginUserEmail) {
        return (User) getDb(false).createCriteria(User.class, "user").add(Restrictions.eq("user.email", loginUserEmail)).uniqueResult();
    }

}
