package org.pms.serviceImpls;

import org.pms.daos.FamilyDao;
import org.pms.dtos.FamilyDto;
import org.pms.models.Family;
import org.pms.services.FamilyService;
import org.pms.utils.DisplayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the implementation for the Family Service contract.
 * User: tijo
 */

@Service
@Transactional
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyDao familyDao;

    @Override
    public boolean addFamilySM(Family family) {
        familyDao.addFamilyDM(family);
        return true;
    }

    @Override
    public List<Family> getAllFamilySM() {
        return familyDao.getAllFamilies();
    }

    @Override
    public Family getFamilyForID(Long id) {
        return familyDao.getFamilyForID(id);
    }

    @Override
    public List<FamilyDto> createFamilyDto(List<Family> familyList) throws IllegalArgumentException {
        List<FamilyDto> familyDtoList = new ArrayList<FamilyDto>(familyList.size());
        if (!familyList.isEmpty()) {
            Integer uniqueId = 0;
            for (Family family : familyList) {
                FamilyDto familyDto = new FamilyDto();
                familyDto.setId(uniqueId);
                familyDto.setFamilyID(family.getId());
                familyDto.setFamilyName(family.getFamilyName());
                familyDto.setDateOfRegistration(family.getDateOfRegistration());
                familyDto.setDioceseIndia(family.getDioceseInNative());
                familyDto.setParishIndia(family.getParishInNative());
                familyDto.setDioceseIndia(family.getDioceseInNative());
                familyDto.setParishLocal(family.getFamilyParish().getChurchName());
                familyDto.setMassCenter(family.getFamilyMassCenter().getName());
                familyDto.setPrayerUnit(family.getFamilyPrayerUnit().getPrayerUnitName());
                familyDto.setLocalAddress(DisplayUtils.getEmbeddedObjectPropertyValueAsSingleString(family.getLocalAddress(),7, "addressLineOne", "addressLineTwo", "addressLineThree", "town", "county", "pin", "country"));
                familyDto.setNativeAddress(DisplayUtils.getEmbeddedObjectPropertyValueAsSingleString(family.getNativeAddress(),8,"addressLineOne", "addressLineTwo", "addressLineThree", "postOffice", "district", "state", "pin", "country"));
                familyDtoList.add(familyDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("Family List cannot be an empty List!!!...");
        }
        return familyDtoList;
    }

    @Override
    public Long getFamilyTotalCount() {
        return familyDao.getFamilyTotalCount();
    }
}
