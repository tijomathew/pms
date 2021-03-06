package org.pms.services;

import org.pms.models.Priest;
import org.pms.models.PriestDesignation;
import org.springframework.ui.Model;

import java.util.List;

/**
 * This interface is the contract for the Priest Service.
 * User: tijo
 */
public interface PriestService {

    boolean addPriestSM(Priest priest);

    List<Priest> getAllPriestSM();

    Priest getPriestForPriestIDSM(Long id);

    Long getTotalCountOfPriestSM();

    Long getHighestAutoIDSM();

    List<Long> getAllPriestsIDsSM();

    boolean addPriestDesignation(PriestDesignation priestDesignation);

    Model createPriestFormBackObject(Model model);
}
