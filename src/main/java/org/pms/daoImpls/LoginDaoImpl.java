package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.LoginDao;
import org.pms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * LoginDaoImpl description
 * User: tijo
 */
@Repository
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByUsernameDM(String loginUserName) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class, "user").add(Restrictions.eq("userName", loginUserName)).uniqueResult();
    }
}
