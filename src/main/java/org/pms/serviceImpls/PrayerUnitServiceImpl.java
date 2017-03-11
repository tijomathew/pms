package org.pms.serviceImpls;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.daos.PrayerUnitDao;
import org.pms.enums.SystemRole;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.Parish;
import org.pms.models.PrayerUnit;
import org.pms.models.User;
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
 * This class is the implementation for the PrayerUnit Service contract.
 * User: tijo
 */

@Service
@Transactional
public class PrayerUnitServiceImpl implements PrayerUnitService {

    @Autowired
    private PrayerUnitDao prayerUnitDao;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private ParishService parishService;

    @Override
    public Boolean addPrayerUnitSM(PrayerUnit prayerUnit) {
        prayerUnitDao.addPrayerUnitDM(prayerUnit);
        return true;
    }

    @Override
    public List<PrayerUnit> getAllPrayerUnits() {
        return prayerUnitDao.getAllPrayerUnit();
    }

    @Override
    public List<PrayerUnit> getAllPrayerUnitsForParishID(Long parishID) {
        return prayerUnitDao.getPrayerUnitsForParishIDDM(parishID);
    }

    @Override
    public PrayerUnit getPrayerUnitForIDSM(Long id) {
        return prayerUnitDao.getPrayerUnitForIDDM(id);
    }

    @Override
    public void updatePrayerUnit(PrayerUnit prayerUnit) {
        prayerUnitDao.updatePrayerUnit(prayerUnit);
    }

    @Override
    public Long getPrayerUnitCountInSystem() {
        Long prayerUnitCount = prayerUnitDao.getPrayerUnitCountInSystem();
        if (prayerUnitCount == null) {
            prayerUnitCount = 0l;
        }
        return prayerUnitCount;
    }

    @Override
    public void createPrayerUnitFormBackObject(Model modelMap) {
        PrayerUnit formBackPrayerUnit = new PrayerUnit();
        Map<Long, String> parishMap = new HashMap<>();

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        modelMap.addAttribute("prayerUnit", formBackPrayerUnit);


        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);

        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParsihName));
        }

        formBackPrayerUnit.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));

        modelMap.addAttribute("parishMap", parishMap);
    }

    @Override
    public List<PrayerUnit> getAllPrayerUnitsForUserRole(User currentUser) {
        final List<PrayerUnit> allPrayerUnits = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allPrayerUnits.addAll(getAllPrayerUnits());
                break;
            case PARISH_ADMIN:
                allPrayerUnits.addAll(getAllPrayerUnitsForParishID(currentUser.getUsersOfParish().getId()));
                break;
            case PRAYER_UNIT_ADMIN:
                allPrayerUnits.add(getPrayerUnitForIDSM(currentUser.getUsersOfPrayerUnits().getId()));
                break;
            case FAMILY_USER:
                if (currentUser.getUserOfFamily() == null) {
                    allPrayerUnits.add(getPrayerUnitForIDSM(currentUser.getUsersOfPrayerUnits().getId()));
                } else {
                    allPrayerUnits.add(getPrayerUnitForIDSM(currentUser.getUserOfFamily().getFamilyPrayerUnit().getId()));
                }
                break;
        }

        return allPrayerUnits;
    }

    @Override
    public void setPrayerUnitNumber(PrayerUnit prayerUnit) {
        Long prayerUnitCounter = getPrayerUnitCountInSystem();
        prayerUnit.setPrayerUnitNo(++prayerUnitCounter);
    }

}
