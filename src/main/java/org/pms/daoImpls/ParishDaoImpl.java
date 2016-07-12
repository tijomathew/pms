package org.pms.daoImpls;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.ParishDao;
import org.pms.models.Parish;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Parish Dao contract.
 * User: tijo
 */

@Repository
public class ParishDaoImpl extends GenericDaoImpl<Parish> implements ParishDao {

    public ParishDaoImpl() {
        setType(Parish.class);
    }

    @Override
    public Boolean addParish(Parish parish) {
        createAndSave(parish);
        return true;
    }

    @Override
    public List<Parish> getAllParish() {
        return readAllInstances();
    }

    @Override
    public Parish getParishForID(Long id) {
        return (Parish) getDb(false).createCriteria(Parish.class, "parish").add(Restrictions.eq("parish.id", id)).uniqueResult();
    }

    @Override
    public Long getParishCount() {
        return (Long) getDb(false).createCriteria(Parish.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Boolean updateParish(Parish parish) {
        updateInstance(parish);
        return true;
    }

}
