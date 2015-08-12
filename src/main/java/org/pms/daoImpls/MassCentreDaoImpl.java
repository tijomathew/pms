package org.pms.daoImpls;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.MassCentreDao;
import org.pms.models.MassCentre;
import org.pms.models.Parish;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Mass Center Dao contract.
 * User: tijo
 */

@Repository
public class MassCentreDaoImpl extends GenericDaoImpl<MassCentre> implements MassCentreDao {

    public MassCentreDaoImpl() {
        setType(MassCentre.class);
    }

    @Override
    public boolean addMassCenterDM(MassCentre massCentre) {
        createAndSave(massCentre);
        return true;
    }

    //TODO remove all parish related dao operations from masscenter dao.
    @Override
    public List<Parish> getAllParishDM() {
        return getDb(false).createCriteria(Parish.class).list();
    }

    @Override
    public List<MassCentre> getAllMassCenters() {
        return readAllInstances();
    }

    @Override
    public MassCentre getMassCenterForID(Long id) {
        return (MassCentre) getDb(false).createCriteria(MassCentre.class, "massCentre").add(Restrictions.eq("massCentre.id", id)).uniqueResult();
    }

    @Override
    public List<MassCentre> getAllMassCentersForParishID(Long parishAutoID) {
        return getDb(false).createCriteria(MassCentre.class, "massCentre").add(Restrictions.eq("massCentre.mappedParish.id", parishAutoID)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Long getMassCenterCountForParish(Long parishId) {
        return (Long) getDb(false).createCriteria(MassCentre.class, "massCentre").setProjection(Projections.rowCount()).add(Restrictions.eq("massCentre.mappedParish.id", parishId)).uniqueResult();
    }

    @Override
    public void updateMassCenter(MassCentre massCentre) {
        updateInstance(massCentre);
    }

    @Override
    public MassCentre getMassCenterByMassCenterID(String massCenterID) {
        return (MassCentre) getDb(false).createCriteria(MassCentre.class, "massCentre").add(Restrictions.eq("massCentre.massCenterID", massCenterID)).uniqueResult();
    }

    @Override
    public Long getAllMassCenterCount() {
        return (Long) getDb(false).createCriteria(MassCentre.class, "massCentre").setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<Long> getAllMassCenterIdsForParish(Long parishId) {
        return getDb(false).createCriteria(MassCentre.class, "massCentre").setProjection(Projections.property("id")).add(Restrictions.eq("massCentre.mappedParish.id", parishId)).list();
    }
}
