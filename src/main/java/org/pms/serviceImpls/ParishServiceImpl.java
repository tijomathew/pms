package org.pms.serviceImpls;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
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

    @Override
    public boolean addParishSM(Parish parish) {
        parishDao.addParishDM(parish);
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

    @Override
    public void createFormBackObject(Model model) {
        Parish modelBackObject = new Parish();
        modelBackObject.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("parish", modelBackObject);
    }
}
