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

            <c:if test = "${showForPrayerUnitAdmin == true}" >
            $('form select#familySelectBox').prepend($('<option/>', {text: '--Select--', value: '', selected: true})).attr('disabled', true);
            </c:if>

            <c:if test = "${showForPrayerUnitAdmin == false}" >
            loadSelectBox("${pageContext.request.contextPath}");
            $('form select').prepend($('<option/>', {text: '--Select--', value: '', selected: true})).attr('disabled', true);
            </c:if>

            function reactiveAdminsComboBox() {
                $('#parishSelectBox').prop('selectedIndex', 0);
                $('#massCentreSelectBox').prop('selectedIndex', 0);
                $('#prayerUnitSelectBox').prop('selectedIndex', 0);
                $('#familySelectBox').prop('selectedIndex', 0);

                $('#massCentreSelectBox').prop('disabled', false);
                $('#prayerUnitSelectBox').prop('disabled', false);
                $('#familySelectBox').prop('disabled', false);

                $('#massCentreSelectBox').removeClass('hideSelectImage');
                $('#prayerUnitSelectBox').removeClass('hideSelectImage');
                $('#familySelectBox').removeClass('hideSelectImage');
            }

            $('#systemRole').change(function () {
                reactiveAdminsComboBox();
                var selectedSystemRole = $('#systemRole').val();
                switch (selectedSystemRole) {
                    case 'PARISH_ADMIN':
                        $('#massCentreSelectBox').prop('disabled', true);
                        $('#prayerUnitSelectBox').prop('disabled', true);
                        $('#familySelectBox').prop('disabled', true);

                        $('#massCentreSelectBox').addClass('hideSelectImage');
                        $('#prayerUnitSelectBox').addClass('hideSelectImage');
                        $('#familySelectBox').addClass('hideSelectImage');

                        $('#massCentreSelectBox').find('option').remove();
                        $('#prayerUnitSelectBox').find('option').remove();
                        $('#familySelectBox').find('option').remove();
                        break;
                    case 'MASS_CENTER_ADMIN':
                        $('#prayerUnitSelectBox').prop('disabled', true);
                        $('#familySelectBox').prop('disabled', true);

                        $('#prayerUnitSelectBox').addClass('hideSelectImage');
                        $('#familySelectBox').addClass('hideSelectImage');

                        $('#prayerUnitSelectBox').find('option').remove();
                        $('#familySelectBox').find('option').remove();
                        break;
                    case 'PRAYER_UNIT_ADMIN':
                        $('#familySelectBox').prop('disabled', true);

                        $('#familySelectBox').addClass('hideSelectImage');

                        $('#familySelectBox').find('option').remove();
                        break;
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
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue">
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tabs-1">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="panel outer-border">
                                                    <div class="panel-heading">
                                                        <h4>Users</h4>
                                                    </div>

                                                    <div class="panel-body">

                                                        <table id="userGrid"></table>
                                                        <div id="userGridPager"></div>

                                                        <div class="tab-content" style="padding: 10px;">

                                                            <div class="tab-pane active">

                                                                <div class="panel hidedisplay" id="panelDiv">

                                                                    <div class="panel-heading">
                                                                        <h4>
                                                                            <ul class="nav nav-tabs">
                                                                                <li class="active">
                                                                                    <a href="#user1"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-list visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">User Details</span></a>
                                                                                </li>
                                                                            </ul>
                                                                        </h4>
                                                                    </div>

                                                                    <div class="panel-body">
                                                                        <form:form modelAttribute="user"
                                                                                   action="${userActionURL}"
                                                                                   method="post" id="userForm"
                                                                                   class="form-horizontal nomargin">

                                                                            <div class="tab-content">

                                                                                <div class="tab-pane active" id="user1">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">

                                                                                            <div class="form-group">
                                                                                                <label for="systemRole"
                                                                                                       class="col-sm-2 control-label required">System
                                                                                                    Role</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:select
                                                                                                            path="systemRole"
                                                                                                            id="systemRole"
                                                                                                            class="form-control toaddUnderScore"
                                                                                                            items="${systemRoles}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                                <label for="isActive"
                                                                                                       class="col-sm-2 control-label required">Active</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:select
                                                                                                            path="isActive"
                                                                                                            id="isActive"
                                                                                                            class="form-control"
                                                                                                            items="${systemRoleStatus}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="usersOfParishes"
                                                                                                       class="col-sm-2 control-label required">Parish</label>

                                                                                                <div class="col-sm-3"
                                                                                                     id="parishSelectBoxer">
                                                                                                    <form:select
                                                                                                            path="usersOfParishes"
                                                                                                            id="parishSelectBox"
                                                                                                            class="form-control"
                                                                                                            items="${parishList}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                                <label for="usersOfMassCentres"
                                                                                                       class="col-sm-2 control-label required">Mass
                                                                                                    Center</label>

                                                                                                <div class="col-sm-3"
                                                                                                     id="massCentreSelectBoxer">
                                                                                                    <form:select
                                                                                                            path="usersOfMassCentres"
                                                                                                            id="massCentreSelectBox"
                                                                                                            class="form-control"
                                                                                                            items="${massCentreList}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="usersOfPrayerUnits"
                                                                                                       class="col-sm-2 control-label required">Prayer
                                                                                                    Unit</label>

                                                                                                <div class="col-sm-3"
                                                                                                     id="prayerUnitSelectBoxer">
                                                                                                    <form:select
                                                                                                            path="usersOfPrayerUnits"
                                                                                                            id="prayerUnitSelectBox"
                                                                                                            class="form-control"
                                                                                                            items="${prayerUnitList}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                                <label for="userOfFamily"
                                                                                                       class="col-sm-2 control-label required">Family</label>

                                                                                                <div class="col-sm-3"
                                                                                                     id="familySelectBoxer">
                                                                                                    <form:select
                                                                                                            path="userOfFamily"
                                                                                                            id="familySelectBox"
                                                                                                            class="form-control"
                                                                                                            items="${familyList}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="email"
                                                                                                       class="col-sm-2 control-label required">Email</label>

                                                                                                <div class="col-sm-3"
                                                                                                     id="email">
                                                                                                    <form:input
                                                                                                            path="email"
                                                                                                            id="email"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="password"
                                                                                                       class="col-sm-2 control-label">Password</label>

                                                                                                <div class="col-sm-3"
                                                                                                     id="password">
                                                                                                    <form:password
                                                                                                            path="password"
                                                                                                            id="password"
                                                                                                            class="form-control"/>
                                                                                                    <form:checkbox
                                                                                                            path="sendMailFlag"
                                                                                                            checked="true"/>Send
                                                                                                    an email
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </form:form>
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
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>


    <%@include file="footerPanelTemplate.jsp" %>

</div>
</body>
</html>
