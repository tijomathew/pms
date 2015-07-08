package org.pms.serviceImpls;

import org.pms.constants.SystemRoles;
import org.pms.daos.ParishDao;
import org.pms.dtos.ParishDto;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.services.ParishService;
import org.pms.utils.DisplayUtils;
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

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Override
    public boolean addParishSM(Parish parish) {
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRoles.PMS_CURRENT_USER);
        boolean permissionDenied = false;
        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            permissionDenied = true;
        }
        if (!permissionDenied) {
            parishDao.addParishDM(parish);
        } else {
            //show the error message that parish cannot be add by Parish Admin. He can edit only the Assigned Parish.
        }
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
                ParishDto parishDto = new ParishDto(uniqueId, parish.getId(), parish.getName(), parish.getRiteName(), parish.getDioceseName(), parish.getDioceseName(), parish.getForaneName(), parish.getFacebookPage(), parish.getWebSite(), parish.getCode(), parish.getPlace(), parish.getDrivingRoute(), parish.getMap(), parish.getRegisteredDate(), parish.getMobileNo(), parish.getEmail(), parish.getLandLineNo(), parish.getFaxNo());
                parishDto.setLocalAddress(DisplayUtils.getEmbeddedObjectPropertyValueAsSingleString(parish.getLocalAddress(), 7, "addressLineOne", "addressLineTwo", "addressLineThree", "town", "county", "country", "pin"));
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

    public Parish createParishFormBackObjectModel(Model model) {
        Long parishCounter = getParishCount();
        Parish formBackParish = new Parish();
        formBackParish.setParishID(++parishCounter);
        model.addAttribute("parish", formBackParish);
        return formBackParish;
    }

}
