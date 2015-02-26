package org.pms.serviceImpls;

import org.pms.daos.FamilyDao;
import org.pms.dtos.FamilyDto;
import org.pms.models.Family;
import org.pms.services.FamilyService;
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
                familyDtoList.add(familyDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("Family List cannot be an empty List!!!...");
        }
        return familyDtoList;
    }
}
