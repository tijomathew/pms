<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 13/05/17
  Time: 09:23
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <%@ include file="scriptLibraryTemplate.jsp" %>

</head>

<body>
<form:form modelAttribute="realexpayment" method="post"
           action="https://pay.sandbox.realexpayments.com/pay" id="realexPaymentForm" target="foo">
    <form:input type="hidden" path="TIMESTAMP"/>
    <form:input type="hidden" path="MERCHANT_ID"/>
    <form:input type="hidden" path="ACCOUNT"/>
    <form:input type="hidden" path="ORDER_ID"/>
    <form:input type="hidden" path="AMOUNT"/>
    <form:input type="hidden" path="CURRENCY"/>
    <form:input type="hidden" path="SHA1HASH"/>
    <form:input type="hidden" path="AUTO_SETTLE_FLAG"/>
    <form:input type="hidden" path="CHANNEL"/>
    <form:input type="hidden" path="COMMENT1"/>
    <form:input type="hidden" path="COMMENT2"/>
    <form:input type="hidden" path="SHIPPING_CODE"/>
    <form:input type="hidden" path="SHIPPING_CO"/>
    <form:input type="hidden" path="BILLING_CODE"/>
    <form:input type="hidden" path="BILLING_CO"/>
    <form:input type="hidden" path="CUST_NUM"/>
    <form:input type="hidden" path="VAR_REF"/>
    <form:input type="hidden" path="PROD_ID"/>
    <form:input type="hidden" path="HPP_LANG"/>
    <form:input type="hidden" path="HPP_VERSION"/>
    <form:input type="hidden" path="MERCHANT_RESPONSE_URL"/>
    <form:input type="hidden" path="CARD_PAYMENT_BUTTON"/>
    <form:input type="hidden" path="SUPPLEMENTARY_DATA"/>

</form:form>

</body>
<script type="text/javascript">
    jQuery(document).ready(function () {
        $("#realexPaymentForm").submit();
    });

</script>

<iframe name="foo"></iframe>
</html>
