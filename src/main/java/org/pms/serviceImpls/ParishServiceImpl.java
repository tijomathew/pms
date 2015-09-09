package org.pms.serviceImpls;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.daos.ParishDao;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.services.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

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
    public Boolean addParish(Parish parish) {
        parishDao.addParish(parish);
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
                parishList.add(currentUser.getUsersOfMassCentres().getMappedParish());
                break;
            case PRAYER_UNIT_ADMIN:
                parishList.add(currentUser.getUsersOfPrayerUnits().getMappedMassCentre().getMappedParish());
                break;
            case FAMILY_USER:
                if (currentUser.getUserOfFamily() == null) {
                    parishList.add(getParishForIDSM(currentUser.getUsersOfPrayerUnits().getMappedMassCentre().getMappedParish().getId()));
                } else {
                    parishList.add(getParishForIDSM(currentUser.getUserOfFamily().getFamilyPrayerUnit().getMappedMassCentre().getMappedParish().getId()));
                }
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

    @Override
    public Boolean updateParish(Parish parish) {
        parishDao.updateParish(parish);
        return true;
    }
}
