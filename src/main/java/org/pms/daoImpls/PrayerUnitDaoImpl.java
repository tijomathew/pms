package org.pms.daoImpls;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.PrayerUnitDao;
import org.pms.models.PrayerUnit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the PrayerUnit Dao contract.
 * User: tijo
 */

@Repository
public class PrayerUnitDaoImpl extends GenericDaoImpl<PrayerUnit> implements PrayerUnitDao {

    public PrayerUnitDaoImpl() {
        setType(PrayerUnit.class);
    }

    @Override
    public boolean addPrayerUnitDM(PrayerUnit prayerUnit) {
        createAndSave(prayerUnit);
        return true;
    }

    @Override
    public List<PrayerUnit> getAllPrayerUnit() {
        return readAllInstances();
    }

    @Override
    public List<PrayerUnit> getPrayerUnitsForMassCenterIDDM(Long massCenterID) {
        return getDb(false).createCriteria(PrayerUnit.class, "prayerUnit").add(Restrictions.eq("mappedMassCenter.id", massCenterID)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public PrayerUnit getPrayerUnitForIDDM(Long id) {
        return (PrayerUnit) getDb(false).createCriteria(PrayerUnit.class, "prayerUnit").add(Restrictions.eq("prayerUnit.id", id)).uniqueResult();
    }

    @Override
    public Long getPrayerUnitCount() {
        return (Long) getDb(false).createCriteria(PrayerUnit.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void updatePrayerUnit(PrayerUnit prayerUnit) {
        updateInstance(prayerUnit);
    }

    @Override
    public Long getPrayerUnitCountUnderParish(Long parishId) {
        return (Long) getDb(false).createCriteria(PrayerUnit.class, "prayerUnit").createAlias("prayerUnit.mappedMassCenter","masscenterInst").createAlias("masscenterInst.mappedParish","parishInst").setProjection(Projections.rowCount()).add(Restrictions.eq("parishInst.id", parishId)).uniqueResult();
    }

    @Override
    public List<Long> getAllPrayerUnitIdsForMassCenterIds(List<Long> massCenterIds) {
        return getDb(false).createCriteria(PrayerUnit.class, "prayerUnit").setProjection(Projections.property("id")).add(Restrictions.in("prayerUnit.mappedMassCenter.id", massCenterIds)).list();
    }
}
