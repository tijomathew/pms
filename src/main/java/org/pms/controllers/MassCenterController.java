package org.pms.controllers;

import org.apache.commons.lang3.StringUtils;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.enums.*;
import org.pms.displaywrappers.MassCenterWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.MassCenterService;
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
public class MassCenterController extends AbstractErrorAndGridHandler {

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

        if (!result.hasErrors()) {

            //add mass center to the parish mass center list.
           massCenter.getMappedParish().addMassCentersForParish(massCenter);

           Long massCenterCount = massCenterService.getMassCenterCountForParish(massCenter.getMappedParish().getId());

           massCenter.setMassCenterNo(++massCenterCount);

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
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

            customResponse = createSuccessMessage(StatusCode.SUCCESS, massCenter.getMassCenterName(), SUCCESS_MESSAGE_DISPLAY);
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
        Integer massCenterCount = allMassCenters.size();

        List<MassCenter> allUsersSubList = new ArrayList<>();
        if (massCenterCount > 0) {
            allUsersSubList = JsonBuilder.generateSubList(page, rows, massCenterCount, allMassCenters);
        }

        List<GridRow> massCenterGridRows = new ArrayList<>(allMassCenters.size());
        if (!allUsersSubList.isEmpty()) {
            massCenterGridRows = allUsersSubList.stream().map(masscenter -> new MassCenterWrapper(masscenter)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(massCenterCount, page, rows, massCenterGridRows));
    }

    @RequestMapping(value = "/createmasscenterselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateMassCenterSelectBox(@RequestParam(value = "selectedParishId", required = true) Long selectedParishID) {
        String returnObject = StringUtils.EMPTY;
        if (selectedParishID != 0l) {
            List<MassCenter> massCenterListForParishID = massCenterService.getMassCenterForParishID(selectedParishID);
            List<SelectBox<String, Long>> selectBoxList = massCenterListForParishID.stream().map(massCenter -> new SelectBox<>(massCenter.getMassCenterName(), massCenter.getId())).collect(Collectors.toList());
            returnObject = SelectBox.getJsonForSelectBoxCreation(selectBoxList);
        }
        return returnObject;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }

}
