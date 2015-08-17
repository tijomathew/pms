package org.pms.services;

import org.pms.models.MassCentre;
import org.pms.models.Parish;
import org.pms.models.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * This interface is the contract for the Mass Center Service.
 * User: tijo
 */
public interface MassCentreService {

    boolean addMassCentreSM(MassCentre massCentre);

    List<Parish> getAllParishSM();

    List<MassCentre> getAllMassCentre();

    MassCentre getMassCentreForIDSM(Long id);

    List<MassCentre> getAllMassCentresForParishID(Long parishAutoID);

    Long getMassCentreCountForParish(Long parishId);

    void updateMassCentre(MassCentre massCentre);

    Long getAllMassCentreCount();

    MassCentre createMassCentreFormBackObject(Model model);

    List<Long> getAllMassCentreIdsForParish(Long parishId);

    List<MassCentre> getAllMassCentresForUserRole(User currentUser);

}
