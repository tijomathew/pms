package org.pms.daos;

import org.pms.models.Priest;
import org.pms.models.PriestDesignation;

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

    boolean addPriestDesignation(PriestDesignation priestDesignation);
}
