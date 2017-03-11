package org.pms.daos;

import org.pms.models.Parish;

import java.util.List;

/**
 * This interface is the contract for the Parish Dao.
 * User: tijo
 */
public interface ParishDao {

    Boolean addParish(Parish parish);

    List<Parish> getAllParish();

    Parish getParishForID(Long id);

    Long getParishCountInSystem();

    Boolean updateParish(Parish parish);

}
