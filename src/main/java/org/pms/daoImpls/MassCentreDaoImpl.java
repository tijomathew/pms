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
    public Boolean addMassCentre(MassCentre massCentre) {
        createAndSave(massCentre);
        return true;
    }

    //TODO remove all parish related dao operations from masscentre dao.
    @Override
    public List<Parish> getAllParishDM() {
        return getDb(false).createCriteria(Parish.class).list();
    }

    @Override
    public List<MassCentre> getAllMassCentres() {
        return readAllInstances();
    }

    @Override
    public MassCentre getMassCentreForID(Long id) {
        return (MassCentre) getDb(false).createCriteria(MassCentre.class, "massCentre").add(Restrictions.eq("massCentre.id", id)).uniqueResult();
    }

    @Override
    public List<MassCentre> getAllMassCentresForParishID(Long parishAutoID) {
        return getDb(false).createCriteria(MassCentre.class, "massCentre").add(Restrictions.eq("massCentre.mappedParish.id", parishAutoID)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Long getMassCentreCountForParish(Long parishId) {
        return (Long) getDb(false).createCriteria(MassCentre.class, "massCentre").setProjection(Projections.max("massCentre.massCentreNo")).add(Restrictions.eq("massCentre.mappedParish.id", parishId)).uniqueResult();
    }

    @Override
    public Boolean updateMassCentre(MassCentre massCentre) {
        updateInstance(massCentre);
        return true;
    }

    @Override
    public MassCentre getMassCentreByMassCentreID(String massCentreID) {
        return (MassCentre) getDb(false).createCriteria(MassCentre.class, "massCentre").add(Restrictions.eq("massCentre.massCentreID", massCentreID)).uniqueResult();
    }

    @Override
    public Long getAllMassCentreCount() {
        return (Long) getDb(false).createCriteria(MassCentre.class, "massCentre").setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<Long> getAllMassCentreIdsForParish(Long parishId) {
        return getDb(false).createCriteria(MassCentre.class, "massCentre").setProjection(Projections.property("id")).add(Restrictions.eq("massCentre.mappedParish.id", parishId)).list();
    }
}
