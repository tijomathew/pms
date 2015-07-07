package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.FamilyDao;
import org.pms.models.Family;
import org.pms.models.PrayerUnit;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Long getFamilyTotalCount() {
        return (Long) getDb(false).createCriteria(Family.class, "family").setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getFamilyCountForParish(Long parishId) {
        return (Long) getDb(false).createCriteria(Family.class, "family").setProjection(Projections.rowCount()).add(Restrictions.eq("family.familyParish.id", parishId)).uniqueResult();
    }

    @Override
    public List<Family> getAllFamilyForParishID(Long parishId) {
        return getDb(false).createCriteria(Family.class, "family").add(Restrictions.eq("family.familyParish.id", parishId)).list();
    }

    @Override
    public List<Family> getAllFamilyForMassCenterID(Long massCenterId) {
        return getDb(false).createCriteria(Family.class, "family").add(Restrictions.eq("family.familyMassCenter.id", massCenterId)).list();
    }

    @Override
    public List<Family> getAllFamilyForPrayerUnitID(Long prayerUnitId) {
        return getDb(false).createCriteria(Family.class, "family").add(Restrictions.eq("family.familyPrayerUnit.id", prayerUnitId)).list();
    }

    @Override
    public List<Family> getFamilyForFamilyID(Long familyId) {
        return getDb(false).createCriteria(Family.class, "family").add(Restrictions.eq("family.id", familyId)).list();
    }

    @Override
    public List<Long> getAllFamiliesIDForParishID(Long parishId) {
        return getDb(false).createCriteria(Family.class, "family").setProjection(Projections.property("id")).add(Restrictions.eq("family.familyParish.id", parishId)).list();
    }
}
