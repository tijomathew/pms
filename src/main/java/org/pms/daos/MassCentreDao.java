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

    List<MassCentre> getAllMassCentresForParishID(Long parishAutoID);

    Long getMassCentreCountForParish(Long parishId);

    Boolean updateMassCentre(MassCentre massCentre);

    MassCentre getMassCentreByMassCentreID(String massCentreID);

    Long getAllMassCentreCount();

    List<Long> getAllMassCentreIdsForParish(Long parishId);
}
