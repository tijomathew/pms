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
            globalSubmissionOfForms('familyForm', '${familyActionURL}','familyGrid');

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
                                           id="familyForm" cssClass="form-horizontal">

                                <div class="tab-content">

                                    <div class="tab-pane active" id="family1">

                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>Family Details</h4>
                                                </div>
                                                <div class="panel-body">

                                                    <div class="form-group">
                                                        <label for="familyName"  class="col-sm-1 control-label">Family Name</label>
                                                        <div class="col-sm-3">
                                                                <form:input path="familyName" id="familyName"  class="form-control"/>
                                                        </div>

                                                        <label for="parishInNative"  class="col-sm-1 control-label">Native Parish</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="parishInNative" id="parishInNative" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="dioceseInNative"  class="col-sm-1 control-label">Native Diocese</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="dioceseInNative" id="dioceseInNative"  class="form-control"/>
                                                        </div>

                                                        <label for="dateOfRegistration"  class="col-sm-1 control-label">Date Of Registration</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="dateOfRegistration" id="dateOfRegistration" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <c:if test="${showForPrayerUnitAdmin == false && showForFamilyUser == false}">

                                                    <div class="form-group">
                                                        <label for="parishId"  class="col-sm-1 control-label">Parish</label>
                                                        <div class="col-sm-3">
                                                            <form:select path="parishId" id="parishSelectBox" class="form-control">
                                                                <option value="select">--Please select--</option>
                                                            </form:select>
                                                        </div>
                                                        <label for="parishId"  class="col-sm-1 control-label">Mass Center</label>
                                                        <div class="col-sm-3">
                                                            <form:select path="massCenterId" id="massCenterSelectBox" class="form-control">
                                                                <option value="select">--Please select--</option>
                                                            </form:select>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="prayerUnitId"  class="col-sm-1 control-label">Prayer Unit</label>
                                                        <div class="col-sm-3">
                                                            <form:select path="prayerUnitId" id="wardSelectBox" class="form-control">
                                                                <option value="select">--Please select--</option>
                                                            </form:select>
                                                        </div>

                                                    </div>

                                                </div>

                                                </c:if>

                                                <c:if test="${(showForPrayerUnitAdmin == true && showForFamilyUser == false)||(showForPrayerUnitAdmin == false && showForFamilyUser == true)}">


                                                    <div class="form-group">
                                                        <label for="parishId"  class="col-sm-1 control-label">Parish</label>
                                                        <div class="col-sm-3">
                                                            <form:select path="parishId" items="${parishList}" class="form-control"></form:select>
                                                        </div>
                                                        <label for="massCenterId"  class="col-sm-1 control-label">Mass Center</label>
                                                        <div class="col-sm-3">
                                                            <form:select path="massCenterId" items="${massCenterList}" class="form-control"></form:select>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="prayerUnitId"  class="col-sm-1 control-label">Prayer Unit</label>
                                                        <div class="col-sm-3">
                                                            <form:select path="prayerUnitId" items="${prayerUnitList}" class="form-control"></form:select>
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

                                                    <div class="form-group">
                                                        <label for="localAddress.addressLineOne"  class="col-sm-1 control-label">Address Line 1</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="localAddress.addressLineOne" id="localAddressaddressLineOne" class="form-control"/>
                                                        </div>
                                                        <label for="localAddress.addressLineTwo"  class="col-sm-1 control-label">Address Line 2</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="localAddress.addressLineTwo" id="localAddressaddressLineTwo" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="localAddress.addressLineThree"  class="col-sm-1 control-label">Address Line 3</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="localAddress.addressLineThree" id="localAddressaddressLineThree" class="form-control"/>
                                                        </div>
                                                        <label for="localAddress.town"  class="col-sm-1 control-label">Town</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="localAddress.town" id="localAddresstown" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="localAddress.county"  class="col-sm-1 control-label">County</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="localAddress.county" id="localAddresscounty" class="form-control"/>
                                                        </div>
                                                        <label for="localAddress.pin"  class="col-sm-1 control-label">Pin code</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="localAddress.pin" id="localAddresspin" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="localAddress.country" class="col-sm-1 control-label">Country</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="localAddress.country" id="localAddresscountry" class="form-control"/>
                                                        </div>
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

                                                    <div class="form-group">
                                                        <label for="nativeAddress.addressLineOne"  class="col-sm-1 control-label">Address Line 1</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="nativeAddress.addressLineOne" id="nativeAddressaddressLineOne" class="form-control"/>
                                                        </div>
                                                        <label for="nativeAddress.addressLineTwo"  class="col-sm-1 control-label">Address Line 2</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="nativeAddress.addressLineTwo" id="nativeAddressaddressLineTwo" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="nativeAddress.addressLineThree"  class="col-sm-1 control-label">Address Line 3</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="nativeAddress.addressLineThree" id="nativeAddressaddressLineThree" class="form-control"/>
                                                        </div>
                                                        <label for="nativeAddress.postOffice"  class="col-sm-1 control-label">Post Office</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="nativeAddress.postOffice" id="nativeAddresspostOffice" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="nativeAddress.district"  class="col-sm-1 control-label">District</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="nativeAddress.district" id="nativeAddressdistrict" class="form-control"/>
                                                        </div>
                                                        <label for="nativeAddress.state"  class="col-sm-1 control-label">State</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="nativeAddress.state" id="nativeAddressstate" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="nativeAddress.country"  class="col-sm-1 control-label">Country</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="nativeAddress.country" id="nativeAddresscountry" class="form-control"/>
                                                        </div>
                                                        <label for="nativeAddress.pin"  class="col-sm-1 control-label">Pin code</label>
                                                        <div class="col-sm-3">
                                                            <form:input path="nativeAddress.pin" id="nativeAddresspin" class="form-control" />
                                                        </div>
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
                                        class="btn btn-primary">Save</button>
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
