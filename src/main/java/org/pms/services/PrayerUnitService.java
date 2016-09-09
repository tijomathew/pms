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

    Boolean addPrayerUnitSM(PrayerUnit ward);

    List<PrayerUnit> getAllPrayerUnits();

    List<PrayerUnit> getAllPrayerUnitsForParishID(Long parishID);

    PrayerUnit getPrayerUnitForIDSM(Long id);

    void updatePrayerUnit(PrayerUnit prayerUnit);

    Long getPrayerUnitCountUnderParish(Long parishId);

    void createPrayerUnitFormBackObject(Model modelMap);

    List<PrayerUnit> getAllPrayerUnitsForUserRole(User currentUser);

    void setPrayerUnitNumber(PrayerUnit prayerUnit);

}
