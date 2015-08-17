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
public class ParishDaoImpl extends GenericDaoImpl<Parish> implements ParishDao {

    public ParishDaoImpl() {
        setType(Parish.class);
    }

    @Override
    public Boolean addOrUpdateParish(Parish parish) {
        createAndSave(parish);
        return true;
    }

    //TODO remove priest related dao operations from parish DAO.
    @Override
    public Priest getPriestDM(String priestID) {
        return (Priest) getDb(false).createCriteria(Priest.class).add(Restrictions.eq("priestID", priestID)).uniqueResult();
    }

    @Override
    public boolean updatePriestForParish(Priest priest) {
        getDb(false).saveOrUpdate(priest);
        return true;
    }

    @Override
    public List<Parish> getAllParish() {
        return getDb(false).createCriteria(Parish.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Parish getParishForIDDM(Long id) {
        return (Parish) getDb(false).createCriteria(Parish.class, "parish").add(Restrictions.eq("parish.id", id)).uniqueResult();
    }

    @Override
    public Long getParishCount() {
        return (Long) getDb(false).createCriteria(Parish.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void updateParish(Parish parish) {
        getDb(false).saveOrUpdate(parish);
    }

    @Override
    public Parish getParishByParishID(String parishID) {
        return (Parish) getDb(false).createCriteria(Parish.class, "parish").add(Restrictions.eq("parish.parishID", parishID)).uniqueResult();
    }

}
