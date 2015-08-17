package org.pms.serviceImpls;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.daos.MassCentreDao;
import org.pms.enums.SystemRole;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.MassCentre;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.services.MassCentreService;
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
public class MassCentreServiceImpl implements MassCentreService {

    @Autowired
    private MassCentreDao massCentreDao;

    @Autowired
    private ParishService parishService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Override
    public boolean addMassCentreSM(MassCentre massCentre) {
        massCentreDao.addMassCentreDM(massCentre);
        return true;
    }

    @Override
    public List<Parish> getAllParishSM() {
        return massCentreDao.getAllParishDM();
    }

    @Override
    public List<MassCentre> getAllMassCentre() {
        return massCentreDao.getAllMassCentres();
    }

    @Override
    public MassCentre getMassCentreForIDSM(Long id) {
        return massCentreDao.getMassCentreForID(id);
    }

    @Override
    public List<MassCentre> getAllMassCentresForParishID(Long parishAutoID) {
        return massCentreDao.getAllMassCentresForParishID(parishAutoID);
    }

    @Override
    public Long getMassCentreCountForParish(Long parishId) {
        return massCentreDao.getMassCentreCountForParish(parishId);
    }

    @Override
    public void updateMassCentre(MassCentre massCentre) {
        massCentreDao.updateMassCentre(massCentre);
    }

    @Override
    public MassCentre createMassCentreFormBackObject(Model model) {
        MassCentre formBackMassCentre = new MassCentre();
        formBackMassCentre.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("massCentre", formBackMassCentre);

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

        Map<Long, String> parishMap = new HashMap<>();
        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParishName));
        }
        parishMap.put(0l, "--Please Select--");
        model.addAttribute("parishList", parishMap);
        return formBackMassCentre;
    }

    @Override
    public Long getAllMassCentreCount() {
        return massCentreDao.getAllMassCentreCount();
    }

    @Override
    public List<Long> getAllMassCentreIdsForParish(Long parishId) {
        return massCentreDao.getAllMassCentreIdsForParish(parishId);
    }

    @Override
    public List<MassCentre> getAllMassCentresForUserRole(User currentUser) {
        List<MassCentre> allMassCentres = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allMassCentres = getAllMassCentre();
                break;
            case PARISH_ADMIN:
                allMassCentres.addAll(getAllMassCentresForParishID(currentUser.getUsersOfParishes().getId()));
                break;
            case MASS_CENTER_ADMIN:
                allMassCentres.add(currentUser.getUsersOfMassCentres());
                break;
            case PRAYER_UNIT_ADMIN:
                //No Op
                break;
            case FAMILY_USER:
                //No Op
                break;
        }
        return allMassCentres;
    }
}
