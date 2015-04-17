package org.pms.services;

import org.pms.dtos.ParishDto;
import org.pms.models.Parish;
import org.pms.models.Priest;

import java.util.List;

/**
 * This interface is the contract for the Parish Service.
 * User: tijo
 */
public interface ParishService {

    boolean addParishSM(Parish parish);

    List<Parish> getAllParish();

    Parish getParishForIDSM(Long id);

    List<ParishDto> createParishDto(List<Parish> parishList) throws IllegalArgumentException;

    Long getParishCount();

    void updateParish(Parish parish);

    Parish getParishByParishID(String parishID);
}
