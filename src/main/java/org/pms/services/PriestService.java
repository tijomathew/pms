package org.pms.services;

import org.pms.dtos.PriestDto;
import org.pms.models.Priest;
import org.pms.models.PriestDesignation;

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

    List<PriestDto> createPriestDto(List<Priest> allPriests) throws IllegalArgumentException;

    Long getHighestAutoIDSM();

    List<Long> getAllPriestsIDsSM();

    boolean addPriestDesignation(PriestDesignation priestDesignation);
}
