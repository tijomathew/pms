package org.pms.controllers;

import org.pms.displaywrappers.MassCenterWrapper;
import org.pms.dtos.MassCenterDto;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.models.*;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the controller of the Mass Center module.
 * User: tijo
 */

@Controller
public class MassCenterController {

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PriestService priestService;

    @RequestMapping(value = "/viewMassCenter.action", method = RequestMethod.GET)
    public String massCenterDisplay(Model modelMap) {
        MassCenter formBackMassCenter = new MassCenter();
        Long massCenterCount = massCenterService.getMassCenterCount();
        formBackMassCenter.setMassCenterID("MC" + (++massCenterCount));
        modelMap.addAttribute("massCenter", formBackMassCenter);
        Map<Long, String> parishMap = new HashMap<Long, String>();
        parishMap.put(0l, "--please select--");
        List<Parish> parishList = parishService.getAllParish();
        if (!parishList.isEmpty()) {
            for (Parish parish : parishList)
                parishMap.put(parish.getId(), parish.getName());
        }
        modelMap.addAttribute("parishList", parishMap);
        return "massCenter";
    }

    @RequestMapping(value = "/addMassCenter.action", method = RequestMethod.POST)
    public String addMassCenter(@ModelAttribute("massCenter") MassCenter massCenter, Model modelMap) {
        modelMap.addAttribute("massCenter", new MassCenter());
        Parish parish = parishService.getParishForIDSM(massCenter.getParish());
        massCenter.setMappedParish(parish);
        parish.addMassCentersForParish(massCenter);
        List<Long> allActivePriestsIDs = priestService.getAllPriestsIDsSM();
        Map<Long, String> mappedPriestDesignations = new HashMap<Long, String>();
        for (Long priestID : allActivePriestsIDs) {
            if (request.getParameter(priestID.toString()) != null) {
                mappedPriestDesignations.put(priestID, request.getParameter(priestID.toString()));
            }
        }

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
        }
        massCenterService.addMassCenterSM(massCenter);
        return "massCenter";
    }

    @RequestMapping(value = "displayMassCenterGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMassCenter() {
        List<MassCenter> allMassCenters = massCenterService.getAllMassCenter();
        List<MassCenterDto> massCenterDtoList = massCenterService.createMassCenterDto(allMassCenters);
        List<GridRow> massCenterGridRows = new ArrayList<GridRow>(massCenterDtoList.size());
        for (MassCenterDto massCenterDto : massCenterDtoList) {
            massCenterGridRows.add(new MassCenterWrapper(massCenterDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(10, 2, 20, massCenterGridRows);

        JsonBuilder jsonBuilder = new JsonBuilder();
        return jsonBuilder.convertToJson(resultContainer);
    }

    @RequestMapping(value = "/createpriestdesignationboxinmasscenter.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object createPriestDesignationBox(@RequestParam(value = "selectedParishId", required = true) Long selectedParishID) {
        if (selectedParishID != 0l) {
            Parish selectedParish = parishService.getParishForIDSM(selectedParishID);
            List<Priest> allPriests = selectedParish.getPriestList();
            List<PriestDesignationBox<String>> priestDesignationBoxList = new ArrayList<PriestDesignationBox<String>>();
            for (Priest priest : allPriests) {
                if (priest.getPriestStatus().equalsIgnoreCase("Active")) {
                    Person priestAsPerson = priest.getPriestAsPerson();
                    priestDesignationBoxList.add(new PriestDesignationBox<String>(priestAsPerson.getFirstName() + priestAsPerson.getLastName(), priest.getId().toString(), "Not Selected"));
                }
            }
            return new PriestDesignationBox<String>().getJsonForPriestDesignationBoxCreation(priestDesignationBoxList);
        }
        return "";
    }
}
