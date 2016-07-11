package org.pms.serviceImpls;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.daos.MassCentreDao;
import org.pms.enums.SystemRole;
import org.pms.helpers.RequestResponseHolder;
import org.pms.models.MassCentre;
import org.pms.models.User;
import org.pms.services.MassCentreService;
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
public class MassCentreServiceImpl implements MassCentreService {

    @Autowired
    private MassCentreDao massCentreDao;

    @Override
    public Boolean addMassCentre(MassCentre massCentre) {
        Long massCentreCount = getMassCentreCount();
        massCentre.setMassCentreNo(++massCentreCount);
        massCentreDao.addMassCentre(massCentre);
        return true;
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
    public Long getMassCentreCount() {
        Long massCenterCount = massCentreDao.getMassCentreCount();
        if (massCenterCount == null) {
            massCenterCount = 0l;
        }
        return massCenterCount;
    }

    @Override
    public Boolean updateMassCentre(MassCentre massCentre) {
        massCentreDao.updateMassCentre(massCentre);
        return true;
    }

    @Override
    public MassCentre createMassCentreFormBackObject(Model model) {
        MassCentre formBackMassCentre = new MassCentre();
        formBackMassCentre.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("massCentre", formBackMassCentre);

        return formBackMassCentre;
    }

    @Override
    public List<MassCentre> getAllMassCentresForUserRole(User currentUser) {
        List<MassCentre> allMassCentres = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allMassCentres = getAllMassCentre();
                break;
            case MASS_CENTER_ADMIN:
                allMassCentres.add(getMassCentreForIDSM(currentUser.getUsersOfMassCentres().getId()));
                break;
            case PRAYER_UNIT_ADMIN:
                allMassCentres.add(getMassCentreForIDSM(currentUser.getUsersOfPrayerUnits().getMappedMassCentre().getId()));
                break;
            case FAMILY_USER:
                if (currentUser.getUserOfFamily() == null) {
                    allMassCentres.add(getMassCentreForIDSM(currentUser.getUsersOfPrayerUnits().getMappedMassCentre().getId()));
                } else {
                    allMassCentres.add(getMassCentreForIDSM(currentUser.getUserOfFamily().getFamilyPrayerUnit().getMappedMassCentre().getId()));
                }
                break;
        }
        return allMassCentres;
    }

}
