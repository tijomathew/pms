package org.pms.controllers;

import org.pms.constants.PageNames;
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

    @RequestMapping(value = "/viewmassCenter.action", method = RequestMethod.GET)
    public String massCenterDisplay(Model modelMap) {
        massCenterService.createMassCenterFormBackObject(modelMap);
        return PageNames.MASSCENTER;
    }

    @RequestMapping(value = "/addmasscenter.action", method = RequestMethod.POST)
    public String addMassCenter(@ModelAttribute("massCenter") MassCenter massCenter, Model modelMap) {
        modelMap.addAttribute("massCenter", new MassCenter());

        //get the parish for mass center to map with mass center and parish.
        Parish parish = parishService.getParishForIDSM(massCenter.getParish());

        //set the parish to the mass center.
        massCenter.setMappedParish(parish);

        //add mass center to the parish mass center list.
        parish.addMassCentersForParish(massCenter);

        //set mass center ID if its not set in the view code.
        if (massCenter.getMassCenterID().isEmpty()) {
            Long massCenterCount = massCenterService.getMassCenterCountForParish(parish.getId());
            massCenter.setMassCenterID("MC" + (++massCenterCount));
        }

        //get all active priests for the respective parish.
        /**
         * TODO
         * add parish id to get the active priests under particular parish.
         */
        List<Long> allActivePriestsIDs = priestService.getAllPriestsIDsSM();

        Map<Long, String> mappedPriestDesignations = new HashMap<>();

        //get priest designations whose designations got selected from the UI and stored in the map.
        for (Long priestID : allActivePriestsIDs) {
            if (request.getParameter(priestID.toString()) != null) {
                mappedPriestDesignations.put(priestID, request.getParameter(priestID.toString()));
            }
        }

        //get the priest IDs who got selected from the UI as incharge for the particular mass center.
        String[] priestsForParish = request.getParameterValues("priest");

        List<PriestDesignation> mappedPriestDesignationList = new ArrayList<>();

        //set the priest designation for priest who got selected from UI using the respective map.
        for (String priestID : priestsForParish) {

            //get the priest using priest ID.
            Priest priest = priestService.getPriestForPriestIDSM(Long.valueOf(priestID));

            //set mass center to the priest.
            priest.setMassCenter(massCenter);

            //set the designation for each priest which are selected from the UI.
            if (mappedPriestDesignations.containsKey(Long.valueOf(priestID))) {
                String priestDesignationFromMap = mappedPriestDesignations.get(Long.valueOf(priestID));

                PriestDesignation priestDesignation = new PriestDesignation();
                priestDesignation.setDesignation(priestDesignationFromMap);
                priestDesignation.setPriestId(priest.getPriestID());
                priestDesignation.setMassCenterId(massCenter.getMassCenterID());

                mappedPriestDesignationList.add(priestDesignation);
            }

        }

        if (!mappedPriestDesignationList.isEmpty()) {
            for (PriestDesignation priestDesignation : mappedPriestDesignationList) {
                priestService.addPriestDesignation(priestDesignation);
            }
        }

        //save the mass center with its various relationships.
        massCenterService.addMassCenterSM(massCenter);

        massCenterService.createMassCenterFormBackObject(modelMap);

        return PageNames.MASSCENTER;
    }

    @RequestMapping(value = "displayMassCenterGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMassCenter(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<MassCenter> allMassCenters = massCenterService.getAllMassCenter();
        List<MassCenterDto> massCenterDtoList = massCenterService.createMassCenterDto(allMassCenters);
        Integer massCenterCount = massCenterService.getMassCenterCount().intValue();
        List<MassCenterDto> allUsersSublist = new ArrayList<MassCenterDto>();
        if (massCenterCount > 0) {
            allUsersSublist = JsonBuilder.generateSubList(page, rows, massCenterCount.intValue(), massCenterDtoList);
        }

        List<GridRow> massCenterGridRows = new ArrayList<GridRow>(massCenterDtoList.size());
        for (MassCenterDto massCenterDto : allUsersSublist) {
            massCenterGridRows.add(new MassCenterWrapper(massCenterDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(massCenterCount, page, rows, massCenterGridRows);

        return JsonBuilder.convertToJson(resultContainer);
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
