package org.pms.family;

import org.pms.domain.Family;
import org.pms.domain.User;

import java.util.List;

/**
 * This interface is the contract for the Family Service.
 * User: tijo
 */
public interface FamilyService {

    Boolean addFamilySM(Family family);

    List<Family> getAllFamilySM();

    Family getFamilyForID(Long id);

    Long getFamilyCountInSystem();

    List<Family> getAllFamilyForParishId(Long parishId);

    List<Family> getAllFamilyForPrayerUnitID(Long prayerUnitId);

    List<Family> getFamilyForFamilyID(Long familyId);

    List<Long> getAllFamiliesIDForParishId(Long parishId);

    List<Family> getAllFamiliesForUserRole(User currentUser);

    Boolean updateFamily(Family family);

    void setFamilyNumber(Family family);

}
