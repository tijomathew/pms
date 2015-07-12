package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.PriestDao;
import org.pms.models.Priest;
import org.pms.models.PriestDesignation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Priest Dao contract.
 * User: tijo
 */

@Repository
public class PriestDaoImpl extends GenericDaoImpl<Priest> implements PriestDao {

    public PriestDaoImpl() {
        setType(Priest.class);
    }

    @Override
    public boolean addPriestDM(Priest priest) {
        createAndSave(priest);
        return true;
    }

    @Override
    public List<Priest> getAllPriest() {
        return readAllInstances();
    }

    @Override
    public Priest getPriestForIDDM(Long id) {
        return (Priest) getDb(false).createCriteria(Priest.class, "priest").add(Restrictions.eq("priest.id", id)).uniqueResult();
    }

    @Override
    public Long getTotalCountOfPriestDM() {
        return (Long) getDb(false).createCriteria(Priest.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getHighestAutoIDDM() {
        return (Long) getDb(false).createCriteria(Priest.class, "priest").setProjection(Projections.max("id")).uniqueResult();
    }

    @Override
    public List<Long> getAllPriestsIDsDM() {
        return getDb(false).createCriteria(Priest.class, "priest").setProjection(Projections.property("id")).list();
    }

    @Override
    public boolean addPriestDesignation(PriestDesignation priestDesignation) {
        getDb(false).save(priestDesignation);
        return true;
    }
}
