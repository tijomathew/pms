package org.pms.controllers;

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


    @RequestMapping(value = "/viewParish.action", method = RequestMethod.GET)
    public String parishPageDisplay(Model model) {
        Long parishCounter = parishService.getParishCount();
        Parish formBackParish = new Parish();
        formBackParish.setParishID("PA" + (++parishCounter));
        model.addAttribute("parish", formBackParish);
        return "parish";
    }

    @RequestMapping(value = "/addParish.action", method = RequestMethod.POST)
    public String addParish(@ModelAttribute("parish") Parish parish, Model model) {
        List<Long> allActivePriestsIDs = priestService.getAllPriestsIDsSM();
        Map<Long, String> mappedPriestDesignations = new HashMap<Long, String>();
        for (Long priestID : allActivePriestsIDs) {
            if (request.getParameter(priestID.toString()) != null) {
                mappedPriestDesignations.put(priestID, request.getParameter(priestID.toString()));
            }
        }
        model.addAttribute("parish", new Parish());

        String[] priestsForParish = request.getParameterValues("priest");
        for (String priestID : priestsForParish) {
            Priest priest = priestService.getPriestForPriestIDSM(Long.valueOf(priestID));
            priest.setParish(parish);
            List<PriestDesignation> temporaryPriestDesignationList = new ArrayList<PriestDesignation>();
            if (mappedPriestDesignations.containsKey(Long.valueOf(priestID))) {
                String priestDesignationFromMap = mappedPriestDesignations.get(Long.valueOf(priestID));
                PriestDesignation priestDesignation = new PriestDesignation(priestDesignationFromMap, priest, priest.getId());
                temporaryPriestDesignationList.add(priestDesignation);
            }
            if (!temporaryPriestDesignationList.isEmpty()) {
                priest.setMappedPriestDesignations(temporaryPriestDesignationList);
            } else {
                throw new IllegalArgumentException("parish cannot be added without at least a priest!!...");
            }
            parish.addPriestsForParish(priest);
        }
        parishService.addParishSM(parish);
        return "parish";
    }

    @RequestMapping(value = "displayParishGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForParish(@RequestParam(value = "rows",required = false) Integer rows,@RequestParam(value = "page",required = false) Integer page) {
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

        JsonBuilder jsonBuilder = new JsonBuilder();
        return jsonBuilder.convertToJson(resultContainer);
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
}
