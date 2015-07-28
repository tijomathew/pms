package org.pms.serviceImpls;

import org.pms.daos.PrayerUnitDao;
import org.pms.enums.SystemRole;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.pms.models.PrayerUnit;
import org.pms.models.User;
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

    @Autowired
    private MassCenterService massCenterService;

    @Override
    public boolean addPrayerUnitSM(PrayerUnit prayerUnit) {
        prayerUnitDao.addPrayerUnitDM(prayerUnit);
        return true;
    }

    @Override
    public List<PrayerUnit> getAllPrayerUnits() {
        return prayerUnitDao.getAllPrayerUnit();
    }

    @Override
    public List<PrayerUnit> getPrayerUnitForMassCenterIDSM(Long massCenterID) {
        return prayerUnitDao.getPrayerUnitsForMassCenterIDDM(massCenterID);
    }

    @Override
    public PrayerUnit getPrayerUnitForIDSM(Long id) {
        return prayerUnitDao.getPrayerUnitForIDDM(id);
    }

    @Override
    public Long getPrayerUnitCount() {
        return prayerUnitDao.getPrayerUnitCount();
    }

    @Override
    public void updatePrayerUnit(PrayerUnit prayerUnit) {
        prayerUnitDao.updatePrayerUnit(prayerUnit);
    }

    @Override
    public Long getPrayerUnitCountUnderParish(Long parishId) {
        return prayerUnitDao.getPrayerUnitCountUnderParish(parishId);
    }

    @Override
    public PrayerUnit createPrayerUnitFormBackObject(Model modelMap) {
        PrayerUnit formBackPrayerUnit = new PrayerUnit();
        Map<Long, String> parishMap = new HashMap<>();

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        modelMap.addAttribute("prayerUnit", formBackPrayerUnit);

        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);

        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParishName));
        }
        parishMap.put(0l, "--please select--");

        modelMap.addAttribute("parishMap", parishMap);
        return formBackPrayerUnit;
    }

    @Override
    public List<Long> getAllPrayerUnitIdsForMassCenterIds(List<Long> massCenterIds) {
        return prayerUnitDao.getAllPrayerUnitIdsForMassCenterIds(massCenterIds);
    }

    @Override
    public List<PrayerUnit> getAllPrayerUnitsForUserRole(User currentUser) {
        final List<PrayerUnit> allPrayerUnits = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allPrayerUnits.addAll(getAllPrayerUnits());
                break;
            case PARISH_ADMIN:
                List<MassCenter> massCentersUnderParish = parishService.getParishForIDSM(currentUser.getParishId()).getMassCenterList();
                if (!massCentersUnderParish.isEmpty()) {
                    massCentersUnderParish.stream().forEach(massCenter -> allPrayerUnits.addAll(massCenter.getPrayerUnits()));
                }
                break;
            case MASS_CENTER_ADMIN:
                allPrayerUnits.addAll(massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId()).getPrayerUnits());
                break;
            case PRAYER_UNIT_ADMIN:
                allPrayerUnits.add(getPrayerUnitForIDSM(currentUser.getPrayerUnitId()));
                break;
            case FAMILY_USER:
                //No Op
                break;
        }

        return allPrayerUnits;
    }
}
