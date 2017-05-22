package org.pms.prayerunit;

import org.pms.domain.PrayerUnit;

import java.util.List;

/**
 * This interface is the contract for the Prayer Unit Dao.
 * User: tijo
 */
public interface PrayerUnitDao {

    boolean addPrayerUnitDM(PrayerUnit prayerUnit);

    List<PrayerUnit> getAllPrayerUnit();

    List<PrayerUnit> getPrayerUnitsForParishIDDM(Long parishID);

    PrayerUnit getPrayerUnitForIDDM(Long id);

    void updatePrayerUnit(PrayerUnit prayerUnit);

    Long getPrayerUnitCountInSystem();
}
