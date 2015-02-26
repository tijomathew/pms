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

    Parish getParishDM(String parishID);

    boolean updateParish(Parish parish);

    List<MassCenter> getAllMassCenters();

    MassCenter getMassCenterForID(Long id);

    List<MassCenter> getMassCenterForParishID(Long parishAutoID);

    Long getMassCenterCount();

    void updateMassCenter(MassCenter massCenter);
}
