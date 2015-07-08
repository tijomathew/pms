package org.pms.daos;

import org.pms.models.MassCenter;
import org.pms.models.Parish;

import java.util.List;

/**
 * This interface is the contract for the Mass Center Dao.
 * User: tijo
 */
public interface MassCenterDao {

    boolean addMassCenterDM(MassCenter massCenter);

    List<Parish> getAllParishDM();

    List<MassCenter> getAllMassCenters();

    MassCenter getMassCenterForID(Long id);

    List<MassCenter> getMassCenterForParishID(Long parishAutoID);

    Long getMassCenterCountForParish(Long parishId);

    void updateMassCenter(MassCenter massCenter);

    MassCenter getMassCenterByMassCenterID(String massCenterID);

    Long getAllMassCenterCount();

    List<Long> getAllMassCenterIdsForParish(Long parishId);
}
