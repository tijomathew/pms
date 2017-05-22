package org.pms.parish;

import org.pms.domain.Parish;
import org.pms.domain.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * This interface is the contract for the Parish Service.
 * User: tijo
 */
public interface ParishService {

    Boolean addParish(Parish parish);

    List<Parish> getAllParish();

    Parish getParishForIDSM(Long id);

    Long getParishCountInSystem();

    Boolean updateParish(Parish parish);

    Parish createParishFormBackObject(Model model);

    List<Parish> getAllParishForUserRole(User currentUser);

}