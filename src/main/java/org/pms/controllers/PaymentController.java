package org.pms.controllers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.custompropertyeditors.CategoryCustomPropertyEditor;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.displaywrappers.PaymentWrapper;
import org.pms.enums.PageName;
import org.pms.enums.StatusCode;
import org.pms.enums.SystemRole;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.Category;
import org.pms.models.Parish;
import org.pms.models.Payment;
import org.pms.models.User;
import org.pms.services.CategoryService;
import org.pms.services.ParishService;
import org.pms.services.PaymentService;
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
public class PaymentController extends AbstractErrorAndGridHandler {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ParishService parishService;


    @RequestMapping(value = "/viewpayment.action", method = RequestMethod.GET)
    public String viewPaymentPageDisplay(Model model) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        Map<Long, String> parishMap = new HashMap<>();
        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParsihName));
        }

        List<Category> categoryList = categoryService.getCategoryList(2l);

        Map<Long, String> categoryMap = new HashMap<>();
        if (!categoryList.isEmpty()) {
            categoryMap.put(0l, "--Select--");
            for (Category category : categoryList) {
                categoryMap.put(category.getId(), category.getCategoryName());
            }
        }


        Payment payment = new Payment();
        payment.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("payment", payment);
        model.addAttribute("parishMap", parishMap);
        model.addAttribute("categoryMap", categoryMap);

        return PageName.PAYMENT.toString();
    }

    @RequestMapping(value = "/addpayment.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addPayment(@ModelAttribute("payment") @Valid Payment payment, BindingResult result) {

        if (!result.hasErrors()) {

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            if (payment.getId() == null) {

                if (currentUser.getSystemRole() == SystemRole.FINANCE_USER) {
                    payment.setAddedByUser(currentUser.getEmail());
                    paymentService.addPayment(payment);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, "Payment", SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a Payment as a " + currentUser.getSystemRole().getUIDisplayValue() + " in the system.");
                }

            } else {
                payment.setModifiedDate(DateUtils.getCurrentDate());
                payment.setUpdatedByUser(currentUser.getEmail());
                paymentService.updatePayment(payment);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, "Payment", "updated successfully!");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displaypaymentgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForPayments(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Payment> allPaymentsForParish = paymentService.getAllPaymentsForParish(currentUser.getParishId());
        Integer totalRows = allPaymentsForParish.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<Payment> allPaymentsSubList = new ArrayList<>();

        if (totalRows > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allPaymentsForParish, formatter.by(sortIndexColumnName, Payment.class));
            }
            allPaymentsSubList = JsonBuilder.generateSubList(page, rows, totalRows, allPaymentsForParish);
        }

        List<GridRow> paymentsGridRows = new ArrayList<GridRow>(allPaymentsForParish.size());
        if (!allPaymentsSubList.isEmpty()) {
            paymentsGridRows = allPaymentsSubList.stream().map(payment -> new PaymentWrapper(payment)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalRows, page, rows, paymentsGridRows));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryCustomPropertyEditor(categoryService));
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }
}
