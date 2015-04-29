package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.FamilyDao;
import org.pms.models.Family;
import org.pms.models.PrayerUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Family Dao contract.
 * User: tijo
 */

@Repository
public class FamilyDaoImpl implements FamilyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addFamilyDM(Family family) {
        sessionFactory.getCurrentSession().save(family);
        return true;
    }

    @Override
    public List<PrayerUnit> getAllWards() {
        return sessionFactory.getCurrentSession().createCriteria(PrayerUnit.class).list();
    }

    @Override
    public List<Family> getAllFamilies() {
        return sessionFactory.getCurrentSession().createCriteria(Family.class).list();
    }

    @Override
    public Family getFamilyForID(Long id) {
        return (Family) sessionFactory.getCurrentSession().createCriteria(Family.class, "family").add(Restrictions.eq("family.id", id)).uniqueResult();
    }

    @Override
    public Long getFamilyTotalCount() {
        return (Long)sessionFactory.getCurrentSession().createCriteria(Family.class,"family").setProjection(Projections.rowCount()).uniqueResult();
    }
}
