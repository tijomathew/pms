package org.pms.services;

import org.pms.dtos.FamilyDto;
import org.pms.models.Family;

import java.util.List;

/**
 * This interface is the contract for the Family Service.
 * User: tijo
 */
public interface FamilyService {

    boolean addFamilySM(Family family);

    List<Family> getAllFamilySM();

    Family getFamilyForID(Long id);

    List<FamilyDto> createFamilyDto(List<Family> familyList) throws IllegalArgumentException;

    Long getFamilyTotalCount();

}
