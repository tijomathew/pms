package org.pms.controllers;

import org.pms.enums.*;
import org.pms.displaywrappers.PriestWrapper;
import org.pms.error.AbstractErrorHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.models.Parish;
import org.pms.models.Priest;
import org.pms.models.PriestDesignation;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class is the controller of the Priest module.
 * User: tijo
 */

@Controller
public class PriestController extends AbstractErrorHandler {

    @Autowired
    private PriestService priestService;

    @Autowired
    private ParishService parishService;


    @RequestMapping(value = "/viewpriest.action", method = RequestMethod.POST)
    public String priestPageDisplay(Model model) {

        createPriestFormBackObject(model);

        return PageName.PRIEST.toString();
    }


    @RequestMapping(value = "/viewpriest.action", method = RequestMethod.GET)
    public String priestStaticPageDisplay(Model model) {

        createPriestFormBackObject(model);

        return PageName.PRIEST.toString();
    }

    @RequestMapping(value = "/addpriest.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addPriest(Model model, @ModelAttribute("priest") @Valid Priest priest, BindingResult result) {

        if (!result.hasErrors()) {

            Long priestAutoID = priestService.getHighestAutoIDSM();

            priest.setPriestNo(priestAutoID);

            priestService.addPriestSM(priest);

            createPriestFormBackObject(model);
            customResponse = createSuccessMessage(StatusCode.SUCCESS, priest.getPriestAsPerson().getFirstName(), "added in to the system");
        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "/displaypriestgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForPriest(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<Priest> allPriest = priestService.getAllPriestSM();
        Integer totalPriestsCount = priestService.getTotalCountOfPriestSM().intValue();

        List<Priest> allUsersSublist = new ArrayList<Priest>();
        if (totalPriestsCount > 0) {
            allUsersSublist = JsonBuilder.generateSubList(page, rows, totalPriestsCount.intValue(), allPriest);
        }

        List<GridRow> priestGridRows = new ArrayList<GridRow>(allPriest.size());
        for (Priest priest : allUsersSublist) {
            priestGridRows.add(new PriestWrapper(priest));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalPriestsCount, page, rows, priestGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }

    private void createPriestFormBackObject(Model model) {
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
    }


}
