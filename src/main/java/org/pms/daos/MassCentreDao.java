package org.pms.daos;

import org.pms.models.MassCentre;
import org.pms.models.Parish;

import java.util.List;

/**
 * This interface is the contract for the Mass Center Dao.
 * User: tijo
 */
public interface MassCentreDao {

    boolean addMassCenterDM(MassCentre massCentre);

    List<Parish> getAllParishDM();

    List<MassCentre> getAllMassCenters();

    MassCentre getMassCenterForID(Long id);

    List<MassCentre> getAllMassCentersForParishID(Long parishAutoID);

    Long getMassCenterCountForParish(Long parishId);

    void updateMassCenter(MassCentre massCentre);

    MassCentre getMassCenterByMassCenterID(String massCenterID);

    Long getAllMassCenterCount();

    List<Long> getAllMassCenterIdsForParish(Long parishId);
}
