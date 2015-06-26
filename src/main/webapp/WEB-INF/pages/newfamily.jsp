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
    <%@ include file="newscriptLibraryTemplate.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#familyViewer').bind("click", function () {
                loadFamilyGrid();
            });

        });

        function loadFamilyGrid() {

            $("#familyGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayfamilygrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [2, 4, 6],
                        colNames: ['Family ID', 'Family Name', 'parishInNative', 'dioceseInNative', 'dateOfRegistration', 'parishLocal', "massCenter", "prayerUnit", "localAddress", "nativeAddress"],
                        colModel: [

                            {name: 'familyID', index: 'familyID', width: 90},
                            {name: 'familyName', index: 'familyName', width: 100},
                            {name: 'parishInNative', index: 'parishInNative', width: 100},
                            {name: 'dioceseInNative', index: 'dioceseInNative', width: 100},
                            {name: 'dateOfRegistration', index: 'dateOfRegistration', width: 100},
                            {name: 'parishLocal', index: 'parishLocal', width: 100},
                            {name: 'massCenter', index: 'massCenter', width: 100},
                            {name: 'prayerUnit', index: 'prayerUnit', width: 100},
                            {name: 'localAddress', index: 'localAddress', width: 100},
                            {name: 'nativeAddress', index: 'nativeAddress', width: 100},
                        ],
                        rowNum: 2,
                        pager: '#familyGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Families",
                        autowidth: true,
                        shrinkToFit: true,
                        height: 'auto',
                        width: 'auto'
                    });
            jQuery("#familyGrid").jqGrid('navGrid', '#familyGridPager', {edit: true, add: true, del: true});
        }


    </script>

</head>

<body>

<spring:url value="/addfamily.action" var="familyActionURL"/>

<%@include file="newheaderTemplate.jsp" %>

