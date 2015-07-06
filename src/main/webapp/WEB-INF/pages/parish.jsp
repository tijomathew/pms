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

            loadParishGrid();

            globalSubmissionOfForms('parishForm', '${parishActionURL}');

        });

    </script>

    <%@include file="headerTemplate.jsp" %>
</head>

<body>

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
                                                        <h4>Parish Information</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <table id="parishGrid"></table>
                                                        <div id="parishGridPager"></div>
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

                                <form:form modelAttribute="parish"
                                           action="${parishActionURL}" method="post"
                                           id="parishForm" class="form-horizontal">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="parish1">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>Parish Details</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="control-group" id="churchName">
                                                            <label class="control-label">Church Name :</label>
                                                            <form:input path="churchName"
                                                                        id="churchName"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="churchName"/></span>
                                                        </div>
                                                        <div class="control-group" id="riteName">
                                                            <label class="control-label">Rite Name :</label>

                                                            <form:input path="riteName"
                                                                        id="riteName"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="riteName"/></span>
                                                        </div>
                                                        <div class="control-group" id="dioceseName">
                                                            <label class="control-label">Diocese Name :</label>

                                                            <form:input path="dioceseName"
                                                                        id="dioceseName"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="dioceseName"/></span>
                                                        </div>
                                                        <div class="control-group" id="foraneName">
                                                            <label class="control-label">Forane Name :</label>

                                                            <form:input path="foraneName"
                                                                        id="foraneName"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="foraneName"/></span>
                                                        </div>
                                                        <div class="control-group" id="parishID">
                                                            <label class="control-label">Parish ID :</label>

                                                            <form:input path="parishID"
                                                                        id="parishID"
                                                                        class="textBox"
                                                                        readonly="true"/><span
                                                                class="help-inline"><form:errors
                                                                path="parishID"/></span>
                                                        </div>
                                                        <div class="control-group" id="name">
                                                            <label class="control-label">Parish Name :</label>

                                                            <form:input path="name" id="name"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="name"/></span>
                                                        </div>
                                                        <div class="control-group" id="place">
                                                            <label class="control-label">Parish Place :</label>

                                                            <form:input path="place"
                                                                        id="place"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="place"/></span>
                                                        </div>
                                                        <div class="control-group" id="code">
                                                            <label class="control-label">Parish Code :</label>

                                                            <form:input path="code" id="code"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="code"/></span>
                                                        </div>
                                                        <div class="control-group" id="webSite">
                                                            <label class="control-label">Parish Web-site :</label>

                                                            <form:input path="webSite"
                                                                        id="webSite"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="webSite"/></span>
                                                        </div>
                                                        <div class="control-group" id="facebookPage">
                                                            <label class="control-label">Parish Facebook Page
                                                                :</label>

                                                            <form:input path="facebookPage"
                                                                        id="facebookPage"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="facebookPage"/></span>
                                                        </div>
                                                        <div class="control-group" id="drivingRoute">
                                                            <label class="control-label">Driving Route :</label>

                                                            <form:input path="drivingRoute"
                                                                        id="drivingRoute"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="drivingRoute"/></span>
                                                        </div>
                                                        <div class="control-group" id="map">
                                                            <label class="control-label">Map :</label>

                                                            <form:input path="map" id="map"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="map"/></span>
                                                        </div>
                                                        <div class="control-group" id="registeredDate">
                                                            <label class="control-label">Registered Date :</label>

                                                            <form:input path="registeredDate"
                                                                        id="registeredDate"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="registeredDate"/></span>
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
                                                        <div class="control-group" id="mobileNo">
                                                            <label class="control-label">Mobile No. :</label>

                                                            <form:input path="mobileNo"
                                                                        id="mobileNo"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="mobileNo"/></span>
                                                        </div>
                                                        <div class="control-group" id="email">
                                                            <label class="control-label">Email :</label>

                                                            <form:input path="email"
                                                                        id="email"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="email"/></span>
                                                        </div>
                                                        <div class="control-group" id="landLineNo">
                                                            <label class="control-label">Land Line No. :</label>

                                                            <form:input path="landLineNo"
                                                                        id="landLineNo"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="landLineNo"/></span>
                                                        </div>
                                                        <div class="control-group" id="faxNo">
                                                            <label class="control-label">Fax No. :</label>

                                                            <form:input path="faxNo"
                                                                        id="faxNo"
                                                                        class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="faxNo"/></span>
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
            <!-- container -->
        </div>
        <!--wrap -->
    </div>
    <!-- page-content -->

    <%@include
            file="footerPanelTemplate.jsp" %>

</div>
<script type="text/javascript">

</script>
</body>
</html>

