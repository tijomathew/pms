package org.pms.family;

import org.pms.domain.Family;

import java.util.List;

/**
 * This interface is the contract for the Family Dao.
 * User: tijo
 */
public interface FamilyDao {

    boolean addFamilyDM(Family family);

    List<Family> getAllFamilies();

    Family getFamilyForID(Long id);

    Long getFamilyCountInSystem();

    List<Family> getAllFamilyForParishID(Long parishId);

    List<Family> getAllFamilyForPrayerUnitID(Long prayerUnitId);

    List<Family> getFamilyForFamilyID(Long familyId);

    List<Long> getAllFamiliesIDForParishId(Long parishId);

    Boolean updateFamily(Family family);
}