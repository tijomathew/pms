<%--  
  User: tijo
  Date: 24/10/14
  Time: 10:44 AM  
--%>

<spring:url value="/resources/css/style.css" var="styleURL"/>
<spring:url value="/resources/css/ui.jqgrid.css" var="jqgridcssURL"/>
<spring:url value="/resources/css/jquery-ui-custom.css" var="uicustomcssURL"/>


<link rel="shortcut icon" href="#.ico">
<!-- CSS -->
<link type="text/css" rel="stylesheet" href="${styleURL}"/>
<link type="text/css" rel="stylesheet" href="${jqgridcssURL}"/>
<link type="text/css" rel="stylesheet" href="${uicustomcssURL}"/>

<style type="text/css">
    .middle-center, .inner-center, .ui-layout-south {
        border: 0;
    }
</style>

<spring:url value="/resources/js/jquery-1.10.2.js" var="jqueryURL"/>
<spring:url value="/resources/js/jquery-ui.js" var="uiURL"/>
<spring:url value="/resources/js/jquery.layout-latest.min.js" var="layoutURL"/>
<spring:url value="/resources/js/jquery.treeview.js" var="treeURL"/>
<spring:url value="/resources/js/jquery.validate.min.js" var="validateURL"/>
<spring:url value="/resources/js/utility.js" var="utilityURL"/>
<spring:url value="/resources/js/jquery.jqGrid.min.js" var="jqgridminURL"/>
<spring:url value="/resources/js/grid.locale-en.js" var="localeURL"/>

<!-- Libraries -->
<script src="${jqueryURL}" type="text/javascript"
        language="javascript"></script>
<script src="${uiURL}" type="text/javascript" language="javascript"></script>
<script src="${layoutURL}" type="text/javascript"
        language="javascript"></script>
<script src="${treeURL}" type="text/javascript"
        language="javascript"></script>
<script src="${validateURL}" type="text/javascript"
        language="javascript"></script>

<script src="${utilityURL}" type="text/javascript" language="javascript"></script>

<script src="${jqgridminURL}" type="text/javascript"
        language="javascript"></script>
<script src="${localeURL}" type="text/javascript"
        language="javascript"></script>

<c:set var="showForAdmin" value="${sessionScope.adminRole}"/>
<c:set var="showForParishAdmin" value="${sessionScope.parishAdminRole}"/>
<c:set var="showForMassCenterAdmin" value="${sessionScope.massCenterAdminRole}"/>
<c:set var="showForPrayerUnitAdmin" value="${sessionScope.prayerUnitAdminRole}"/>
<c:set var="showForFamilyUser" value="${sessionScope.familyUserRole}"/>
<c:set var="loggedInUser" scope="session" value="${sessionScope.PMS_CURRENT_USER}"/>



