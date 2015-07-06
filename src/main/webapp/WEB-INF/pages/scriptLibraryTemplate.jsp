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
<meta name="description" content="">
<meta name="author" content="Tijo">

<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapUrl"/>

<link href="${bootstrapUrl}" rel="stylesheet">

<spring:url value="/resources/css/styles.min.css" var="styleminURL"/>

<link rel="stylesheet" href="${styleminURL}">


<spring:url value="/resources/css/ui.jqgrid.css" var="jqgridcssURL"/>
<spring:url value="/resources/css/jquery-ui-custom.css" var="uicustomcssURL"/>

<link type="text/css" rel="stylesheet" href="${jqgridcssURL}"/>
<link type="text/css" rel="stylesheet" href="${uicustomcssURL}"/>

<script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>

<spring:url value="/resources/js/jquery-1.10.2.min.js" var="jqueryURL"/>
<spring:url value="/resources/js/jqueryui-1.10.3.min.js" var="jqueryUIURL"/>

<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapURL"/>
<spring:url value="/resources/js/enquire.js" var="enquireURL"/>
<spring:url value="/resources/js/jquery.cookie.js" var="cookieURL"/>
<spring:url value="/resources/js/jquery.nicescroll.min.js" var="nicescrollURL"/>


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
<spring:url value="/resources/js/placeholdr.js" var="placeholderURL"/>
<spring:url value="/resources/js/application.js" var="applicationURL"/>
<spring:url value="/resources/js/demo.js" var="demoURL"/>
<spring:url value="/resources/js/jquery-css-bootstrap.js" var="bootStrapOverrideJqgrid"/>

<script type='text/javascript' src="${demoindexURL}"></script>
<script type='text/javascript' src="${placeholderURL}"></script>
<script type='text/javascript' src="${applicationURL}"></script>
<script type='text/javascript' src="${demoURL}"></script>
<script type='text/javascript' src="${bootStrapOverrideJqgrid}"></script>

<spring:url value="/resources/js/globalutils.js" var="globalUtilizor"/>

<script src="${globalUtilizor}" type="text/javascript"></script>

<c:set var="showForAdmin" value="${sessionScope.adminRole}"/>
<c:set var="showForParishAdmin" value="${sessionScope.parishAdminRole}"/>
<c:set var="showForMassCenterAdmin" value="${sessionScope.massCenterAdminRole}"/>
<c:set var="showForPrayerUnitAdmin" value="${sessionScope.prayerUnitAdminRole}"/>
<c:set var="showForFamilyUser" value="${sessionScope.familyUserRole}"/>
<c:set var="loggedInUserEmail" value="${sessionScope.currentUserEmail}"/>