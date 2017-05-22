package org.pms.prayerunit;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.common.dao.GenericDaoImpl;
import org.pms.domain.PrayerUnit;
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
    public List<PrayerUnit> getPrayerUnitsForParishIDDM(Long parishID) {
        return getDb(false).createCriteria(PrayerUnit.class, "prayerUnit").add(Restrictions.eq("mappedParish.id", parishID)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public PrayerUnit getPrayerUnitForIDDM(Long id) {
        return (PrayerUnit) getDb(false).createCriteria(PrayerUnit.class, "prayerUnit").add(Restrictions.eq("prayerUnit.id", id)).uniqueResult();
    }

    @Override
    public void updatePrayerUnit(PrayerUnit prayerUnit) {
        updateInstance(prayerUnit);
    }

    @Override
    public Long getPrayerUnitCountInSystem() {
        return (Long) getDb(false).createCriteria(PrayerUnit.class, "prayerUnit").setProjection(Projections.max("prayerUnit.prayerUnitNo")).uniqueResult();
    }
}
