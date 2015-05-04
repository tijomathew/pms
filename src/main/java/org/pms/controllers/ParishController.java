package org.pms.controllers;

import org.pms.constants.PageNames;
import org.pms.displaywrappers.ParishWrapper;
import org.pms.dtos.ParishDto;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        parishService.createParishFormBackObjectModel(model);
        model.addAttribute("showAddButton", true);
        return PageNames.PARISH;
    }

    @RequestMapping(value = "/addParish.action", method = RequestMethod.POST)
    public String addParish(@ModelAttribute("parish") Parish parish, Model model) {
        parishService.addParishSM(parish);
        parishService.createParishFormBackObjectModel(model);
        model.addAttribute("showAddButton", true);
        return PageNames.PARISH;
    }

    @RequestMapping(value = "displayParishGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForParish(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<Parish> allParishes = parishService.getAllParish();
        List<ParishDto> parishDtoList = parishService.createParishDto(allParishes);
        List<GridRow> parishGridRows = new ArrayList<GridRow>(parishDtoList.size());
        Long totalParishCount = parishService.getParishCount();

        List<ParishDto> allUsersSublist = new ArrayList<ParishDto>();
        if (allParishes.size() > 0) {
            allUsersSublist = JsonBuilder.generateSubList(page, rows, totalParishCount.intValue(), parishDtoList);
        }

        for (ParishDto parishDto : allUsersSublist) {
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
        return PageNames.PARISH;
    }

    @RequestMapping(value = "/updateparishinformation.action", method = RequestMethod.POST)
    public String updateParish(@ModelAttribute("parish") Parish parish, Model model) {
        parishService.addParishSM(parish);
        parishService.createParishFormBackObjectModel(model);
        model.addAttribute("showUpdateButton", false);
        return PageNames.PARISH;
    }


}
