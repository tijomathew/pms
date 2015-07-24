package org.pms.serviceImpls;

import org.pms.daos.PriestDao;
import org.pms.models.Person;
import org.pms.models.Priest;
import org.pms.models.PriestDesignation;
import org.pms.services.PriestService;
import org.pms.utils.DisplayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the implementation for the Priest Service contract.
 * User: tijo
 */

@Service
@Transactional
public class PriestServiceImpl implements PriestService {

    @Autowired
    private PriestDao priestDao;

    @Override
    public boolean addPriestSM(Priest priest) {
        priestDao.addPriestDM(priest);
        return true;
    }

    @Override
    public List<Priest> getAllPriestSM() {
        return priestDao.getAllPriest();
    }

    @Override
    public Priest getPriestForPriestIDSM(Long id) {
        return priestDao.getPriestForIDDM(id);
    }

    @Override
    public Long getTotalCountOfPriestSM() {
        return priestDao.getTotalCountOfPriestDM();
    }

    @Override
    public Long getHighestAutoIDSM() {
        Long priestID = priestDao.getHighestAutoIDDM();
        if (priestID == null) {
            priestID = 0l;
        }
        ++priestID;
        return priestID;
    }

    @Override
    public List<Long> getAllPriestsIDsSM() {
        return priestDao.getAllPriestsIDsDM();
    }

    @Override
    public boolean addPriestDesignation(PriestDesignation priestDesignation) {
        return priestDao.addPriestDesignation(priestDesignation);
    }
}
