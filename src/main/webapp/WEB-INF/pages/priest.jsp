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
    <%@ include file="scriptLibraryTemplate.jsp" %>

    <spring:url value="/addpriest.action" var="priestActionURL"/>
    <spring:url value="/resources/js/createpriestgridlayout.js" var="priestGridURL"/>

    <script src="${priestGridURL}" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        jQuery(document).ready(function () {

            loadPriestGrid();

            globalSubmissionOfForms('priestForm', '${priestActionURL}');

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
                                                        <h4>Priest Information</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <table id="priestGrid"></table>
                                                        <div id="priestGridPager"></div>
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
                                            <a href="#priest1" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Personal Details</span></a>
                                        </li>
                                        <li>
                                            <a href="#priest2" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Contact Details</span></a>
                                        </li>
                                        <li>
                                            <a href="#priest3" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Local Address</span></a>
                                        </li>
                                        <li>
                                            <a href="#priest4" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Native Address</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">

                                <form:form modelAttribute="priest"
                                           action="${priestActionURL}" method="post"
                                           id="priestForm" class="form-horizontal">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="priest1">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>Personal Details</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="priestStatus"
                                                                   class="col-sm-1 control-label">Priest Status</label>

                                                            <div class="col-sm-3">
                                                                <form:select path="priestStatus"
                                                                             id="priestStatus" class="form-control">
                                                                    <form:option value="Active"
                                                                                 selected="selected">Active</form:option>
                                                                    <form:option
                                                                            value="Transferred">Transferred</form:option>
                                                                    <form:option
                                                                            value="Not-Active">Not-Active</form:option>
                                                                </form:select>
                                                            </div>
                                                            <label for="priestID" class="col-sm-1 control-label">Priest
                                                                ID</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="priestID"
                                                                            id="priestID"
                                                                            readonly="true"
                                                                            placeholder="auto generated value"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPerson.salutation"
                                                                   class="col-sm-1 control-label">Salutation</label>

                                                            <div class="col-sm-3">
                                                                <form:select
                                                                        path="priestAsPerson.salutation"
                                                                        id="priestAsPerson.salutation"
                                                                        class="form-control">
                                                                    <form:option value="Rev."
                                                                                 selected="selected">Rev.</form:option>
                                                                    <form:option value="Rev. Dr.">Rev. Dr.</form:option>
                                                                </form:select>
                                                            </div>
                                                            <label for="priestAsPersonfirstName"
                                                                   class="col-sm-1 control-label">First Name</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.firstName"
                                                                        id="priestAsPersonfirstName"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPersonmiddleName"
                                                                   class="col-sm-1 control-label">Middle Name</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.middleName"
                                                                        id="priestAsPersonmiddleName"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="priestAsPersonlastName"
                                                                   class="col-sm-1 control-label">Last Name</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.lastName"
                                                                        id="priestAsPersonlastName"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPersondateOfBirth"
                                                                   class="col-sm-1 control-label">Date of Birth</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.dateOfBirth"
                                                                        id="priestAsPersondateOfBirth"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="familyName" class="col-sm-1 control-label">Family
                                                                Name</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="familyName"
                                                                        id="familyName" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPersonplaceOfBirth"
                                                                   class="col-sm-1 control-label">Place of Birth</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.placeOfBirth"
                                                                        id="priestAsPersonplaceOfBirth"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="priestAsPerson.gender"
                                                                   class="col-sm-1 control-label">Gender</label>

                                                            <div class="col-sm-3">
                                                                <form:radiobutton
                                                                        path="priestAsPerson.gender"
                                                                        id="priestAsPersongender" value="Male"
                                                                        class="form-control"/>Male

                                                                <form:radiobutton path="priestAsPerson.gender"
                                                                                  id="priestAsPersongender"
                                                                                  value="Female" class="form-control"/>Female
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPerson.nationality"
                                                                   class="col-sm-1 control-label">Nationality</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.nationality"
                                                                        id="priestAsPersonnationality"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="designation" class="col-sm-1 control-label">Designation</label>

                                                            <div class="col-sm-3">
                                                                <form:select path="designation"
                                                                             items="${priestDesignation}"
                                                                             class="form-control"></form:select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="parishId"
                                                                   class="col-sm-1 control-label">Parish</label>

                                                            <div class="col-sm-3">
                                                                <form:select path="parishId"
                                                                             items="${parishList}" class="form-control"
                                                                        ></form:select>
                                                            </div>
                                                            <label for="priestAsPerson.educationQualifications"
                                                                   class="col-sm-1 control-label">Education
                                                                Qualifications</label>

                                                            <div class="col-sm-3">
                                                                <form:textarea
                                                                        path="priestAsPerson.educationQualifications"
                                                                        id="priestAsPersoneducationQualifications"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPerson.jobDetails"
                                                                   class="col-sm-1 control-label">Job Details</label>

                                                            <div class="col-sm-3">
                                                                <form:textarea
                                                                        path="priestAsPerson.jobDetails"
                                                                        id="priestAsPersonjobDetails"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="priestAsPerson.personalStatus"
                                                                   class="col-sm-1 control-label">Personal
                                                                Status</label>

                                                            <div class="col-sm-3">
                                                                <form:select
                                                                        path="priestAsPerson.personalStatus"
                                                                        id="personalStatus" class="form-control">
                                                                    <form:option value="Priest"
                                                                                 selected="selected">Priest</form:option>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPerson.bloodGroup"
                                                                   class="col-sm-1 control-label">Blood Group</label>

                                                            <div class="col-sm-3">
                                                                <form:select
                                                                        path="priestAsPerson.bloodGroup"
                                                                        id="bloodGroup" class="form-control">
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
                                                            </div>
                                                            <label for="priestAsPerson.carNumber"
                                                                   class="col-sm-1 control-label">Car Number</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.carNumber" id="carNumber"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPerson.lifeStatus"
                                                                   class="col-sm-1 control-label">Life Status</label>

                                                            <div class="col-sm-3">
                                                                <form:select
                                                                        path="priestAsPerson.lifeStatus"
                                                                        id="lifeStatus" class="form-control">
                                                                    <form:option value="Live"
                                                                                 selected="selected">Live</form:option>
                                                                    <form:option value="Late">Late</form:option>
                                                                </form:select>
                                                            </div>
                                                            <label for="priestAsPerson.personalRemarks"
                                                                   class="col-sm-1 control-label">Personal
                                                                Remarks</label>

                                                            <div class="col-sm-3">
                                                                <form:textarea
                                                                        path="priestAsPerson.personalRemarks"
                                                                        id="personalRemarks" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="dateOfOrdination"
                                                                   class="col-sm-1 control-label">Date of
                                                                Ordination</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="dateOfOrdination"
                                                                        id="dateOfOrdination" class="form-control"/>
                                                            </div>
                                                            <label for="feastDay" class="col-sm-1 control-label">Feast
                                                                Day</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="feastDay"
                                                                            id="feastDay" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="heavenlyPatron"
                                                                   class="col-sm-1 control-label">Heavenly
                                                                Patron</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="heavenlyPatron"
                                                                            id="heavenlyPatron" class="form-control"/>
                                                            </div>
                                                            <label for="nativeDiocese" class="col-sm-1 control-label">Native
                                                                Diocese</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeDiocese"
                                                                            id="nativeDiocese" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="congregation"
                                                                   class="col-sm-1 control-label">Congregation</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="congregation"
                                                                            id="congregation" class="form-control"/>
                                                            </div>
                                                            <label for="nativeParish" class="col-sm-1 control-label">Native
                                                                Parish</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeParish"
                                                                            id="nativeParish" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="nativePlace"
                                                                   class="col-sm-1 control-label">Native Place</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativePlace"
                                                                            id="nativePlace" class="form-control"/>
                                                            </div>
                                                            <label for="priestCardValidity"
                                                                   class="col-sm-1 control-label">Card Validity</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestCardValidity"
                                                                        id="priestCardValidity" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="ordainedToDiocese"
                                                                   class="col-sm-1 control-label">Ordained To
                                                                Diocese</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="ordainedToDiocese"
                                                                        id="ordainedToDiocese" class="form-control"/>
                                                            </div>
                                                            <label for="fatherName" class="col-sm-1 control-label">Father's
                                                                Name</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="fatherName"
                                                                            id="fatherName" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="motherName"
                                                                   class="col-sm-1 control-label">Mother's Name</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="motherName"
                                                                            id="motherName" class="form-control"/>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="tab-pane" id="priest2">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Contact Details</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="priestAsPerson.email"
                                                                   class="col-sm-1 control-label">Email</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.email"
                                                                        id="priestAsPersonemail" class="form-control"/>
                                                            </div>
                                                            <label for="priestAsPerson.mobileNo"
                                                                   class="col-sm-1 control-label">Mobile No.</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.mobileNo"
                                                                        id="priestAsPersonmobileNo"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="priestAsPerson.landLine"
                                                                   class="col-sm-1 control-label">Land Line No.</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.landLine"
                                                                        id="priestAsPersonlandLine"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="priestAsPerson.faxNo"
                                                                   class="col-sm-1 control-label">Fax No.</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="priestAsPerson.faxNo"
                                                                        id="priestAsPersonfaxNo" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="emergencyContact.name"
                                                                   class="col-sm-1 control-label">Name</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="emergencyContact.name"
                                                                        id="emergencyContactname" class="form-control"/>
                                                            </div>
                                                            <label for="emergencyContact.addressLineOne"
                                                                   class="col-sm-1 control-label">Address Line 1</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="emergencyContact.addressLineOne"
                                                                        id="emergencyContactaddressLineOne"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="emergencyContact.addressLineTwo"
                                                                   class="col-sm-1 control-label">Address Line 2</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="emergencyContact.addressLineTwo"
                                                                        id="emergencyContactaddressLineTwo"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="emergencyContact.addressLineThree"
                                                                   class="col-sm-1 control-label">Address Line 3</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="emergencyContact.addressLineThree"
                                                                        id="emergencyContactaddressLineThree"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="emergencyContact.email"
                                                                   class="col-sm-1 control-label">Email</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="emergencyContact.email"
                                                                        id="emergencyContactemail"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="emergencyContact.mobileNo"
                                                                   class="col-sm-1 control-label">Mobile No.</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="emergencyContact.mobileNo"
                                                                        id="emergencyContactmobileNo"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="emergencyContact.landLineNo"
                                                                   class="col-sm-1 control-label">Land Line No.</label>

                                                            <div class="col-sm-3">
                                                                <form:input
                                                                        path="emergencyContact.landLineNo"
                                                                        id="emergencyContactlandLineNo"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>

                                        <div class="tab-pane" id="priest3">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Local Address</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="localAddress.addressLineOne"
                                                                   class="col-sm-1 control-label">Address Line 1</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.addressLineOne"
                                                                            id="localAddressaddressLineOne"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.addressLineTwo"
                                                                   class="col-sm-1 control-label">Address Line 2</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.addressLineTwo"
                                                                            id="localAddressaddressLineTwo"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.addressLineThree"
                                                                   class="col-sm-1 control-label">Address Line 3</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.addressLineThree"
                                                                            id="localAddressaddressLineThree"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.town"
                                                                   class="col-sm-1 control-label">Town</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.town"
                                                                            id="localAddresstown" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.county"
                                                                   class="col-sm-1 control-label">County</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.county"
                                                                            id="localAddresscounty"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="localAddress.pin"
                                                                   class="col-sm-1 control-label">Pin code</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.pin" id="localAddresspin"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="localAddress.country"
                                                                   class="col-sm-1 control-label">Country</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="localAddress.country"
                                                                            id="localAddresscountry"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>

                                        <div class="tab-pane" id="priest4">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Native Address</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="nativeAddress.addressLineOne"
                                                                   class="col-sm-1 control-label">Address Line 1</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeAddress.addressLineOne"
                                                                            id="nativeAddressaddressLineOne"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="nativeAddress.addressLineTwo"
                                                                   class="col-sm-1 control-label">Address Line 2</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeAddress.addressLineTwo"
                                                                            id="nativeAddressaddressLineTwo"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="nativeAddress.addressLineThree"
                                                                   class="col-sm-1 control-label">Address Line 3</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeAddress.addressLineThree"
                                                                            id="nativeAddressaddressLineThree"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="nativeAddress.postOffice"
                                                                   class="col-sm-1 control-label">Post Office</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeAddress.postOffice"
                                                                            id="nativeAddresspostOffice"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="nativeAddress.district"
                                                                   class="col-sm-1 control-label">District</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeAddress.district"
                                                                            id="nativeAddressdistrict"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="nativeAddress.state"
                                                                   class="col-sm-1 control-label">State</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeAddress.state"
                                                                            id="nativeAddressstate"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="nativeAddress.country"
                                                                   class="col-sm-1 control-label">Country</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeAddress.country"
                                                                            id="nativeAddresscountry"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="nativeAddress.pin"
                                                                   class="col-sm-1 control-label">Pin code</label>

                                                            <div class="col-sm-3">
                                                                <form:input path="nativeAddress.pin"
                                                                            id="nativeAddresspin" class="form-control"/>
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
