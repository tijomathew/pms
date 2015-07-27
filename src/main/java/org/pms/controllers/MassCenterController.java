package org.pms.controllers;

import org.pms.enums.*;
import org.pms.displaywrappers.MassCenterWrapper;
import org.pms.error.AbstractErrorHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the controller of the Mass Center module.
 * User: tijo
 */

@Controller
public class MassCenterController extends AbstractErrorHandler {

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewmasscenter.action", method = RequestMethod.GET)
    public String massCenterDisplay(Model modelMap) {
        massCenterService.createMassCenterFormBackObject(modelMap);
        return PageName.MASSCENTER.toString();
    }

    @RequestMapping(value = "/addmasscenter.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addMassCenter(Model modelMap, @ModelAttribute("massCenter") @Valid MassCenter massCenter, BindingResult result) {

        modelMap.addAttribute("massCenter", new MassCenter());

        if (!result.hasErrors()) {

            //get the parish for mass center to map with mass center and parish.
            Parish parish = parishService.getParishForIDSM(massCenter.getParish());

            //set the parish to the mass center.
            massCenter.setMappedParish(parish);

            //add mass center to the parish mass center list.
            parish.addMassCentersForParish(massCenter);

            Long massCenterCount = massCenterService.getMassCenterCountForParish(parish.getId());

            massCenter.setMassCenterNo(++massCenterCount);

            User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRole.PMS_CURRENT_USER.toString());
            boolean permissionDenied = false;

            if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
                permissionDenied = true;
            }
            //save the mass center with its various relationships.
            if (!permissionDenied) {
                massCenterService.addMassCenterSM(massCenter);
            } else {
                // show the error that mass center cannot be add by mass center admin. He can edit only his mass center information.
            }

            massCenterService.createMassCenterFormBackObject(modelMap);
            customResponse = createSuccessMessage(StatusCode.SUCCESS, massCenter.getMassCenterName(), "added in to the system");
        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displaymasscentergrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMassCenter(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<MassCenter> allMassCenters = massCenterService.getAllMassCentersForUserRole(currentUser);
        Long massCenterCount = Long.valueOf(allMassCenters.size());

        List<MassCenter> allUsersSubList = new ArrayList<MassCenter>();
        if (massCenterCount > 0) {
            allUsersSubList = JsonBuilder.generateSubList(page, rows, massCenterCount.intValue(), allMassCenters);
        }

        List<GridRow> massCenterGridRows = new ArrayList<GridRow>(allMassCenters.size());
        for (MassCenter massCenter : allUsersSubList) {
            massCenterGridRows.add(new MassCenterWrapper(massCenter));
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
                if (priest.getPriestStatus() == PriestStatus.ACTIVE) {
                    Person priestAsPerson = priest.getPriestAsPerson();
                    priestDesignationBoxList.add(new PriestDesignationBox<String>(priestAsPerson.getFirstName() + priestAsPerson.getLastName(), priest.getId().toString(), "Not Selected"));
                }
            }
            return new PriestDesignationBox<String>().getJsonForPriestDesignationBoxCreation(priestDesignationBoxList);
        }
        return "";
    }

}
