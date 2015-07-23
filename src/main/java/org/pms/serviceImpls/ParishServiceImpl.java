package org.pms.serviceImpls;

import org.pms.enums.SystemRole;
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
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRole.PMS_CURRENT_USER.toString());
        boolean permissionDenied = false;
        if (currentUser.getSystemRole().toString().equalsIgnoreCase(SystemRole.PARISH_ADMIN.toString())) {
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
