package org.pms.daos;

import org.pms.models.Priest;

import java.util.List;

/**
 * This interface is the contract for the Priest Dao.
 * User: tijo
 */
public interface PriestDao {

    boolean addPriestDM(Priest priest);

    List<Priest> getAllPriest();

    Priest getPriestForIDDM(Long id);

    Long getTotalCountOfPriestDM();

    Long getHighestAutoIDDM();

    List<Long> getAllPriestsIDsDM();
}
