package org.pms.serviceImpls;

import org.pms.daos.PriestDao;
import org.pms.enums.*;
import org.pms.models.Person;
import org.pms.models.Priest;
import org.pms.models.PriestDesignation;
import org.pms.services.PriestService;
import org.pms.utils.DisplayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Override
    public Model createPriestFormBackObject(Model model) {
        Priest formDisplayPriest = new Priest();
        model.addAttribute("priest", formDisplayPriest);

        Predicate<PersonSalutation> includeRev = p -> p.name().equalsIgnoreCase(PersonSalutation.REV.toString());
        Predicate<PersonSalutation> includeRevDr = p -> p.name().equalsIgnoreCase(PersonSalutation.REV_DR.toString());

        Predicate<PersonSalutation> includeOnlyPriestSalutation = includeRev.or(includeRevDr);

        Predicate<PersonalStatus> includePriestStatus = p -> p.name().equalsIgnoreCase(PersonalStatus.PRIEST.toString());

        Predicate<PriestDesignations> excludeInCharge = p -> !(p.name().equalsIgnoreCase(PriestDesignations.IN_CHARGE.toString()));

        Predicate<PriestDesignations> excludeAssistant = p -> !(p.name().equalsIgnoreCase(PriestDesignations.ASSISTANT.toString()));

        Predicate<PriestDesignations> excludeInChargeAndAssistant = excludeInCharge.and(excludeAssistant);

        model.addAttribute("priestDesignation", Arrays.stream(PriestDesignations.values()).filter(excludeInChargeAndAssistant).collect(Collectors.toMap(PriestDesignations::name, PriestDesignations::getUIDisplayValue)));
        model.addAttribute("sex", Arrays.stream(Gender.values()).collect(Collectors.toMap(Gender::name, Gender::getUIDisplayValue)));
        model.addAttribute("priestSalutation", Arrays.stream(PersonSalutation.values()).filter(includeOnlyPriestSalutation).collect(Collectors.toMap(PersonSalutation::name, PersonSalutation::getUIDisplayValue)));
        model.addAttribute("priestStatus", Arrays.stream(PriestStatus.values()).collect(Collectors.toMap(PriestStatus::name, PriestStatus::getUIDisplayValue)));
        model.addAttribute("lifeStatus", Arrays.stream(LifeStatus.values()).collect(Collectors.toMap(LifeStatus::name, LifeStatus::getUIDisplayValue)));
        model.addAttribute("personalStatus", Arrays.stream(PersonalStatus.values()).filter(includePriestStatus).collect(Collectors.toMap(PersonalStatus::name, PersonalStatus::getUIDisplayValue)));
        model.addAttribute("bloodGroup", Arrays.stream(BloodGroup.values()).collect(Collectors.toMap(BloodGroup::name, BloodGroup::getUIDisplayValue)));

        return model;
    }
}
