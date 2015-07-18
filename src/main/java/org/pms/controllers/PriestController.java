package org.pms.controllers;

import org.pms.enums.*;
import org.pms.displaywrappers.PriestWrapper;
import org.pms.dtos.PriestDto;
import org.pms.error.AbstractErrorHandler;
import org.pms.error.CustomErrorMessage;
import org.pms.error.CustomResponse;
import org.pms.error.StatusCode;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        /*model.addAttribute("loginUser", new User());
        model.addAttribute("error", "please errorrrrr");
        mailService.sendUserCredentials(new User());*/

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
            Parish mappedParish = parishService.getParishForIDSM(priest.getParishId());
            priest.setParish(mappedParish);
            mappedParish.addPriestsForParish(priest);

            Long priestAutoID = priestService.getHighestAutoIDSM();

            priest.setPriestID(priestAutoID);

            PriestDesignation priestDesignation = new PriestDesignation();
            priestDesignation.setDesignation(priest.getDesignation());
            priestDesignation.setParishId(mappedParish.getParishID());
            priestDesignation.setPriestId(priest.getPriestID());

            priestService.addPriestDesignation(priestDesignation);
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

        List<PriestDto> allPriestDtoList = priestService.createPriestDto(allPriest);


        List<PriestDto> allUsersSublist = new ArrayList<PriestDto>();
        if (totalPriestsCount > 0) {
            allUsersSublist = JsonBuilder.generateSubList(page, rows, totalPriestsCount.intValue(), allPriestDtoList);
        }

        List<GridRow> priestGridRows = new ArrayList<GridRow>(allPriest.size());
        for (PriestDto priestDto : allUsersSublist) {
            priestGridRows.add(new PriestWrapper(priestDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalPriestsCount, page, rows, priestGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }

    private void createPriestFormBackObject(Model model) {
        Priest formDisplayPriest = new Priest();
        model.addAttribute("priest", formDisplayPriest);

        Map<Long, String> parishMap = new HashMap<Long, String>();
        List<Parish> addedParishes = parishService.getAllParish();
        parishMap.put(0l, "--Please Select--");
        for (Parish parish : addedParishes)
            parishMap.put(parish.getId(), parish.getName());

        model.addAttribute("parishList", parishMap);
        model.addAttribute("priestDesignation", PriestDesignations.values());


        model.addAttribute("sex", Gender.values());
        model.addAttribute("priestSalutation", PriestSalutation.values());
        model.addAttribute("priestStatus", PriestStatus.values());
        model.addAttribute("lifeStatus", LifeStatus.values());
        model.addAttribute("personalStatus", PersonalStatus.values());
        model.addAttribute("bloodGroup", BloodGroup.values());
    }

}
