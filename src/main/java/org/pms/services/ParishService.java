package org.pms.services;

import org.pms.models.Parish;
import org.pms.models.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * This interface is the contract for the Mass Center Service.
 * User: tijo
 */
public interface ParishService {

    Boolean addParish(Parish parish);

    List<Parish> getAllParish();

    Parish getParishForIDSM(Long id);

    Long getParishCount();

    Boolean updateParish(Parish parish);

    Parish createParishFormBackObject(Model model);

    List<Parish> getAllParishForUserRole(User currentUser);

}
