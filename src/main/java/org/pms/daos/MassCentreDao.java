package org.pms.daos;

import org.pms.models.MassCentre;

import java.util.List;

/**
 * This interface is the contract for the Mass Center Dao.
 * User: tijo
 */
public interface MassCentreDao {

    Boolean addMassCentre(MassCentre massCentre);

    List<MassCentre> getAllMassCentres();

    MassCentre getMassCentreForID(Long id);

    Long getMassCentreCount();

    Boolean updateMassCentre(MassCentre massCentre);

}
