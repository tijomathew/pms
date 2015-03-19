package org.pms.services;

import org.pms.dtos.PrayerUnitDto;
import org.pms.models.PrayerUnit;
import org.springframework.ui.Model;

import java.util.List;

/**
 * This interface is the contract for the PrayerUnit Service.
 * User: tijo
 */
public interface PrayerUnitService {

    boolean addPrayerUnitSM(PrayerUnit ward);

    List<PrayerUnit> getAllPrayerUnits();

    List<PrayerUnit> getWardsForMassCenterIDSM(Long massCenterID);

    PrayerUnit getPrayerUnitForIDSM(Long id);

    Long getPrayerUnitCount();

    List<PrayerUnitDto> createPrayerUnitDtos(List<PrayerUnit> wardList) throws IllegalArgumentException;

    void updatePrayerUnit(PrayerUnit prayerUnit);

    PrayerUnit createPrayerUnitFormBackObject(Model modelMap);
}
