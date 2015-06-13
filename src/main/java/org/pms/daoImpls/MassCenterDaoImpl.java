package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.MassCenterDao;
import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Mass Center Dao contract.
 * User: tijo
 */

@Repository
public class MassCenterDaoImpl implements MassCenterDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addMassCenterDM(MassCenter massCenter) {
        sessionFactory.getCurrentSession().save(massCenter);
        return true;
    }

    @Override
    public List<Parish> getAllParishDM() {
        return sessionFactory.getCurrentSession().createCriteria(Parish.class).list();
    }

    @Override
    public List<MassCenter> getAllMassCenters() {
        return sessionFactory.getCurrentSession().createCriteria(MassCenter.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public MassCenter getMassCenterForID(Long id) {
        return (MassCenter) sessionFactory.getCurrentSession().createCriteria(MassCenter.class, "massCenter").add(Restrictions.eq("massCenter.id", id)).uniqueResult();
    }

    @Override
    public List<MassCenter> getMassCenterForParishID(Long parishAutoID) {
        return sessionFactory.getCurrentSession().createCriteria(MassCenter.class, "massCenter").add(Restrictions.eq("massCenter.mappedParish.id", parishAutoID)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Long getMassCenterCountForParish(Long parishId) {
        return (Long) sessionFactory.getCurrentSession().createCriteria(MassCenter.class, "massCenter").setProjection(Projections.rowCount()).add(Restrictions.eq("massCenter.mappedParish.id", parishId)).uniqueResult();
    }

    @Override
    public void updateMassCenter(MassCenter massCenter) {
        sessionFactory.getCurrentSession().saveOrUpdate(massCenter);
    }

    @Override
    public MassCenter getMassCenterByMassCenterID(String massCenterID) {
        return (MassCenter) sessionFactory.getCurrentSession().createCriteria(MassCenter.class, "massCenter").add(Restrictions.eq("massCenter.massCenterID", massCenterID)).uniqueResult();
    }

    @Override
    public Long getAllMassCenterCount() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(MassCenter.class, "massCenter").setProjection(Projections.rowCount()).uniqueResult();
    }
}
