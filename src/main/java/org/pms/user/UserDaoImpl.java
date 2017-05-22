package org.pms.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.common.dao.GenericDaoImpl;
import org.pms.domain.User;
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
    public List<User> getAllUsersForParishIds(List<Long> parishIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.usersOfParish.id", parishIds)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<User> getAllUsersForPrayerUnitIds(List<Long> prayerUnitIds) {
        return getDb(true).createCriteria(User.class, "user").add(Restrictions.in("user.usersOfPrayerUnits.id", prayerUnitIds)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<User> getAllUsersForCurrentUser(User currentUser, Boolean isLoggedIn) {
        Criteria criteria = getDb(true).createCriteria(User.class, "user");

        if (currentUser.getSystemRole().equals(SystemRole.PARISH_ADMIN)) {
            criteria.add(Restrictions.eq("user.usersOfParish.id", currentUser.getParishId()));
        } else if (currentUser.getSystemRole().equals(SystemRole.PRAYER_UNIT_ADMIN)) {
            criteria.add(Restrictions.eq("user.usersOfPrayerUnits.id", currentUser.getPrayerUnitId()));
        }

        criteria.add(Restrictions.eq("user.alreadyLoggedIn", isLoggedIn));

        return criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
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
