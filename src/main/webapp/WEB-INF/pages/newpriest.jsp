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
    <%@ include file="newscriptLibraryTemplate.jsp" %>

    <spring:url value="/resources/js/priestvalidator.js" var="priestValidatorURL"/>

    <spring:url value="/addpriest.action" var="priestActionURL"/>

    <%--<script src="${priestValidatorURL}" type="text/javascript"
            language="javascript"></script>--%>

    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#priestViewer').bind("click", function () {
                loadPriestGrid();
            });

        });

        function loadPriestGrid() {

            jQuery("#priestGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displaypriestgrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [2, 4, 6],
                        colNames: ['ID', 'Name', 'Designation', 'Heavenly Patron', 'Native Diocese', 'Native Parish', 'Native Place', 'Priest Card Validity', 'Ordained To Diocese', 'Father Name', 'Mother Name', 'Priest Status', 'Congregation', 'Local Address', 'Native Address', 'Emergency Contact'],
                        colModel: [

                            {name: 'priestID', index: 'priestID', width: 120},
                            {name: 'name', index: 'name', width: 150},
                            {name: 'designation', index: 'designation', width: 150, align: "right"},
                            {name: 'heavenlyPatron', index: 'heavenlyPatron', width: 150, align: "right"},
                            {name: 'nativeDiocese', index: 'nativeDiocese', width: 150, align: "right"},
                            {name: 'nativeParish', index: 'nativeParish', width: 150, align: "right"},
                            {name: 'nativePlace', index: 'nativePlace', width: 150, align: "right"},
                            {name: 'priestCardValidity', index: 'priestCardValidity', width: 150, align: "right"},
                            {name: 'ordainedToDiocese', index: 'ordainedToDiocese', width: 150, align: "right"},
                            {name: 'fatherName', index: 'fatherName', width: 150, align: "right"},
                            {name: 'motherName', index: 'motherName', width: 150, align: "right"},
                            {name: 'priestStatus', index: 'priestStatus', width: 150, align: "right"},
                            {name: 'congregation', index: 'congregation', width: 150, align: "right"},
                            {name: 'localAddress', index: 'localAddress', width: 150, align: "right"},
                            {name: 'nativeAddress', index: 'nativeAddress', width: 150, align: "right"},
                            {name: 'emergencyContact', index: 'emergencyContact', width: 150, align: "right"}
                        ],
                        rowNum: 2,
                        pager: '#priestGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Priests",
                        autowidth: true,
                        shrinkToFit: false,
                        emptyrecords: "Nothing to display"

                    });
            jQuery("#priestGrid").jqGrid('navGrid', '#priestGridPager', {edit: true, add: true, del: true});
        }


    </script>

    <script type="text/javascript">

        jQuery(document).ready(function () {
            var $form = $('#priestForm1');
            $form.bind('submit', function (e) {
                // Ajax validation
                //var $inputs = $form.find('input');
                var data = collectFormData();

                /* var $selects=$form.find('select');
                 var selectData=collectFormData($selects);
                 data.push(selectData);*/

                jQuery.post('${priestActionURL}', data, function (response) {
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
                        $('#priestForm1')[0].reset();
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
                                            <a href="#priestRegistration" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Priest Registration</span></a>
                                        </li>
                                        <li>
                                            <a href="#priestView" data-toggle="tab" id="priestViewer"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Priest View</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="priestRegistration">
                                        <form:form modelAttribute="priest"
                                                   action="${priestActionURL}" method="post"
                                                   id="priestForm1">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="panel panel-indigo">
                                                        <div class="panel-heading">
                                                            <h4>Personal Details</h4>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="control-group" id="priestStatus">
                                                                <label class="control-label">Priest Status :</label>

                                                                <form:select path="priestStatus"
                                                                             id="priestStatus">
                                                                    <form:option value="Active"
                                                                                 selected="selected">Active</form:option>
                                                                    <form:option
                                                                            value="Transferred">Transferred</form:option>
                                                                    <form:option
                                                                            value="Not-Active">Not-Active</form:option>
                                                                </form:select>
                                                                <span
                                                                        class="help-inline"><form:errors
                                                                        path="priestStatus"/></span>
                                                            </div>

                                                            <div class="control-group" id="priestID">
                                                                <label class="control-label">Priest ID:</label>

                                                                <form:input path="priestID"
                                                                            id="priestID"
                                                                            readonly="true"
                                                                            placeholder="auto generated value"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestID"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPerson.salutation">
                                                                <label class="control-label">Salutation :</label>

                                                                <form:select
                                                                        path="priestAsPerson.salutation"
                                                                        id="priestAsPerson.salutation">
                                                                    <form:option value="Rev."
                                                                                 selected="selected">Rev.</form:option>
                                                                    <form:option value="Rev. Dr.">Rev. Dr.</form:option>
                                                                </form:select>
                                                                <span
                                                                        class="help-inline"><form:errors
                                                                        path="priestAsPerson.salutation"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersonfirstName">
                                                                <label class="control-label">First Name :</label>

                                                                <form:input
                                                                        path="priestAsPerson.firstName"
                                                                        id="priestAsPersonfirstName"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.firstName"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersonmiddleName">
                                                                <label class="control-label">Middle Name :</label>

                                                                <form:input
                                                                        path="priestAsPerson.middleName"
                                                                        id="priestAsPersonmiddleName"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.middleName"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersonlastName">
                                                                <label class="control-label">Last Name :</label>

                                                                <form:input
                                                                        path="priestAsPerson.lastName"
                                                                        id="priestAsPersonlastName"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.lastName"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersondateOfBirth">
                                                                <label class="control-label">Date of Birth :</label>

                                                                <form:input
                                                                        path="priestAsPerson.dateOfBirth"
                                                                        id="priestAsPersondateOfBirth"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.dateOfBirth"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersonplaceOfBirth">
                                                                <label class="control-label">Place of Birth :</label>

                                                                <form:input
                                                                        path="priestAsPerson.placeOfBirth"
                                                                        id="priestAsPersonplaceOfBirth"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.placeOfBirth"/></span>

                                                            </div>
                                                            <div class="control-group" id="priestAsPersongender">
                                                                <label class="control-label">Gender :</label>

                                                                <form:radiobutton
                                                                        path="priestAsPerson.gender"
                                                                        id="priestAsPersongender" value="Male"/>Male

                                                                <form:radiobutton path="priestAsPerson.gender"
                                                                                  id="priestAsPersongender"
                                                                                  value="Female"/>Female
                                                                <span
                                                                        class="help-inline"><form:errors
                                                                        path="priestAsPerson.gender"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersonnationality">
                                                                <label class="control-label">Nationality :</label>

                                                                <form:input
                                                                        path="priestAsPerson.nationality"
                                                                        id="priestAsPersonnationality"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.nationality"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestDesignation">
                                                                <label class="control-label">Designation:</label>

                                                                <form:select path="designation"
                                                                             items="${priestDesignation}"
                                                                        ></form:select><span
                                                                    class="help-inline"><form:errors
                                                                    path="designation"/></span>
                                                            </div>
                                                            <div class="control-group" id="parishId">
                                                                <label class="control-label">Parish:</label>

                                                                <form:select path="parishId"
                                                                             items="${parishList}"
                                                                        ></form:select><span
                                                                    class="help-inline"><form:errors
                                                                    path="parishId"/></span>
                                                            </div>
                                                            <div class="control-group"
                                                                 id="priestAsPersoneducationQualifications">
                                                                <label class="control-label">Education Qualifications
                                                                    :</label>

                                                                <form:textarea
                                                                        path="priestAsPerson.educationQualifications"
                                                                        id="priestAsPersoneducationQualifications"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.educationQualifications"/></span>

                                                            </div>
                                                            <div class="control-group" id="priestAsPersonjobDetails">
                                                                <label class="control-label">Job Details :</label>

                                                                <form:textarea
                                                                        path="priestAsPerson.jobDetails"
                                                                        id="priestAsPersonjobDetails"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.jobDetails"/></span>
                                                            </div>
                                                            <div class="control-group" id="personalStatus">
                                                                <label class="control-label">Personal Status :</label>

                                                                <form:select
                                                                        path="priestAsPerson.personalStatus"
                                                                        id="personalStatus">
                                                                    <form:option value="Priest"
                                                                                 selected="selected">Priest</form:option>
                                                                </form:select>
                                                                <span
                                                                        class="help-inline"><form:errors
                                                                        path="priestAsPerson.personalStatus"/></span>

                                                            </div>
                                                            <div class="control-group" id="bloodGroup">
                                                                <label class="control-label">Blood Group :</label>

                                                                <form:select
                                                                        path="priestAsPerson.bloodGroup"
                                                                        id="bloodGroup">
                                                                    <form:option value="select">--select--</form:option>
                                                                    <form:option value="O-">O-</form:option>
                                                                    <form:option value="O+">O+</form:option>
                                                                    <form:option value="A-">A-</form:option>
                                                                    <form:option value="A+">A+</form:option>
                                                                    <form:option value="B-">B-</form:option>
                                                                    <form:option value="B+">B+</form:option>
                                                                    <form:option value="AB-">AB-</form:option>
                                                                    <form:option value="AB+">AB+</form:option>
                                                                </form:select>
                                                                <span
                                                                        class="help-inline"><form:errors
                                                                        path="priestAsPerson.bloodGroup"/></span>
                                                            </div>
                                                            <div class="control-group" id="carNumber">
                                                                <label class="control-label">Car Number :</label>

                                                                <form:input
                                                                        path="priestAsPerson.carNumber" id="carNumber"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.carNumber"/></span>
                                                            </div>
                                                            <div class="control-group" id="lifeStatus">
                                                                <label class="control-label">Life Status :</label>

                                                                <form:select
                                                                        path="priestAsPerson.lifeStatus"
                                                                        id="lifeStatus">
                                                                    <form:option value="Live"
                                                                                 selected="selected">Live</form:option>
                                                                    <form:option value="Late">Late</form:option>
                                                                </form:select>
                                                                <span
                                                                        class="help-inline"><form:errors
                                                                        path="priestAsPerson.lifeStatus"/></span>
                                                            </div>
                                                            <div class="control-group"
                                                                 id="priestAsPersonpersonalRemarks">
                                                                <label class="control-label">Personal Remarks :</label>

                                                                <form:textarea
                                                                        path="priestAsPerson.personalRemarks"
                                                                        id="personalRemarks"/><span
                                                                    class="help-inline"
                                                                    id="priestAsPersonpersonalRemarks"><form:errors
                                                                    path="priestAsPerson.personalRemarks"/></span>

                                                            </div>
                                                            <div class="control-group" id="dateOfOrdination">
                                                                <label class="control-label">Date of Ordination
                                                                    :</label>
                                                                <form:input
                                                                        path="dateOfOrdination"
                                                                        id="dateOfOrdination"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="dateOfOrdination"/></span>
                                                            </div>
                                                            <div class="control-group" id="feastDay">
                                                                <label class="control-label">Feast Day :</label>

                                                                <form:input path="feastDay"
                                                                            id="feastDay"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="feastDay"/></span>
                                                            </div>
                                                            <div class="control-group" id="heavenlyPatron">
                                                                <label class="control-label">Heavenly Patron :</label>

                                                                <form:input path="heavenlyPatron"
                                                                            id="heavenlyPatron"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="heavenlyPatron"/></span>
                                                            </div>
                                                            <div class="control-group" id="nativeDiocese">
                                                                <label class="control-label">Native Diocese :</label>

                                                                <form:input path="nativeDiocese"
                                                                            id="nativeDiocese"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="nativeDiocese"/></span>
                                                            </div>
                                                            <div class="control-group" id="congregation">
                                                                <label class="control-label">Congregation :</label>

                                                                <form:input path="congregation"
                                                                            id="congregation"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="congregation"/></span>
                                                            </div>
                                                            <div class="control-group" id="nativeParish">
                                                                <label class="control-label">Native Parish :</label>

                                                                <form:input path="nativeParish"
                                                                            id="nativeParish"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="nativeParish"/></span>
                                                            </div>
                                                            <div class="control-group" id="nativePlace">
                                                                <label class="control-label">Native Place :</label>

                                                                <form:input path="nativePlace"
                                                                            id="nativePlace"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="nativePlace"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestCardValidity">
                                                                <label class="control-label">Card Validity :</label>

                                                                <form:input
                                                                        path="priestCardValidity"
                                                                        id="priestCardValidity"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestCardValidity"/></span>
                                                            </div>
                                                            <div class="control-group" id="ordainedToDiocese">
                                                                <label class="control-label">Ordained To Diocese
                                                                    :</label>

                                                                <form:input
                                                                        path="ordainedToDiocese"
                                                                        id="ordainedToDiocese"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="ordainedToDiocese"/></span>
                                                            </div>
                                                            <div class="control-group" id="fatherName">
                                                                <label class="control-label">Father's Name :</label>

                                                                <form:input path="fatherName"
                                                                            id="fatherName"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="fatherName"/></span>
                                                            </div>
                                                            <div class="control-group" id="motherName">
                                                                <label class="control-label">Mother's Name :</label>

                                                                <form:input path="motherName"
                                                                            id="motherName"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="motherName"/></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6">
                                                    <div class="panel panel-grape">
                                                        <div class="panel-heading">
                                                            <h4>
                                                                Contact
                                                                Details</h4>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="control-group" id="priestAsPersonemail">
                                                                <label class="control-label">Email :</label>

                                                                <form:input
                                                                        path="priestAsPerson.email"
                                                                        id="priestAsPersonemail"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.email"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersonmobileNo">
                                                                <label class="control-label">Mobile No. :</label>

                                                                <form:input
                                                                        path="priestAsPerson.mobileNo"
                                                                        id="priestAsPersonmobileNo"
                                                                        value=""/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.mobileNo"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersonlandLine">
                                                                <label class="control-label">Land Line No. :</label>

                                                                <form:input
                                                                        path="priestAsPerson.landLine"
                                                                        id="priestAsPersonlandLine"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.landLine"/></span>
                                                            </div>
                                                            <div class="control-group" id="priestAsPersonfaxNo">
                                                                <label class="control-label">Fax No. :</label>

                                                                <form:input
                                                                        path="priestAsPerson.faxNo"
                                                                        id="priestAsPersonfaxNo"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="priestAsPerson.faxNo"/></span>
                                                            </div>
                                                            <div class="control-group" id="emergencyContactname">
                                                                <label class="control-label">Name :</label>

                                                                <form:input
                                                                        path="emergencyContact.name"
                                                                        id="emergencyContactname"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="emergencyContact.name"/></span>
                                                            </div>
                                                            <div class="control-group"
                                                                 id="emergencyContactaddressLineOne">
                                                                <label class="control-label">Address Line 1 :</label>

                                                                <form:input
                                                                        path="emergencyContact.addressLineOne"
                                                                        id="emergencyContactaddressLineOne"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="emergencyContact.addressLineOne"/></span>

                                                            </div>
                                                            <div class="control-group"
                                                                 id="emergencyContactaddressLineTwo">
                                                                <label class="control-label">Address Line 2 :</label>

                                                                <form:input
                                                                        path="emergencyContact.addressLineTwo"
                                                                        id="emergencyContactaddressLineTwo"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="emergencyContact.addressLineTwo"/></span>

                                                            </div>
                                                            <div class="control-group"
                                                                 id="emergencyContactaddressLineThree">
                                                                <label class="control-label">Address Line 3 :</label>

                                                                <form:input
                                                                        path="emergencyContact.addressLineThree"
                                                                        id="emergencyContactaddressLineThree"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="emergencyContact.addressLineThree"/></span>

                                                            </div>
                                                            <div class="control-group" id="emergencyContactemail">
                                                                <label class="control-label">Email :</label>

                                                                <form:input
                                                                        path="emergencyContact.email"
                                                                        id="emergencyContactemail"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="emergencyContact.email"/></span>
                                                            </div>
                                                            <div class="control-group" id="emergencyContactmobileNo">
                                                                <label class="control-label">Mobile No. :</label>

                                                                <form:input
                                                                        path="emergencyContact.mobileNo"
                                                                        id="emergencyContactmobileNo"
                                                                        value=""/><span
                                                                    class="help-inline"><form:errors
                                                                    path="emergencyContact.mobileNo"/></span>
                                                            </div>
                                                            <div class="control-group" id="emergencyContactlandLineNo">
                                                                <label class="control-label">Land Line No. :</label>

                                                                <form:input
                                                                        path="emergencyContact.landLineNo"
                                                                        id="emergencyContactlandLineNo"/><span
                                                                    class="help-inline"><form:errors
                                                                    path="emergencyContact.landLineNo"/></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
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
                                                <div class="col-md-6">
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
                                            <div class="form-actions">
                                                <button type="submit"
                                                        value="Save"
                                                        class="btn btn-primary"/>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="tab-pane "
                                         id="priestView">
                                        <table id="priestGrid"></table>
                                        <div id="priestGridPager"></div>
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

    <%@include
            file="newfooterPanelTemplate.jsp" %>

</div>
</body>


</html>
