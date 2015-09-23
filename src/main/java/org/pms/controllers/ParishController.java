package org.pms.controllers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.displaywrappers.ParishWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is the controller of the Parish module.
 * User: tijo
 */

@Controller
public class ParishController extends AbstractErrorAndGridHandler {

    @Autowired
    private ParishService parishService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewparish.action", method = RequestMethod.GET)
    public String viewParishPageDisplay(Model model) {
        Parish modelBackObject = new Parish();
        modelBackObject.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("parish", modelBackObject);
        return PageName.PARISH.toString();
    }

    @RequestMapping(value = "/addparish.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addParish(@ModelAttribute("parish") @Valid Parish parish, BindingResult result) {

        if (!result.hasErrors()) {

            if (parish.getId() == null && parish.getParishNo() == null) {
                Long parishCounter = parishService.getParishCount();
                parish.setParishNo(++parishCounter);

                User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

                if (currentUser.getSystemRole() != SystemRole.PARISH_ADMIN) {
                    parishService.addParish(parish);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, parish.getParishName(), SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a parish by a Parish Admin in the system.");
                }
            } else {
                parishService.updateParish(parish);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, parish.getParishName(), "updated successfully");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }
        return customResponse;
    }

    @RequestMapping(value = "displayparishgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForParish(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Parish> allParishes = parishService.getAllParishForUserRole(currentUser);
        Integer totalParishCount = allParishes.size();

        List<GridRow> parishGridRows = new ArrayList<GridRow>(allParishes.size());
        List<Parish> allUsersSubList = new ArrayList<Parish>();

        if (totalParishCount > 0) {
            allUsersSubList = JsonBuilder.generateSubList(page, rows, totalParishCount, allParishes);
        }

        if (!allUsersSubList.isEmpty()) {
            parishGridRows = allUsersSubList.stream().map(parish -> new ParishWrapper(parish)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalParishCount, page, rows, parishGridRows));
    }

    @RequestMapping(value = "createparishselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateParishSelectBox() {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);

        List<SelectBox<String, Long>> selectBoxList = parishList.stream().map(parish -> new SelectBox<>(parish.getParishName(), parish.getId())).collect(Collectors.toList());

        return SelectBox.getJsonForSelectBoxCreation(selectBoxList);
    }

}
