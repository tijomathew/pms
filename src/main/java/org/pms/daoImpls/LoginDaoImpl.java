package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.LoginDao;
import org.pms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
