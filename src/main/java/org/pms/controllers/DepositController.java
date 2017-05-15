package org.pms.controllers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.custompropertyeditors.CategoryCustomPropertyEditor;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.displaywrappers.CashFlowWrapper;
import org.pms.enums.PageName;
import org.pms.enums.StatusCode;
import org.pms.enums.SystemRole;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.CashFlowService;
import org.pms.services.CategoryService;
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
    private CashFlowService cashFlowService;

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
        List<Category> categoryList = categoryService.getCategoryList(3l);

        Map<Long, String> categoryMap = new HashMap<>();
        if (!categoryList.isEmpty()) {
            categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getCategoryName));
        }

        CashFlow cashFlowDeposit = new CashFlow();
        cashFlowDeposit.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("cashFlowDeposit", cashFlowDeposit);
        model.addAttribute("parishMap", parishMap);
        model.addAttribute("categoryMap", categoryMap);

        return PageName.DEPOSIT.toString();
    }

    @RequestMapping(value = "/adddeposit.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addDeposit(@ModelAttribute("cashFlowDeposit") @Valid CashFlow cashFlowDeposit, BindingResult result) {

        if (!result.hasErrors()) {

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            if (cashFlowDeposit.getId() == null) {

                if (currentUser.getSystemRole() == SystemRole.FINANCE_USER) {
                    cashFlowDeposit.setAddedByUser(currentUser.getEmail());
                    cashFlowService.addCashFlow(cashFlowDeposit);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, "Deposit", SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a Deposit as a " + currentUser.getSystemRole().getUIDisplayValue() + " in the system.");
                }

            } else {
                cashFlowDeposit.setModifiedDate(DateUtils.getCurrentDate());
                cashFlowDeposit.setUpdatedByUser(currentUser.getEmail());
                cashFlowService.updateCashFlow(cashFlowDeposit);
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
        List<CashFlow> allDepositsForParish = cashFlowService.getAllCashFlowForParish(currentUser.getParishId());
        Integer totalRows = allDepositsForParish.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<CashFlow> allDepositsSubList = new ArrayList<>();

        if (totalRows > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allDepositsForParish, formatter.by(sortIndexColumnName, CashFlow.class));
            }
            allDepositsSubList = JsonBuilder.generateSubList(page, rows, totalRows, allDepositsForParish);
        }

        List<GridRow> cashFlowGridRows = new ArrayList<GridRow>(allDepositsForParish.size());
        if (!allDepositsSubList.isEmpty()) {
            cashFlowGridRows = allDepositsSubList.stream().map(cashFlow -> new CashFlowWrapper(cashFlow)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalRows, page, rows, cashFlowGridRows));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryCustomPropertyEditor(categoryService));
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }
}
