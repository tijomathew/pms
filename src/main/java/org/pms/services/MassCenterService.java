package org.pms.services;

import org.pms.models.MassCenter;
import org.pms.models.Parish;
import org.pms.models.User;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * This interface is the contract for the Mass Center Service.
 * User: tijo
 */
public interface MassCenterService {

    boolean addMassCenterSM(MassCenter massCenter);

    List<Parish> getAllParishSM();

    List<MassCenter> getAllMassCenter();

    MassCenter getMassCenterForIDSM(Long id);

    List<MassCenter> getMassCenterForParishID(Long parishAutoID);

    Long getMassCenterCountForParish(Long parishId);

    void updateMassCenter(MassCenter massCenter);

    Long getAllMassCenterCount();

    MassCenter createMassCenterFormBackObject(Model model);

    List<Long> getAllMassCenterIdsForParish(Long parishId);

    Map<Long, String> getMassCenterMapForUserRole(User currentUser);

}
