package org.pms.controllers;

import org.apache.commons.lang3.StringUtils;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.enums.*;
import org.pms.displaywrappers.MassCentreWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.MassCentreService;
import org.pms.services.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the controller of the Mass Center module.
 * User: tijo
 */

@Controller
public class MassCentreController extends AbstractErrorAndGridHandler {

    @Autowired
    private MassCentreService massCentreService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewmasscenter.action", method = RequestMethod.GET)
    public String massCenterDisplay(Model modelMap) {

        massCentreService.createMassCenterFormBackObject(modelMap);
        return PageName.MASSCENTRE.toString();
    }

    @RequestMapping(value = "/addmasscenter.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addMassCenter(@ModelAttribute("massCenter") @Valid MassCentre massCentre, BindingResult result) {

        if (!result.hasErrors()) {

            massCentre.getMappedParish().addMassCentersForParish(massCentre);

            Long massCenterCount = massCentreService.getMassCenterCountForParish(massCentre.getMappedParish().getId());

            massCentre.setMassCenterNo(++massCenterCount);

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            if (currentUser.getSystemRole() != SystemRole.MASS_CENTER_ADMIN) {
                massCentreService.addMassCenterSM(massCentre);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, massCentre.getMassCenterName(), SUCCESS_MESSAGE_DISPLAY);
            } else {
                customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a mass center as a MC admin in the system.");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displaymasscentregrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMassCenter(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<MassCentre> allMassCentres = massCentreService.getAllMassCentersForUserRole(currentUser);
        Integer massCenterCount = allMassCentres.size();

        List<MassCentre> allUsersSubList = new ArrayList<>();
        if (massCenterCount > 0) {
            allUsersSubList = JsonBuilder.generateSubList(page, rows, massCenterCount, allMassCentres);
        }

        List<GridRow> massCenterGridRows = new ArrayList<>(allMassCentres.size());
        if (!allUsersSubList.isEmpty()) {
            massCenterGridRows = allUsersSubList.stream().map(masscenter -> new MassCentreWrapper(masscenter)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(massCenterCount, page, rows, massCenterGridRows));
    }

    @RequestMapping(value = "/createmasscentreselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateMassCenterSelectBox(@RequestParam(value = "selectedParishId", required = true) Long selectedParishID) {
        String returnObject = StringUtils.EMPTY;
        if (selectedParishID != 0l) {
            List<MassCentre> massCentreListForParishID = massCentreService.getAllMassCentersForParishID(selectedParishID);
            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
            if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
                massCentreListForParishID = massCentreListForParishID.stream().filter(massCenter -> massCenter.getMassCenterName().equalsIgnoreCase(currentUser.getUsersOfMassCenters().getMassCenterName())).collect(Collectors.toList());
            }
            List<SelectBox<String, Long>> selectBoxList = massCentreListForParishID.stream().map(massCenter -> new SelectBox<>(massCenter.getMassCenterName(), massCenter.getId())).collect(Collectors.toList());
            returnObject = SelectBox.getJsonForSelectBoxCreation(selectBoxList);
        }
        return returnObject;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }

}
