package org.pms.daos;

import org.pms.models.PrayerUnit;

import java.util.List;

/**
 * This interface is the contract for the Prayer Unit Dao.
 * User: tijo
 */
public interface PrayerUnitDao {

    boolean addPrayerUnitDM(PrayerUnit prayerUnit);

    List<PrayerUnit> getAllPrayerUnit();

    List<PrayerUnit> getPrayerUnitsForParishIDDM(Long massCentreID);

    PrayerUnit getPrayerUnitForIDDM(Long id);

    void updatePrayerUnit(PrayerUnit prayerUnit);

    Long getPrayerUnitCountUnderParish(Long massCentreId);
}
