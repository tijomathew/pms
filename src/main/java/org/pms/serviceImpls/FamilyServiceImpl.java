package org.pms.serviceImpls;

import org.pms.daos.FamilyDao;
import org.pms.models.Family;
import org.pms.models.User;
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
    public Long getFamilyTotalCount() {
        return familyDao.getFamilyTotalCount();
    }

    @Override
    public Long getFamilyCountForMassCentre(Long massCentreId) {
        Long familyNumber = familyDao.getFamilyCountForParish(massCentreId);
        if (familyNumber == null) {
            familyNumber = 0l;
        }
        return familyNumber;
    }

    @Override
    public List<Family> getAllFamilyForMassCentreID(Long massCentreId) {
        return familyDao.getAllFamilyForParishID(massCentreId);
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
    public List<Long> getAllFamiliesIDForMassCentreId(Long massCentreId) {
        return familyDao.getAllFamiliesIDForParishId(massCentreId);
    }

    @Override
    public List<Long> getAllFamilyIdsForPrayerUnitId(List<Long> prayerUnitIds) {
        return familyDao.getAllFamilyIdsForPrayerUnitId(prayerUnitIds);
    }

    @Override
    public List<Family> getAllFamiliesForUserRole(User currentUser) {
        List<Family> familyList = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                familyList = getAllFamilySM();
                break;
            case PARISH_ADMIN:
                familyList = getAllFamilyForMassCentreID(currentUser.getUsersOfParish().getId());
                break;
            case PRAYER_UNIT_ADMIN:
                familyList = getAllFamilyForPrayerUnitID(currentUser.getUsersOfPrayerUnits().getId());
                break;
            case FAMILY_USER:
                familyList.addAll(getFamilyForFamilyID(currentUser.getUserOfFamily().getId()));
                break;
        }

        return familyList;
    }

    @Override
    public Boolean updateFamily(Family family) {
        familyDao.updateFamily(family);
        return true;
    }

    @Override
    public void setFamilyNumber(Family family) {
        Long familyCounterForParish = getFamilyCountForMassCentre(family.getFamilyPrayerUnit().getMappedParish().getId());
        family.setFamilyNo(++familyCounterForParish);
    }
}
