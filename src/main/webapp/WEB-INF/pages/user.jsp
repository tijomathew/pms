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

        .hideSelectImage {
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {
            backToTop();

            loadUserGrid();

            <c:if test = "${showForPrayerUnitAdmin == false}" >
            loadSelectBox("${pageContext.request.contextPath}");
            </c:if>

            globalSubmissionOfForms('userForm', '${userActionURL}', 'userGrid');

            $('#systemRole').change(function () {

                $('#parishSelectBox').prop('selectedIndex', 0);
                $('#massCenterSelectBox').prop('selectedIndex', 0);
                $('#prayerUnitSelectBox').prop('selectedIndex', 0);
                $('#familySelectBox').prop('selectedIndex', 0);

                $('#massCenterSelectBox').prop('disabled', false);
                $('#prayerUnitSelectBox').prop('disabled', false);
                $('#familySelectBox').prop('disabled', false);

                $('#massCenterSelectBox').removeClass('hideSelectImage');
                $('#prayerUnitSelectBox').removeClass('hideSelectImage');
                $('#familySelectBox').removeClass('hideSelectImage');

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
                                                        <h4>User Information</h4>
                                                    </div>
                                                    <div class="panel-body nopadding">
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


            <div class="container padding7 paddingTop0">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue nomargin">
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
                            <div class="panel-body padding7">

                                <form:form modelAttribute="user"
                                           action="${userActionURL}" method="post" id="userForm"
                                           class="form-horizontal nomargin">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="user1">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>User Details</h4>
                                                    </div>
                                                    <div class="panel-body padding7">

                                                        <div class="form-group">
                                                            <label for="systemRole"
                                                                   class="col-sm-2 control-label">System Role</label>

                                                            <div class="col-sm-4">
                                                                <form:select path="systemRole"
                                                                             id="systemRole" class="form-control" items="${systemRoles}">
                                                                </form:select>
                                                            </div>
                                                            <label for="isActive"
                                                                   class="col-sm-2 control-label">Active</label>

                                                            <div class="col-sm-4">
                                                                <form:select path="isActive"
                                                                             id="isActive" class="form-control" items="${systemRoleStatus}">
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                        <c:if test="${showForPrayerUnitAdmin == false}">
                                                            <div class="form-group">
                                                                <label for="parishId"
                                                                       class="col-sm-2 control-label">Parish</label>

                                                                <div class="col-sm-4" id="parishSelectBoxer">
                                                                    <form:select path="parishId"
                                                                                 id="parishSelectBox"
                                                                                 class="form-control">
                                                                    </form:select>
                                                                </div>
                                                                <label for="massCenterId"
                                                                       class="col-sm-2 control-label">Mass
                                                                    Center</label>

                                                                <div class="col-sm-4" id="massCenterSelectBoxer">
                                                                    <form:select
                                                                            path="massCenterId"
                                                                            id="massCenterSelectBox"
                                                                            class="form-control">
                                                                    </form:select>
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="prayerUnitId"
                                                                       class="col-sm-2 control-label">Prayer
                                                                    Unit</label>

                                                                <div class="col-sm-4" id="prayerUnitSelectBoxer">
                                                                    <form:select
                                                                            path="prayerUnitId"
                                                                            id="prayerUnitSelectBox"
                                                                            class="form-control">
                                                                    </form:select>
                                                                </div>
                                                                <label for="familyId" class="col-sm-2 control-label">Family</label>

                                                                <div class="col-sm-4" id="familySelectBoxer">
                                                                    <form:select path="familyId"
                                                                                 id="familySelectBox"
                                                                                 class="form-control">
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${showForPrayerUnitAdmin == true}">

                                                            <div class="form-group">
                                                                <label for="parishId"
                                                                       class="col-sm-2 control-label">Parish</label>

                                                                <div class="col-sm-4" id="parishSelectBoxer">
                                                                    <form:select path="parishId"
                                                                                 items="${parishList}"
                                                                                 id="parishId"
                                                                                 class="form-control"></form:select>
                                                                </div>
                                                                <label for="massCenterId"
                                                                       class="col-sm-2 control-label">Mass
                                                                    Center</label>

                                                                <div class="col-sm-4" id="massCenterSelectBoxer">
                                                                    <form:select
                                                                            path="massCenterId"
                                                                            items="${massCenterList}"
                                                                            id="massCenterId"
                                                                            class="form-control"></form:select>

                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="prayerUnitId"
                                                                       class="col-sm-2 control-label">Prayer
                                                                    Unit</label>

                                                                <div class="col-sm-4" id="prayerUnitSelectBoxer">
                                                                    <form:select
                                                                            path="prayerUnitId"
                                                                            items="${prayerUnitList}"
                                                                            id="prayerUnitId"
                                                                            class="form-control"></form:select>
                                                                </div>
                                                                <label for="familyId" class="col-sm-2 control-label">Family</label>

                                                                <div class="col-sm-4" id="familySelectBoxer">
                                                                    <form:select path="familyId"
                                                                                 id="familySelectBox"
                                                                                 class="form-control">
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </c:if>

                                                        <div class="form-group">
                                                            <label for="email"
                                                                   class="col-sm-2 control-label">Email</label>

                                                            <div class="col-sm-4" id="email">
                                                                <form:input path="email"
                                                                            id="email" class="form-control"/>
                                                            </div>
                                                            <label for="password" class="col-sm-2 control-label">Password</label>

                                                            <div class="col-sm-4" id="password">
                                                                <form:password path="password"
                                                                               id="password" class="form-control"/>
                                                                <form:checkbox path="sendMailFlag" value="true"
                                                                               checked="true"/>Send an email
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
    <!-- page-content -->

    <%@include file="footerPanelTemplate.jsp" %>

</div>
</body>
</html>
