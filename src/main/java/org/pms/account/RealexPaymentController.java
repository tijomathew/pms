package org.pms.account;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.common.CategoryCustomPropertyEditor;
import org.pms.common.PageName;
import org.pms.user.SystemRole;
import org.pms.common.error.AbstractErrorAndGridHandler;
import org.pms.common.RequestResponseHolder;
import org.pms.domain.BankTransfer;
import org.pms.domain.Category;
import org.pms.domain.RealexPayment;
import org.pms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tijo on 04/05/17.
 */
@Controller
public class RealexPaymentController extends AbstractErrorAndGridHandler {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/viewrealexpayment.action", method = RequestMethod.GET)
    public String viewRealexPageDisplay(Model model) {

        BankTransfer bankTransfer = new BankTransfer();
        model.addAttribute("bankTransfer", bankTransfer);

        List<Category> categoryList = categoryService.getCategoryList(2l);

        Map<Long, String> categoryMap = new HashMap<>();
        if (!categoryList.isEmpty()) {
            categoryMap.put(0l, "--Select--");
            for (Category category : categoryList) {
                categoryMap.put(category.getId(), category.getCategoryName());
            }
        }

        model.addAttribute("categoryMap", categoryMap);

        return PageName.REALEXPAYMENT.toString();
    }

    @RequestMapping(value = "/processbanktransfer.action", method = RequestMethod.POST)
    public String processRealexPayment(@ModelAttribute("bankTransfer") BankTransfer bankTransfer, Model model) {

        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        String currentTransactionDateTime = DateTimeFormat.forPattern("YYYYMMddHHmmss").print(new DateTime());
        String accountID = "internet";
        String orderID = "";
        Double amount = bankTransfer.getTransferAmount() * 100;
        String amountToSend = String.valueOf(amount.intValue());

        orderID = currentUser.getUsersOfDiocese().getDioceseNo().toString().concat("_").concat(currentUser.getZonal().getZonalNo().toString()).concat("_").concat(currentUser.getParishId().toString()).concat("_").concat(currentUser.getFamilyId().toString()).concat("_").concat(currentTransactionDateTime);

        RealexPayment realexPayment = new RealexPayment();

        realexPayment.setTIMESTAMP(currentTransactionDateTime);
        realexPayment.setMERCHANT_ID("syromalabarchurch");
        realexPayment.setACCOUNT(accountID);
        realexPayment.setORDER_ID(orderID);
        realexPayment.setAMOUNT(amountToSend);
        realexPayment.setCURRENCY("EUR");

        String sh1 = currentTransactionDateTime.concat(".").concat("syromalabarchurch").concat(".").concat(orderID).concat(".").concat(amountToSend).concat(".").concat("EUR");
        String hashOne = getHex(sh1);
        String sh2 = hashOne.concat(".").concat("sGj4TyUAym");
        String hashTwo = getHex(sh2);

        realexPayment.setSHA1HASH(hashTwo);
        realexPayment.setAUTO_SETTLE_FLAG("1");
        realexPayment.setCHANNEL("ECOM");
        realexPayment.setCOMMENT1(bankTransfer.getCategory().getCategoryName());
        realexPayment.setCOMMENT2(bankTransfer.getComment());

        realexPayment.setHPP_VERSION("2");
        realexPayment.setMERCHANT_RESPONSE_URL("http://54.171.127.190:8080/pmsdummy/paymentresponse.action");
        realexPayment.setCARD_PAYMENT_BUTTON("Pay Now");

        model.addAttribute("realexpayment", realexPayment);

        return PageName.PROCESSREALEXPAYMENT.toString();
    }

