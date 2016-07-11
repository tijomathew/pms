package org.pms.services;

import org.pms.models.MassCentre;
import org.pms.models.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * This interface is the contract for the Mass Center Service.
 * User: tijo
 */
public interface MassCentreService {

    Boolean addMassCentre(MassCentre massCentre);

    List<MassCentre> getAllMassCentre();

    MassCentre getMassCentreForIDSM(Long id);

    Long getMassCentreCount();

    Boolean updateMassCentre(MassCentre massCentre);

    MassCentre createMassCentreFormBackObject(Model model);

    List<MassCentre> getAllMassCentresForUserRole(User currentUser);

}
