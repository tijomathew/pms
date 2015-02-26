package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.UserDao;
import org.pms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * UserDao description
 * User: tijo
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addUserDM(User user) {
        sessionFactory.getCurrentSession().save(user);
        return true;
    }

    @Override
    public User getUserByUserName(String userName) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("userName", userName)).uniqueResult();
    }
}
