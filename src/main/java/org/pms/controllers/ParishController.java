package org.pms.controllers;

import org.pms.enums.*;
import org.pms.displaywrappers.ParishWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public String viewParishDisplay(Model modelMap) {

        parishService.createParishFormBackObject(modelMap);
        return PageName.PARISH.toString();
    }

    @RequestMapping(value = "/addparish.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addParish(@ModelAttribute("parish") @Valid Parish parish, BindingResult result) {

        if (!result.hasErrors()) {

            if (parish.getId() == null && parish.getParishNo() == null) {

                User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

                if (currentUser.getSystemRole() != SystemRole.PARISH_ADMIN) {
                    parishService.addParish(parish);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, parish.getParsihName(), SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a Parish as a Parish admin in the system.");
                }
            } else {
                Parish retrievedParish = parishService.getParishForIDSM(parish.getId());
                parish.setParishNo(retrievedParish.getParishNo());
                parishService.updateParish(parish);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, parish.getParsihName(), "updated successfully!");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displayparishgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForParish(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Parish> allParishes = parishService.getAllParishForUserRole(currentUser);
        Integer parishCount = allParishes.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<Parish> allUsersSubList = new ArrayList<>();
        if (parishCount > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allParishes, formatter.by(sortIndexColumnName, Parish.class));
            }
            allUsersSubList = JsonBuilder.generateSubList(page, rows, parishCount, allParishes);
        }

        List<GridRow> parishGridRows = new ArrayList<>(allParishes.size());
        if (!allUsersSubList.isEmpty()) {
            parishGridRows = allUsersSubList.stream().map(parish -> new ParishWrapper(parish)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(parishCount, page, rows, parishGridRows));
    }

}
