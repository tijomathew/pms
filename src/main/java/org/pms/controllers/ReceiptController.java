package org.pms.controllers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.custompropertyeditors.CategoryCustomPropertyEditor;
import org.pms.custompropertyeditors.ParishCustomPropertyEditor;
import org.pms.displaywrappers.ReceiptWrapper;
import org.pms.enums.PageName;
import org.pms.enums.StatusCode;
import org.pms.enums.SystemRole;
import org.pms.error.AbstractErrorAndGridHandler;
import org.pms.error.CustomResponse;
import org.pms.helpers.*;
import org.pms.models.*;
import org.pms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tijo on 11/03/17.
 */
@Controller
public class ReceiptController extends AbstractErrorAndGridHandler {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private PaymentService paymentService;


    @RequestMapping(value = "/viewreceipt.action", method = RequestMethod.GET)
    public String viewreceiptPageDisplay(Model model) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        Map<Long, String> parishMap = new HashMap<>();
        List<Parish> parishList = parishService.getAllParishForUserRole(currentUser);
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParsihName));
        }

        List<Category> categoryList = categoryService.getCategoryList(1l);

        Map<Long, String> categoryMap = new HashMap<>();
        if (!categoryList.isEmpty()) {
            categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getCategoryName));
        }
        categoryMap.put(0l, "--Select--");

        Receipt receipt = new Receipt();
        receipt.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("receipt", receipt);
        model.addAttribute("parishMap", parishMap);
        model.addAttribute("categoryMap", categoryMap);

        return PageName.RECEIPT.toString();
    }

    @RequestMapping(value = "/addreceipt.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addReceipt(@ModelAttribute("receipt") @Valid Receipt receipt, BindingResult result) {

        if (!result.hasErrors()) {

            User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

            if (receipt.getId() == null) {

                if (currentUser.getSystemRole() == SystemRole.FINANCE_USER) {
                    receipt.setAddedByUser(currentUser.getEmail());
                    receiptService.addReceipt(receipt);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, "Receipt", SUCCESS_MESSAGE_DISPLAY);
                } else {
                    customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add a Receipt as a " + currentUser.getSystemRole().getUIDisplayValue() + " in the system.");
                }

            } else {
                receipt.setModifiedDate(DateUtils.getCurrentDate());
                receipt.setUpdatedByUser(currentUser.getEmail());
                receiptService.updateReceipt(receipt);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, "Receipt", "updated successfully!");
            }

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }

        return customResponse;
    }

    @RequestMapping(value = "displayreceiptgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForReceipts(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Receipt> allReceiptsForParish = receiptService.getAllReceiptsForParish(currentUser.getParishId());
        Integer totalRows = allReceiptsForParish.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<Receipt> allReceiptsSubList = new ArrayList<>();

        if (totalRows > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allReceiptsForParish, formatter.by(sortIndexColumnName, Receipt.class));
            }
            allReceiptsSubList = JsonBuilder.generateSubList(page, rows, totalRows, allReceiptsForParish);
        }

        List<GridRow> receiptGridRows = new ArrayList<GridRow>(allReceiptsForParish.size());
        if (!allReceiptsSubList.isEmpty()) {
            receiptGridRows = allReceiptsSubList.stream().map(receipt -> new ReceiptWrapper(receipt)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalRows, page, rows, receiptGridRows));
    }

    @RequestMapping(value = "displaysummary.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateTotals() {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Receipt> allReceiptsForParish = receiptService.getAllReceiptsForParish(currentUser.getParishId());
        List<Deposit> allDepositsForParish = depositService.getAllDepositsForParish(currentUser.getParishId());
        List<Withdrawal> allWithdrawalsForParish = withdrawalService.getAllWithdrawalsForParish(currentUser.getParishId());
        List<Payment> allPaymentsForParish = paymentService.getAllPaymentsForParish(currentUser.getParishId());
        BigDecimal cashInHand = BigDecimal.ZERO;
        BigDecimal cashInBank = BigDecimal.ZERO;
        for (Receipt receipt : allReceiptsForParish) {
            if (receipt.getReceiptType().equalsIgnoreCase("Cash")) {
                cashInHand = cashInHand.add(receipt.getReceiptAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            } else {
                cashInBank = cashInBank.add(receipt.getReceiptAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            }
        }

        for (Deposit deposit : allDepositsForParish) {
            cashInBank = cashInBank.add(deposit.getDepositAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            cashInHand = cashInHand.subtract(deposit.getDepositAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }

        for (Withdrawal withdrawal : allWithdrawalsForParish) {
            cashInHand = cashInHand.add(withdrawal.getWithdrawalAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            cashInBank = cashInBank.subtract(withdrawal.getWithdrawalAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }

        for (Payment payment : allPaymentsForParish) {
            if (payment.getPaymentType().equalsIgnoreCase("Cash")) {
                cashInHand = cashInHand.subtract(payment.getPaymentAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            } else {
                cashInBank = cashInBank.subtract(payment.getPaymentAmount()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            }
        }

        TotalsWrapper totalsWrapper = new TotalsWrapper(cashInBank.add(cashInHand).setScale(2, BigDecimal.ROUND_HALF_EVEN), cashInHand, cashInBank);
        return JsonBuilder.convertToJson(totalsWrapper);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryCustomPropertyEditor(categoryService));
        binder.registerCustomEditor(Parish.class, new ParishCustomPropertyEditor(parishService));
    }

    private class TotalsWrapper {
        BigDecimal totalBalance;
        BigDecimal cashInHand;
        BigDecimal bankBalance;

        public TotalsWrapper(BigDecimal totalBalance, BigDecimal cashInHand, BigDecimal bankBalance) {
            this.totalBalance = totalBalance;
            this.cashInHand = cashInHand;
            this.bankBalance = bankBalance;
        }

        public BigDecimal getTotalBalance() {
            return totalBalance;
        }

        public BigDecimal getCashInHand() {
            return cashInHand;
        }

        public BigDecimal getBankBalance() {
            return bankBalance;
        }
    }
}
