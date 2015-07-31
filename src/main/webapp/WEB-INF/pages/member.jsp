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
        $(document).ready(function () {

            backToTop();

            loadDatePicker();

            <c:if test="${showForFamilyUser == false}">
            loadSelectBox("${pageContext.request.contextPath}");
            </c:if>

            loadMemberGrid();
            globalSubmissionOfForms('memberForm', '${memberActionURL}', 'memberGrid');

           // $("select#personalStatus").val("0");
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
                                                        <h4>Member Information</h4>
                                                    </div>
                                                    <div class="panel-body nopadding">
                                                        <table id="memberGrid"></table>
                                                        <div id="memberGridPager"></div>
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


            <div class="container padding7 paddingTop0">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue nomargin">
                            <div class="panel-heading">
                                <h4>
                                    <ul class="nav nav-tabs">

                                        <li class="active">
                                            <a href="#member2" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Member Details</span></a>
                                        </li>
                                        <li>
                                            <a href="#member3" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Member Details</span></a>
                                        </li>
                                        <li>
                                            <a href="#member4" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Contact Details</span></a>
                                        </li>
                                        <li>
                                            <a href="#member5" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Baptism</span></a>
                                        </li>
                                        <li>
                                            <a href="#member6" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Confirmation</span></a>
                                        </li>
                                        <li>
                                            <a href="#member7" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">First Holy Communion</span></a>
                                        </li>
                                        <li>
                                            <a href="#member8" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Betrothal</span></a>
                                        </li>
                                        <li>
                                            <a href="#member9" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Marriage</span></a>
                                        </li>
                                        <li>
                                            <a href="#member10" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Death</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body padding7">

                                <form:form modelAttribute="member"
                                           action="${memberActionURL}" method="post"
                                           id="memberForm" cssClass="form-horizontal nomargin">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="member2">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>Member Details</h4>
                                                    </div>
                                                    <div class="panel-body padding7">
                                                        <c:if test="${showForFamilyUser == false}">
                                                            <div class="form-group">
                                                                <label for="familyId" class="col-sm-2 control-label">Family</label>

                                                                <div class="col-sm-4">
                                                                    <form:select path="familyId"
                                                                                 id="familySelectBoxofMember"
                                                                                 class="form-control"/>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${showForFamilyUser == true}">

                                                            <div class="form-group">
                                                                <label for="familyId" class="col-sm-2 control-label">Family</label>

                                                                <div class="col-sm-4">
                                                                    <form:select path="familyId" id="familySelectBox"
                                                                                 items="${familyName}"
                                                                                 class="form-control"/>
                                                                </div>
                                                            </div>

                                                        </c:if>

                                                        <div class="form-group">
                                                            <label for="memberAsPerson.salutation"
                                                                   class="col-sm-2 control-label">Salutation</label>

                                                            <div class="col-sm-4">
                                                                <form:select path="memberAsPerson.salutation"
                                                                             id="memberAsPerson.salutation"
                                                                             class="form-control" items="${salutation}">
                                                                </form:select>
                                                            </div>
                                                            <label for="memberAsPerson.firstName"
                                                                   class="col-sm-2 control-label">First Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="memberAsPerson.firstName"
                                                                            id="memberAsPersonfirstName"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="memberAsPerson.middleName"
                                                                   class="col-sm-2 control-label">Middle Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="memberAsPerson.middleName"
                                                                            id="memberAsPersonmiddleName"
                                                                            class="form-control"/>
                                                            </div>
                                                            <label for="memberAsPerson.lastName"
                                                                   class="col-sm-2 control-label">Last Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="memberAsPerson.lastName"
                                                                            id="memberAsPersonlastName"
                                                                            class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="relationshipInFamily"
                                                                   class="col-sm-2 control-label">Relationship In
                                                                Family</label>

                                                            <div class="col-sm-4">
                                                                <form:select path="relationshipInFamily"
                                                                             id="relationshipInFamily"
                                                                             class="form-control"
                                                                             items="${relationshipInFamily}">
                                                                </form:select>
                                                            </div>
                                                            <label for="memberAsPerson.dateOfBirth"
                                                                   class="col-sm-2 control-label">Date of Birth</label>

                                                            <div class="col-sm-4">
                                                                <form:input path="memberAsPerson.dateOfBirth"
                                                                            id="memberAsPersondateOfBirth"
                                                                            class="form-control date"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="memberAsPersongender"
                                                                   class="col-sm-2 control-label">Gender</label>

                                                            <div class="col-sm-4">
                                                                <form:radiobuttons
                                                                        path="memberAsPerson.gender"
                                                                        id="memberAsPersongender" class="form-control"
                                                                        items="${sex}"/>
                                                            </div>
                                                            <label for="memberAsPerson.placeOfBirth"
                                                                   class="col-sm-2 control-label">Place of Birth</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="memberAsPerson.placeOfBirth"
                                                                        id="memberAsPersonplaceOfBirth"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="memberAsPerson.educationQualifications"
                                                                   class="col-sm-2 control-label">Education
                                                                Qualifications</label>

                                                            <div class="col-sm-4">
                                                                <form:textarea
                                                                        path="memberAsPerson.educationQualifications"
                                                                        id="memberAsPersoneducationQualifications"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="memberAsPerson.nationality"
                                                                   class="col-sm-2 control-label">Nationality</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="memberAsPerson.nationality"
                                                                        id="memberAsPersonnationality"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">

                                                            <label for="memberAsPerson.jobDetails"
                                                                   class="col-sm-2 control-label">Job Details</label>

                                                            <div class="col-sm-4">
                                                                <form:textarea
                                                                        path="memberAsPerson.jobDetails"
                                                                        id="memberAsPersonjobDetails"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="memberAsPerson.personalStatus"
                                                                   class="col-sm-2 control-label">Personal
                                                                Status</label>

                                                            <div class="col-sm-4">
                                                                <form:select
                                                                        path="memberAsPerson.personalStatus"
                                                                        id="personalStatus" class="form-control"
                                                                        items="${personalStatus}">
                                                                </form:select>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">

                                                            <label for="memberAsPerson.bloodGroup"
                                                                   class="col-sm-2 control-label">Blood Group</label>

                                                            <div class="col-sm-4">
                                                                <form:select
                                                                        path="memberAsPerson.bloodGroup"
                                                                        id="bloodGroup" class="form-control"
                                                                        items="${bloodGroup}">
                                                                </form:select>
                                                            </div>
                                                            <label for="memberAsPerson.carNumber"
                                                                   class="col-sm-2 control-label">Car Number</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="memberAsPerson.carNumber"
                                                                        id="carNumber" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">

                                                            <label for="memberAsPerson.lifeStatus"
                                                                   class="col-sm-2 control-label">Life Status</label>

                                                            <div class="col-sm-4">
                                                                <form:select
                                                                        path="memberAsPerson.lifeStatus"
                                                                        id="lifeStatus" class="form-control"
                                                                        items="${lifeStatus}">
                                                                </form:select>
                                                            </div>
                                                            <label for="memberAsPerson.personalRemarks"
                                                                   class="col-sm-2 control-label">Personal
                                                                Remarks</label>

                                                            <div class="col-sm-4">
                                                                <form:textarea
                                                                        path="memberAsPerson.personalRemarks"
                                                                        id="personalRemarks" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>


                                        <div class="tab-pane" id="member3">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Member
                                                            Details</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="memberNo" class="col-sm-2 control-label">Member
                                                                No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="memberNo"
                                                                        id="memberNo" class="form-control"
                                                                        readonly="true"/>
                                                            </div>
                                                            <label for="piousAssociation"
                                                                   class="col-sm-2 control-label">Pious
                                                                Association</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="piousAssociation"
                                                                        id="piousAssociation" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="sundayCatechism" class="col-sm-2 control-label">Catechism
                                                                Qualification</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="sundayCatechism"
                                                                        id="sundayCatechism" class="form-control"/>
                                                            </div>
                                                            <label for="sacramentalLife" class="col-sm-2 control-label">Sacramental
                                                                Life
                                                                Remarks</label>

                                                            <div class="col-sm-4">
                                                                <form:textarea
                                                                        path="sacramentalLife"
                                                                        id="sacramentalLife" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="churchRemarks" class="col-sm-2 control-label">Church
                                                                Remarks</label>

                                                            <div class="col-sm-4">
                                                                <form:textarea
                                                                        path="churchRemarks"
                                                                        id="churchRemarks" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>


                                        <div class="tab-pane" id="member4">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Contact
                                                            Details</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="memberAsPersonemail"
                                                                   class="col-sm-2 control-label">Email</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="memberAsPerson.email"
                                                                        id="memberAsPersonemail" class="form-control"/>
                                                            </div>
                                                            <label for="memberAsPerson.mobileNo"
                                                                   class="col-sm-2 control-label">Mobile
                                                                No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="memberAsPerson.mobileNo"
                                                                        id="memberAsPersonmobileNo"
                                                                        value="" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="memberAsPerson.landLine"
                                                                   class="col-sm-2 control-label">Land
                                                                Line
                                                                No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="memberAsPerson.landLine"
                                                                        id="memberAsPersonlandLine"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="memberAsPerson.faxNo"
                                                                   class="col-sm-2 control-label">Fax
                                                                No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="memberAsPerson.faxNo"
                                                                        id="memberAsPersonfaxNo" class="form-control"/>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="tab-pane" id="member5">

                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Baptism</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="dateOfBaptism" class="col-sm-2 control-label">Date
                                                                of
                                                                Baptism</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="dateOfBaptism"
                                                                        id="dateOfBaptism" class="form-control date"/>
                                                            </div>
                                                            <label for="churchOfBaptism" class="col-sm-2 control-label">Place/Church
                                                                of
                                                                Baptism</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="churchOfBaptism"
                                                                        id="churchOfBaptism" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="countryOfBaptism"
                                                                   class="col-sm-2 control-label">Country
                                                                of
                                                                Baptism</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="countryOfBaptism"
                                                                        id="countryOfBaptism" class="form-control"/>
                                                            </div>
                                                            <label for="baptismName" class="col-sm-2 control-label">Baptism
                                                                Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="baptismName"
                                                                        id="baptismName" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="ministerOfBaptism"
                                                                   class="col-sm-2 control-label">Minister
                                                                of
                                                                Baptism</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="ministerOfBaptism"
                                                                        id="ministerOfBaptism" class="form-control"/>
                                                            </div>
                                                            <label for="baptismGodFather"
                                                                   class="col-sm-2 control-label">God
                                                                Father</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="baptismGodFather"
                                                                        id="baptismGodFather" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="baptismGodMother"
                                                                   class="col-sm-2 control-label">God
                                                                Mother</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="baptismGodMother"
                                                                        id="baptismGodMother" class="form-control"/>
                                                            </div>
                                                            <label for="patronSaint" class="col-sm-2 control-label">Patron
                                                                Saint</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="patronSaint"
                                                                        id="patronSaint" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="patronSaintFeastDay"
                                                                   class="col-sm-2 control-label">Patron
                                                                Saint
                                                                Feast
                                                                Day</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="patronSaintFeastDay"
                                                                        id="patronSaintFeastDay" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>


                                        <div class="tab-pane" id="member6">
                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Confirmation</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="dateOfConfirmation"
                                                                   class="col-sm-2 control-label">Date
                                                                of
                                                                Confirmation</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="dateOfConfirmation"
                                                                        id="dateOfConfirmation"
                                                                        class="form-control date"/>
                                                            </div>
                                                            <label for="churchOfConfirmation"
                                                                   class="col-sm-2 control-label">Place/Church
                                                                of
                                                                Confirmation</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="churchOfConfirmation"
                                                                        id="churchOfConfirmation" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="countryOfConfirmation"
                                                                   class="col-sm-2 control-label">Country
                                                                of
                                                                Confirmation</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="countryOfConfirmation"
                                                                        id="countryOfConfirmation"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="ministerOfConfirmation"
                                                                   class="col-sm-2 control-label">Minister
                                                                of
                                                                Confirmation</label>

                                                            <div class="col-sm-4">
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

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="confirmationGodFather"
                                                                        id="confirmationGodFather"
                                                                        class="form-control"/>
                                                            </div>
                                                            <label for="confirmationGodMother"
                                                                   class="col-sm-2 control-label">God
                                                                Mother</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="confirmationGodMother"
                                                                        id="confirmationGodMother"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>


                                        <div class="tab-pane" id="member7">
                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            First
                                                            Holy
                                                            Communion</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="dateOfFirstCommunion"
                                                                   class="col-sm-2 control-label">Date
                                                                of
                                                                Holy
                                                                Communion</label>

                                                            <div class="col-sm-4">
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

                                                            <div class="col-sm-4">
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

                                                            <div class="col-sm-4">
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

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="ministerOfHolyCommunion"
                                                                        id="ministerOfHolyCommunion"
                                                                        class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="tab-pane" id="member8">
                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Betrothal</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="dateOfBetrothal" class="col-sm-2 control-label">Date
                                                                of
                                                                Betrothal</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="dateOfBetrothal"
                                                                        id="dateOfBetrothal" class="form-control date"/>
                                                            </div>
                                                            <label for="churchOfBetrothal"
                                                                   class="col-sm-2 control-label">Place/Church
                                                                of
                                                                Betrothal</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="churchOfBetrothal"
                                                                        id="churchOfBetrothal" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="countryOfBetrothal"
                                                                   class="col-sm-2 control-label">Country
                                                                of
                                                                Betrothal</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="countryOfBetrothal"
                                                                        id="countryOfBetrothal" class="form-control"/>
                                                            </div>
                                                            <label for="priestOfBetrothal"
                                                                   class="col-sm-2 control-label">Priest/Bishop
                                                                of
                                                                Betrothal</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="priestOfBetrothal"
                                                                        id="priestOfBetrothal" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="spouseName" class="col-sm-2 control-label">Spouse
                                                                Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="spouseName"
                                                                        id="spouseName" class="form-control"/>
                                                            </div>
                                                            <label for="spouseBaptismName"
                                                                   class="col-sm-2 control-label">Spouse
                                                                Baptism
                                                                Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="spouseBaptismName"
                                                                        id="spouseBaptismName" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="spouseNativeParish"
                                                                   class="col-sm-2 control-label">Spouse
                                                                Native
                                                                Parish</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="spouseNativeParish"
                                                                        id="spouseNativeParish" class="form-control"/>
                                                            </div>
                                                            <label for="spouseNativeDiocese"
                                                                   class="col-sm-2 control-label">Spouse
                                                                Native
                                                                Diocese</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="spouseNativeDiocese"
                                                                        id="spouseNativeDiocese" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="spouseFatherName"
                                                                   class="col-sm-2 control-label">Spouse
                                                                Father
                                                                Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="spouseFatherName"
                                                                        id="spouseFatherName" class="form-control"/>
                                                            </div>
                                                            <label for="spouseMotherName"
                                                                   class="col-sm-2 control-label">Spouse
                                                                Mother
                                                                Name</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="spouseMotherName"
                                                                        id="spouseMotherName" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="spouseNativeAddress"
                                                                   class="col-sm-2 control-label">Spouse
                                                                Native
                                                                Address</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="spouseNativeAddress"
                                                                        id="spouseNativeAddress" class="form-control"/>
                                                            </div>
                                                            <label for="spouseNationality"
                                                                   class="col-sm-2 control-label">Spouse
                                                                Nationality</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="spouseNationality"
                                                                        id="spouseNationality" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="betrothalWitnessOne"
                                                                   class="col-sm-2 control-label">Betrothal
                                                                Witness-1</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="betrothalWitnessOne"
                                                                        id="betrothalWitnessOne" class="form-control"/>
                                                            </div>
                                                            <label for="betrothalWitnessTwo"
                                                                   class="col-sm-2 control-label">Betrothal
                                                                Witness-2</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="betrothalWitnessTwo"
                                                                        id="betrothalWitnessTwo" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="tab-pane" id="member9">
                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Marriage</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="dateOfMarriage" class="col-sm-2 control-label">Date
                                                                of
                                                                Marriage</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="dateOfMarriage"
                                                                        id="dateOfMarriage" class="form-control date"/>
                                                            </div>
                                                            <label for="churchOfMarriage"
                                                                   class="col-sm-2 control-label">Place/Church
                                                                of
                                                                Marriage</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="churchOfMarriage"
                                                                        id="churchOfMarriage" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="priestOfMarriage"
                                                                   class="col-sm-2 control-label">Priest/Bishop
                                                                of
                                                                Marriage</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="priestOfMarriage"
                                                                        id="priestOfMarriage" class="form-control"/>
                                                            </div>
                                                            <label for="marriageWitnessOne"
                                                                   class="col-sm-2 control-label">Marriage
                                                                Witness-1</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="marriageWitnessOne"
                                                                        id="marriageWitnessOne" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="marriageWitnessTwo"
                                                                   class="col-sm-2 control-label">Marriage
                                                                Witness-2</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="marriageWitnessTwo"
                                                                        id="marriageWitnessTwo" class="form-control"/>
                                                            </div>

                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="tab-pane" id="member10">
                                            <div class="col-md-12">
                                                <div class="panel panel-grape marginBottom7">
                                                    <div class="panel-heading">
                                                        <h4>
                                                            Death</h4>
                                                    </div>
                                                    <div class="panel-body">

                                                        <div class="form-group">
                                                            <label for="dateOfDeath" class="col-sm-2 control-label">Date
                                                                of
                                                                Death</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="dateOfDeath"
                                                                        id="dateOfDeath" class="form-control date"/>
                                                            </div>
                                                            <label for="placeOfDeath" class="col-sm-2 control-label">Place
                                                                of
                                                                Death</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="placeOfDeath"
                                                                        id="placeOfDeath" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="funeralDate" class="col-sm-2 control-label">Funeral
                                                                Date</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="funeralDate"
                                                                        id="funeralDate" class="form-control date"/>
                                                            </div>
                                                            <label for="buriedChurch" class="col-sm-2 control-label">Buried
                                                                Church</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="buriedChurch"
                                                                        id="buriedChurch" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="ministerOfDeath" class="col-sm-2 control-label">Minister
                                                                of
                                                                Death</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="ministerOfDeath"
                                                                        id="ministerOfDeath" class="form-control"/>
                                                            </div>
                                                            <label for="placeOfCemetery" class="col-sm-2 control-label">Place
                                                                of
                                                                Cemetery</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="placeOfCemetery"
                                                                        id="placeOfCemetery" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="tombNo" class="col-sm-2 control-label">Tomb
                                                                No.</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="tombNo"
                                                                        id="tombNo" class="form-control"/>
                                                            </div>
                                                            <label for="confession" class="col-sm-2 control-label">Confession</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="confession"
                                                                        id="confession" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="communion" class="col-sm-2 control-label">Communion</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="communion"
                                                                        id="communion" class="form-control"/>
                                                            </div>
                                                            <label for="anointingTheSick"
                                                                   class="col-sm-2 control-label">Anointing
                                                                the
                                                                Sick</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="anointingTheSick"
                                                                        id="anointingTheSick" class="form-control"/>
                                                            </div>
                                                        </div>


                                                        <div class="form-group">
                                                            <label for="ministerOfAnointingTheSick"
                                                                   class="col-sm-2 control-label">Minister
                                                                of
                                                                Anointing
                                                                the
                                                                Sick</label>

                                                            <div class="col-sm-4">
                                                                <form:input
                                                                        path="ministerOfAnointingTheSick"
                                                                        id="ministerOfAnointingTheSick"
                                                                        class="form-control"/>
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