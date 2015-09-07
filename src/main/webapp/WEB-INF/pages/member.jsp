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


    <spring:url value="/resources/js/userselectbox.js" var="memberSelectBox"/>
    <spring:url value="/resources/js/createmembergridlayout.js" var="memberGrid"/>
    <spring:url value="/addmember.action" var="memberActionURL"/>

    <script src="${memberSelectBox}" type="text/javascript"
            language="javascript"></script>
    <script src="${memberGrid}" type="text/javascript"
            language="javascript"></script>


    <script type="text/javascript">
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#ItemPreview').attr('src', e.target.result);
                    $('#thumbnail').show();
                }

                reader.readAsDataURL(input.files[0]);
            }
        }
        $(document).ready(function () {

            $("#memberAsPerson\\.file").change(function(){
                readURL(this);
            });



            $('form select').prepend($('<option/>', {text: '--Select--', value: '0', selected: true})).attr('disabled', true);
            $('#memberAsPersonnationalityTextBox').hide();

            backToTop();

            $('#memberAsPersondateOfBirth').datepicker({
                autoclose: true,
                todayHighlight: true,
                format: 'dd-mm-yyyy',
                endDate: '+0d',
                onClose: function () {
                    $(this).valid();
                }
            });

            <c:if test="${showForFamilyUser == false}">
            loadSelectBox("${pageContext.request.contextPath}");
            </c:if>

            loadMemberGrid();

            $('#memberAsPersonnationality').change(function () {
                        $('#memberAsPersonnationalityTextBox').hide();
                        var nationality = $("#memberAsPersonnationality option:selected").val();
                        if (nationality == 'Other') {
                            $('#memberAsPersonnationalityTextBox').show();
                        }
                    }
            );
        });

        function blinker(blinkerElt) {
            blinkerElt.find('span').addClass('tabErrorHighlight').effect("pulsate", {times: 10}, 5000);
        }

        function callImageSubmission() {
            var formData = new FormData($('#memberForm')[0]);
            $.ajax({
                type: 'POST',
                url: $('#memberForm').attr('action'),
                data: formData,
                dataType: 'json',
                processData: false,
                contentType: false,
                success: function (response) {
                    if (response.statusCode == 'FAIL') {

                        for (var i = 0; i < response.customErrorMessages.length; i++) {
                            var item = response.customErrorMessages[i];
                            var itemFieldName = item.fieldName.replace(/\./g, '\.');
                            var field = $('#memberForm').find("[name='" + itemFieldName + "']");
                            $("label[for='" + itemFieldName + "']").addClass('labelErrorAlert');
                            var tabHeaderId = field.closest('div.tab-pane').attr("id");
                            blinker($("a[href=#" + tabHeaderId + "]"));
                            field.addClass('borderRed');
                            field.attr('title', item.message);
                            field.tooltip({
                                placement: "top",
                                trigger: "hover",
                                template: '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner tooltip-error"></div></div>'
                            });
                            field.change(function () {
                                var fieldName = $(this).attr('name').replace(/\./g, '\.');
                                $("label[for='" + fieldName + "']").removeClass('labelErrorAlert');
                                $(this).removeClass('borderRed');
                                $(this).removeAttr('title');
                                $(this).tooltip('destroy');
                            });
                        }
                        $("div.container").errorFieldsDialog({
                            responseData: response.customErrorMessages,
                            parentFormId: 'memberForm',
                            parentGridId: 'memberGrid'
                        });
                        return [true, "", ""];

                    }
                    else if (response.statusCode == 'SUCCESS') {
                        jQuery.jqGrowl.timeout = 2000;
                        jQuery.jqGrowl.init({right: '8px', bottom: '', top: '8px', left: ''});
                        jQuery.jqGrowl.msg(response.customErrorMessages[0].fieldName + ' ' + response.customErrorMessages[0].message, 'SUCCESS');

                        $('#memberForm')[0].reset();
                        $(':input', '#memberForm')
                                .not(':button, :submit, :reset, :checkbox, #registeredDate, :radio')
                                .attr('value', '')
                                .removeAttr('checked')
                                .removeAttr('selected');
                        $('#memberAsPersonnationalityTextBox').hide();
                        jQuery('#memberGrid').trigger('reloadGrid');
                        $('ul.nav-tabs').find('span').removeClass('tabErrorHighlight');
                        $('ul.nav-tabs').find('span').stop(true,true).css('opacity', 1);
                    }
                    else if (response.statusCode == 'FAILURE') {
                        jQuery.jqGrowl.timeout = 2000;
                        jQuery.jqGrowl.init({right: '8px', bottom: '', top: '8px', left: ''});
                        jQuery.jqGrowl.msg(response.customErrorMessages[0].fieldName + ' ' + response.customErrorMessages[0].message, 'FAILURE');

                        $('#memberAsPersonnationalityTextBox').hide();
                        jQuery('#memberGrid').trigger('reloadGrid');
                    }
                    else {
                        var $alert = $('<div class="alert alert-success"></div>');
                        $alert.html(response.customErrorMessages[0].message);
                        $alert.prependTo($form);
                        $('#memberForm')[0].reset();
                        $('#memberAsPersonnationalityTextBox').hide();
                    }
                }
            });
        }
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
                                                        <h4>Members Details</h4>
                                                    </div>

                                                    <div class="panel-body">
                                                        <table id="memberGrid"></table>
                                                        <div id="memberGridPager"></div>
                                                        <div class="tab-content" style="padding: 10px;">

                                                            <div class="tab-pane active">
                                                                <div class="panel hidedisplay" id="panelDiv">
                                                                    <div class="panel-heading">
                                                                        <h4>
                                                                            <ul class="nav nav-tabs">
                                                                                <li class="active">
                                                                                    <a href="#member2"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale fa-exclamation-circle"></i><span
                                                                                            class="hidden-xs">Member Details</span></a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#member4"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">Contact Details</span></a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#member5"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">Baptism</span></a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#member6"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">Confirmation</span></a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#member7"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">First Holy Communion</span></a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#member8"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">Betrothal</span></a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#member9"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">Marriage</span></a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#member10"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">Death</span></a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#member11"
                                                                                       data-toggle="tab"><i
                                                                                            class="fa fa-comments visible-xs icon-scale"></i><span
                                                                                            class="hidden-xs">Photo</span></a>
                                                                                </li>
                                                                            </ul>
                                                                        </h4>
                                                                    </div>
                                                                    <div class="panel-body">
                                                                        <form:form modelAttribute="member"
                                                                                   action="${memberActionURL}"
                                                                                   method="POST"
                                                                                   id="memberForm"
                                                                                   cssClass="form-horizontal"
                                                                                   enctype="multipart/form-data">
                                                                            <div class="tab-content">

                                                                                <div class="tab-pane active"
                                                                                     id="member2">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">
                                                                                            <div class="form-group">
                                                                                                <label for="memberNo"
                                                                                                       class="col-sm-2 control-label required">Member
                                                                                                    Number</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="memberNo"
                                                                                                            id="memberNo"
                                                                                                            class="form-control"
                                                                                                            readonly="true"/>
                                                                                                </div>
                                                                                                <form:input path="id"
                                                                                                            type="hidden"/>
                                                                                                <c:if test="${showForFamilyUser == false}">
                                                                                                    <label for="familyMember"
                                                                                                           class="col-sm-2 control-label required">Family</label>

                                                                                                    <div class="col-sm-3">
                                                                                                        <form:select
                                                                                                                path="familyMember"
                                                                                                                id="familySelectBoxofMember"
                                                                                                                class="form-control"/>
                                                                                                    </div>
                                                                                                </c:if>
                                                                                                <c:if test="${showForFamilyUser == true}">
                                                                                                    <label for="familyMember"
                                                                                                           class="col-sm-2 control-label required">Family</label>

                                                                                                    <div class="col-sm-3">
                                                                                                        <form:select
                                                                                                                path="familyMember"
                                                                                                                id="familySelectBox"
                                                                                                                items="${familyName}"
                                                                                                                class="form-control"/>
                                                                                                    </div>
                                                                                                </c:if>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.salutation"
                                                                                                       class="col-sm-2 control-label required">Full
                                                                                                    Name</label>

                                                                                                <div class="col-sm-1">
                                                                                                    <form:select
                                                                                                            path="memberAsPerson.salutation"
                                                                                                            id="memberAsPerson.salutation"
                                                                                                            class="form-control"
                                                                                                            items="${salutation}">
                                                                                                    </form:select>
                                                                                                </div>

                                                                                                <div class="col-sm-2">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.firstName"
                                                                                                            id="memberAsPerson.firstName"
                                                                                                            class="form-control"
                                                                                                            placeholder="First Name"/>
                                                                                                </div>

                                                                                                <div class="col-sm-2">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.middleName"
                                                                                                            id="memberAsPerson.middleName"
                                                                                                            class="form-control"
                                                                                                            placeholder="Middle Name"/>
                                                                                                </div>

                                                                                                <div class="col-sm-2">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.lastName"
                                                                                                            id="memberAsPerson.lastName"
                                                                                                            class="form-control"
                                                                                                            placeholder="Last Name"/>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="relationshipInFamily"
                                                                                                       class="col-sm-2 control-label required">Relationship
                                                                                                    In
                                                                                                    Family</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:select
                                                                                                            path="relationshipInFamily"
                                                                                                            id="relationshipInFamily"
                                                                                                            class="form-control toCaps"
                                                                                                            items="${relationshipInFamily}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                                <label for="familyHead"
                                                                                                       class="col-sm-2 control-label">Family
                                                                                                    Head</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:checkbox
                                                                                                            path="familyHead"/>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.gender"
                                                                                                       class="col-sm-2 control-label required">Gender</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:radiobuttons
                                                                                                            path="memberAsPerson.gender"
                                                                                                            id="memberAsPerson.gender"
                                                                                                            class="form-control toCaps"
                                                                                                            items="${sex}"/>
                                                                                                </div>
                                                                                                <label for="memberAsPerson.dateOfBirth"
                                                                                                       class="col-sm-2 control-label required">Date
                                                                                                    of Birth</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.dateOfBirth"
                                                                                                            id="memberAsPersondateOfBirth"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.placeOfBirth"
                                                                                                       class="col-sm-2 control-label required">Place
                                                                                                    of Birth</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.placeOfBirth"
                                                                                                            id="memberAsPersonplaceOfBirth"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="memberAsPerson.personalStatus"
                                                                                                       class="col-sm-2 control-label required">Personal
                                                                                                    Status</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:select
                                                                                                            path="memberAsPerson.personalStatus"
                                                                                                            id="personalStatus"
                                                                                                            class="form-control toCaps"
                                                                                                            items="${personalStatus}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.nationality"
                                                                                                       class="col-sm-2 control-label required">Nationality</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:select
                                                                                                            path="memberAsPerson.nationality"
                                                                                                            id="memberAsPersonnationality"
                                                                                                            class="form-control">
                                                                                                        <form:option
                                                                                                                value="Indian">Indian</form:option>
                                                                                                        <form:option
                                                                                                                value="Ireland">Ireland</form:option>
                                                                                                        <form:option
                                                                                                                value="Other">Other</form:option>
                                                                                                    </form:select>
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.nationality"
                                                                                                            id="memberAsPersonnationalityTextBox"/>
                                                                                                </div>
                                                                                                <label for="memberAsPerson.educationQualifications"
                                                                                                       class="col-sm-2 control-label">Education
                                                                                                    Qualifications</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:textarea
                                                                                                            path="memberAsPerson.educationQualifications"
                                                                                                            id="memberAsPersoneducationQualifications"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.jobDetails"
                                                                                                       class="col-sm-2 control-label">Job
                                                                                                    Details</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:textarea
                                                                                                            path="memberAsPerson.jobDetails"
                                                                                                            id="memberAsPersonjobDetails"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="memberAsPerson.bloodGroup"
                                                                                                       class="col-sm-2 control-label">Blood
                                                                                                    Group</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:select
                                                                                                            path="memberAsPerson.bloodGroup"
                                                                                                            id="bloodGroup"
                                                                                                            class="form-control"
                                                                                                            items="${bloodGroup}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.carNumber"
                                                                                                       class="col-sm-2 control-label">Car
                                                                                                    Number</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.carNumber"
                                                                                                            id="carNumber"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="memberAsPerson.lifeStatus"
                                                                                                       class="col-sm-2 control-label">Life
                                                                                                    Status</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:select
                                                                                                            path="memberAsPerson.lifeStatus"
                                                                                                            id="lifeStatus"
                                                                                                            class="form-control toCaps"
                                                                                                            items="${lifeStatus}">
                                                                                                    </form:select>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.personalRemarks"
                                                                                                       class="col-sm-2 control-label">Personal
                                                                                                    Remarks</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:textarea
                                                                                                            path="memberAsPerson.personalRemarks"
                                                                                                            id="personalRemarks"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <label for="registeredDate"
                                                                                                       class="col-sm-2 control-label required">Registered
                                                                                                    Date</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="registeredDate"
                                                                                                            id="registeredDate"
                                                                                                            class="form-control"
                                                                                                            readonly="true"/>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="tab-pane" id="member4">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.email"
                                                                                                       class="col-sm-2 control-label">Email</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.email"
                                                                                                            id="memberAsPerson.email"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="memberAsPerson.mobileNo"
                                                                                                       class="col-sm-2 control-label">Mobile
                                                                                                    No.</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.mobileNo"
                                                                                                            id="memberAsPersonmobileNo"
                                                                                                            value=""
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.landLine"
                                                                                                       class="col-sm-2 control-label">Land
                                                                                                    Line
                                                                                                    No.</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.landLine"
                                                                                                            id="memberAsPersonlandLine"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="memberAsPerson.faxNo"
                                                                                                       class="col-sm-2 control-label">Fax
                                                                                                    No.</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.faxNo"
                                                                                                            id="memberAsPersonfaxNo"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="tab-pane" id="member5">
                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">
                                                                                            <div class="form-group">
                                                                                                <label for="dateOfBaptism"
                                                                                                       class="col-sm-2 control-label required">Date
                                                                                                    of
                                                                                                    Baptism</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="dateOfBaptism"
                                                                                                            id="dateOfBaptism"
                                                                                                            class="form-control date"/>
                                                                                                </div>
                                                                                                <label for="churchOfBaptism"
                                                                                                       class="col-sm-2 control-label">Place/Church
                                                                                                    of
                                                                                                    Baptism</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="churchOfBaptism"
                                                                                                            id="churchOfBaptism"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="countryOfBaptism"
                                                                                                       class="col-sm-2 control-label">Country
                                                                                                    of
                                                                                                    Baptism</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="countryOfBaptism"
                                                                                                            id="countryOfBaptism"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="baptismName"
                                                                                                       class="col-sm-2 control-label">Baptism
                                                                                                    Name</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="baptismName"
                                                                                                            id="baptismName"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="ministerOfBaptism"
                                                                                                       class="col-sm-2 control-label">Minister
                                                                                                    of
                                                                                                    Baptism</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="ministerOfBaptism"
                                                                                                            id="ministerOfBaptism"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="baptismGodFather"
                                                                                                       class="col-sm-2 control-label">God
                                                                                                    Father</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="baptismGodFather"
                                                                                                            id="baptismGodFather"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="baptismGodMother"
                                                                                                       class="col-sm-2 control-label">God
                                                                                                    Mother</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="baptismGodMother"
                                                                                                            id="baptismGodMother"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="patronSaint"
                                                                                                       class="col-sm-2 control-label">Patron
                                                                                                    Saint</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="patronSaint"
                                                                                                            id="patronSaint"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="patronSaintFeastDay"
                                                                                                       class="col-sm-2 control-label">Patron
                                                                                                    Saint
                                                                                                    Feast
                                                                                                    Day</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="patronSaintFeastDay"
                                                                                                            id="patronSaintFeastDay"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="tab-pane" id="member6">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">
                                                                                            <div class="form-group">
                                                                                                <label for="dateOfConfirmation"
                                                                                                       class="col-sm-2 control-label">Date
                                                                                                    of
                                                                                                    Confirmation</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="dateOfConfirmation"
                                                                                                            id="dateOfConfirmation"
                                                                                                            class="form-control date"/>
                                                                                                </div>
                                                                                                <label for="churchOfConfirmation"
                                                                                                       class="col-sm-2 control-label">Place/Church
                                                                                                    of
                                                                                                    Confirmation</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="churchOfConfirmation"
                                                                                                            id="churchOfConfirmation"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="countryOfConfirmation"
                                                                                                       class="col-sm-2 control-label">Country
                                                                                                    of
                                                                                                    Confirmation</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="countryOfConfirmation"
                                                                                                            id="countryOfConfirmation"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="ministerOfConfirmation"
                                                                                                       class="col-sm-2 control-label">Minister
                                                                                                    of
                                                                                                    Confirmation</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="ministerOfConfirmation"
                                                                                                            id="ministerOfConfirmation"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="confirmationGodFather"
                                                                                                       class="col-sm-2 control-label">God
                                                                                                    Father</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="confirmationGodFather"
                                                                                                            id="confirmationGodFather"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="confirmationGodMother"
                                                                                                       class="col-sm-2 control-label">God
                                                                                                    Mother</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="confirmationGodMother"
                                                                                                            id="confirmationGodMother"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="tab-pane" id="member7">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">

                                                                                            <div class="form-group">
                                                                                                <label for="dateOfFirstCommunion"
                                                                                                       class="col-sm-2 control-label">Date
                                                                                                    of
                                                                                                    Holy
                                                                                                    Communion</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="dateOfFirstCommunion"
                                                                                                            id="dateOfFirstCommunion"
                                                                                                            class="form-control date"/>
                                                                                                </div>
                                                                                                <label for="churchOfHolyCommunion"
                                                                                                       class="col-sm-2 control-label">Place/Church
                                                                                                    of
                                                                                                    Holy
                                                                                                    Communion</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="churchOfHolyCommunion"
                                                                                                            id="churchOfHolyCommunion"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="countryOfHolyCommunion"
                                                                                                       class="col-sm-2 control-label">Country
                                                                                                    of
                                                                                                    Holy
                                                                                                    Communion</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="countryOfHolyCommunion"
                                                                                                            id="countryOfHolyCommunion"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="ministerOfHolyCommunion"
                                                                                                       class="col-sm-2 control-label">Minister
                                                                                                    of
                                                                                                    Holy
                                                                                                    Communion</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="ministerOfHolyCommunion"
                                                                                                            id="ministerOfHolyCommunion"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="tab-pane" id="member8">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">
                                                                                            <div class="form-group">
                                                                                                <label for="dateOfBetrothal"
                                                                                                       class="col-sm-2 control-label">Date
                                                                                                    of
                                                                                                    Betrothal</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="dateOfBetrothal"
                                                                                                            id="dateOfBetrothal"
                                                                                                            class="form-control date"/>
                                                                                                </div>
                                                                                                <label for="churchOfBetrothal"
                                                                                                       class="col-sm-2 control-label">Place/Church
                                                                                                    of
                                                                                                    Betrothal</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="churchOfBetrothal"
                                                                                                            id="churchOfBetrothal"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="countryOfBetrothal"
                                                                                                       class="col-sm-2 control-label">Country
                                                                                                    of
                                                                                                    Betrothal</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="countryOfBetrothal"
                                                                                                            id="countryOfBetrothal"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="priestOfBetrothal"
                                                                                                       class="col-sm-2 control-label">Priest/Bishop
                                                                                                    of
                                                                                                    Betrothal</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="priestOfBetrothal"
                                                                                                            id="priestOfBetrothal"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="spouseName"
                                                                                                       class="col-sm-2 control-label">Spouse
                                                                                                    Name</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="spouseName"
                                                                                                            id="spouseName"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="spouseBaptismName"
                                                                                                       class="col-sm-2 control-label">Spouse
                                                                                                    Baptism
                                                                                                    Name</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="spouseBaptismName"
                                                                                                            id="spouseBaptismName"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="spouseNativeParish"
                                                                                                       class="col-sm-2 control-label">Spouse
                                                                                                    Native
                                                                                                    Parish</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="spouseNativeParish"
                                                                                                            id="spouseNativeParish"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="spouseNativeDiocese"
                                                                                                       class="col-sm-2 control-label">Spouse
                                                                                                    Native
                                                                                                    Diocese</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="spouseNativeDiocese"
                                                                                                            id="spouseNativeDiocese"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="spouseFatherName"
                                                                                                       class="col-sm-2 control-label">Spouse
                                                                                                    Father
                                                                                                    Name</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="spouseFatherName"
                                                                                                            id="spouseFatherName"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="spouseMotherName"
                                                                                                       class="col-sm-2 control-label">Spouse
                                                                                                    Mother
                                                                                                    Name</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="spouseMotherName"
                                                                                                            id="spouseMotherName"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="spouseNativeAddress"
                                                                                                       class="col-sm-2 control-label">Spouse
                                                                                                    Native
                                                                                                    Address</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="spouseNativeAddress"
                                                                                                            id="spouseNativeAddress"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="spouseNationality"
                                                                                                       class="col-sm-2 control-label">Spouse
                                                                                                    Nationality</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="spouseNationality"
                                                                                                            id="spouseNationality"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="betrothalWitnessOne"
                                                                                                       class="col-sm-2 control-label">Betrothal
                                                                                                    Witness-1</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="betrothalWitnessOne"
                                                                                                            id="betrothalWitnessOne"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="betrothalWitnessTwo"
                                                                                                       class="col-sm-2 control-label">Betrothal
                                                                                                    Witness-2</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="betrothalWitnessTwo"
                                                                                                            id="betrothalWitnessTwo"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="tab-pane" id="member9">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">

                                                                                            <div class="form-group">
                                                                                                <label for="dateOfMarriage"
                                                                                                       class="col-sm-2 control-label">Date
                                                                                                    of
                                                                                                    Marriage</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="dateOfMarriage"
                                                                                                            id="dateOfMarriage"
                                                                                                            class="form-control date"/>
                                                                                                </div>
                                                                                                <label for="churchOfMarriage"
                                                                                                       class="col-sm-2 control-label">Place/Church
                                                                                                    of
                                                                                                    Marriage</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="churchOfMarriage"
                                                                                                            id="churchOfMarriage"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="priestOfMarriage"
                                                                                                       class="col-sm-2 control-label">Priest/Bishop
                                                                                                    of
                                                                                                    Marriage</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="priestOfMarriage"
                                                                                                            id="priestOfMarriage"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="marriageWitnessOne"
                                                                                                       class="col-sm-2 control-label">Marriage
                                                                                                    Witness-1</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="marriageWitnessOne"
                                                                                                            id="marriageWitnessOne"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="marriageWitnessTwo"
                                                                                                       class="col-sm-2 control-label">Marriage
                                                                                                    Witness-2</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="marriageWitnessTwo"
                                                                                                            id="marriageWitnessTwo"
                                                                                                            class="form-control"/>
                                                                                                </div>

                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="tab-pane" id="member10">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel">
                                                                                            <div class="form-group">
                                                                                                <label for="dateOfDeath"
                                                                                                       class="col-sm-2 control-label">Date
                                                                                                    of
                                                                                                    Death</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="dateOfDeath"
                                                                                                            id="dateOfDeath"
                                                                                                            class="form-control date"/>
                                                                                                </div>
                                                                                                <label for="placeOfDeath"
                                                                                                       class="col-sm-2 control-label">Place
                                                                                                    of
                                                                                                    Death</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="placeOfDeath"
                                                                                                            id="placeOfDeath"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="funeralDate"
                                                                                                       class="col-sm-2 control-label">Funeral
                                                                                                    Date</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="funeralDate"
                                                                                                            id="funeralDate"
                                                                                                            class="form-control date"/>
                                                                                                </div>
                                                                                                <label for="buriedChurch"
                                                                                                       class="col-sm-2 control-label">Buried
                                                                                                    Church</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="buriedChurch"
                                                                                                            id="buriedChurch"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="ministerOfDeath"
                                                                                                       class="col-sm-2 control-label">Minister
                                                                                                    of
                                                                                                    Death</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="ministerOfDeath"
                                                                                                            id="ministerOfDeath"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="placeOfCemetery"
                                                                                                       class="col-sm-2 control-label">Place
                                                                                                    of
                                                                                                    Cemetery</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="placeOfCemetery"
                                                                                                            id="placeOfCemetery"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="tombNo"
                                                                                                       class="col-sm-2 control-label">Tomb
                                                                                                    No.</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="tombNo"
                                                                                                            id="tombNo"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="confession"
                                                                                                       class="col-sm-2 control-label">Confession</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="confession"
                                                                                                            id="confession"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="communion"
                                                                                                       class="col-sm-2 control-label">Communion</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="communion"
                                                                                                            id="communion"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                                <label for="anointingTheSick"
                                                                                                       class="col-sm-2 control-label">Anointing
                                                                                                    the
                                                                                                    Sick</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="anointingTheSick"
                                                                                                            id="anointingTheSick"
                                                                                                            class="form-control"/>
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="form-group">
                                                                                                <label for="ministerOfAnointingTheSick"
                                                                                                       class="col-sm-2 control-label">Minister
                                                                                                    of
                                                                                                    Anointing
                                                                                                    the
                                                                                                    Sick</label>

                                                                                                <div class="col-sm-3">
                                                                                                    <form:input
                                                                                                            path="ministerOfAnointingTheSick"
                                                                                                            id="ministerOfAnointingTheSick"
                                                                                                            class="form-control"/>
                                                                                                </div>

                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="tab-pane" id="member11">

                                                                                    <div class="col-md-12">
                                                                                        <div class="panel" style="width:420px; margin: 0 auto">
                                                                                            <div class="form-group">
                                                                                                <label for="memberAsPerson.file" class="col-sm-2 control-label" style="width: 105px;padding-top: 54px;padding-bottom: 50px;">Upload Photo</label>
                                                                                                <div class="col-sm-3" style="width: 135px;overflow: hidden;padding-top: 45px;">


                                                                                                    <span class="btn btn-primary btn-file">
                                                                                                                    Choose File...
                                                                                                        <form:input
                                                                                                                path="memberAsPerson.file"
                                                                                                                id="memberAsPerson.file"
                                                                                                                class="form-control"
                                                                                                                type="file"/>
                                                                                                    </span>
                                                                                                    <form:input
                                                                                                            path="memberAsPerson.imageBytesAsString"
                                                                                                            id="memberAsPerson.imageBytesAsString"
                                                                                                            class="form-control"
                                                                                                            type="hidden"
                                                                                                            />


                                                                                                </div>
                                                                                                <div class="col-sm-3" style="width: auto;">

                                                                                                    <div class="thumbnail" style="padding-top: 4px;display:none;" id="thumbnail">
                                                                                                        <div class="caption text-center" style="padding: 0;" id="imagePreviewHeader">
                                                                                                            <h4 style="margin: 0;">Preview</h4>
                                                                                                        </div>
                                                                                                        <img id="ItemPreview" src="" alt="Upload Image" class="img-rounded" style="height: 84px;" />
                                                                                                    </div>

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
            <!-- container -->
            <!-- container -->
        </div>
        <!--wrap -->
    </div>
    <!-- page-content -->

    <%@include
            file="footerPanelTemplate.jsp" %>

</div>
</body>
</html>