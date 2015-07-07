<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/6/15
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="scriptLibraryTemplate.jsp" %>

    <spring:url value="/addfamily.action" var="familyActionURL"/>
    <spring:url value="/resources/js/createfamilygridlayout.js" var="familyGridURL"/>
    <spring:url value="/resources/js/familyselectbox.js" var="familySelectBox"/>

    <script src="${familySelectBox}" type="text/javascript"
            language="javascript"></script>

    <script src="${familyGridURL}" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            <c:if test="${showForPrayerUnitAdmin == false && showForFamilyUser == false}">
            loadSelectBox("${pageContext.request.contextPath}");
            </c:if>

            loadFamilyGrid();
            globalSubmissionOfForms('familyForm', '${familyActionURL}');

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
                                                        <h4>Family Information</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <table id="familyGrid"></table>
                                                        <div id="familyGridPager"></div>
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
                                            <a href="#family1" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Family Details</span></a>
                                        </li>
                                        <li>
                                            <a href="#family2" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Local Address</span></a>
                                        </li>
                                        <li>
                                            <a href="#family3" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Native Address</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">

                                <form:form modelAttribute="family"
                                           action="${familyActionURL}" method="post"
                                           id="familyForm" cssClass="form-inline">

                                <div class="tab-content">

                                    <div class="tab-pane active" id="family1">

                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>Family Details</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group" id="familyName">
                                                        <label class="control-label">Family Name :</label>

                                                        <form:input path="familyName" id="familyName"
                                                                    class="textBox"/>
                                                                 <span class="help-inline"><form:errors
                                                                         path="familyName"/></span>
                                                    </div>

                                                    <div class="control-group" id="parishInNative">
                                                        <label class="control-label">Native Parish :</label>

                                                        <form:input path="parishInNative"
                                                                    id="parishInNative"/>
                                                                 <span class="help-inline"><form:errors
                                                                         path="parishInNative"/></span>

                                                    </div>

                                                    <div class="control-group" id="dioceseInNative">
                                                        <label class="control-label">Native Diocese :</label>
                                                        <form:input path="dioceseInNative"
                                                                    id="dioceseInNative"/>
                                                         <span class="help-inline"><form:errors
                                                                 path="dioceseInNative"/> </span>
                                                    </div>

                                                    <div class="control-group" id="dateOfRegistration">
                                                        <label class="control-label">Date Of Registration
                                                            :</label>

                                                        <form:input
                                                                path="dateOfRegistration"
                                                                id="dateOfRegistration"/>
                                                         <span class="help-inline"><form:errors
                                                                 path="dateOfRegistration"/> </span>
                                                    </div>

                                                    <c:if test="${showForPrayerUnitAdmin == false && showForFamilyUser == false}">

                                                    <div class="control-group" id="parishId">
                                                        <label class="control-label">Parish :</label>

                                                        <form:select path="parishId"
                                                                     id="parishSelectBox">
                                                            <option value="select">--Please select--
                                                            </option>
                                                        </form:select>
                                                                         <span class="help-inline"><form:errors
                                                                                 path="parishId"/> </span>
                                                    </div>

                                                    <div class="control-group" id="massCenterId">
                                                        <label class="control-label">Mass Center :</label>
                                                        <form:select
                                                                path="massCenterId"
                                                                id="massCenterSelectBox">
                                                            <option value="select">--Please select--
                                                            </option>
                                                        </form:select>
                                                             <span class="help-inline"><form:errors
                                                                     path="massCenterId"/> </span>
                                                    </div>

                                                    <div class="control-group" id="prayerUnitId">
                                                        <label class="control-label">Prayer Unit :</label>

                                                        <form:select
                                                                path="prayerUnitId"
                                                                id="wardSelectBox">
                                                            <option value="select">--Please select--
                                                            </option>
                                                        </form:select>
                                                             <span class="help-inline"><form:errors
                                                                     path="prayerUnitId"/> </span>
                                                    </div>
                                                </div>

                                                </c:if>

                                                <c:if test="${(showForPrayerUnitAdmin == true && showForFamilyUser == false)||(showForPrayerUnitAdmin == false && showForFamilyUser == true)}">

                                                    <div class="control-group" id="parishId">
                                                        <label class="control-label">Parish :</label>

                                                        <div class="controls"><form:select path="parishId"
                                                                                           items="${parishList}"></form:select>
                                                                         <span class="help-inline"><form:errors
                                                                                 path="parishId"/> </span>
                                                        </div>
                                                    </div>

                                                    <div class="control-group" id="massCenterId">
                                                        <label class="control-label">Mass Center :</label>

                                                        <div class="controls"><form:select
                                                                path="massCenterId"
                                                                items="${massCenterList}"></form:select>
                                                             <span class="help-inline"><form:errors
                                                                     path="massCenterId"/> </span>
                                                        </div>
                                                    </div>

                                                    <div class="control-group" id="prayerUnitId">
                                                        <label class="control-label">Prayer Unit :</label>

                                                        <div class="controls"><form:select
                                                                path="prayerUnitId"
                                                                items="${prayerUnitList}"></form:select>
                                                             <span class="help-inline"><form:errors
                                                                     path="prayerUnitId"/> </span>
                                                        </div>
                                                    </div>

                                                </c:if>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="tab-pane" id="family2">

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

                                    <div class="tab-pane" id="family3">

                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        Native Address</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group" id="nativeAddressaddressLineOne">
                                                        <label class="control-label">Address Line 1 :</label>
                                                        <form:input
                                                                path="nativeAddress.addressLineOne"
                                                                id="nativeAddressaddressLineOne"/><span
                                                            class="help-inline"><form:errors
                                                            path="nativeAddress.addressLineOne"/> </span>
                                                    </div>
                                                    <div class="control-group" id="nativeAddressaddressLineTwo">
                                                        <label class="control-label">Address Line 2 :</label>
                                                        <form:input
                                                                path="nativeAddress.addressLineTwo"
                                                                id="nativeAddressaddressLineTwo"/><span
                                                            class="help-inline"><form:errors
                                                            path="nativeAddress.addressLineTwo"/></span>
                                                    </div>

                                                    <div class="control-group"
                                                         id="nativeAddressaddressLineThree">
                                                        <label class="control-label">Address Line 3 :</label>
                                                        <form:input
                                                                path="nativeAddress.addressLineThree"
                                                                id="nativeAddressaddressLineThree"/><span
                                                            class="help-inline"><form:errors
                                                            path="nativeAddress.addressLineThree"/></span>
                                                    </div>

                                                    <div class="control-group" id="nativeAddresspostOffice">
                                                        <label class="control-label">Post Office :</label>
                                                        <form:input
                                                                path="nativeAddress.postOffice"
                                                                id="nativeAddresspostOffice"/><span
                                                            class="help-inline"><form:errors
                                                            path="nativeAddress.postOffice"/></span>
                                                    </div>

                                                    <div class="control-group" id="nativeAddressdistrict">
                                                        <label class="control-label">District :</label>
                                                        <form:input
                                                                path="nativeAddress.district"
                                                                id="nativeAddressdistrict"/><span
                                                            class="help-inline"><form:errors
                                                            path="nativeAddress.district"/></span>
                                                    </div>

                                                    <div class="control-group" id="nativeAddressstate">
                                                        <label class="control-label">State :</label>
                                                        <form:input
                                                                path="nativeAddress.state"
                                                                id="nativeAddressstate"/><span
                                                            class="help-inline"><form:errors
                                                            path="nativeAddress.state"/></span>
                                                    </div>

                                                    <div class="control-group" id="nativeAddresscountry">
                                                        <label class="control-label">Country :</label>
                                                        <form:input
                                                                path="nativeAddress.country"
                                                                id="nativeAddresscountry"/><span
                                                            class="help-inline"><form:errors
                                                            path="nativeAddress.country"/></span>
                                                    </div>

                                                    <div class="control-group" id="nativeAddresspin">
                                                        <label class="control-label">Pin code :</label>
                                                        <form:input
                                                                path="nativeAddress.pin"
                                                                id="nativeAddresspin"/><span
                                                            class="help-inline"><form:errors
                                                            path="nativeAddress.pin"/></span>
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
