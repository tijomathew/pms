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
import java.util.Collections;
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

    @RequestMapping(value = "/viewmasscentre.action", method = RequestMethod.GET)
    public String viewMassCentreDisplay(Model modelMap) {

        massCentreService.createMassCentreFormBackObject(modelMap);
        return PageName.MASSCENTRE.toString();
    }

    @RequestMapping(value = "/addmasscentre.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addMassCentre(@ModelAttribute("massCentre") @Valid MassCentre massCentre, BindingResult result) {

        if (!result.hasErrors()) {

            if (massCentre.getId() == null && massCentre.getMassCentreNo() == null) {

                massCentre.getMappedParish().addMassCentresForParish(massCentre);

                massCentreService.setMassCentreNumber(massCentre);

                User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

                if (currentUser.getSystemRole() != SystemRole.MASS_CENTER_ADMIN) {
                    massCentreService.addMassCentre(massCentre);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, massCentre.getMassCentreName(), SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a mass center as a MC admin in the system.");
                }
            } else {
                MassCentre retrievedMassCentre = massCentreService.getMassCentreForIDSM(massCentre.getId());

                if (!retrievedMassCentre.getMappedParish().equals(massCentre.getMappedParish())) {
                    massCentreService.setMassCentreNumber(massCentre);
                }
                massCentreService.updateMassCentre(massCentre);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, massCentre.getMassCentreName(), "updated successfully!");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displaymasscentregrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMassCentre(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<MassCentre> allMassCentres = massCentreService.getAllMassCentresForUserRole(currentUser);
        Integer massCentreCount = allMassCentres.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<MassCentre> allUsersSubList = new ArrayList<>();
        if (massCentreCount > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allMassCentres, formatter.by(sortIndexColumnName, MassCentre.class));
            }
            allUsersSubList = JsonBuilder.generateSubList(page, rows, massCentreCount, allMassCentres);
        }

        List<GridRow> massCentreGridRows = new ArrayList<>(allMassCentres.size());
        if (!allUsersSubList.isEmpty()) {
            massCentreGridRows = allUsersSubList.stream().map(masscentre -> new MassCentreWrapper(masscentre)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(massCentreCount, page, rows, massCentreGridRows));
    }

    @RequestMapping(value = "/createmasscentreselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateMassCentreSelectBox(@RequestParam(value = "selectedParishId", required = true) Long selectedParishID) {
        String returnObject = StringUtils.EMPTY;
        if (selectedParishID != 0l) {
            List<MassCentre> massCentreListForParishID = massCentreService.getAllMassCentresForParishID(selectedParishID);
            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
            if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
                massCentreListForParishID = massCentreListForParishID.stream().filter(massCentre -> massCentre.getMassCentreName().equalsIgnoreCase(currentUser.getUsersOfMassCentres().getMassCentreName())).collect(Collectors.toList());
            }
            List<SelectBox<String, Long>> selectBoxList = massCentreListForParishID.stream().map(massCentre -> new SelectBox<>(massCentre.getMassCentreName(), massCentre.getId())).collect(Collectors.toList());
            returnObject = SelectBox.getJsonForSelectBoxCreation(selectBoxList);
        }
        return returnObject;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }

}
