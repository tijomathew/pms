package org.pms.controllers;

import org.pms.enums.PageName;
import org.pms.enums.SystemRole;
import org.pms.displaywrappers.ParishWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String parishPageDisplay(Model model) {

        model.addAttribute("parish", new Parish());
        return PageName.PARISH.toString();
    }

    @RequestMapping(value = "/addparish.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addParish(Model model, @ModelAttribute("parish") @Valid Parish parish, BindingResult result) {

        if (!result.hasErrors()) {
            Long parishCounter = parishService.getParishCount();
            parish.setParishNo(++parishCounter);
            parishService.addParishSM(parish);

            customResponse = createSuccessMessage(StatusCode.SUCCESS, parish.getParishName(), SUCCESS_MESSAGE_DISPLAY);
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
