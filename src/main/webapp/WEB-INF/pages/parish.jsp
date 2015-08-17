<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 26/6/15
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="scriptLibraryTemplate.jsp" %>

    <spring:url value="/resources/js/priestdesignationdisplay.js" var="priestdesignationdisplayURL"/>
    <spring:url value="/resources/js/createparishgridlayout.js" var="parishGridURL"/>
    <spring:url value="/addparish.action" var="parishActionURL"/>

    <script src="${priestdesignationdisplayURL}" type="text/javascript"
            language="javascript"></script>

    <script src="${parishGridURL}" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        jQuery(document).ready(function () {

            backToTop();

            loadDatePicker();

            loadParishGrid();

        });

    </script>

    <%@include file="headerTemplate.jsp" %>
</head>

<body> <!--oncontextmenu="return false;" -->

<div id="page-container">

    <%@include file="leftMenuPanelTemplate.jsp" %>

    <!-- BEGIN RIGHTBAR -->
    <div id="page-rightbar">

        <div id="widgetarea">
            <div class="widget">
                <div class="widget-heading">
                    <a href="javascript:;" data-toggle="collapse" data-target="#accsummary"><h4>Search Panel</h4></a>
                </div>
                <div class="widget-body collapse in" id="accsummary">
                    Search Criteria Entries shows here in this panel
                </div>
            </div>


        </div>
    </div>
    <!-- END RIGHTBAR -->
    <div id="page-content">
        <div id='wrap'>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue">
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tabs-1">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="panel outer-border">

                                                    <div class="panel-heading">
                                                        <h4>
                                                            <ul class="nav nav-tabs">
                                                                <li class="active">
                                                                    <a href="#parish1" data-toggle="tab"><i
                                                                            class="fa fa-list visible-xs icon-scale"></i><span
                                                                            class="hidden-xs">Parish Details</span></a>
                                                                </li>
                                                                <li>
                                                                    <a href="#parish2" data-toggle="tab"><i
                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                            class="hidden-xs">Contact Details</span></a>
                                                                </li>
                                                                <li>
                                                                    <a href="#parish3" data-toggle="tab"><i
                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                            class="hidden-xs">Address</span></a>
                                                                </li>
                                                            </ul>
                                                        </h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <table id="parishGrid"></table>
                                                        <div id="parishGridPager"></div>


                                                        <form:form modelAttribute="parish"
                                                                   action="${parishActionURL}" method="post"
                                                                   id="parishForm" class="form-horizontal hidedisplay">

                                                            <div class="tab-content">

                                                                <div class="tab-pane active" id="parish1">

                                                                    <div class="col-md-12">
                                                                        <div class="panel">
                                                                            <div class="panel-heading">
                                                                                <h4>Parish Details</h4>
                                                                            </div>
                                                                            <div class="panel-body padding7">
                                                                                <form:hidden
                                                                                        path="id"
                                                                                        id="id"
                                                                                        class="form-control"/>
                                                                                <div class="form-group">
                                                                                    <label for="parishNo"
                                                                                           class="col-sm-2 control-label">Parish
                                                                                        No.</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="parishNo"
                                                                                                id="parishNo"
                                                                                                class="form-control"
                                                                                                readonly="true"/>
                                                                                    </div>
                                                                                    <label for="parishName"
                                                                                           class="col-sm-2 control-label">Parish
                                                                                        Name</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="parishName"
                                                                                                id="parishName"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                </div>


                                                                                <div class="form-group">
                                                                                    <label for="place"
                                                                                           class="col-sm-2 control-label">Parish
                                                                                        Place</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="place"
                                                                                                id="place"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                    <label for="patron"
                                                                                           class="col-sm-2 control-label">Patron</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="patron"
                                                                                                id="patron"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label for="webSite"
                                                                                           class="col-sm-2 control-label">Parish
                                                                                        Web-site</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="webSite"
                                                                                                id="webSite"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                    <label for="facebookPage"
                                                                                           class="col-sm-2 control-label">Parish
                                                                                        Facebook Page</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="facebookPage"
                                                                                                id="facebookPage"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label for="registeredDate"
                                                                                           class="col-sm-2 control-label">Registered
                                                                                        Date</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="registeredDate"
                                                                                                id="registeredDate"
                                                                                                class="form-control"
                                                                                                readonly="true"/>
                                                                                    </div>

                                                                                </div>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="tab-pane" id="parish2">

                                                                    <div class="col-md-12">
                                                                        <div class="panel panel-grape">
                                                                            <div class="panel-heading">
                                                                                <h4>
                                                                                    Contact Details</h4>
                                                                            </div>
                                                                            <div class="panel-body">
                                                                                <div class="form-group">
                                                                                    <label for="mobileNo"
                                                                                           class="col-sm-2 control-label">Mobile
                                                                                        No.</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="mobileNo"
                                                                                                id="mobileNo"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                    <label for="landLineNo"
                                                                                           class="col-sm-2 control-label">Land
                                                                                        Line No.</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="landLineNo"
                                                                                                id="landLineNo"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label for="faxNo"
                                                                                           class="col-sm-2 control-label">Fax
                                                                                        No.</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="faxNo"
                                                                                                id="faxNo"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>

                                                                <div class="tab-pane" id="parish3">

                                                                    <div class="col-md-12">
                                                                        <div class="panel panel-grape">
                                                                            <div class="panel-heading">
                                                                                <h4>
                                                                                    Local Address</h4>
                                                                            </div>
                                                                            <div class="panel-body">
                                                                                <div class="form-group">
                                                                                    <label for="localAddressaddressLineOne"
                                                                                           class="col-sm-2 control-label">Address
                                                                                        Line 1</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="localAddress.addressLineOne"
                                                                                                id="localAddressaddressLineOne"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                    <label for="localAddressaddressLineTwo"
                                                                                           class="col-sm-2 control-label">Address
                                                                                        Line
                                                                                        2 </label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="localAddress.addressLineTwo"
                                                                                                id="localAddressaddressLineTwo"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label for="localAddressaddressLineThree"
                                                                                           class="col-sm-2 control-label">Address
                                                                                        Line
                                                                                        3 </label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="localAddress.addressLineThree"
                                                                                                id="localAddressaddressLineThree"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                    <label for="localAddresstown"
                                                                                           class="col-sm-2 control-label">Town</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="localAddress.town"
                                                                                                id="localAddresstown"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label for="localAddresscounty"
                                                                                           class="col-sm-2 control-label">County</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="localAddress.county"
                                                                                                id="localAddresscounty"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                    <label for="localAddresspin"
                                                                                           class="col-sm-2 control-label">Pin
                                                                                        code</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="localAddress.pin"
                                                                                                id="localAddresspin"
                                                                                                class="form-control"/>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label for="localAddresscountry"
                                                                                           class="col-sm-2 control-label">Country</label>

                                                                                    <div class="col-sm-4">
                                                                                        <form:input
                                                                                                path="localAddress.country"
                                                                                                id="localAddresscountry"
                                                                                                class="form-control"
                                                                                                readonly="true"/>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </form:form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>

                        </div>
                    </div>
                </div>
                <!-- container -->
            </div>
            <!-- container -->
        </div>
        <!--wrap -->
    </div>
    <!-- page-content -->

    <%@include
            file="footerPanelTemplate.jsp" %>

</div>
</body>
</html>

