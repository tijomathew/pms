package org.pms.prayerunit;

import org.pms.domain.PrayerUnit;
import org.pms.domain.User;
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

    Long getPrayerUnitCountInSystem();

    void createPrayerUnitFormBackObject(Model modelMap);

    List<PrayerUnit> getAllPrayerUnitsForUserRole(User currentUser);

    void setPrayerUnitNumber(PrayerUnit prayerUnit);

}
