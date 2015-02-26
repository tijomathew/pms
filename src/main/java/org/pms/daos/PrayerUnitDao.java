package org.pms.daos;

import org.pms.models.MassCenter;
import org.pms.models.PrayerUnit;

import java.util.List;

/**
 * This interface is the contract for the PrayerUnit Dao.
 * User: tijo
 */
public interface PrayerUnitDao {

    boolean addPrayerUnitDM(PrayerUnit prayerUnit);

    List<PrayerUnit> getAllPrayerUnit();

    List<PrayerUnit> getPrayerUnitsForMassCenterIDDM(Long massCenterID);

    PrayerUnit getPrayerUnitForIDDM(Long id);

    Long getPrayerUnitCount();

    void updatePrayerUnit(PrayerUnit prayerUnit);
}
