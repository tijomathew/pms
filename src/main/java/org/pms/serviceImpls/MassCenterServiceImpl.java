package org.pms.serviceImpls;

import org.pms.daos.MassCenterDao;
import org.pms.enums.SystemRole;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.services.MassCenterService;
import org.pms.services.PrayerUnitService;
import org.pms.services.ParishService;
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
 * This class is the implementation for the Mass Center Service contract.
 * User: tijo
 */

@Service
@Transactional
public class MassCenterServiceImpl implements MassCenterService {

    @Autowired
    private MassCenterDao massCenterDao;

    @Autowired
    private ParishService parishService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Override
    public boolean addMassCenterSM(MassCenter massCenter) {
        massCenterDao.addMassCenterDM(massCenter);
        return true;
    }

    @Override
    public List<Parish> getAllParishSM() {
        return massCenterDao.getAllParishDM();
    }

    @Override
    public List<MassCenter> getAllMassCenter() {
        return massCenterDao.getAllMassCenters();
    }

    @Override
    public MassCenter getMassCenterForIDSM(Long id) {
        return massCenterDao.getMassCenterForID(id);
    }

    @Override
    public List<MassCenter> getMassCenterForParishID(Long parishAutoID) {
        return massCenterDao.getMassCenterForParishID(parishAutoID);
    }

    @Override
    public Long getMassCenterCountForParish(Long parishId) {
        return massCenterDao.getMassCenterCountForParish(parishId);
    }

    @Override
    public void updateMassCenter(MassCenter massCenter) {
        massCenterDao.updateMassCenter(massCenter);
    }

    @Override
    public MassCenter createMassCenterFormBackObject(Model model) {
        MassCenter formBackMassCenter = new MassCenter();

        model.addAttribute("massCenter", formBackMassCenter);

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

        Map<Long, String> parishMap = new HashMap<>();
        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParishName));
        }

        model.addAttribute("parishList", parishMap);
        return formBackMassCenter;
    }

    @Override
    public Long getAllMassCenterCount() {
        return massCenterDao.getAllMassCenterCount();
    }

    @Override
    public List<Long> getAllMassCenterIdsForParish(Long parishId) {
        return massCenterDao.getAllMassCenterIdsForParish(parishId);
    }

    @Override
    public Map<Long, String> getMassCenterMapForUserRole(User currentUser) {
        Map<Long, String> massCenterMap = new HashMap<>();
        List<MassCenter> massCenterList = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                massCenterList.addAll(getAllMassCenter());
                break;
            case PARISH_ADMIN:
                massCenterList.addAll(parishService.getParishForIDSM(currentUser.getParishId()).getMassCenterList());
                break;
            case MASS_CENTER_ADMIN:
                massCenterList.add(getMassCenterForIDSM(currentUser.getMassCenterId()));
                break;
            case PRAYER_UNIT_ADMIN:
                massCenterList.add(prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId()).getMappedMassCenter());
                break;
            case FAMILY_USER:
                //No Op
                break;
        }
        if (!massCenterList.isEmpty()) {
            massCenterMap = massCenterList.stream().collect(Collectors.toMap(MassCenter::getId, MassCenter::getMassCenterName));
        }
        massCenterMap.put(0l, "--Please Select--");
        return massCenterMap;
    }

    @Override
    public List<MassCenter> getAllMassCentersForUserRole(User currentUser) {
        List<MassCenter> allMassCenters = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allMassCenters = getAllMassCenter();
                break;
            case PARISH_ADMIN:
                allMassCenters.addAll(parishService.getParishForIDSM(currentUser.getParishId()).getMassCenterList());
                break;
            case MASS_CENTER_ADMIN:
                allMassCenters.add(getMassCenterForIDSM(currentUser.getMassCenterId()));
                break;
            case PRAYER_UNIT_ADMIN:
                //No Op
                break;
            case FAMILY_USER:
                //No Op
                break;
        }
        return allMassCenters;
    }
}
