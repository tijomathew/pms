package org.pms.serviceImpls;

import org.pms.enums.SystemRole;
import org.pms.daos.ParishDao;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.services.FamilyService;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is the implementation for the Parish Service contract.
 * User: tijo
 */

@Service
@Transactional
public class ParishServiceImpl implements ParishService {

    @Autowired
    private ParishDao parishDao;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Override
    public boolean addParishSM(Parish parish) {
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRole.PMS_CURRENT_USER.toString());
        boolean permissionDenied = false;
        if (currentUser.getSystemRole().toString().equalsIgnoreCase(SystemRole.PARISH_ADMIN.toString())) {
            permissionDenied = true;
        }
        if (!permissionDenied) {
            parishDao.addParishDM(parish);
        } else {
            //show the error message that parish cannot be add by Parish Admin. He can edit only the Assigned Parish.
        }
        return true;
    }

    @Override
    public List<Parish> getAllParish() {
        return parishDao.getAllParish();
    }

    @Override
    public Parish getParishForIDSM(Long id) {
        return parishDao.getParishForIDDM(id);
    }

    @Override
    public Long getParishCount() {
        return parishDao.getParishCount();
    }

    @Override
    public void updateParish(Parish parish) {
        parishDao.updateParish(parish);
    }

    @Override
    public List<Parish> getAllParishForUserRole(User currentUser) {
        List<Parish> parishList = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                parishList.addAll(getAllParish());
                break;
            case PARISH_ADMIN:
                parishList.add(currentUser.getUsersOfParishes());
                break;
            case MASS_CENTER_ADMIN:
                parishList.add(currentUser.getUsersOfMassCenters().getMappedParish());
                break;
            case PRAYER_UNIT_ADMIN:
                parishList.add(currentUser.getUsersOfPrayerUnits().getMappedMassCenter().getMappedParish());
                break;
            case FAMILY_USER:
                parishList.add(currentUser.getUserOfFamily().getFamilyParish());
                break;
        }
        return parishList;
    }
}
