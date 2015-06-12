package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.PrayerUnitDao;
import org.pms.models.PrayerUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the PrayerUnit Dao contract.
 * User: tijo
 */

@Repository
public class PrayerUnitDaoImpl implements PrayerUnitDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addPrayerUnitDM(PrayerUnit prayerUnit) {
        sessionFactory.getCurrentSession().save(prayerUnit);
        return true;
    }

    @Override
    public List<PrayerUnit> getAllPrayerUnit() {
        return sessionFactory.getCurrentSession().createCriteria(PrayerUnit.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<PrayerUnit> getPrayerUnitsForMassCenterIDDM(Long massCenterID) {
        return sessionFactory.getCurrentSession().createCriteria(PrayerUnit.class, "prayerUnit").add(Restrictions.eq("mappedMassCenter.id", massCenterID)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public PrayerUnit getPrayerUnitForIDDM(Long id) {
        return (PrayerUnit) sessionFactory.getCurrentSession().createCriteria(PrayerUnit.class, "prayerUnit").add(Restrictions.eq("prayerUnit.id", id)).uniqueResult();
    }

    @Override
    public Long getPrayerUnitCount() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(PrayerUnit.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void updatePrayerUnit(PrayerUnit prayerUnit) {
        sessionFactory.getCurrentSession().saveOrUpdate(prayerUnit);
    }

    @Override
    public Long getPrayerUnitCountForMassCenter(Long massCenterId) {
        return (Long) sessionFactory.getCurrentSession().createCriteria(PrayerUnit.class, "prayerUnit").setProjection(Projections.rowCount()).add(Restrictions.eq("prayerUnit.mappedMassCenter.id", massCenterId)).uniqueResult();
    }
}
