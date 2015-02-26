package org.pms.serviceImpls;

import org.pms.daos.PriestDao;
import org.pms.dtos.PriestDto;
import org.pms.models.Person;
import org.pms.models.Priest;
import org.pms.services.PriestService;
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
    public Integer getTotalCountOfPriestSM() {
        return priestDao.getTotalCountOfPriestDM();
    }

    @Override
    public List<PriestDto> createPriestDto(List<Priest> allPriests) throws IllegalArgumentException {
        List<PriestDto> priestDtoList = new ArrayList<PriestDto>(allPriests.size());
        if (!allPriests.isEmpty()) {
            Integer uniqueId = 0;
            for (Priest priest : allPriests) {
                Person personIdentity = priest.getPriestAsPerson();
                StringBuilder name = new StringBuilder(personIdentity.getSalutation() + " " + personIdentity.getFirstName() + " " + personIdentity.getLastName());
                priestDtoList.add(new PriestDto(uniqueId, priest.getId(), name.toString(), priest.getCongregation()));
            }
        } else {
            throw new IllegalArgumentException("Priest List cannot be an empty List!!!...");
        }
        return priestDtoList;
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
}
