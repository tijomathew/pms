<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 26/6/15
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <%@ include file="scriptLibraryTemplate.jsp" %>

    <spring:url value="/addprayerunit.action" var="prayerUnitActionURL"/>
    <spring:url value="/resources/js/createprayerunitgridlayout.js" var="prayerUnitGrid"/>

    <script src="${prayerUnitGrid}" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        jQuery(document).ready(function () {

            loadPrayerUnitGrid();
            globalSubmissionOfForms('prayerUnitForm', '${prayerUnitActionURL}');
        });

    </script>

</head>

<body>

<%@include file="headerTemplate.jsp" %>

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
            <div id="page-heading">
                <!--<h1>Dashboard</h1>-->
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue">
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tabs-1">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>Prayer Unit Information</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <table id="prayerUnitGrid"></table>
                                                        <div id="prayerUnitGridPager"></div>
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
            </div>


            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue">
                            <div class="panel-heading">
                                <h4>
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a href="#prayerunit1" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Prayer Unit Details</span></a>
                                        </li>
                                        <li>
                                            <a href="#prayerunit2" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Local Address</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">

                                <form:form modelAttribute="prayerUnit"
                                           action="${prayerUnitActionURL}" method="post"
                                           id="prayerUnitForm" cssClass="form-horizontal">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="prayerunit1">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>Prayer Unit Details</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="prayerUnitName"
                                                                   class="col-sm-1 control-label">Prayer Unit
                                                                Name</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="prayerUnitName"
                                                                            id="prayerUnitName" class="form-control"/>
                                                            </div>
                                                            <label for="prayerUnitCode" class="col-sm-1 control-label">Prayer
                                                                Unit Code</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="prayerUnitCode"
                                                                            id="prayerUnitCode"
                                                                            readonly="true" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="prayerUnitPlace"
                                                                   class="col-sm-1 control-label">Prayer Unit
                                                                Place</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="prayerUnitPlace"
                                                                            id="prayerUnitPlace" class="form-control"/>
                                                            </div>
                                                            <label for="massCenterId" class="col-sm-1 control-label">Mass
                                                                Center</label>

                                                            <div class="col-sm-3">
                                                                <form:select path="massCenterId"
                                                                             items="${massCenterMap}"
                                                                             id="massCenterId" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="tab-pane" id="prayerunit2">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Local Address</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="localAddress.addressLineOne"
                                                                   class="col-sm-1 control-label">Address Line 1</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.addressLineOne"
                                                                            id="localAddressaddressLineOne"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.addressLineTwo"
                                                                   class="col-sm-1 control-label">Address Line 2</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.addressLineTwo"
                                                                            id="localAddressaddressLineTwo"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.addressLineThree"
                                                                   class="col-sm-1 control-label">Address Line 3</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.addressLineThree"
                                                                            id="localAddressaddressLineThree"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.town"
                                                                   class="col-sm-1 control-label">Town</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.town"
                                                                            id="localAddresstown" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.county"
                                                                   class="col-sm-1 control-label">County</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.county"
                                                                            id="localAddresscounty"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.pin"
                                                                   class="col-sm-1 control-label">Pin code</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.pin" id="localAddresspin"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.country"
                                                                   class="col-sm-1 control-label">Country</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.country"
                                                                            id="localAddresscountry"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <button type="submit"
                                                value="Save"
                                                class="btn btn-primary"/>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- container -->
            </div>
            <!--wrap -->
        </div>
        <!-- page-content -->

        <%@include file="footerPanelTemplate.jsp" %>

    </div>
</body>
</html>
