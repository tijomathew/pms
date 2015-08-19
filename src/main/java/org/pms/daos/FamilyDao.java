package org.pms.daos;

import org.pms.models.Family;
import org.pms.models.PrayerUnit;

import java.util.List;

/**
 * This interface is the contract for the Family Dao.
 * User: tijo
 */
public interface FamilyDao {

    boolean addFamilyDM(Family family);

    List<Family> getAllFamilies();

    Family getFamilyForID(Long id);

    Long getFamilyTotalCount();

    Long getFamilyCountForParish(Long parishId);

    List<Family> getAllFamilyForParishID(Long parishId);

    List<Family> getAllFamilyForMassCentreID(Long massCentreId);

    List<Family> getAllFamilyForPrayerUnitID(Long prayerUnitId);

    List<Family> getFamilyForFamilyID(Long familyId);

    List<Long> getAllFamiliesIDForParishID(Long parishId);

    List<Long> getAllFamilyIdsForPrayerUnitId(List<Long> prayerUnitIds);

    Boolean updateFamily(Family family);
}
