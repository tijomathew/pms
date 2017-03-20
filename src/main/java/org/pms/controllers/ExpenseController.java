package org.pms.controllers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.custompropertyeditors.CategoryCustomPropertyEditor;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.displaywrappers.ExpenseWrapper;
import org.pms.enums.PageName;
import org.pms.enums.StatusCode;
import org.pms.enums.SystemRole;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.Category;
import org.pms.models.Expense;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.services.CategoryService;
import org.pms.services.ExpenseService;
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
public class ExpenseController extends AbstractErrorAndGridHandler {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ParishService parishService;


    @RequestMapping(value = "/viewexpense.action", method = RequestMethod.GET)
    public String viewExpensePageDisplay(Model model) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        Map<Long, String> parishMap = new HashMap<>();
        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParsihName));
        }

        Expense expense = new Expense();
        expense.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("expense", expense);
        model.addAttribute("parishMap", parishMap);

        return PageName.EXPENSE.toString();
    }

    @RequestMapping(value = "/addexpense.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addExpense(@ModelAttribute("expense") @Valid Expense expense, BindingResult result) {

        if (!result.hasErrors()) {

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            if (expense.getId() == null) {

                if (currentUser.getSystemRole() == SystemRole.FINANCE_USER) {
                    expense.setAddedByUser(currentUser.getEmail());
                    expenseService.addExpense(expense);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, "Expense", SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a Expense as a " + currentUser.getSystemRole().getUIDisplayValue() + " in the system.");
                }

            } else {
                expense.setModifiedDate(DateUtils.getCurrentDate());
                expense.setUpdatedByUser(currentUser.getEmail());
                expenseService.updateExpense(expense);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, "Expense", "updated successfully!");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displayexpensegrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForExpenses(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Expense> allExpensesForParish = expenseService.getAllExpensesForParish(currentUser.getParishId());
        Integer totalRows = allExpensesForParish.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<Expense> allExpensesSubList = new ArrayList<>();

        if (totalRows > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allExpensesForParish, formatter.by(sortIndexColumnName, Expense.class));
            }
            allExpensesSubList = JsonBuilder.generateSubList(page, rows, totalRows, allExpensesForParish);
        }

        List<GridRow> expensesGridRows = new ArrayList<GridRow>(allExpensesForParish.size());
        if (!allExpensesSubList.isEmpty()) {
            expensesGridRows = allExpensesSubList.stream().map(expense -> new ExpenseWrapper(expense)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalRows, page, rows, expensesGridRows));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryCustomPropertyEditor(categoryService));
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }
}
