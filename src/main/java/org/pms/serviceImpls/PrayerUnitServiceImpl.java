package org.pms.serviceImpls;

import org.pms.daos.PrayerUnitDao;
import org.pms.enums.SystemRole;
import org.pms.models.MassCenter;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.Parish;
import org.pms.models.PrayerUnit;
import org.pms.models.User;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.pms.utils.DisplayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        /*String massCenterID = prayerUnit.getMassCenterID();
        MassCenter massCenter = prayerUnitDao.getMassCenter(massCenterID);
        massCenter.addWard(prayerUnit);
        prayerUnit.setMassCenter(massCenter);*/
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
    public Long getPrayerUnitCountForMassCenter(Long massCenterId) {
        return prayerUnitDao.getPrayerUnitCountForMassCenter(massCenterId);
    }

    @Override
    public PrayerUnit createPrayerUnitFormBackObject(Model modelMap) {
        PrayerUnit formBackPrayerUnit = new PrayerUnit();

        List<MassCenter> massCenterList = new ArrayList<>();

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

        if (currentUser.getSystemRole().toString().equalsIgnoreCase(SystemRole.PARISH_ADMIN.toString())) {
            Parish parishForMassCenter = parishService.getParishForIDSM(currentUser.getParishId());
            massCenterList = parishForMassCenter.getMassCenterList();
        } else if (currentUser.getSystemRole().toString().equalsIgnoreCase(SystemRole.MASS_CENTER_ADMIN.toString())) {
            massCenterList.add(massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId()));
        } else if (currentUser.getSystemRole().toString().equalsIgnoreCase(SystemRole.PRAYER_UNIT_ADMIN.toString())) {
            PrayerUnit prayerUnit = getPrayerUnitForIDSM(currentUser.getPrayerUnitId());
            massCenterList.add(prayerUnit.getMappedMassCenter());
            Long prayerUnitCounter = getPrayerUnitCountForMassCenter(prayerUnit.getMappedMassCenter().getId());
            //formBackPrayerUnit.setPrayerUnitCode("PU" + (++prayerUnitCounter));
        } else {
            massCenterList = massCenterService.getAllMassCenter();
        }


        modelMap.addAttribute("prayerUnit", formBackPrayerUnit);

        Map<Long, String> massCenterMap = new HashMap<Long, String>();

        massCenterMap.put(0l, "--Please Select--");
        if (!massCenterList.isEmpty()) {
            for (MassCenter massCenter : massCenterList)
                massCenterMap.put(massCenter.getId(), massCenter.getName());
        }
        modelMap.addAttribute("massCenterMap", massCenterMap);
        return formBackPrayerUnit;
    }

    @Override
    public List<Long> getAllPrayerUnitIdsForMassCenterIds(List<Long> massCenterIds) {
        return prayerUnitDao.getAllPrayerUnitIdsForMassCenterIds(massCenterIds);
    }
}
