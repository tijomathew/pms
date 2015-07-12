package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.UserDao;
import org.pms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao description
 * User: tijo
 */

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        setType(User.class);
    }

    @Override
    public boolean addUserDM(User user) {
        createAndSave(user);
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        return (User) getDb(true).createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public List<User> getAllUsers() {
        return readAllInstances();
    }

    @Override
    public Long getAllUserCount() {
        return (Long) getDb(true).createCriteria(User.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<User> getAllUsersForParishIds(List<Long> parishIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.parishId", parishIds)).list();
    }

    @Override
    public List<User> getAllUsersForMassCenterIds(List<Long> massCenterIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.massCenterId", massCenterIds)).list();
    }

    @Override
    public List<User> getAllUsersForPrayerUnitIds(List<Long> prayerUnitIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.prayerUnitId", prayerUnitIds)).list();
    }

    @Override
    public List<User> getAllUsersForFamilyIds(List<Long> familyIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.familyId", familyIds)).list();
    }
}
