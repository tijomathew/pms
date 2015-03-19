package org.pms.serviceImpls;

import org.pms.daos.MassCenterDao;
import org.pms.dtos.MassCenterDto;
import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.pms.services.MassCenterService;
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
    private ParishService parishService;

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
                MassCenterDto massCenterDto = new MassCenterDto();
                massCenterDto.setId(uniqueId);
                massCenterDto.setMassCenterID(massCenter.getId());
                massCenterDto.setMassCenterName(massCenter.getName());
                massCenterDtoList.add(massCenterDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("Mass Center List cannot be an empty List!!!...");
        }
        return massCenterDtoList;
    }

    @Override
    public Long getMassCenterCount() {
        return massCenterDao.getMassCenterCount();
    }

    @Override
    public void updateMassCenter(MassCenter massCenter) {
        massCenterDao.updateMassCenter(massCenter);
    }

    @Override
    public MassCenter createMassCenterFormBackObject(Model model) {
        MassCenter formBackMassCenter = new MassCenter();
        Long massCenterCount = getMassCenterCount();
        formBackMassCenter.setMassCenterID("MC" + (++massCenterCount));
        model.addAttribute("massCenter", formBackMassCenter);
        Map<Long, String> parishMap = new HashMap<Long, String>();
        parishMap.put(0l, "--please select--");
        List<Parish> parishList = parishService.getAllParish();
        if (!parishList.isEmpty()) {
            for (Parish parish : parishList)
                parishMap.put(parish.getId(), parish.getName());
        }
        model.addAttribute("parishList", parishMap);
        return formBackMassCenter;
    }
}
