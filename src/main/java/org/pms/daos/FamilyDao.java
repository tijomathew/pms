package org.pms.daos;

import org.pms.models.Family;
import org.pms.models.PrayerUnit;

import java.util.List;

/**
 * This interface is the contract for the Family Dao.
 * User: tijo
 */
public interface FamilyDao {

    public boolean addFamilyDM(Family family);

    public List<PrayerUnit> getAllWards();

    public List<Family> getAllFamilies();

    public Family getFamilyForID(Long id);
}