<div id="page-container">

    <%@include file="newleftMenuPanelTemplate.jsp" %>

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
                                            <a href="#familyRegistration" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Family Registration</span></a>
                                        </li>
                                        <li>
                                            <a href="#familyView" data-toggle="tab" id="familyViewer"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Family View</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="familyRegistration">
                                        <form:form modelAttribute="family"
                                                   action="${familyActionURL}" method="post"
                                                   id="familyForm1" cssClass="form-inline">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="panel panel-indigo">
                                                        <div class="panel-heading">
                                                            <h4>Family Details</h4>
                                                        </div>
                                                        <div class="panel-body">
                                                                <%--<div class="form-group has-error">
                                                                    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>

                                                                    <div class="col-sm-10">
                                                                        <input type="email" class="form-control"
                                                                               id="inputEmail3" placeholder="Email">
                                                                    </div>
                                                                    <p class=""></p>
                                                                </div>--%>

                                                            <div class="control-group" id="familyName">
                                                                <label class="control-label">Family Name :</label>

                                                                <div class="controls">
                                                                    <form:input path="familyName" id="familyName"
                                                                                class="textBox"/>
                                                                 <span class="help-inline"><form:errors
                                                                         path="familyName"/></span>
                                                                </div>
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

                                                                <div class="controls"><form:input path="dioceseInNative"
                                                                                                  id="dioceseInNative"/>
                                                         <span class="help-inline"><form:errors
                                                                 path="dioceseInNative"/> </span>
                                                                </div>
                                                            </div>

                                                            <div class="control-group" id="dateOfRegistration">
                                                                <label class="control-label">Date Of Registration
                                                                    :</label>

                                                                <div class="controls"><form:input
                                                                        path="dateOfRegistration"
                                                                        id="dateOfRegistration"/>
                                                         <span class="help-inline"><form:errors
                                                                 path="dateOfRegistration"/> </span>
                                                                </div>
                                                            </div>

                                                            <c:if test="${showForPrayerUnitAdmin == false && showForFamilyUser == false}">

                                                                <div class="control-group" id="parishId">
                                                                    <label class="control-label">Parish :</label>

                                                                    <div class="controls"><form:select path="parishId"
                                                                                                       id="parishSelectBox">
                                                                        <option value="select">--Please select--
                                                                        </option>
                                                                    </form:select>
                                                                         <span class="help-inline"><form:errors
                                                                                 path="parishId"/> </span>
                                                                    </div>
                                                                </div>

                                                                <div class="control-group" id="massCenterId">
                                                                    <label class="control-label">Mass Center :</label>

                                                                    <div class="controls"><form:select
                                                                            path="massCenterId"
                                                                            id="massCenterSelectBox">
                                                                        <option value="select">--Please select--
                                                                        </option>
                                                                    </form:select>
                                                             <span class="help-inline"><form:errors
                                                                     path="massCenterId"/> </span>
                                                                    </div>
                                                                </div>

                                                                <div class="control-group" id="prayerUnitId">
                                                                    <label class="control-label">Prayer Unit :</label>

                                                                    <div class="controls"><form:select
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

                                                <div class="col-md-6">
                                                    <div class="panel panel-grape">
                                                        <div class="panel-heading">
                                                            <h4>Local Adderss</h4>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="control-group" id="localAddressaddressLineOne">
                                                                <label class="control-label">Address Line 1 :</label>

                                                                <div class="controls">
                                                                    <form:input path="localAddress.addressLineOne"
                                                                                id="localAddressaddressLineOne"/> <span
                                                                        class="help-inline"><form:errors
                                                                        path="localAddress.addressLineOne"/> </span>
                                                                </div>
                                                            </div>
                                                            <div class="control-group" id="localAddressaddressLineTwo">
                                                                <label class="control-label">Address Line 2 :</label>

                                                                <div class="controls"><form:input
                                                                        path="localAddress.addressLineTwo"
                                                                        id="localAddressaddressLineTwo"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="localAddress.addressLineTwo"/> </span>
                                                                </div>
                                                            </div>

                                                            <div class="control-group"
                                                                 id="localAddressaddressLineThree">
                                                                <label class="control-label">Address Line 3 :</label>

                                                                <div class="controls"><form:input
                                                                        path="localAddress.addressLineThree"
                                                                        id="localAddressaddressLineThree"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="localAddress.addressLineThree"/> </span>
                                                                </div>
                                                            </div>

                                                            <div class="control-group" id="localAddresstown">
                                                                <label class="control-label">Town:</label>

                                                                <div class="controls"><form:input
                                                                        path="localAddress.town"
                                                                        id="localAddresstown"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="localAddress.town"/> </span></div>
                                                            </div>

                                                            <div class="control-group" id="localAddresscounty">
                                                                <label class="control-label">County:</label>

                                                                <div class="controls"><form:input
                                                                        path="localAddress.county"
                                                                        id="localAddresscounty"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="localAddress.county"/></span></div>
                                                            </div>

                                                            <div class="control-group" id="localAddresspin">
                                                                <label class="control-label">Pin code:</label>

                                                                <div class="controls"><form:input
                                                                        path="localAddress.pin"
                                                                        id="localAddresspin"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="localAddress.pin"/> </span></div>
                                                            </div>

                                                            <div class="control-group" id="localAddresscountry">
                                                                <label class="control-label">Country:</label>

                                                                <div class="controls"><form:input
                                                                        path="localAddress.country"
                                                                        id="localAddresscountry"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="localAddress.country"/> </span></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="panel panel-grape">
                                                        <div class="panel-heading">
                                                            <h4>Native Address</h4>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="control-group" id="nativeAddressaddressLineOne">
                                                                <label class="control-label">Address Line 1 :</label>

                                                                <div class="controls"><form:input
                                                                        path="nativeAddress.addressLineOne"
                                                                        id="nativeAddressaddressLineOne"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="nativeAddress.addressLineOne"/> </span>
                                                                </div>
                                                            </div>
                                                            <div class="control-group" id="nativeAddressaddressLineTwo">
                                                                <label class="control-label">Address Line 2 :</label>

                                                                <div class="controls"><form:input
                                                                        path="nativeAddress.addressLineTwo"
                                                                        id="nativeAddressaddressLineTwo"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="nativeAddress.addressLineTwo"/></span>
                                                                </div>
                                                            </div>

                                                            <div class="control-group"
                                                                 id="nativeAddressaddressLineThree">
                                                                <label class="control-label">Address Line 3 :</label>

                                                                <div class="controls"><form:input
                                                                        path="nativeAddress.addressLineThree"
                                                                        id="nativeAddressaddressLineThree"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="nativeAddress.addressLineThree"/></span>
                                                                </div>
                                                            </div>

                                                            <div class="control-group" id="nativeAddresspostOffice">
                                                                <label class="control-label">Post Office :</label>

                                                                <div class="controls"><form:input
                                                                        path="nativeAddress.postOffice"
                                                                        id="nativeAddresspostOffice"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="nativeAddress.postOffice"/></span></div>
                                                            </div>

                                                            <div class="control-group" id="nativeAddressdistrict">
                                                                <label class="control-label">District :</label>

                                                                <div class="controls"><form:input
                                                                        path="nativeAddress.district"
                                                                        id="nativeAddressdistrict"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="nativeAddress.district"/></span></div>
                                                            </div>

                                                            <div class="control-group" id="nativeAddressstate">
                                                                <label class="control-label">State :</label>

                                                                <div class="controls"><form:input
                                                                        path="nativeAddress.state"
                                                                        id="nativeAddressstate"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="nativeAddress.state"/></span></div>
                                                            </div>

                                                            <div class="control-group" id="nativeAddresscountry">
                                                                <label class="control-label">Country :</label>

                                                                <div class="controls"><form:input
                                                                        path="nativeAddress.country"
                                                                        id="nativeAddresscountry"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="nativeAddress.country"/></span></div>
                                                            </div>

                                                            <div class="control-group" id="nativeAddresspin">
                                                                <label class="control-label">Pin code :</label>

                                                                <div class="controls"><form:input
                                                                        path="nativeAddress.pin"
                                                                        id="nativeAddresspin"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="nativeAddress.pin"/></span></div>
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
                                    <div class="tab-pane " id="familyView">
                                        <table id="familyGrid"></table>
                                        <div id="familyGridPager"></div>
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

    <%@include file="newfooterPanelTemplate.jsp" %>

</div>
<script type="text/javascript">
    function collectFormData(fields) {
        var data = {};
        for (var i = 0; i < fields.length; i++) {
            var $item = $(fields[i]);
            data[$item.attr('name')] = $item.val();
        }
        return data;
    }

    $(document).ready(function () {
        var $form = $('#familyForm1');
        $form.bind('submit', function (e) {
            // Ajax validation
            var $inputs = $form.find('input');
            var data = collectFormData($inputs);

            $.post('${familyActionURL}', data, function (response) {
                $form.find('.control-group').removeClass('error');
                $form.find('.help-inline').empty();
                $form.find('.alert').remove();

                if (response.statusMessage == 'FAIL') {
                    for (var i = 0; i < response.customErrorMessages.length; i++) {
                        var item = response.customErrorMessages[i];
                        var $controlGroup = $('#' + item.fieldName);
                        $controlGroup.addClass('error');
                        $controlGroup.find('.help-inline').html(item.message);
                    }
                } else {
                    var $alert = $('<div class="alert alert-success"></div>');
                    $alert.html(response.customErrorMessages);
                    $alert.prependTo($form);
                }
            }, 'json');

            e.preventDefault();
            return false;
        });
    });
</script>
</body>
</html>