    @RequestMapping(value = "/paymentresponse.action", method = RequestMethod.POST)
    public
    @ResponseBody
    Object getRealexPaymentCode(@RequestParam(value = "RESULT", required = false) String RESULT, @RequestParam(value = "AUTHCODE", required = false) String AUTHCODE, @RequestParam(value = "MESSAGE", required = false) String MESSAGE,
                                @RequestParam(value = "PASREF", required = false) String PASREF, @RequestParam(value = "AVSPOSTCODERESULT", required = false) String AVSPOSTCODERESULT, @RequestParam(value = "AVSADDRESSRESULT", required = false) String AVSADDRESSRESULT,
                                @RequestParam(value = "CVNRESULT", required = false) String CVNRESULT, @RequestParam(value = "ACCOUNT", required = false) String ACCOUNT, @RequestParam(value = "MERCHANT_ID", required = false) String MERCHANT_ID,
                                @RequestParam(value = "ORDER_ID", required = false) String ORDER_ID, @RequestParam(value = "TIMESTAMP", required = false) String TIMESTAMP, @RequestParam(value = "AMOUNT", required = false) String AMOUNT,
                                @RequestParam(value = "CARD_PAYMENT_BUTTON", required = false) String CARD_PAYMENT_BUTTON, @RequestParam(value = "MERCHANT_RESPONSE_URL", required = false) String MERCHANT_RESPONSE_URL, @RequestParam(value = "HPP_LANG", required = false) String HPP_LANG,
                                @RequestParam(value = "SHIPPING_CODE", required = false) String SHIPPING_CODE, @RequestParam(value = "SHIPPING_CO", required = false) String SHIPPING_CO, @RequestParam(value = "BILLING_CODE", required = false) String BILLING_CODE,
                                @RequestParam(value = "BILLING_CO", required = false) String BILLING_CO, @RequestParam(value = "COMMENT1", required = false) String COMMENT1, @RequestParam(value = "COMMENT2", required = false) String COMMENT2,
                                @RequestParam(value = "ECI", required = false) String ECI, @RequestParam(value = "CAVV", required = false) String CAVV, @RequestParam(value = "XID", required = false) String XID,
                                @RequestParam(value = "SHA1HASH", required = false) String SHA1HASH) {
        System.out.println("RESULT-" + RESULT);
        System.out.println("AUTHCODE-" + AUTHCODE);
        System.out.println("MESSAGE-" + MESSAGE);
        System.out.println("PASREF-" + PASREF);
        System.out.println("AVSPOSTCODERESULT-" + AVSPOSTCODERESULT);
        System.out.println("AVSADDRESSRESULT-" + AVSADDRESSRESULT);
        System.out.println("CVNRESULT-" + CVNRESULT);
        System.out.println("ACCOUNT-" + ACCOUNT);
        System.out.println("MERCHANT_ID-" + MERCHANT_ID);
        System.out.println("ORDER_ID-" + ORDER_ID);
        System.out.println("TIMESTAMP-" + TIMESTAMP);
        System.out.println("AMOUNT-" + AMOUNT);
        System.out.println("CARD_PAYMENT_BUTTON-" + CARD_PAYMENT_BUTTON);
        System.out.println("MERCHANT_RESPONSE_URL-" + MERCHANT_RESPONSE_URL);
        System.out.println("HPP_LANG-" + HPP_LANG);
        System.out.println("SHIPPING_CODE-" + SHIPPING_CODE);
        System.out.println("SHIPPING_CO-" + SHIPPING_CO);
        System.out.println("BILLING_CODE-" + BILLING_CODE);
        System.out.println("BILLING_CO-" + BILLING_CO);
        System.out.println("COMMENT1-" + COMMENT1);
        System.out.println("COMMENT2-" + COMMENT2);
        System.out.println("ECI-" + ECI);
        System.out.println("CAVV-" + CAVV);
        System.out.println("XID-" + XID);
        System.out.println("SHA1HASH-" + SHA1HASH);

        return "Success";
    }

    public static String getHex(String raw) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(raw.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            System.out.println("Hex format : " + sb.toString());
        } catch (Exception exception) {

        }
        return sb.toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryCustomPropertyEditor(categoryService));
    }


}
