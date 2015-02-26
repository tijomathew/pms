package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.PriestDao;
import org.pms.models.Priest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Priest Dao contract.
 * User: tijo
 */

@Repository
public class PriestDaoImpl implements PriestDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addPriestDM(Priest priest) {
        sessionFactory.getCurrentSession().save(priest);
        return true;
    }

    @Override
    public List<Priest> getAllPriest() {
        return sessionFactory.getCurrentSession().createCriteria(Priest.class).list();
    }

    @Override
    public Priest getPriestForIDDM(Long id) {
        return (Priest) sessionFactory.getCurrentSession().createCriteria(Priest.class, "priest").add(Restrictions.eq("priest.id", id)).uniqueResult();
    }

    @Override
    public Integer getTotalCountOfPriestDM() {
        return (Integer) sessionFactory.getCurrentSession().createCriteria(Priest.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getHighestAutoIDDM() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(Priest.class, "priest").setProjection(Projections.max("id")).uniqueResult();
    }

    @Override
    public List<Long> getAllPriestsIDsDM() {
        return sessionFactory.getCurrentSession().createCriteria(Priest.class, "priest").setProjection(Projections.property("id")).list();
    }
}
