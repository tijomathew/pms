package org.pms.controllers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.custompropertyeditors.CategoryCustomPropertyEditor;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.displaywrappers.WithdrawalWrapper;
import org.pms.enums.PageName;
import org.pms.enums.StatusCode;
import org.pms.enums.SystemRole;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.Category;
import org.pms.models.Parish;
import org.pms.models.User;
import org.pms.models.Withdrawal;
import org.pms.services.CategoryService;
import org.pms.services.ParishService;
import org.pms.services.WithdrawalService;
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
public class WithdrawalController extends AbstractErrorAndGridHandler {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ParishService parishService;


    @RequestMapping(value = "/viewwithdrawal.action", method = RequestMethod.GET)
    public String viewWithdrawalPageDisplay(Model model) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        Map<Long, String> parishMap = new HashMap<>();
        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParsihName));
        }

        List<Category> categoryList = categoryService.getCategoryList(4l);

        Map<Long, String> categoryMap = new HashMap<>();
        if (!categoryList.isEmpty()) {
            categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getCategoryName));
        }

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("withdrawal", withdrawal);
        model.addAttribute("parishMap", parishMap);
        model.addAttribute("categoryMap", categoryMap);

        return PageName.WITHDRAWAL.toString();
    }

    @RequestMapping(value = "/addwithdrawal.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addWithdrawal(@ModelAttribute("withdrawal") @Valid Withdrawal withdrawal, BindingResult result) {

        if (!result.hasErrors()) {

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            if (withdrawal.getId() == null) {

                if (currentUser.getSystemRole() == SystemRole.FINANCE_USER) {
                    withdrawal.setAddedByUser(currentUser.getEmail());
                    withdrawalService.addWithdrawal(withdrawal);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, "Withdrawal", SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a Withdrawal as a " + currentUser.getSystemRole().getUIDisplayValue() + " in the system.");
                }

            } else {
                withdrawal.setModifiedDate(DateUtils.getCurrentDate());
                withdrawal.setUpdatedByUser(currentUser.getEmail());
                withdrawalService.updateWithdrawal(withdrawal);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, "Withdrawal", "updated successfully!");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displaywithdrawalgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForWithdrawals(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Withdrawal> allWithdrawalsForParish = withdrawalService.getAllWithdrawalsForParish(currentUser.getParishId());
        Integer totalRows = allWithdrawalsForParish.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<Withdrawal> allWithdrawalsSubList = new ArrayList<>();

        if (totalRows > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allWithdrawalsForParish, formatter.by(sortIndexColumnName, Withdrawal.class));
            }
            allWithdrawalsSubList = JsonBuilder.generateSubList(page, rows, totalRows, allWithdrawalsForParish);
        }

        List<GridRow> withdrawalsGridRows = new ArrayList<GridRow>(allWithdrawalsForParish.size());
        if (!allWithdrawalsSubList.isEmpty()) {
            withdrawalsGridRows = allWithdrawalsSubList.stream().map(withdrawal -> new WithdrawalWrapper(withdrawal)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalRows, page, rows, withdrawalsGridRows));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryCustomPropertyEditor(categoryService));
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }
}
