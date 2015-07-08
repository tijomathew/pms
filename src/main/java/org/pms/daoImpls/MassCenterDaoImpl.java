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
public class MassCenterDaoImpl extends GenericDaoImpl<MassCenter> implements MassCenterDao {

    public MassCenterDaoImpl() {
        setType(MassCenter.class);
    }

    @Override
    public boolean addMassCenterDM(MassCenter massCenter) {
        createAndSave(massCenter);
        return true;
    }

    //TODO remove all parish related dao operations from masscenter dao.
    @Override
    public List<Parish> getAllParishDM() {
        return getDb(false).createCriteria(Parish.class).list();
    }

    @Override
    public List<MassCenter> getAllMassCenters() {
        return readAllInstances();
    }

    @Override
    public MassCenter getMassCenterForID(Long id) {
        return (MassCenter) getDb(false).createCriteria(MassCenter.class, "massCenter").add(Restrictions.eq("massCenter.id", id)).uniqueResult();
    }

    @Override
    public List<MassCenter> getMassCenterForParishID(Long parishAutoID) {
        return getDb(false).createCriteria(MassCenter.class, "massCenter").add(Restrictions.eq("massCenter.mappedParish.id", parishAutoID)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Long getMassCenterCountForParish(Long parishId) {
        return (Long) getDb(false).createCriteria(MassCenter.class, "massCenter").setProjection(Projections.rowCount()).add(Restrictions.eq("massCenter.mappedParish.id", parishId)).uniqueResult();
    }

    @Override
    public void updateMassCenter(MassCenter massCenter) {
        updateInstance(massCenter);
    }

    @Override
    public MassCenter getMassCenterByMassCenterID(String massCenterID) {
        return (MassCenter) getDb(false).createCriteria(MassCenter.class, "massCenter").add(Restrictions.eq("massCenter.massCenterID", massCenterID)).uniqueResult();
    }

    @Override
    public Long getAllMassCenterCount() {
        return (Long) getDb(false).createCriteria(MassCenter.class, "massCenter").setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<Long> getAllMassCenterIdsForParish(Long parishId) {
        return getDb(false).createCriteria(MassCenter.class, "massCenter").setProjection(Projections.property("id")).add(Restrictions.eq("massCenter.mappedParish.id", parishId)).list();
    }
}
