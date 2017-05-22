package org.pms.family;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.common.dao.GenericDaoImpl;
import org.pms.domain.Family;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Family Dao contract.
 * User: tijo
 */

@Repository
public class FamilyDaoImpl extends GenericDaoImpl<Family> implements FamilyDao {

    public FamilyDaoImpl() {
        setType(Family.class);
    }

    @Override
    public boolean addFamilyDM(Family family) {
        createAndSave(family);
        return true;
    }

    @Override
    public List<Family> getAllFamilies() {
        return readAllInstances();
    }

    @Override
    public Family getFamilyForID(Long id) {
        return (Family) getDb(false).createCriteria(Family.class, "family").add(Restrictions.eq("family.id", id)).uniqueResult();
    }

    @Override
    public Long getFamilyCountInSystem() {
        return (Long) getDb(false).createCriteria(Family.class, "family").setProjection(Projections.max("family.familyNo")).uniqueResult();
    }

    @Override
    public List<Family> getAllFamilyForParishID(Long parishId) {
        return getDb(false).createCriteria(Family.class, "family").createAlias("family.familyPrayerUnit", "familyPrayerUnit").createAlias("familyPrayerUnit.mappedParish", "familyParish").add(Restrictions.eq("familyParish.id", parishId)).list();
    }

    @Override
    public List<Family> getAllFamilyForPrayerUnitID(Long prayerUnitId) {
        return getDb(false).createCriteria(Family.class, "family").createAlias("family.familyPrayerUnit", "familyprayerunit").add(Restrictions.eq("familyprayerunit.id", prayerUnitId)).list();
    }

    @Override
    public List<Family> getFamilyForFamilyID(Long familyId) {
        return getDb(false).createCriteria(Family.class, "family").add(Restrictions.eq("family.id", familyId)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Long> getAllFamiliesIDForParishId(Long parishId) {
        return getDb(false).createCriteria(Family.class, "family").createAlias("family.familyPrayerUnit", "familyPrayerUnit").createAlias("familyPrayerUnit.mappedParish", "familyParish").setProjection(Projections.distinct(Projections.property("family.id"))).add(Restrictions.eq("familyParish.id", parishId)).list();
    }

    @Override
    public Boolean updateFamily(Family family) {
        updateInstance(family);
        return true;
    }
}
