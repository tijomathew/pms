package org.pms.daos;

import org.pms.models.Parish;

import java.util.List;

/**
 * This interface is the contract for the Mass Center Dao.
 * User: tijo
 */
public interface ParishDao {

    Boolean addParish(Parish parish);

    List<Parish> getAllParish();

    Parish getParishForID(Long id);

    Long getParishCount();

    Boolean updateParish(Parish parish);

}
