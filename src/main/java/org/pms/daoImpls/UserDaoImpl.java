package org.pms.daoImpls;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.UserDao;
import org.pms.models.User;
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
    public Boolean addUserDM(User user) {
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
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.usersOfParishes.id", parishIds)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<User> getAllUsersForMassCentreIds(List<Long> massCentreIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.usersOfMassCentres.id", massCentreIds)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<User> getAllUsersForPrayerUnitIds(List<Long> prayerUnitIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.usersOfPrayerUnits.id", prayerUnitIds)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<User> getAllUsersForFamilyIds(List<Long> familyIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.userOfFamily.id", familyIds)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Long verifyEmailIsPresent(String mailID) {
        return (Long) getDb(true).createCriteria(User.class, "user").setProjection(Projections.rowCount()).add(Restrictions.eq("email", mailID)).uniqueResult();
    }

    @Override
    public Boolean updateUser(User user) {
        updateInstance(user);
        return true;
    }
}
