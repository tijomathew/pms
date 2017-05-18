package org.pms.daos;

import org.pms.models.Diocese;

import java.util.List;

/**
 * Created by tijo on 19/03/17.
 */
public interface DioceseDao {

    boolean addDiocese(Diocese diocese);

    Long getDioceseCountInSystem();

    List<Diocese> getAllDiocese();

    List<Diocese> getDioceseForDioceseIDDM(Long dioceseID);

}
