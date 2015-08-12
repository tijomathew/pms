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

    boolean addMassCenterSM(MassCentre massCentre);

    List<Parish> getAllParishSM();

    List<MassCentre> getAllMassCenter();

    MassCentre getMassCenterForIDSM(Long id);

    List<MassCentre> getAllMassCentersForParishID(Long parishAutoID);

    Long getMassCenterCountForParish(Long parishId);

    void updateMassCenter(MassCentre massCentre);

    Long getAllMassCenterCount();

    MassCentre createMassCenterFormBackObject(Model model);

    List<Long> getAllMassCenterIdsForParish(Long parishId);

    List<MassCentre> getAllMassCentersForUserRole(User currentUser);

}
