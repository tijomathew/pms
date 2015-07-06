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
    <spring:url value="/resources/js/createmasscentergridlayout.js" var="masscenterGridURL"/>
    <spring:url value="/addmasscenter.action" var="massCenterActionURL"/>

    <script src="${masscenterGridURL}" type="text/javascript"
            language="javascript"></script>

    <script src="${priestdesignationdisplayURL}" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#masscenterViewer').bind("click", function () {
                loadMassCenterGrid();
            });
            jQuery('#parishSelectBox').change(function () {
                loadPriestDesignationBoxes('${pageContext.request.contextPath}');
            });

            globalSubmissionOfForms('massCenterForm', '${massCenterActionURL}');

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
                            <div class="panel-heading">
                                <h4>
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a href="#masscenterRegistration" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Mass Center Registration</span></a>
                                        </li>
                                        <li>
                                            <a href="#masscenterView" data-toggle="tab" id="masscenterViewer"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Mass Center View</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="masscenterRegistration">
                                        <form:form modelAttribute="massCenter"
                                                   action="${massCenterActionURL}" method="post"
                                                   id="massCenterForm">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="panel panel-indigo">
                                                        <div class="panel-heading">
                                                            <h4>Mass Center Details</h4>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="control-group" id="name">
                                                                <label class="control-label">Mass Center Name :</label>

                                                                <form:input path="name"
                                                                            id="name"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="name"/></span>

                                                            </div>
                                                            <div class="control-group" id="centerCode">
                                                                <label class="control-label">Mass Center Code :</label>

                                                                <form:input path="centerCode"
                                                                            id="centerCode"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="centerCode"/></span>

                                                            </div>
                                                            <div class="control-group" id="massCenterID">
                                                                <label class="control-label">Mass Center ID :</label>

                                                                <form:input path="massCenterID"
                                                                            id="massCenterID"
                                                                            readonly="true"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="massCenterID"/></span>

                                                            </div>
                                                            <div class="control-group" id="place">
                                                                <label class="control-label">Mass Center Place :</label>

                                                                <form:input path="place"
                                                                            id="place"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="place"/></span>

                                                            </div>
                                                            <div class="control-group" id="patronName">
                                                                <label class="control-label">Patron Name :</label>

                                                                <form:input path="patronName"
                                                                            id="patronName"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="patronName"/></span>

                                                            </div>
                                                            <div class="control-group" id="landLineNo">
                                                                <label class="control-label">Mass Center Land Line No.
                                                                    :</label>

                                                                <form:input path="landLineNo"
                                                                            id="landLineNo"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="landLineNo"/></span>

                                                            </div>
                                                            <div class="control-group" id="mobileNo">
                                                                <label class="control-label">Mass Center Mobile No.
                                                                    :</label>

                                                                <form:input path="mobileNo"
                                                                            id="mobileNo"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="mobileNo"/></span>

                                                            </div>
                                                            <div class="control-group" id="email">
                                                                <label class="control-label">Mass Center Email :</label>

                                                                <form:input path="email"
                                                                            id="email"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="email"/></span>

                                                            </div>
                                                            <div class="control-group" id="faxNo">
                                                                <label class="control-label">Mass Center Fax No.
                                                                    :</label>

                                                                <form:input path="faxNo"
                                                                            id="faxNo"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="faxNo"/></span>

                                                            </div>
                                                            <div class="control-group" id="facebookPage">
                                                                <label class="control-label">Facebook Page :</label>

                                                                <form:input path="facebookPage"
                                                                            id="facebookPage"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="facebookPage"/></span>

                                                            </div>
                                                            <div class="control-group" id="registeredDate">
                                                                <label class="control-label">Registered Date :</label>

                                                                <form:input path="registeredDate"
                                                                            id="registeredDate"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="registeredDate"/></span>

                                                            </div>
                                                            <div class="control-group" id="drivingRoute">
                                                                <label class="control-label">Driving Route :</label>

                                                                <form:input path="drivingRoute"
                                                                            id="drivingRoute"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="drivingRoute"/></span>

                                                            </div>
                                                            <div class="control-group" id="map">
                                                                <label class="control-label">Map :</label>

                                                                <form:input path="map"
                                                                            id="map"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="map"/></span>

                                                            </div>
                                                            <div class="control-group" id="parishSelectBoxer">
                                                                <label class="control-label">Parish:</label>

                                                                <form:select path="parish"
                                                                             items="${parishList}"
                                                                             id="parishSelectBox"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="parish"/></span>

                                                            </div>
                                                            <div class="control-group" id="priestSelectBoxer">
                                                                <label class="control-label">Priest :</label>

                                                                <div id="priestSelectBox">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6">
                                                    <div class="panel panel-grape">
                                                        <div class="panel-heading">
                                                            <h4>Local Adderss</h4>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="control-group"
                                                                 id="localAddressaddressLineOne">
                                                                <label class="control-label">Address Line 1
                                                                    :</label>


                                                                <form:input path="localAddress.addressLineOne"
                                                                            id="localAddressaddressLineOne"/> <span
                                                                    class="help-inline"><form:errors
                                                                    path="localAddress.addressLineOne"/> </span>

                                                            </div>
                                                            <div class="control-group"
                                                                 id="localAddressaddressLineTwo">
                                                                <label class="control-label">Address Line 2
                                                                    :</label>

                                                                <form:input
                                                                        path="localAddress.addressLineTwo"
                                                                        id="localAddressaddressLineTwo"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="localAddress.addressLineTwo"/> </span>

                                                            </div>

                                                            <div class="control-group"
                                                                 id="localAddressaddressLineThree">
                                                                <label class="control-label">Address Line 3
                                                                    :</label>

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


                                            <div class="form-actions">
                                                <button type="submit" value="Save" class="btn btn-primary"/>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="tab-pane " id="masscenterView">
                                        <table id="massCenterGrid"></table>
                                        <div id="massCenterGridPager"></div>
                                    </div>

                                </div>
                            </div>
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
