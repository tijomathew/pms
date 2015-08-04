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

            backToTop();

            loadDatePicker();

            loadMassCenterGrid();

            globalSubmissionOfForms('massCenterForm', '${massCenterActionURL}', 'massCenterGrid');

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
            <div class="container padding7">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue nomargin">
                            <div class="panel-body noborder nopadding">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tabs-1">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="panel panel-grape nomargin">
                                                    <div class="panel-heading">
                                                        <h4>Mass Center Information</h4>
                                                    </div>
                                                    <div class="panel-body nopadding">
                                                        <table id="massCenterGrid"></table>
                                                        <div id="massCenterGridPager"></div>
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


            <div class="container padding7 paddingTop0">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue nomargin">
                            <div class="panel-heading">
                                <h4>
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a href="#masscenter1" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Mass Center Details</span></a>
                                        </li>
                                        <li>
                                            <a href="#masscenter2" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Local Address</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body padding7">

                                <form:form modelAttribute="massCenter"
                                           action="${massCenterActionURL}" method="post"
                                           id="massCenterForm" cssClass="form-horizontal nomargin">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="masscenter1">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>Mass Center Details</h4>
                                                    </div>
                                                    <div class="panel-body padding7">

                                                        <div class="form-group">
                                                            <label for="massCenterName" class="col-sm-2 control-label">Mass Center
                                                                Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="massCenterName" id="massCenterName" class="form-control"/>
                                                            </div>
                                                            <label for="massCenterNo" class="col-sm-2 control-label">Mass
                                                                Center No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="massCenterNo" id="massCenterNo"
                                                                            class="form-control" readonly="true"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="place" class="col-sm-2 control-label">Mass
                                                                Center Place</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="place" id="place"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="drivingRoute" class="col-sm-2 control-label">Parish</label>

                                                            <div class="col-sm-4">
                                                                <form:select path="mappedParish" items="${parishList}"
                                                                             id="parishSelectBox" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="patronName" class="col-sm-2 control-label">Patron
                                                                Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="patronName" id="patronName"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="landLineNo" class="col-sm-2 control-label">Mass
                                                                Center Land Line No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="landLineNo" id="landLineNo"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="mobileNo" class="col-sm-2 control-label">Mass
                                                                Center Mobile No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="mobileNo" id="mobileNo"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="email" class="col-sm-2 control-label">Mass
                                                                Center Email</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="email" id="email"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="faxNo" class="col-sm-2 control-label">Mass
                                                                Center Fax No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="faxNo" id="faxNo"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="landLineNo" class="col-sm-2 control-label">Facebook
                                                                Page</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="facebookPage" id="facebookPage"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="registeredDate" class="col-sm-2 control-label">Registered
                                                                Date</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="registeredDate" id="registeredDate"
                                                                            class="form-control date"/>
                                                            </div>
                                                            <label for="drivingRoute" class="col-sm-2 control-label">Driving
                                                                Route</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="drivingRoute" id="drivingRoute"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="map" class="col-sm-2 control-label">Map</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="map" id="map" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="tab-pane" id="masscenter2">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Local Address</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="localAddress.addressLineOne"
                                                                   class="col-sm-2 control-label">Address Line 1</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="localAddress.addressLineOne"
                                                                            id="localAddressaddressLineOne"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.addressLineTwo"
                                                                   class="col-sm-2 control-label">Address Line 2</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="localAddress.addressLineTwo"
                                                                            id="localAddressaddressLineTwo"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.addressLineThree"
                                                                   class="col-sm-2 control-label">Address Line 3</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="localAddress.addressLineThree"
                                                                            id="localAddressaddressLineThree"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.addressLineTwo"
                                                                   class="col-sm-2 control-label">Town</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="localAddress.town"
                                                                            id="localAddresstown" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.addressLineThree"
                                                                   class="col-sm-2 control-label">County</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="localAddress.county"
                                                                            id="localAddresscounty"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.pin"
                                                                   class="col-sm-2 control-label">Pin code</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="localAddress.pin" id="localAddresspin"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.addressLineThree"
                                                                   class="col-sm-2 control-label">Country</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="localAddress.country"
                                                                            id="localAddresscountry"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="row nomargin">
                                            <div class="col-md-12 text-left">
                                                <button type="submit" value="Save"
                                                        class="btn btn-primary defaultButtonWidth">SAVE
                                                </button>
                                                <button type="submit" value="Save"
                                                        class="btn btn-primary defaultButtonWidth">RESET
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
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

    <%@include file="footerPanelTemplate.jsp" %>

</div>

</body>
</html>
