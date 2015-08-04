package org.pms.services;

import org.pms.models.PrayerUnit;
import org.pms.models.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * This interface is the contract for the PrayerUnit Service.
 * User: tijo
 */
public interface PrayerUnitService {

    boolean addPrayerUnitSM(PrayerUnit ward);

    List<PrayerUnit> getAllPrayerUnits();

    List<PrayerUnit> getAllPrayerUnitsForMassCenterID(Long massCenterID);

    PrayerUnit getPrayerUnitForIDSM(Long id);

    Long getPrayerUnitCount();

    void updatePrayerUnit(PrayerUnit prayerUnit);

    Long getPrayerUnitCountUnderParish(Long parishId);

    PrayerUnit createPrayerUnitFormBackObject(Model modelMap);

    List<Long> getAllPrayerUnitIdsForMassCenterIds(List<Long> massCenterIds);

    List<PrayerUnit> getAllPrayerUnitsForUserRole(User currentUser);

}
