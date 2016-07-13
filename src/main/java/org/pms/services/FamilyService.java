package org.pms.services;

import org.pms.models.Family;
import org.pms.models.User;

import java.util.List;

/**
 * This interface is the contract for the Family Service.
 * User: tijo
 */
public interface FamilyService {

    boolean addFamilySM(Family family);

    List<Family> getAllFamilySM();

    Family getFamilyForID(Long id);

    Long getFamilyCountForParish(Long parishId);

    List<Family> getAllFamilyForParishId(Long massCentreId);

    List<Family> getAllFamilyForPrayerUnitID(Long prayerUnitId);

    List<Family> getFamilyForFamilyID(Long familyId);

    List<Long> getAllFamiliesIDForParishId(Long parishId);

    List<Family> getAllFamiliesForUserRole(User currentUser);

    Boolean updateFamily(Family family);

    void setFamilyNumber(Family family);

}
