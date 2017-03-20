package org.pms.controllers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.custompropertyeditors.CategoryCustomPropertyEditor;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.displaywrappers.DepositWrapper;
import org.pms.enums.PageName;
import org.pms.enums.StatusCode;
import org.pms.enums.SystemRole;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.Category;
import org.pms.models.Deposit;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.services.CategoryService;
import org.pms.services.DepositService;
import org.pms.services.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tijo on 11/03/17.
 */
@Controller
public class DepositController extends AbstractErrorAndGridHandler {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private DepositService depositService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ParishService parishService;


    @RequestMapping(value = "/viewdeposit.action", method = RequestMethod.GET)
    public String viewDepositPageDisplay(Model model) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        Map<Long, String> parishMap = new HashMap<>();
        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParsihName));
        }

        Deposit deposit = new Deposit();
        deposit.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("deposit", deposit);
        model.addAttribute("parishMap", parishMap);

        return PageName.DEPOSIT.toString();
    }

    @RequestMapping(value = "/adddeposit.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addDeposit(@ModelAttribute("deposit") @Valid Deposit deposit, BindingResult result) {

        if (!result.hasErrors()) {

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            if (deposit.getId() == null) {

                if (currentUser.getSystemRole() == SystemRole.FINANCE_USER) {
                    deposit.setAddedByUser(currentUser.getEmail());
                    depositService.addDeposit(deposit);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, "Deposit", SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a Deposit as a " + currentUser.getSystemRole().getUIDisplayValue() + " in the system.");
                }

            } else {
                deposit.setModifiedDate(DateUtils.getCurrentDate());
                deposit.setUpdatedByUser(currentUser.getEmail());
                depositService.updateDeposit(deposit);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, "Deposit", "updated successfully!");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displaydepositgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForDeposit(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Deposit> allDepositsForParish = depositService.getAllDepositsForParish(currentUser.getParishId());
        Integer totalRows = allDepositsForParish.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<Deposit> allDepositsSubList = new ArrayList<>();

        if (totalRows > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allDepositsForParish, formatter.by(sortIndexColumnName, Deposit.class));
            }
            allDepositsSubList = JsonBuilder.generateSubList(page, rows, totalRows, allDepositsForParish);
        }

        List<GridRow> depositsGridRows = new ArrayList<GridRow>(allDepositsForParish.size());
        if (!allDepositsSubList.isEmpty()) {
            depositsGridRows = allDepositsSubList.stream().map(deposit -> new DepositWrapper(deposit)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalRows, page, rows, depositsGridRows));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryCustomPropertyEditor(categoryService));
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }
}
