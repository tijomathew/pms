package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.ParishDao;
import org.pms.models.Parish;
import org.pms.models.Priest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Parish Dao contract.
 * User: tijo
 */

@Repository
public class ParishDaoImpl implements ParishDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addParishDM(Parish parish) {
        sessionFactory.getCurrentSession().save(parish);
        return true;
    }

    @Override
    public Priest getPriestDM(String priestID) {
        return (Priest) sessionFactory.getCurrentSession().createCriteria(Priest.class).add(Restrictions.eq("priestID", priestID)).uniqueResult();
    }

    @Override
    public boolean updatePriestForParish(Priest priest) {
        sessionFactory.getCurrentSession().saveOrUpdate(priest);
        return true;
    }

    @Override
    public List<Parish> getAllParish() {
        return sessionFactory.getCurrentSession().createCriteria(Parish.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Parish getParishForIDDM(Long id) {
        return (Parish) sessionFactory.getCurrentSession().createCriteria(Parish.class, "parish").add(Restrictions.eq("parish.id", id)).uniqueResult();
    }

    @Override
    public Long getParishCount() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(Parish.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void updateParish(Parish parish) {
        sessionFactory.getCurrentSession().saveOrUpdate(parish);
    }

    @Override
    public Parish getParishByParishID(String parishID) {
        return (Parish) sessionFactory.getCurrentSession().createCriteria(Parish.class, "parish").add(Restrictions.eq("parish.parishID", parishID)).uniqueResult();
    }
}
