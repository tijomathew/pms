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

    <script type="text/javascript">
        $(document).ready(function () {

            loadUserGrid();

            <c:if test = "${showForPrayerUnitAdmin == false}" >
            loadSelectBox("${pageContext.request.contextPath}");
            </c:if>

            globalSubmissionOfForms('userForm', '${userActionURL}');
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
                                           action="${userActionURL}" method="post" id="userForm">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="user1">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>User Details</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="control-group" id="email">
                                                            <label class="control-label">Email :</label>
                                                            <form:input path="email"
                                                                        id="email"/><span
                                                                class="help-inline"><form:errors
                                                                path="email"/></span>

                                                        </div>

                                                        <div class="control-group" id="systemRole">
                                                            <label class="control-label">System Role :</label>
                                                            <form:select path="systemRole"
                                                                         id="systemRole">
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
                                                               <span class="help-inline"><form:errors
                                                                       path="systemRole"/></span>
                                                        </div>

                                                        <div class="control-group" id="isActive">
                                                            <label class="control-label">Active :</label>
                                                            <form:select path="isActive"
                                                                         id="isActive">
                                                                <form:option value="Active">Active</form:option>
                                                                <form:option
                                                                        value="De-active">De-active</form:option>
                                                            </form:select>
                                                               <span class="help-inline"><form:errors
                                                                       path="isActive"/></span>
                                                        </div>

                                                        <c:if test="${showForPrayerUnitAdmin == false}">
                                                            <div class="control-group" id="parishSelectBoxer">
                                                                <label class="control-label">Parish:</label>
                                                                <form:select path="parishId"
                                                                             id="parishSelectBox">
                                                                </form:select><span class="help-inline"><form:errors
                                                                    path="parishId"/></span>
                                                            </div>

                                                            <div class="control-group" id="massCenterSelectBoxer">
                                                                <label class="control-label">Mass Center:</label>
                                                                <form:select
                                                                        path="massCenterId"
                                                                        id="massCenterSelectBox">
                                                                </form:select><span class="help-inline"><form:errors
                                                                    path="massCenterId"/></span>
                                                            </div>

                                                            <div class="control-group" id="prayerUnitSelectBoxer">
                                                                <label class="control-label">Prayer Unit:</label>
                                                                <form:select
                                                                        path="prayerUnitId"
                                                                        id="prayerUnitSelectBox">
                                                                </form:select><span class="help-inline"><form:errors
                                                                    path="prayerUnitId"/></span>
                                                            </div>

                                                            <div class="control-group" id="familySelectBoxer">
                                                                <label class="control-label">Family:</label>
                                                                <form:select path="familyId"
                                                                             id="familySelectBox">
                                                                </form:select><span
                                                                    class="help-inline"><form:errors
                                                                    path="familyId"/></span>
                                                            </div>

                                                        </c:if>
                                                        <c:if test="${showForPrayerUnitAdmin == true}">
                                                            <div class="control-group" id="parishId">
                                                                <label class="control-label">Parish:</label>
                                                                <form:select path="parishId"
                                                                             items="${parishList}"
                                                                             id="parishId"></form:select>
                                                                    <span class="help-inline"><form:errors
                                                                            path="parishId"/></span>
                                                            </div>

                                                            <div class="control-group" id="massCenterId">
                                                                <label class="control-label">Mass Center:</label>
                                                                <form:select
                                                                        path="massCenterId"
                                                                        items="${massCenterList}"
                                                                        id="massCenterId"></form:select>
                                                                   <span class="help-inline"><form:errors
                                                                           path="massCenterId"/></span>
                                                            </div>

                                                            <div class="control-group" id="prayerUnitId">
                                                                <label class="control-label">Prayer Unit:</label>
                                                                <form:select
                                                                        path="prayerUnitId"
                                                                        items="${prayerUnitList}"
                                                                        id="prayerUnitId"></form:select>
                                                                   <span class="help-inline"><form:errors
                                                                           path="prayerUnitId"/></span>
                                                            </div>

                                                        </c:if>
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

    <%@include file="footerPanelTemplate.jsp" %>

</div>
</body>
</html>
