package org.pms.serviceImpls;

import org.pms.daos.FamilyDao;
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
    public Long getFamilyTotalCount() {
        return familyDao.getFamilyTotalCount();
    }

    @Override
    public Long getFamilyCountForParish(Long parishId) {
        return familyDao.getFamilyCountForParish(parishId);
    }

    @Override
    public List<Family> getAllFamilyForParishID(Long parishId) {
        return familyDao.getAllFamilyForParishID(parishId);
    }

    @Override
    public List<Family> getAllFamilyForMassCenterID(Long massCenterId) {
        return familyDao.getAllFamilyForMassCenterID(massCenterId);
    }

    @Override
    public List<Family> getAllFamilyForPrayerUnitID(Long prayerUnitId) {
        return familyDao.getAllFamilyForPrayerUnitID(prayerUnitId);
    }

    @Override
    public List<Family> getFamilyForFamilyID(Long familyId) {
        return familyDao.getFamilyForFamilyID(familyId);
    }

    @Override
    public List<Long> getAllFamiliesIDForParishID(Long parishId) {
        return familyDao.getAllFamiliesIDForParishID(parishId);
    }

    @Override
    public List<Long> getAllFamilyIdsForPrayerUnitId(List<Long> prayerUnitIds) {
        return familyDao.getAllFamilyIdsForPrayerUnitId(prayerUnitIds);
    }
}
