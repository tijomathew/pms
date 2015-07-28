package org.pms.controllers;

import org.pms.enums.*;
import org.pms.displaywrappers.PriestWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.models.Priest;
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
public class PriestController extends AbstractErrorAndGridHandler {

    @Autowired
    private PriestService priestService;

    @RequestMapping(value = "/viewpriest.action", method = RequestMethod.GET)
    public String priestStaticPageDisplay(Model model) {

        priestService.createPriestFormBackObject(model);

        return PageName.PRIEST.toString();
    }

    @RequestMapping(value = "/addpriest.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addPriest(@ModelAttribute("priest") @Valid Priest priest, BindingResult result) {

        if (!result.hasErrors()) {

            Long priestAutoID = priestService.getHighestAutoIDSM();

            priest.setPriestNo(priestAutoID);

            priestService.addPriestSM(priest);

            customResponse = createSuccessMessage(StatusCode.SUCCESS, priest.getPriestAsPerson().getFirstName(), SUCCESS_MESSAGE_DISPLAY);
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
        Integer totalPriestsCount = allPriest.size();

        List<Priest> allPriestsSubList = new ArrayList<Priest>();
        if (totalPriestsCount > 0) {
            allPriestsSubList = JsonBuilder.generateSubList(page, rows, totalPriestsCount, allPriest);
        }

        List<GridRow> priestGridRows = new ArrayList<GridRow>(allPriest.size());
        if (!allPriestsSubList.isEmpty()) {
            priestGridRows = allPriestsSubList.stream().map(priest -> new PriestWrapper(priest)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalPriestsCount, page, rows, priestGridRows));
    }

}
