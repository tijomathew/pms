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

    <spring:url value="/resources/js/userselectbox.js" var="userSelectBoxURL"/>
    <spring:url value="/resources/js/createusergridlayout.js" var="userGridURL"/>
    <spring:url value="/adduser.action" var="userActionURL"/>

    <script src="${userSelectBoxURL}" type="text/javascript"
            language="javascript"></script>

    <script src="${userGridURL}" type="text/javascript"
            language="javascript"></script>
    <style type="text/css">
        .hideClass {
            display: none !important;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {

            $('#extensionOfMail').addClass('hideClass');
            $('#extensionOfMail').empty();

            loadUserGrid();

            <c:if test = "${showForPrayerUnitAdmin == false}" >
            loadSelectBox("${pageContext.request.contextPath}");
            </c:if>

            globalSubmissionOfForms('userForm', '${userActionURL}');

            $('#systemRole').change(function () {
                var systemRole = $("#systemRole option:selected").val();
                if (systemRole == 'Parish Admin') {
                    $('#extensionOfMail').removeClass('hideClass');
                    var parish = $("#parishSelectBox option:selected").text();
                    var displayValue = "@" + parish + ".pms";
                    $('#extensionOfMail').val(displayValue);
                }
                if (systemRole == 'Family User') {
                    $('#extensionOfMail').addClass('hideClass');
                    $('#extensionOfMail').empty();

                }
            });
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
                                                        <h4>User Information</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <table id="userGrid"></table>
                                                        <div id="userGridPager"></div>
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
                                            <a href="#user1" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">User Details</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">

                                <form:form modelAttribute="user"
                                           action="${userActionURL}" method="post" id="userForm" class="form-horizontal">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="user1">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>User Details</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="systemRole"
                                                                   class="col-sm-1 control-label">System Role</label>

                                                            <div class="col-sm-3" >
                                                                <form:select path="systemRole"
                                                                             id="systemRole" class="form-control">
                                                                    <form:option value="Family User">Family
                                                                        User</form:option>
                                                                    <form:option value="Parish Admin">Parish
                                                                        Admin</form:option>
                                                                    <form:option
                                                                            value="Mass Center Admin">Mass Center
                                                                        Admin</form:option>
                                                                    <form:option
                                                                            value="Prayer Unit Admin">Prayer Unit
                                                                        Admin</form:option>

                                                                </form:select>
                                                            </div>
                                                            <label for="isActive" class="col-sm-1 control-label">Active</label>

                                                            <div class="col-sm-3">
                                                                <form:select path="isActive"
                                                                             id="isActive" class="form-control">
                                                                    <form:option value="Active">Active</form:option>
                                                                    <form:option
                                                                            value="De-active">De-active</form:option>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                        <c:if test="${showForPrayerUnitAdmin == false}">
                                                            <div class="form-group">
                                                                <label for="parishId"
                                                                       class="col-sm-1 control-label">Parish</label>

                                                                <div class="col-sm-3" id="parishSelectBoxer">
                                                                    <form:select path="parishId"
                                                                                 id="parishSelectBox" class="form-control">
                                                                    </form:select>
                                                                </div>
                                                                <label for="massCenterId" class="col-sm-1 control-label">Mass Center</label>

                                                                <div class="col-sm-3" id="massCenterSelectBoxer">
                                                                    <form:select
                                                                            path="massCenterId"
                                                                            id="massCenterSelectBox" class="form-control">
                                                                    </form:select>
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="prayerUnitId"
                                                                       class="col-sm-1 control-label">Prayer Unit</label>

                                                                <div class="col-sm-3" id="prayerUnitSelectBoxer">
                                                                    <form:select
                                                                            path="prayerUnitId"
                                                                            id="prayerUnitSelectBox" class="form-control">
                                                                    </form:select>
                                                                </div>
                                                                <label for="familyId" class="col-sm-1 control-label">Family</label>

                                                                <div class="col-sm-3" id="familySelectBoxer">
                                                                    <form:select path="familyId"
                                                                                 id="familySelectBox" class="form-control">
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${showForPrayerUnitAdmin == true}">

                                                            <div class="form-group">
                                                                <label for="parishId"
                                                                       class="col-sm-1 control-label">Parish</label>

                                                                <div class="col-sm-3" id="parishSelectBoxer">
                                                                    <form:select path="parishId"
                                                                                 items="${parishList}"
                                                                                 id="parishId" class="form-control"></form:select>
                                                                </div>
                                                                <label for="massCenterId" class="col-sm-1 control-label">Mass Center</label>

                                                                <div class="col-sm-3" id="massCenterSelectBoxer">
                                                                    <form:select
                                                                            path="massCenterId"
                                                                            items="${massCenterList}"
                                                                            id="massCenterId" class="form-control"></form:select>

                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="prayerUnitId"
                                                                       class="col-sm-1 control-label">Prayer Unit</label>

                                                                <div class="col-sm-3" id="prayerUnitSelectBoxer">
                                                                    <form:select
                                                                            path="prayerUnitId"
                                                                            items="${prayerUnitList}"
                                                                            id="prayerUnitId" class="form-control"></form:select>
                                                                </div>
                                                                <label for="familyId" class="col-sm-1 control-label">Family</label>

                                                                <div class="col-sm-3" id="familySelectBoxer">
                                                                    <form:select path="familyId"
                                                                                 id="familySelectBox" class="form-control">
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </c:if>

                                                        <div class="form-group">
                                                            <label for="email"
                                                                   class="col-sm-1 control-label">Email</label>

                                                            <div class="col-sm-3" id="email">
                                                                <form:input path="email"
                                                                            id="email" class="form-control"/> <div><form:input path="extensionOfEmail"
                                                                                                                             readonly="true"
                                                                                                                             id="extensionOfMail" class="form-control"/></div>
                                                            </div>
                                                            <label for="password" class="col-sm-1 control-label">Password</label>

                                                            <div class="col-sm-3" id="password">
                                                                <form:password path="password"
                                                                               id="password" class="form-control"/>
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
            <!-- container -->
        </div>
        <!--wrap -->
    </div>
    <!-- page-content -->

    <%@include file="footerPanelTemplate.jsp" %>

</div>
</body>
</html>
