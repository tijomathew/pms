package org.pms.serviceImpls;

import org.pms.daos.ParishDao;
import org.pms.dtos.ParishDto;
import org.pms.models.Parish;
import org.pms.services.ParishService;
import org.pms.utils.DisplayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean addParishSM(Parish parish) {
       /* String priestIDs =""; //parish.getPriest();
        List<String> seperatedPriestIDs = Arrays.asList(priestIDs.split(","));
        List<Priest> priestListOfParish = new ArrayList<Priest>();
        if (!seperatedPriestIDs.isEmpty()) {
            for (String priestID : seperatedPriestIDs) {
                priestListOfParish.add(parishDao.getPriestDM(priestID));
            }
        }
        if (!priestListOfParish.isEmpty()) {
            for (Priest priest : priestListOfParish) {
                priest.setParish(parish);
                parish.addPriestsForParish(priest);
                parishDao.updatePriestForParish(priest);
            }
        }*/
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
    public List<ParishDto> createParishDto(List<Parish> parishList) throws IllegalArgumentException {
        List<ParishDto> parishDtoList = new ArrayList<ParishDto>(parishList.size());
        if (!parishList.isEmpty()) {
            Integer uniqueId = 0;
            for (Parish parish : parishList) {
                ParishDto parishDto = new ParishDto(uniqueId, parish.getId(), parish.getName(), parish.getRiteName(), parish.getDioceseName(), parish.getDioceseName(), parish.getForaneName(),parish.getFacebookPage(),parish.getWebSite(),parish.getCode(),parish.getPlace(),parish.getDrivingRoute(),parish.getMap(),parish.getRegisteredDate(),parish.getMobileNo(),parish.getEmail(),parish.getLandLineNo(),parish.getFaxNo());
                parishDto.setLocalAddress(DisplayUtils.getEmbeddedObjectString(parish.getLocalAddress(),"addressLineOne","addressLineTwo","addressLineThree"));
                parishDtoList.add(parishDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("Parish List cannot be an empty List!!!...");
        }
        return parishDtoList;
    }

    @Override
    public Long getParishCount() {
        return parishDao.getParishCount();
    }

    @Override
    public void updateParish(Parish parish) {
        parishDao.updateParish(parish);
    }
}
