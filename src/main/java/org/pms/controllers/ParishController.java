package org.pms.controllers;

import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.displaywrappers.ParishWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * This class is the controller of the Parish module.
 * User: tijo
 */

@Controller
public class ParishController extends AbstractErrorAndGridHandler {

    @Autowired
    private ParishService parishService;

    @Autowired
    private PriestService priestService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    MessageSource messageSource;


    @RequestMapping(value = "/viewparish.action", method = RequestMethod.GET)
    public String parishPageDisplay(Model model) {
        model.addAttribute("parish", new Parish());
        model.addAttribute("showAddButton", true);
        return PageName.PARISH.toString();
    }

    @RequestMapping(value = "/addparish.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addParish(Model model, @ModelAttribute("parish") @Valid Parish parish, BindingResult result) {

        if (!result.hasErrors()) {
            Long parishCounter = parishService.getParishCount();
            parish.setParishNo(++parishCounter);
            parishService.addParishSM(parish);
            model.addAttribute("showAddButton", true);
            customResponse = createSuccessMessage(StatusCode.SUCCESS, parish.getParishName(), "added in to the system");
        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }
        return customResponse;
    }

    @RequestMapping(value = "displayparishgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForParish(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<Parish> allParishes = new ArrayList<>();
        Long totalParishCount = 0l;
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        if (currentUser.getSystemRole() == SystemRole.ADMIN) {
            allParishes = parishService.getAllParish();
            totalParishCount = parishService.getParishCount();
        } else if (currentUser.getSystemRole() == SystemRole.PARISH_ADMIN) {
            allParishes.add(parishService.getParishForIDSM(currentUser.getParishId()));
            //since user is parish Admin, only one parish will be assigned to the user. So data base access is not required to show the total count in the UI.
            totalParishCount = 1l;
        }

        List<GridRow> parishGridRows = new ArrayList<GridRow>(allParishes.size());


        List<Parish> allUsersSubList = new ArrayList<Parish>();
        if (allParishes.size() > 0) {
            allUsersSubList = JsonBuilder.generateSubList(page, rows, totalParishCount.intValue(), allParishes);
        }

        for (Parish parish : allUsersSubList) {
            parishGridRows.add(new ParishWrapper(parish));
        }


        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalParishCount.intValue(), page, rows, parishGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }

    @RequestMapping(value = "/createpriestdesignationbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object createPriestDesignationBox() {
        List<Priest> allPriests = priestService.getAllPriestSM();
        List<PriestDesignationBox<String>> priestDesignationBoxList = new ArrayList<PriestDesignationBox<String>>();
        for (Priest priest : allPriests) {
            Person priestAsPerson = priest.getPriestAsPerson();
            priestDesignationBoxList.add(new PriestDesignationBox<String>(priestAsPerson.getFirstName() + priestAsPerson.getLastName(), priest.getId().toString(), "Not Selected"));
        }
        return new PriestDesignationBox<String>().getJsonForPriestDesignationBoxCreation(priestDesignationBoxList);
    }

    @RequestMapping(value = "/editparishdetails.action", method = RequestMethod.GET)
    public String editParishInformation(@RequestParam(value = "parishName", required = true) Long parishName, Model model) {
        Parish parishToEdit = parishService.getParishForIDSM(parishName);
        model.addAttribute("parish", parishToEdit);
        model.addAttribute("showUpdateButton", true);
        return PageName.PARISH.toString();
    }

    @RequestMapping(value = "/updateparishinformation.action", method = RequestMethod.POST)
    public String updateParish(@ModelAttribute("parish") Parish parish, Model model) {
        parishService.addParishSM(parish);
        model.addAttribute("showUpdateButton", false);
        return PageName.PARISH.toString();
    }

    /*@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new ParishValidator());
    }*/


}
