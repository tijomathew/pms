package org.pms.parish;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.domain.Parish;
import org.pms.domain.User;
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
        Long parishCount = getParishCountInSystem();
        parish.setParishNo(++parishCount);
        parishDao.addParish(parish);
        return true;
    }

    @Override
    public List<Parish> getAllParish() {
        return parishDao.getAllParish();
    }

    @Override
    public Parish getParishForIDSM(Long id) {
        return parishDao.getParishForID(id);
    }

    @Override
    public Long getParishCountInSystem() {
        Long parishCount = parishDao.getParishCountInSystem();
        if (parishCount == null) {
            parishCount = 0l;
        }
        return parishCount;
    }

    @Override
    public Boolean updateParish(Parish parish) {
        parishDao.updateParish(parish);
        return true;
    }

    @Override
    public Parish createParishFormBackObject(Model model) {
        Parish formBackParish = new Parish();
        formBackParish.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("parish", formBackParish);

        return formBackParish;
    }

    @Override
    public List<Parish> getAllParishForUserRole(User currentUser) {
        List<Parish> allParishes = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allParishes = getAllParish();
                break;
            case PARISH_ADMIN:
                allParishes.add(getParishForIDSM(currentUser.getUsersOfParish().getId()));
                break;
            case PRAYER_UNIT_ADMIN:
                allParishes.add(getParishForIDSM(currentUser.getUsersOfPrayerUnits().getMappedParish().getId()));
                break;
            case FAMILY_USER:
                if (currentUser.getUserOfFamily() == null) {
                    allParishes.add(getParishForIDSM(currentUser.getUsersOfPrayerUnits().getMappedParish().getId()));
                } else {
                    allParishes.add(getParishForIDSM(currentUser.getUserOfFamily().getFamilyPrayerUnit().getMappedParish().getId()));
                }
                break;
            case FINANCE_USER:
                allParishes.add(getParishForIDSM(currentUser.getUsersOfParish().getId()));
                break;
        }
        return allParishes;
    }

}
