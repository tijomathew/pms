<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/6/15
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>

<meta charset="utf-8">
<title>PMS</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Parish Management System Software">
<meta name="author" content="Tijo Mathew(tijopower888@gmail.com)">
<link rel="shortcut icon" href="resources/images/favicon.png">
<spring:url value="/resources/css/bootstrap.min.latest.css" var="bootstrapcsslatestUrl"/>
<spring:url value="/resources/css/jqgrowl.css" var="jqGrowlCSS"/>

<link href="${bootstrapcsslatestUrl}" rel="stylesheet">
<link href="${jqGrowlCSS}" rel="stylesheet">

<spring:url value="/resources/css/styles.min.css" var="styleminURL"/>

<link rel="stylesheet" href="${styleminURL}">


<spring:url value="/resources/css/ui.jqgrid.css" var="jqgridcssURL"/>
<spring:url value="/resources/css/layout.jqgrid.css" var="uitestcssURL"/>

<link type="text/css" rel="stylesheet" href="${jqgridcssURL}"/>
<link type="text/css" rel="stylesheet" href="${uitestcssURL}"/>

<spring:url value="/resources/js/jquery-1.11.0.min.js" var="jqueryURL"/>
<spring:url value="/resources/js/jqueryui-1-11.0.min.js" var="jqueryUIURL"/>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapURL"/>
<spring:url value="/resources/js/enquire.js" var="enquireURL"/>
<spring:url value="/resources/js/jquery.cookie.js" var="cookieURL"/>
<spring:url value="/resources/js/jquery.nicescroll.min.js" var="nicescrollURL"/>

<script src="<c:url value="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.js"/> "></script>
<link href="<c:url value="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.css"/> ">


<script type='text/javascript' src="${jqueryURL}"></script>
<script type='text/javascript' src="${jqueryUIURL}"></script>
<script type='text/javascript' src="${bootstrapURL}"></script>
<script type='text/javascript' src="${enquireURL}"></script>
<script type='text/javascript' src="${cookieURL}"></script>
<script type='text/javascript' src="${nicescrollURL}"></script>

<spring:url value="/resources/js/jquery.jqGrid.min.js" var="jqgridminURL"/>
<spring:url value="/resources/js/grid.locale-en.js" var="localeURL"/>

<script src="${localeURL}" type="text/javascript"></script>
<script src="${jqgridminURL}" type="text/javascript"></script>

<spring:url value="/resources/js/demo-index.js" var="demoindexURL"/>
<spring:url value="/resources/js/application.js" var="applicationURL"/>
<spring:url value="/resources/js/demo.js" var="demoURL"/>
<spring:url value="/resources/js/jquery-css-bootstrap.js" var="bootStrapOverrideJqgrid"/>

<script type='text/javascript' src="${demoindexURL}"></script>
<script type='text/javascript' src="${applicationURL}"></script>
<script type='text/javascript' src="${demoURL}"></script>
<script type='text/javascript' src="${bootStrapOverrideJqgrid}"></script>

<spring:url value="/resources/js/globalutils.js" var="globalUtilizor"/>
<spring:url value="/resources/js/jqgrowl.js" var="jqGrowlURL"/>
<spring:url value="/resources/js/bootstrap-datepicker.min.js" var="bootStrapminCalendarURL"/>
<spring:url value="/resources/js/bootstrap.datetimepicker.js" var="bootStrapCalendarURL"/>
<spring:url value="/resources/js/jquery.loadJSON.js" var="jqueryLoadJson"/>
<spring:url value="/resources/js/errorfieldsdialog.js" var="errorfieldsdialog"/>


<script src="${globalUtilizor}" type="text/javascript"></script>
<script src="${jqGrowlURL}" type="text/javascript"></script>
<script src="${bootStrapminCalendarURL}" type="text/javascript"></script>
<script src="${bootStrapCalendarURL}" type="text/javascript"></script>
<script src="${jqueryLoadJson}" type="text/javascript"></script>
<script src="${errorfieldsdialog}" type="text/javascript"></script>

<c:set var="showForAdmin" value="${sessionScope.adminRole}"/>
<c:set var="showForMassCentreAdmin" value="${sessionScope.massCentreAdminRole}"/>
<c:set var="showForPrayerUnitAdmin" value="${sessionScope.prayerUnitAdminRole}"/>
<c:set var="showForFamilyUser" value="${sessionScope.familyUserRole}"/>
<c:set var="loggedInUserEmail" value="${sessionScope.currentUserEmail}"/>
