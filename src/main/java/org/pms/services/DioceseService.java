package org.pms.services;

import org.pms.models.Diocese;
import org.pms.models.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by tijo on 19/03/17.
 */
public interface DioceseService {

    Boolean addDiocese(Diocese diocese);

    void setDioceseNumber(Diocese diocese);

    void createDioceseFormBackObject(Model modelMap);

    List<Diocese> getAllDioceseForUserRole(User currentUser);

    List<Diocese> getAllDiocese();

    List<Diocese> getAllDioceseForDioceseID(Long dioceseID);
}
