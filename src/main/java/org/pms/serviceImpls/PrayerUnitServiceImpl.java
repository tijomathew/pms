package org.pms.serviceImpls;

import org.pms.daos.PrayerUnitDao;
import org.pms.dtos.PrayerUnitDto;
import org.pms.models.MassCenter;
import org.pms.models.PrayerUnit;
import org.pms.services.PrayerUnitService;
import org.pms.utils.DisplayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the implementation for the PrayerUnit Service contract.
 * User: tijo
 */

@Service
@Transactional
public class PrayerUnitServiceImpl implements PrayerUnitService {

    @Autowired
    private PrayerUnitDao prayerUnitDao;

    @Override
    public boolean addPrayerUnitSM(PrayerUnit prayerUnit) {
        /*String massCenterID = prayerUnit.getMassCenterID();
        MassCenter massCenter = prayerUnitDao.getMassCenter(massCenterID);
        massCenter.addWard(prayerUnit);
        prayerUnit.setMassCenter(massCenter);*/
        prayerUnitDao.addPrayerUnitDM(prayerUnit);
        return true;
    }

    @Override
    public List<PrayerUnit> getAllPrayerUnits() {
        return prayerUnitDao.getAllPrayerUnit();
    }

    @Override
    public List<PrayerUnit> getWardsForMassCenterIDSM(Long massCenterID) {
        return prayerUnitDao.getPrayerUnitsForMassCenterIDDM(massCenterID);
    }

    @Override
    public PrayerUnit getPrayerUnitForIDSM(Long id) {
        return prayerUnitDao.getPrayerUnitForIDDM(id);
    }

    @Override
    public List<PrayerUnitDto> createPrayerUnitDtos(List<PrayerUnit> wardList) throws IllegalArgumentException {
        List<PrayerUnitDto> prayerUnitDtoList = new ArrayList<PrayerUnitDto>(wardList.size());
        if (!wardList.isEmpty()) {
            Integer uniqueId = 0;
            for (PrayerUnit ward : wardList) {
                PrayerUnitDto prayerUnitDto = new PrayerUnitDto();
                prayerUnitDto.setId(uniqueId);
                prayerUnitDto.setWardID(ward.getId());
                prayerUnitDto.setWardCode(ward.getPrayerUnitCode());
                prayerUnitDto.setWardName(ward.getPrayerUnitName());
                prayerUnitDto.setWardPlace(ward.getPrayerUnitPlace());
                prayerUnitDto.setLocalAddress(DisplayUtils.getEmbeddedObjectPropertyValueAsSingleString(ward.getLocalAddress(),7, "addressLineOne", "addressLineTwo", "addressLineThree", "town", "county", "pin", "country"));
                prayerUnitDto.setMassCenterName(ward.getMappedMassCenter().getName());
                prayerUnitDtoList.add(prayerUnitDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("PrayerUnit List cannot be an empty List!!!...");
        }
        return prayerUnitDtoList;
    }

    @Override
    public Long getPrayerUnitCount() {
        return prayerUnitDao.getPrayerUnitCount();
    }

    @Override
    public void updatePrayerUnit(PrayerUnit prayerUnit) {
        prayerUnitDao.updatePrayerUnit(prayerUnit);
    }
}
