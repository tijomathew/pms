package org.pms.serviceImpls;

import org.pms.constants.SystemRoles;
import org.pms.daos.MassCenterDao;
import org.pms.daos.ParishDao;
import org.pms.daos.PriestDao;
import org.pms.dtos.MassCenterDto;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.services.MassCenterService;
import org.pms.utils.DisplayUtils;
import org.pms.services.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private PriestDao priestDao;

    @Autowired
    private ParishService parishService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Override
    public boolean addMassCenterSM(MassCenter massCenter) {
        /*String parishID = massCenter.getParishs();
        Parish parish = massCenterDao.getParishDM(parishID);
        if (!parishID.isEmpty()) {
            parish = massCenterDao.getParishDM(parishID);
            massCenter.setParish(parish);
        }
        parish.addMassCentersForParish(massCenter);
        massCenterDao.updateParish(parish);*/

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
    public List<MassCenterDto> createMassCenterDto(List<MassCenter> massCenterList) throws IllegalArgumentException {
        List<MassCenterDto> massCenterDtoList = new ArrayList<MassCenterDto>(massCenterList.size());
        if (!massCenterList.isEmpty()) {
            Integer uniqueId = 0;
            for (MassCenter massCenter : massCenterList) {
                MassCenterDto massCenterDto = new MassCenterDto(uniqueId,massCenter.getMassCenterID(),massCenter.getName(),massCenter.getPatronName(),massCenter.getPlace(),massCenter.getFacebookPage(),massCenter.getRegisteredDate(),massCenter.getDrivingRoute(),massCenter.getMap(),massCenter.getLandLineNo(),massCenter.getMobileNo(),massCenter.getEmail(),massCenter.getFaxNo());
                massCenterDto.setParishName(massCenter.getMappedParish().getChurchName());
                massCenterDto.setLocalAddress(DisplayUtils.getEmbeddedObjectPropertyValueAsSingleString(massCenter.getLocalAddress(),7,"addressLineOne", "addressLineTwo", "addressLineThree", "town", "county", "pin", "country"));
                /*massCenterDto.setPriestNames(priestDao.getPriestForIDDM(massCenter.));*/
                massCenterDtoList.add(massCenterDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("Mass Center List cannot be an empty List!!!...");
        }
        return massCenterDtoList;
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

        setMassCenterIDUnderParish(formBackMassCenter);

        model.addAttribute("massCenter", formBackMassCenter);
        Map<Long, String> parishMap = new HashMap<>();
        parishMap.put(0l, "--please select--");

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        List<Parish> parishList = new ArrayList<>();
        Parish parishForMassCenter = null;

        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.ADMIN)) {
            parishList = parishService.getAllParish();
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            parishForMassCenter = parishService.getParishForIDSM(currentUser.getParishId());
            parishList.add(parishForMassCenter);
        }
        if (!parishList.isEmpty()) {
            for (Parish parish : parishList)
                parishMap.put(parish.getId(), parish.getName());
        }
        model.addAttribute("parishList", parishMap);
        return formBackMassCenter;
    }

    private void setMassCenterIDUnderParish(MassCenter formBackMassCenter) {
        Parish parishForMassCenter = null;

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRoles.PMS_CURRENT_USER, User.class);
        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            parishForMassCenter = parishService.getParishForIDSM(currentUser.getParishId());
        }

        if (parishForMassCenter != null) {
            Long massCenterCount = getMassCenterCountForParish(parishForMassCenter.getId());
            formBackMassCenter.setMassCenterID("MC" + (++massCenterCount));
        }
    }
}
