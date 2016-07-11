package org.pms.daos;

import org.pms.models.PrayerUnit;

import java.util.List;

/**
 * This interface is the contract for the PrayerUnit Dao.
 * User: tijo
 */
public interface PrayerUnitDao {

    boolean addPrayerUnitDM(PrayerUnit prayerUnit);

    List<PrayerUnit> getAllPrayerUnit();

    List<PrayerUnit> getPrayerUnitsForMassCentreIDDM(Long massCentreID);

    PrayerUnit getPrayerUnitForIDDM(Long id);

    Long getPrayerUnitCount();

    void updatePrayerUnit(PrayerUnit prayerUnit);

    Long getPrayerUnitCountUnderMassCentre(Long massCentreId);

    List<Long> getAllPrayerUnitIdsForMassCentreIds(List<Long> massCentreIds);
}
