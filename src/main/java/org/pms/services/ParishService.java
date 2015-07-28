package org.pms.services;

import org.pms.models.Parish;
import org.pms.models.Priest;
import org.pms.models.User;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * This interface is the contract for the Parish Service.
 * User: tijo
 */
public interface ParishService {

    boolean addParishSM(Parish parish);

    List<Parish> getAllParish();

    Parish getParishForIDSM(Long id);

    Long getParishCount();

    void updateParish(Parish parish);

    List<Parish> getAllParishForUserRole(User currentUser);
}
