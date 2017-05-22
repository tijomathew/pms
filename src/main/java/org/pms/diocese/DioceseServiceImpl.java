package org.pms.diocese;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.domain.Diocese;
import org.pms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 19/03/17.
 */
@Service
@Transactional
public class DioceseServiceImpl implements DioceseService{

    @Autowired
    private DioceseDao dioceseDao;

    @Override
    public Boolean addDiocese(Diocese diocese) {
        dioceseDao.addDiocese(diocese);
        return true;
    }

    @Override
    public void setDioceseNumber(Diocese diocese) {
        Long dioceseCounter = dioceseDao.getDioceseCountInSystem();
        if(dioceseCounter == null){
            dioceseCounter=0l;
        }
        diocese.setDioceseNo(++dioceseCounter);
    }

    @Override
    public void createDioceseFormBackObject(Model modelMap) {
        Diocese formBackDiocese = new Diocese();

        modelMap.addAttribute("diocese", formBackDiocese);

        formBackDiocese.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));

    }

    @Override
    public List<Diocese> getAllDioceseForUserRole(User currentUser) {
        final List<Diocese> allDiocese = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allDiocese.addAll(getAllDiocese());
                break;
            case DIOCESE_ADMIN:
                allDiocese.addAll(getAllDioceseForDioceseID(currentUser.getUsersOfDiocese().getId()));
                break;
        }

        return allDiocese;
    }

    @Override
    public List<Diocese> getAllDiocese() {
        return dioceseDao.getAllDiocese();
    }

    @Override
    public List<Diocese> getAllDioceseForDioceseID(Long dioceseID) {
        return dioceseDao.getDioceseForDioceseIDDM(dioceseID);
    }

}
