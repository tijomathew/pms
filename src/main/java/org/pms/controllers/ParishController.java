package org.pms.controllers;

import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.displaywrappers.ParishWrapper;
import org.pms.dtos.ParishDto;
import org.pms.error.CustomErrorMessage;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * This class is the controller of the Parish module.
 * User: tijo
 */

@Controller
public class ParishController {

    @Autowired
    private ParishService parishService;

    @Autowired
    private PriestService priestService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private RequestResponseHolder requestResponseHolder;


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
        CustomResponse res = null;
        List<CustomErrorMessage> customErrorMessages = new ArrayList<CustomErrorMessage>();
        if (!result.hasErrors()) {
            Long parishCounter = parishService.getParishCount();
            parish.setParishID(++parishCounter);
            parishService.addParishSM(parish);
            model.addAttribute("showAddButton", true);
            customErrorMessages.add(new CustomErrorMessage("success", "successfully added"));
            res = new CustomResponse("SUCCESS", customErrorMessages);
        } else {
            List<FieldError> allErrors = result.getFieldErrors();
            for (FieldError objectError : allErrors) {
                customErrorMessages.add(new CustomErrorMessage(objectError.getField(), objectError.getField() + "  " + objectError.getDefaultMessage()));
            }
            res = new CustomResponse("FAIL", customErrorMessages);
        }
        return res;
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

        List<ParishDto> parishDtoList = parishService.createParishDto(allParishes);
        List<GridRow> parishGridRows = new ArrayList<GridRow>(parishDtoList.size());


        List<ParishDto> allUsersSubList = new ArrayList<ParishDto>();
        if (allParishes.size() > 0) {
            allUsersSubList = JsonBuilder.generateSubList(page, rows, totalParishCount.intValue(), parishDtoList);
        }

        for (ParishDto parishDto : allUsersSubList) {
            parishGridRows.add(new ParishWrapper(parishDto));
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


}
