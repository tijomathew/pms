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
            globalSubmissionOfForms('prayerUnitForm','${prayerUnitActionURL}');
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
                                           id="prayerUnitForm">

                                <div class="tab-content">

                                    <div class="tab-pane active" id="prayerunit1">

                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>Prayer Unit Details</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group" id="prayerUnitName">
                                                        <label class="control-label">Prayer Unit Name :</label>

                                                        <form:input path="prayerUnitName"
                                                                    id="prayerUnitName"/><span
                                                            class="help-inline"><form:errors
                                                            path="prayerUnitName"/></span>

                                                    </div>
                                                    <div class="control-group" id="prayerUnitCode">
                                                        <label class="control-label">Prayer Unit Code :</label>

                                                        <form:input path="prayerUnitCode"
                                                                    id="prayerUnitCode"
                                                                    readonly="true"/><span
                                                            class="help-inline"><form:errors
                                                            path="prayerUnitCode"/></span>

                                                    </div>
                                                    <div class="control-group" id="prayerUnitPlace">
                                                        <label class="control-label">Prayer Unit Place :</label>

                                                        <form:input path="prayerUnitPlace"
                                                                    id="prayerUnitPlace"/><span
                                                            class="help-inline"><form:errors
                                                            path="prayerUnitPlace"/></span>

                                                    </div>
                                                    <div class="control-group" id="massCenterId">
                                                        <label class="control-label">Mass Center:</label>

                                                        <form:select path="massCenterId"
                                                                     items="${massCenterMap}"
                                                                     id="massCenterId"/><span
                                                            class="help-inline"><form:errors
                                                            path="massCenterId"/></span>

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
                                                    <div class="control-group" id="localAddressaddressLineOne">
                                                        <label class="control-label">Address Line 1 :</label>

                                                        <form:input path="localAddress.addressLineOne"
                                                                    id="localAddressaddressLineOne"/> <span
                                                            class="help-inline"><form:errors
                                                            path="localAddress.addressLineOne"/> </span>

                                                    </div>
                                                    <div class="control-group" id="localAddressaddressLineTwo">
                                                        <label class="control-label">Address Line 2 :</label>

                                                        <form:input
                                                                path="localAddress.addressLineTwo"
                                                                id="localAddressaddressLineTwo"/><span
                                                            class="help-inline"><form:errors
                                                            path="localAddress.addressLineTwo"/> </span>

                                                    </div>

                                                    <div class="control-group"
                                                         id="localAddressaddressLineThree">
                                                        <label class="control-label">Address Line 3 :</label>

                                                        <form:input
                                                                path="localAddress.addressLineThree"
                                                                id="localAddressaddressLineThree"/><span
                                                            class="help-inline"><form:errors
                                                            path="localAddress.addressLineThree"/> </span>

                                                    </div>

                                                    <div class="control-group" id="localAddresstown">
                                                        <label class="control-label">Town:</label>

                                                        <form:input
                                                                path="localAddress.town"
                                                                id="localAddresstown"/><span
                                                            class="help-inline"><form:errors
                                                            path="localAddress.town"/> </span>
                                                    </div>

                                                    <div class="control-group" id="localAddresscounty">
                                                        <label class="control-label">County:</label>

                                                        <form:input
                                                                path="localAddress.county"
                                                                id="localAddresscounty"/><span
                                                            class="help-inline"><form:errors
                                                            path="localAddress.county"/></span>
                                                    </div>

                                                    <div class="control-group" id="localAddresspin">
                                                        <label class="control-label">Pin code:</label>

                                                        <form:input
                                                                path="localAddress.pin"
                                                                id="localAddresspin"/><span
                                                            class="help-inline"><form:errors
                                                            path="localAddress.pin"/> </span>
                                                    </div>

                                                    <div class="control-group" id="localAddresscountry">
                                                        <label class="control-label">Country:</label>

                                                        <form:input
                                                                path="localAddress.country"
                                                                id="localAddresscountry"/><span
                                                            class="help-inline"><form:errors
                                                            path="localAddress.country"/> </span>
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
