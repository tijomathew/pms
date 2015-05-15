package org.pms.controllers;

import org.pms.constants.PageNames;
import org.pms.constants.SystemRoles;
import org.pms.displaywrappers.MassCenterWrapper;
import org.pms.dtos.MassCenterDto;
import org.pms.helpers.*;
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

    @Autowired
    private RequestResponseHolder requestResponseHolder;

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

        String attachedStringToID = parish.getParishID() + "-MC";

        //set mass center ID if its not set in the view code.
        if (massCenter.getMassCenterID().isEmpty()) {
            Long massCenterCount = massCenterService.getMassCenterCountForParish(parish.getId());
            if (massCenterCount < 10) {
                attachedStringToID += "0";
            }
            massCenter.setMassCenterID(attachedStringToID + (++massCenterCount));
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

        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRoles.PMS_CURRENT_USER);
        boolean permissionDenied = false;

        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            permissionDenied = true;
        }
        //save the mass center with its various relationships.
        if (!permissionDenied) {
            massCenterService.addMassCenterSM(massCenter);
        } else {
            // show the error that mass center cannot be add by mass center admin. He can edit only his mass center information.
        }

        massCenterService.createMassCenterFormBackObject(modelMap);

        return PageNames.MASSCENTER;
    }

    @RequestMapping(value = "displayMassCenterGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMassCenter(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<MassCenter> allMassCenters = massCenterService.getAllMassCenter();
        List<MassCenterDto> massCenterDtoList = massCenterService.createMassCenterDto(allMassCenters);
        Long massCenterCount = massCenterService.getAllMassCenterCount();
        List<MassCenterDto> allUsersSublist = new ArrayList<MassCenterDto>();
        if (massCenterCount > 0) {
            allUsersSublist = JsonBuilder.generateSubList(page, rows, massCenterCount.intValue(), massCenterDtoList);
        }

        List<GridRow> massCenterGridRows = new ArrayList<GridRow>(massCenterDtoList.size());
        for (MassCenterDto massCenterDto : allUsersSublist) {
            massCenterGridRows.add(new MassCenterWrapper(massCenterDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(massCenterCount.intValue(), page, rows, massCenterGridRows);

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
