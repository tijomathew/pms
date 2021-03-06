package org.pms.daos;

import org.pms.models.Parish;
import org.pms.models.Priest;

import java.util.List;

/**
 * This interface is the contract for the Parish Dao.
 * User: tijo
 */
public interface ParishDao {

    Boolean addParish(Parish parish);

    Priest getPriestDM(String priestID);

    boolean updatePriestForParish(Priest priest);

    List<Parish> getAllParish();

    Parish getParishForIDDM(Long id);

    Long getParishCount();

    Boolean updateParish(Parish parish);

    Parish getParishByParishID(String parishID);
}
