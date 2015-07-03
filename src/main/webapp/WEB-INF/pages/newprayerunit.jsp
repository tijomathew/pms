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

    <%@ include file="newscriptLibraryTemplate.jsp" %>

    <spring:url value="/resources/js/prayerunitvalidator.js" var="prayerUnitValidatorURL"/>
    <spring:url value="/addprayerunit.action" var="prayerUnitActionURL"/>

   <%-- <script src="${prayerUnitValidatorURL}" type="text/javascript"
            language="javascript"></script>--%>

    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#prayerUnitViewer').bind("click", function () {
                loadPrayerUnitGrid();
            });
        });

        function loadPrayerUnitGrid() {

            jQuery("#prayerUnitGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayprayerunitgrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [2, 4, 6],
                        colNames: ['PrayerUnit ID', 'PrayerUnit Code', 'PrayerUnit Name', 'PrayerUnit Place', 'MassCenter Name', 'Local address'],
                        colModel: [

                            {name: 'prayerUnitID', index: 'prayerUnitID', width: 90},
                            {name: 'prayerUnitCode', index: 'prayerUnitCode', width: 90},
                            {name: 'prayerUnitName', index: 'prayerUnitName', width: 100},
                            {name: 'prayerUnitPlace', index: 'prayerUnitPlace', width: 100},
                            {name: 'massCenterName', index: 'massCenterName', width: 100},
                            {name: 'localAddress', index: 'localAddress', width: 100},

                        ],
                        rowNum: 2,
                        pager: '#prayerUnitGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Prayer Unit",
                        autowidth: true,
                        shrinkToFit: true,
                        height: 'auto',
                        width: 'auto'
                    });
            jQuery("#prayerUnitGrid").jqGrid('navGrid', '#prayerUnitGridPager', {edit: true, add: true, del: true});
        }


    </script>

    <script type="text/javascript">

        jQuery(document).ready(function () {
            var $form = $('#prayerUnitForm');
            $form.bind('submit', function (e) {
                // Ajax validation
                //var $inputs = $form.find('input');
                //var data = collectFormData();

                /* var $selects=$form.find('select');
                 var selectData=collectFormData($selects);
                 data.push(selectData);*/

                jQuery.post('${prayerUnitActionURL}', $form.serializeArray(), function (response) {
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
                        $alert.html(response.customErrorMessages[0].message);
                        $alert.prependTo($form);
                        $('#prayerUnitForm')[0].reset();
                    }
                }, 'json');

                e.preventDefault();
                return false;
            });
        });

        function collectFormData() {
            var data = {};
            var $form = $('#priestForm1');
            var $inputs = $form.find('input');
            if ($inputs.length != 0) {
                for (var i = 0; i < $inputs.length; i++) {
                    var $item = $($inputs[i]);
                    data[$item.attr('name')] = $item.val();
                }
            }
            var $selects = $form.find('select');
            if ($selects.length != 0) {
                for (var i = 0; i < $selects.length; i++) {
                    var $item = $($selects[i]);
                    data[$item.attr('name')] = $item.val();
                }
            }
            var $radiobuttons = $form.find('radiobutton');
            if ($radiobuttons.length != 0) {
                for (var i = 0; i < $radiobuttons.length; i++) {
                    var $item = $($radiobuttons[i]);
                    data[$item.attr('name')] = $item.val();
                }
            }
            return data;
        }
    </script>

</head>

<body>

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
                                            <a href="#prayerUnitRegistration" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Prayer Unit Registration</span></a>
                                        </li>
                                        <li>
                                            <a href="#prayerUnitView" data-toggle="tab" id="prayerUnitViewer"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Prayer Unit View</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="prayerUnitRegistration">
                                        <form:form modelAttribute="prayerUnit"
                                                   action="${prayerUnitActionURL}" method="post"
                                                   id="prayerUnitForm">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="panel panel-indigo">
                                                        <div class="panel-heading">
                                                            <h4>Prayer Unit Details</h4>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="control-group" id="prayerUnitName">
                                                                <label class="control-label">Prayer Unit Name :</label>

                                                                <form:input path="prayerUnitName"
                                                                                                  id="prayerUnitName"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="prayerUnitName"/></span>

                                                            </div>
                                                            <div class="control-group" id="prayerUnitCode">
                                                                <label class="control-label">Prayer Unit Code :</label>

                                                                <form:input path="prayerUnitCode"
                                                                                                  id="prayerUnitCode"
                                                                                                  readonly="true"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="prayerUnitCode"/></span>

                                                            </div>
                                                            <div class="control-group" id="prayerUnitPlace">
                                                                <label class="control-label">Prayer Unit Place :</label>

                                                                <form:input path="prayerUnitPlace"
                                                                                                  id="prayerUnitPlace"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="prayerUnitPlace"/></span>

                                                            </div>
                                                            <div class="control-group" id="massCenterId">
                                                                <label class="control-label">Mass Center:</label>

                                                               <form:select path="massCenterId"
                                                                                                   items="${massCenterMap}"
                                                                                                   id="massCenterId"/><span
                                                                        class="help-inline"><form:errors
                                                                        path="massCenterId"/></span>

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
                                            <div class="form-actions">
                                                <button type="submit" value="Save" class="btn btn-primary"/>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="tab-pane " id="prayerUnitView">
                                        <table id="prayerUnitGrid"></table>
                                        <div id="prayerUnitGridPager"></div>
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
</body>
</html>
