package org.pms.daoImpls;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.MassCentreDao;
import org.pms.models.MassCentre;
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

    @Override
    public List<MassCentre> getAllMassCentres() {
        return readAllInstances();
    }

    @Override
    public MassCentre getMassCentreForID(Long id) {
        return (MassCentre) getDb(false).createCriteria(MassCentre.class, "massCentre").add(Restrictions.eq("massCentre.id", id)).uniqueResult();
    }

    @Override
    public Long getMassCentreCount() {
        return (Long) getDb(false).createCriteria(MassCentre.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Boolean updateMassCentre(MassCentre massCentre) {
        updateInstance(massCentre);
        return true;
    }

}
