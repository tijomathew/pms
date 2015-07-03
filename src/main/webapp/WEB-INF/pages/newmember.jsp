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

    <spring:url value="/resources/js/membervaildator.js" var="memberValidator"/>
    <spring:url value="/resources/js/memberselectbox.js" var="memberSelectBox"/>
    <spring:url value="/addmember.action" var="memberActionURL"/>


    <%--<script src="${memberValidator}" type="text/javascript"
            language="javascript"></script>--%>
    <script src="${memberSelectBox}" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {

            <c:if test="${showForFamilyUser == false}">
            loadSelectBox("${pageContext.request.contextPath}");
            </c:if>
        });
    </script>


    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#memberViewer').bind("click", function () {

            });
            loadMemberGrid();

        });

        function loadMemberGrid() {
            var subGridData = "";
            jQuery("#memberGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displaymembergrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [2, 4, 6],
                        colNames: ['MemberID', 'Member Name', 'Date of Birth', 'placeOfBirth', 'gender', 'nationality', 'jobDetails', 'personalStatus', 'bloodGroup', 'carNumber', 'liveStatus', 'personalRemarks', 'piousAssociation', 'sundayCatechism', 'sacramentalLife', 'churchRemarks', 'email', 'mobNo', 'landLineNo', 'faxNo', 'dateOfBaptism', 'churchOfBaptism', 'countryOfBaptism', 'baptismName', 'ministerOfBaptism', 'baptismGodFather', 'baptismGodMother', 'patronSaint', 'patronSaintFeastDay', "dateOfConfirmation", "churchOfConfirmation", "countryOfConfirmation", "ministerOfConfirmation", "confirmationGodFather", "confirmationGodMother", "dateOfFirstCommunion", "churchOfHolyCommunion", "countryOfHolyCommunion", "ministerOfHolyCommunion", "dateOfBetrothal", "churchOfBetrothal", "countryOfBetrothal", "priestOfBetrothal", "spouseName", "spouseBaptismName", "spouseNativeParish", "spouseNativeDiocese", "spouseFatherName", "spouseMotherName", "spouseNativeAddress", "spouseNationality", "betrothalWitnessOne", "betrothalWitnessTwo", "dateOfMarriage", "churchOfMarriage", "priestOfMarriage", "marriageWitnessOne", "marriageWitnessTwo", "dateOfDeath", "placeOfDeath", "funeralDate", "buriedChurch", "ministerOfDeath", "placeOfCemetery", "tombNo", "confession", "communion", "anointingTheSick", "ministerOfAnointingTheSick"],
                        colModel: [

                            {name: 'memberID', index: 'memberID', width: 90},
                            {name: 'name', index: 'name', width: 100},
                            {name: 'dob', index: 'dob', width: 100},
                            {name: 'placeOfBirth', index: 'placeOfBirth', width: 100},
                            {name: 'gender', index: 'gender', width: 100},
                            {name: 'nationality', index: 'nationality', width: 100},
                            {name: 'jobDetails', index: 'jobDetails', width: 100},
                            {name: 'personalStatus', index: 'personalStatus', width: 100},
                            {name: 'bloodGroup', index: 'bloodGroup', width: 100},
                            {name: 'carNumber', index: 'carNumber', width: 100},
                            {name: 'liveStatus', index: 'liveStatus', width: 100},
                            {name: 'personalRemarks', index: 'personalRemarks', width: 100},
                            {name: 'piousAssociation', index: 'piousAssociation', width: 100},
                            {name: 'sundayCatechism', index: 'sundayCatechism', width: 100},
                            {name: 'sacramentalLife', index: 'sacramentalLife', width: 100},
                            {name: 'churchRemarks', index: 'churchRemarks', width: 100},
                            {name: 'email', index: 'email', width: 100},
                            {name: 'mobNo', index: 'mobNo', width: 100},
                            {name: 'landLineNo', index: 'landLineNo', width: 100},
                            {name: 'faxNo', index: 'faxNo', width: 100},
                            {name: 'dateOfBaptism', index: 'dateOfBaptism', width: 100},
                            {name: 'churchOfBaptism', index: 'churchOfBaptism', width: 100},
                            {name: 'countryOfBaptism', index: 'countryOfBaptism', width: 100},
                            {name: 'baptismName', index: 'baptismName', width: 100},
                            {name: 'ministerOfBaptism', index: 'ministerOfBaptism', width: 100},
                            {name: 'baptismGodFather', index: 'baptismGodFather', width: 100},
                            {name: 'baptismGodMother', index: 'baptismGodMother', width: 100},
                            {name: 'patronSaint', index: 'patronSaint', width: 100},
                            {name: 'patronSaintFeastDay', index: 'patronSaintFeastDay', width: 100},
                            {name: 'dateOfConfirmation', index: 'dateOfConfirmation', width: 100},
                            {name: 'churchOfConfirmation', index: 'churchOfConfirmation', width: 100},
                            {name: 'countryOfConfirmation', index: 'countryOfConfirmation', width: 100},
                            {name: 'ministerOfConfirmation', index: 'ministerOfConfirmation', width: 100},
                            {name: 'confirmationGodFather', index: 'confirmationGodFather', width: 100},
                            {name: 'confirmationGodMother', index: 'confirmationGodMother', width: 100},
                            {name: 'dateOfFirstCommunion', index: 'dateOfFirstCommunion', width: 90},
                            {name: 'churchOfHolyCommunion', index: 'churchOfHolyCommunion', width: 100},
                            {name: 'countryOfHolyCommunion', index: 'countryOfHolyCommunion', width: 100},
                            {name: 'ministerOfHolyCommunion', index: 'ministerOfHolyCommunion', width: 100},
                            {
                                name: 'dateOfBetrothal',
                                index: 'dateOfBetrothal',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'churchOfBetrothal',
                                index: 'churchOfBetrothal',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'countryOfBetrothal',
                                index: 'countryOfBetrothal',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'priestOfBetrothal',
                                index: 'priestOfBetrothal',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {name: 'spouseName', index: 'spouseName', width: 100, hidedlg: true, hidden: true},
                            {
                                name: 'spouseBaptismName',
                                index: 'spouseBaptismName',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'spouseNativeParish',
                                index: 'spouseNativeParish',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'spouseNativeDiocese',
                                index: 'spouseNativeDiocese',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'spouseFatherName',
                                index: 'spouseFatherName',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'spouseMotherName',
                                index: 'spouseMotherName',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'spouseNativeAddress',
                                index: 'spouseNativeAddress',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'spouseNationality',
                                index: 'spouseNationality',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'betrothalWitnessOne',
                                index: 'betrothalWitnessOne',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'betrothalWitnessTwo',
                                index: 'betrothalWitnessTwo',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {name: 'dateOfMarriage', index: 'dateOfMarriage', width: 100, hidedlg: true, hidden: true},
                            {
                                name: 'churchOfMarriage',
                                index: 'churchOfMarriage',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'priestOfMarriage',
                                index: 'priestOfMarriage',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'marriageWitnessOne',
                                index: 'marriageWitnessOne',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'marriageWitnessTwo',
                                index: 'marriageWitnessTwo',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {name: 'dateOfDeath', index: 'dateOfDeath', width: 100, hidedlg: true, hidden: true},
                            {name: 'placeOfDeath', index: 'placeOfDeath', width: 100, hidedlg: true, hidden: true},
                            {name: 'funeralDate', index: 'funeralDate', width: 100, hidedlg: true, hidden: true},
                            {name: 'buriedChurch', index: 'buriedChurch', width: 100, hidedlg: true, hidden: true},
                            {
                                name: 'ministerOfDeath',
                                index: 'ministerOfDeath',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'placeOfCemetery',
                                index: 'placeOfCemetery',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {name: 'tombNo', index: 'tombNo', width: 100, hidedlg: true, hidden: true},
                            {name: 'confession', index: 'confession', width: 100, hidedlg: true, hidden: true},
                            {name: 'communion', index: 'communion', width: 100, hidedlg: true, hidden: true},
                            {
                                name: 'anointingTheSick',
                                index: 'anointingTheSick',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                            {
                                name: 'ministerOfAnointingTheSick',
                                index: 'ministerOfAnointingTheSick',
                                width: 100,
                                hidedlg: true,
                                hidden: true
                            },
                        ],
                        rowNum: 2,
                        pager: '#memberGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Members",
                        autowidth: true,
                        shrinkToFit: false,
                        subGrid: true,
                        height: 'auto',
                        width: 'auto',
                        subGridRowExpanded: function (subgrid_id, row_id) {
                            var subgrid_table_id = subgrid_id + "_t";
                            $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "'></table>");
                            var subGridData = [$('#memberGrid').jqGrid('getRowData', row_id)];
                            $("#" + subgrid_table_id).jqGrid({
                                datatype: "local",
                                data: subGridData,
                                colNames: ["dateOfBetrothal", "churchOfBetrothal", "countryOfBetrothal", "priestOfBetrothal", "spouseName", "spouseBaptismName", "spouseNativeParish", "spouseNativeDiocese", "spouseFatherName", "spouseMotherName", "spouseNativeAddress", "spouseNationality", "betrothalWitnessOne", "betrothalWitnessTwo", "dateOfMarriage", "churchOfMarriage", "priestOfMarriage", "marriageWitnessOne", "marriageWitnessTwo", "dateOfDeath", "placeOfDeath", "funeralDate", "buriedChurch", "ministerOfDeath", "placeOfCemetery", "tombNo", "confession", "communion", "anointingTheSick", "ministerOfAnointingTheSick"],
                                colModel: [
                                    {name: 'dateOfBetrothal', index: 'dateOfBetrothal', width: 120},
                                    {name: 'churchOfBetrothal', index: 'churchOfBetrothal', width: 120},
                                    {name: 'countryOfBetrothal', index: 'countryOfBetrothal', width: 120},
                                    {name: 'priestOfBetrothal', index: 'priestOfBetrothal', width: 120},
                                    {name: 'spouseName', index: 'spouseName', width: 120},
                                    {name: 'spouseBaptismName', index: 'spouseBaptismName', width: 120},
                                    {name: 'spouseNativeParish', index: 'spouseNativeParish', width: 120},
                                    {name: 'spouseNativeDiocese', index: 'spouseNativeDiocese', width: 120},
                                    {name: 'spouseFatherName', index: 'spouseFatherName', width: 120},
                                    {name: 'spouseMotherName', index: 'spouseMotherName', width: 120},
                                    {name: 'spouseNativeAddress', index: 'spouseNativeAddress', width: 120},
                                    {name: 'spouseNationality', index: 'spouseNationality', width: 120},
                                    {name: 'betrothalWitnessOne', index: 'betrothalWitnessOne', width: 120},
                                    {name: 'betrothalWitnessTwo', index: 'betrothalWitnessTwo', width: 120},
                                    {name: 'dateOfMarriage', index: 'dateOfMarriage', width: 120},
                                    {name: 'churchOfMarriage', index: 'churchOfMarriage', width: 120},
                                    {name: 'priestOfMarriage', index: 'priestOfMarriage', width: 120},
                                    {name: 'marriageWitnessOne', index: 'marriageWitnessOne', width: 120},
                                    {name: 'marriageWitnessTwo', index: 'marriageWitnessTwo', width: 120},
                                    {name: 'dateOfDeath', index: 'dateOfDeath', width: 120},
                                    {name: 'placeOfDeath', index: 'placeOfDeath', width: 120},
                                    {name: 'funeralDate', index: 'funeralDate', width: 120},
                                    {name: 'buriedChurch', index: 'buriedChurch', width: 120},
                                    {name: 'ministerOfDeath', index: 'ministerOfDeath', width: 120},
                                    {name: 'placeOfCemetery', index: 'placeOfCemetery', width: 120},
                                    {name: 'tombNo', index: 'tombNo', width: 120},
                                    {name: 'confession', index: 'confession', width: 120},
                                    {name: 'communion', index: 'communion', width: 120},
                                    {name: 'anointingTheSick', index: 'anointingTheSick', width: 120},
                                    {
                                        name: 'ministerOfAnointingTheSick',
                                        index: 'ministerOfAnointingTheSick',
                                        width: 120
                                    }],
                                rowNum: 1,
                                height: 'auto',
                                shrinkToFit: false,
                                width: 'auto',
                                loadComplete: function () {
                                    jQuery("#" + subgrid_table_id).jqGrid('setGroupHeaders', {
                                        useColSpanStyle: true,
                                        groupHeaders: [
                                            {
                                                startColumnName: 'dateOfBetrothal',
                                                numberOfColumns: 14,
                                                titleText: 'Bethrotal Details'
                                            },
                                            {
                                                startColumnName: 'dateOfMarriage',
                                                numberOfColumns: 5,
                                                titleText: 'Marriage Details'
                                            },
                                            {
                                                startColumnName: 'dateOfDeath',
                                                numberOfColumns: 11,
                                                titleText: 'Death Details'
                                            },
                                        ]
                                    });
                                }
                            });
                        }
                    });

            jQuery("#memberGrid").jqGrid('navGrid', '#memberGridPager', {edit: true, add: true, del: true});

            //to solve groupheader duplicate issue on clicking #tab-2 more than once
            jQuery("#memberGrid").jqGrid('destroyGroupHeader');

            jQuery("#memberGrid").jqGrid('setGroupHeaders', {
                useColSpanStyle: true,
                groupHeaders: [
                    {startColumnName: 'email', numberOfColumns: 4, titleText: 'Contact Details'},
                    {startColumnName: 'dateOfBaptism', numberOfColumns: 9, titleText: 'Baptism Details'},
                    {startColumnName: 'dateOfConfirmation', numberOfColumns: 6, titleText: 'Confirmation Details'},
                    {startColumnName: 'dateOfFirstCommunion', numberOfColumns: 4, titleText: 'HolyCommunion Details'},
                ]
            });
        }


    </script>
    <script type="text/javascript">
        jQuery(document).ready(function () {
            var $form = $('#memberForm1');
            $form.bind('submit', function (e) {
                // Ajax validation
                //var $inputs = $form.find('input');
                //var data = collectFormData();

                /* var $selects=$form.find('select');
                 var selectData=collectFormData($selects);
                 data.push(selectData);*/

                jQuery.post('${memberActionURL}',$form.serializeArray() , function (response) {
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
                        $('#memberForm1')[0].reset();
                    }
                }, 'json');

                e.preventDefault();
                return false;
            });
        });
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
                                    <div class="panel-body">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="tabs-1">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="panel panel-grape">
                                                            <div class="panel-heading">
                                                                <h4>Client Info</h4>
                                                            </div>
                                                            <div class="panel-body">
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


            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue">
                            <div class="panel-heading">
                                <h4>
                                    <ul class="nav nav-tabs">

                                        <li class = "active">
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
                                            <a href="#member5" data-toggle="tab" ><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Baptism</span></a>
                                        </li> <li>
                                        <a href="#member6" data-toggle="tab" ><i
                                                class="fa fa-comments visible-xs icon-scale"></i><span
                                                class="hidden-xs">Confirmation</span></a>
                                    </li> <li>
                                        <a href="#member7" data-toggle="tab" ><i
                                                class="fa fa-comments visible-xs icon-scale"></i><span
                                                class="hidden-xs">First Holy Communion</span></a>
                                    </li> <li>
                                        <a href="#member8" data-toggle="tab" ><i
                                                class="fa fa-comments visible-xs icon-scale"></i><span
                                                class="hidden-xs">Betrothal</span></a>
                                    </li> <li>
                                        <a href="#member9" data-toggle="tab" ><i
                                                class="fa fa-comments visible-xs icon-scale"></i><span
                                                class="hidden-xs">Marriage</span></a>
                                    </li> <li>
                                        <a href="#member10" data-toggle="tab" ><i
                                                class="fa fa-comments visible-xs icon-scale"></i><span
                                                class="hidden-xs">Death</span></a>
                                    </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">

                                <form:form modelAttribute="member"
                                           action="${memberActionURL}" method="post"
                                           id="memberForm1" cssClass="form-inline">

                                <div class="tab-content">

                                    <div class="tab-pane active" id="member2">

                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>Member Details</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <c:if test="${showForFamilyUser == false}">
                                                        <div class="control-group" id="familySelectBox">
                                                            <label class="control-label">Family :</label>

                                                            <form:select path="familyId"
                                                                         id="familySelectBox"/>
                                                                        <span class="help-inline"><form:errors
                                                                                path="familyId"/> </span>

                                                        </div>
                                                    </c:if>
                                                    <c:if test="${showForFamilyUser == true}">
                                                        <div class="control-group" id="familySelectBox">
                                                            <label class="control-label">Family :</label>

                                                            <form:select path="familyId"
                                                                         id="familySelectBox"
                                                                         items="${familyName}"/><span
                                                                class="help-inline"><form:errors
                                                                path="familyId"/> </span>
                                                        </div>
                                                    </c:if>
                                                    <div class="control-group" id="memberAsPerson.salutation">
                                                        <label class="control-label">Salutation :</label>

                                                        <form:select
                                                                path="memberAsPerson.salutation"
                                                                id="memberAsPerson.salutation">
                                                            <form:option value="Mr."
                                                                         selected="selected">Mr.</form:option>
                                                            <form:option value="Mrs.">Mrs.</form:option>
                                                            <form:option value="Master">Master</form:option>
                                                            <form:option value="Miss.">Miss.</form:option>
                                                        </form:select>
                                                                    <span
                                                                            class="help-inline"><form:errors
                                                                            path="memberAsPerson.salutation"/> </span>

                                                    </div>
                                                    <div class="control-group" id="memberAsPersonfirstName">
                                                        <label class="control-label">First Name :</label>

                                                        <form:input
                                                                path="memberAsPerson.firstName"
                                                                id="memberAsPersonfirstName"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.firstName"/> </span>
                                                    </div>
                                                    <div class="control-group" id="memberAsPersonmiddleName">
                                                        <label class="control-label">Middle Name :</label>

                                                        <form:input
                                                                path="memberAsPerson.middleName"
                                                                id="memberAsPersonmiddleName"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.middleName"/> </span>
                                                    </div>
                                                    <div class="control-group" id="memberAsPersonlastName">
                                                        <label class="control-label">Last Name :</label>

                                                        <form:input
                                                                path="memberAsPerson.lastName"
                                                                id="memberAsPersonlastName"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.lastName"/> </span>
                                                    </div>
                                                    <div class="control-group" id="relationshipInFamily">
                                                        <label class="control-label">Relationship In Family
                                                            :</label>


                                                        <form:select path="relationshipInFamily"
                                                                     id="relationshipInFamily">
                                                            <form:option value="Head of Family"
                                                                         selected="selected">Family
                                                                Head</form:option>
                                                            <form:option
                                                                    value="Spouse">Husband</form:option>
                                                            <form:option value="Son">Wife</form:option>
                                                            <form:option value="Daughter">Son</form:option>
                                                            <form:option
                                                                    value="Cousin">Daughter</form:option>
                                                            <form:option
                                                                    value="Relative">Father</form:option>
                                                            <form:option value="Friend">Mother</form:option>
                                                        </form:select><span
                                                            class="help-inline"><form:errors
                                                            path="relationshipInFamily"/></span>

                                                    </div>
                                                    <div class="control-group" id="memberAsPersondateOfBirth">
                                                        <label class="control-label">Date of Birth :</label>

                                                        <form:input
                                                                path="memberAsPerson.dateOfBirth"
                                                                id="memberAsPersondateOfBirth"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.dateOfBirth"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="memberAsPersonplaceOfBirth">
                                                        <label class="control-label">Place of Birth
                                                            :</label>

                                                        <form:input
                                                                path="memberAsPerson.placeOfBirth"
                                                                id="memberAsPersonplaceOfBirth"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.placeOfBirth"/></span>

                                                    </div>
                                                    <div class="control-group"
                                                         id="memberAsPersongender">
                                                        <label class="control-label">Gender
                                                            :</label>
                                                        <div>
                                                            <form:radiobutton
                                                                    path="memberAsPerson.gender"
                                                                    id="memberAsPersongender"
                                                                    value="Male"/>Male
                                                        </div>
                                                        <div><form:radiobutton
                                                                path="memberAsPerson.gender"
                                                                id="memberAsPersongender"
                                                                value="Female"/>Female
                                                        </div>
                                                                <span
                                                                        class="help-inline"><form:errors
                                                                        path="memberAsPerson.gender"/></span>

                                                    </div>
                                                    <div class="control-group"
                                                         id="memberAsPersonnationality">
                                                        <label class="control-label">Nationality
                                                            :</label>


                                                        <form:input
                                                                path="memberAsPerson.nationality"
                                                                id="memberAsPersonnationality"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.nationality"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="memberAsPersoneducationQualifications">
                                                        <label class="control-label">Education
                                                            Qualifications
                                                            :</label>


                                                        <form:textarea
                                                                path="memberAsPerson.educationQualifications"
                                                                id="memberAsPersoneducationQualifications"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.educationQualifications"/></span>

                                                    </div>
                                                    <div class="control-group"
                                                         id="memberAsPersonjobDetails">
                                                        <label class="control-label">Job
                                                            Details
                                                            :</label>

                                                        <form:textarea
                                                                path="memberAsPerson.jobDetails"
                                                                id="memberAsPersonjobDetails"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.jobDetails"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="personalStatus">
                                                        <label class="control-label">Personal
                                                            Status
                                                            :</label>


                                                        <form:select
                                                                path="memberAsPerson.personalStatus"
                                                                id="personalStatus">
                                                            <form:option
                                                                    value="Single"
                                                                    selected="selected">Single</form:option>
                                                            <form:option
                                                                    value="Married">Married</form:option>
                                                            <form:option
                                                                    value="Student">Student</form:option>
                                                            <form:option
                                                                    value="Divorsed">Divorsed</form:option>
                                                            <form:option
                                                                    value="Other">Other</form:option>
                                                        </form:select><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.personalStatus"/></span>

                                                    </div>
                                                    <div class="control-group"
                                                         id="bloodGroup">
                                                        <label class="control-label">Blood
                                                            Group
                                                            :</label>


                                                        <form:select
                                                                path="memberAsPerson.bloodGroup"
                                                                id="bloodGroup">
                                                            <form:option
                                                                    value="select">--select--</form:option>
                                                            <form:option
                                                                    value="O-">O-</form:option>
                                                            <form:option
                                                                    value="O+">O+</form:option>
                                                            <form:option
                                                                    value="A-">A-</form:option>
                                                            <form:option
                                                                    value="A+">A+</form:option>
                                                            <form:option
                                                                    value="B-">B-</form:option>
                                                            <form:option
                                                                    value="B+">B+</form:option>
                                                            <form:option
                                                                    value="AB-">AB-</form:option>
                                                            <form:option
                                                                    value="AB+">AB+</form:option>
                                                        </form:select><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.bloodGroup"/></span>

                                                    </div>
                                                    <div class="control-group"
                                                         id="carNumber">
                                                        <label class="control-label">Car
                                                            Number
                                                            :</label>


                                                        <form:input
                                                                path="memberAsPerson.carNumber"
                                                                id="carNumber"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.carNumber"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="lifeStatus">
                                                        <label class="control-label">Life
                                                            Status
                                                            :</label>


                                                        <form:select
                                                                path="memberAsPerson.lifeStatus"
                                                                id="lifeStatus">
                                                            <form:option
                                                                    value="Live"
                                                                    selected="selected">Live</form:option>
                                                            <form:option
                                                                    value="Late">Late</form:option>
                                                        </form:select>
                                                                        <span
                                                                                class="help-inline"><form:errors
                                                                                path="memberAsPerson.lifeStatus"/></span>

                                                    </div>
                                                    <div class="control-group"
                                                         id="personalRemarks">
                                                        <label class="control-label">Personal
                                                            Remarks
                                                            :</label>


                                                        <form:textarea
                                                                path="memberAsPerson.personalRemarks"
                                                                id="personalRemarks"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.personalRemarks"/></span>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>


                                    <div class="tab-pane" id="member3">

                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        Member
                                                        Details</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group"
                                                         id="memberID">
                                                        <label class="control-label">Member
                                                            ID
                                                            :</label>

                                                        <form:input
                                                                path="memberID"
                                                                id="memberID"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberID"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="piousAssociation">
                                                        <label class="control-label">Pious
                                                            Association
                                                            :</label>


                                                        <form:input
                                                                path="piousAssociation"
                                                                id="piousAssociation"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="piousAssociation"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="sundayCatechism">
                                                        <label class="control-label">Catechism
                                                            Qualification
                                                            :</label>


                                                        <form:input
                                                                path="sundayCatechism"
                                                                id="sundayCatechism"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="sundayCatechism"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="sacramentalLife">
                                                        <label class="control-label">Sacramental
                                                            Life
                                                            Remarks
                                                            :</label>


                                                        <form:textarea
                                                                path="sacramentalLife"
                                                                id="sacramentalLife"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="sacramentalLife"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="churchRemarks">
                                                        <label class="control-label">Church
                                                            Remarks
                                                            :</label>


                                                        <form:textarea
                                                                path="churchRemarks"
                                                                id="churchRemarks"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="churchRemarks"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>


                                    <div class="tab-pane" id="member4">

                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        Contact
                                                        Details</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group"
                                                         id="memberAsPersonemail">
                                                        <label class="control-label">Email
                                                            :</label>


                                                        <form:input
                                                                path="memberAsPerson.email"
                                                                id="memberAsPersonemail"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.email"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="memberAsPersonmobileNo">
                                                        <label class="control-label">Mobile
                                                            No.
                                                            :</label>


                                                        <form:input
                                                                path="memberAsPerson.mobileNo"
                                                                id="memberAsPersonmobileNo"
                                                                value=""/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.mobileNo"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="memberAsPersonlandLine">
                                                        <label class="control-label">Land
                                                            Line
                                                            No.
                                                            :</label>

                                                        <form:input
                                                                path="memberAsPerson.landLine"
                                                                id="memberAsPersonlandLine"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.landLine"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="memberAsPersonfaxNo">
                                                        <label class="control-label">Fax
                                                            No.
                                                            :</label>


                                                        <form:input
                                                                path="memberAsPerson.faxNo"
                                                                id="memberAsPersonfaxNo"/><span
                                                            class="help-inline"><form:errors
                                                            path="memberAsPerson.faxNo"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="tab-pane" id="member5">

                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        Baptism</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group"
                                                         id="dateOfBaptism">
                                                        <label class="control-label">Date
                                                            of
                                                            Baptism
                                                            :</label>


                                                        <form:input
                                                                path="dateOfBaptism"
                                                                id="dateOfBaptism"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="dateOfBaptism"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="churchOfBaptism">
                                                        <label class="control-label">Place/Church
                                                            of
                                                            Baptism
                                                            :</label>

                                                        <form:input
                                                                path="churchOfBaptism"
                                                                id="churchOfBaptism"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="churchOfBaptism"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="countryOfBaptism">
                                                        <label class="control-label">Country
                                                            of
                                                            Baptism
                                                            :</label>

                                                        <form:input
                                                                path="countryOfBaptism"
                                                                id="countryOfBaptism"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="countryOfBaptism"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="baptismName">
                                                        <label class="control-label">Baptism
                                                            Name
                                                            :</label>

                                                        <form:input
                                                                path="baptismName"
                                                                id="baptismName"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="baptismName"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="ministerOfBaptism">
                                                        <label class="control-label">Minister
                                                            of
                                                            Baptism
                                                            :</label>

                                                        <form:input
                                                                path="ministerOfBaptism"
                                                                id="ministerOfBaptism"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="ministerOfBaptism"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="baptismGodFather">
                                                        <label class="control-label">God
                                                            Father
                                                            :</label>

                                                        <form:input
                                                                path="baptismGodFather"
                                                                id="baptismGodFather"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="baptismGodFather"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="baptismGodMother">
                                                        <label class="control-label">God
                                                            Mother
                                                            :</label>

                                                        <form:input
                                                                path="baptismGodMother"
                                                                id="baptismGodMother"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="baptismGodMother"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="patronSaint">
                                                        <label class="control-label">Patron
                                                            Saint
                                                            :</label>

                                                        <form:input
                                                                path="patronSaint"
                                                                id="patronSaint"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="patronSaint"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="patronSaintFeastDay">
                                                        <label class="control-label">Patron
                                                            Saint
                                                            Feast
                                                            Day
                                                            :</label>

                                                        <form:input
                                                                path="patronSaintFeastDay"
                                                                id="patronSaintFeastDay"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="patronSaintFeastDay"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>


                                    <div class="tab-pane" id="member6">
                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        Confirmation</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group"
                                                         id="dateOfConfirmation">
                                                        <label class="control-label">Date
                                                            of
                                                            Confirmation
                                                            :</label>

                                                        <form:input
                                                                path="dateOfConfirmation"
                                                                id="dateOfConfirmation"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="dateOfConfirmation"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="churchOfConfirmation">
                                                        <label class="control-label">Place/Church
                                                            of
                                                            Confirmation
                                                            :</label>

                                                        <form:input
                                                                path="churchOfConfirmation"
                                                                id="churchOfConfirmation"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="churchOfConfirmation"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="countryOfConfirmation">
                                                        <label class="control-label">Country
                                                            of
                                                            Confirmation
                                                            :</label>

                                                        <form:input
                                                                path="countryOfConfirmation"
                                                                id="countryOfConfirmation"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="countryOfConfirmation"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="ministerOfConfirmation">
                                                        <label class="control-label">Minister
                                                            of
                                                            Confirmation
                                                            :</label>

                                                        <form:input
                                                                path="ministerOfConfirmation"
                                                                id="ministerOfConfirmation"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="ministerOfConfirmation"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="confirmationGodFather">
                                                        <label class="control-label">God
                                                            Father
                                                            :</label>

                                                        <form:input
                                                                path="confirmationGodFather"
                                                                id="confirmationGodFather"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="confirmationGodFather"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="confirmationGodMother">
                                                        <label class="control-label">God
                                                            Mother
                                                            :</label>

                                                        <form:input
                                                                path="confirmationGodMother"
                                                                id="confirmationGodMother"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="confirmationGodMother"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>


                                    <div class="tab-pane" id="member7">
                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        First
                                                        Holy
                                                        Communion</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group"
                                                         id="dateOfFirstCommunion">
                                                        <label class="control-label">Date
                                                            of
                                                            Holy
                                                            Communion
                                                            :</label>

                                                        <form:input
                                                                path="dateOfFirstCommunion"
                                                                id="dateOfFirstCommunion"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="dateOfFirstCommunion"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="churchOfHolyCommunion">
                                                        <label class="control-label">Place/Church
                                                            of
                                                            Holy
                                                            Communion
                                                            :</label>

                                                        <form:input
                                                                path="churchOfHolyCommunion"
                                                                id="churchOfHolyCommunion"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="churchOfHolyCommunion"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="countryOfHolyCommunion">
                                                        <label class="control-label">Country
                                                            of
                                                            Holy
                                                            Communion
                                                            :</label>

                                                        <form:input
                                                                path="countryOfHolyCommunion"
                                                                id="countryOfHolyCommunion"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="countryOfHolyCommunion"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="ministerOfHolyCommunion">
                                                        <label class="control-label">Minister
                                                            of
                                                            Holy
                                                            Communion
                                                            :</label>

                                                        <form:input
                                                                path="ministerOfHolyCommunion"
                                                                id="ministerOfHolyCommunion"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="ministerOfHolyCommunion"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="tab-pane" id="member8">
                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        Betrothal</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group"
                                                         id="dateOfBetrothal">
                                                        <label class="control-label">Date
                                                            of
                                                            Betrothal
                                                            :</label>

                                                        <form:input
                                                                path="dateOfBetrothal"
                                                                id="dateOfBetrothal"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="dateOfBetrothal"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="churchOfBetrothal">
                                                        <label class="control-label">Place/Church
                                                            of
                                                            Betrothal
                                                            :</label>

                                                        <form:input
                                                                path="churchOfBetrothal"
                                                                id="churchOfBetrothal"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="churchOfBetrothal"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="countryOfBetrothal">
                                                        <label class="control-label">Country
                                                            of
                                                            Betrothal
                                                            :</label>

                                                        <form:input
                                                                path="countryOfBetrothal"
                                                                id="countryOfBetrothal"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="countryOfBetrothal"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="priestOfBetrothal">
                                                        <label class="control-label">Priest/Bishop
                                                            of
                                                            Betrothal
                                                            :</label>

                                                        <form:input
                                                                path="priestOfBetrothal"
                                                                id="priestOfBetrothal"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="priestOfBetrothal"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="spouseName">
                                                        <label class="control-label">Spouse
                                                            Name
                                                            :</label>

                                                        <form:input
                                                                path="spouseName"
                                                                id="spouseName"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="spouseName"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="spouseBaptismName">
                                                        <label class="control-label">Spouse
                                                            Baptism
                                                            Name
                                                            :</label>

                                                        <form:input
                                                                path="spouseBaptismName"
                                                                id="spouseBaptismName"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="spouseBaptismName"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="spouseNativeParish">
                                                        <label class="control-label">Spouse
                                                            Native
                                                            Parish
                                                            :</label>

                                                        <form:input
                                                                path="spouseNativeParish"
                                                                id="spouseNativeParish"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="spouseNativeParish"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="spouseNativeDiocese">
                                                        <label class="control-label">Spouse
                                                            Native
                                                            Diocese
                                                            :</label>

                                                        <form:input
                                                                path="spouseNativeDiocese"
                                                                id="spouseNativeDiocese"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="spouseNativeDiocese"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="spouseFatherName">
                                                        <label class="control-label">Spouse
                                                            Father
                                                            Name
                                                            :</label>

                                                        <form:input
                                                                path="spouseFatherName"
                                                                id="spouseFatherName"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="spouseFatherName"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="spouseMotherName">
                                                        <label class="control-label">Spouse
                                                            Mother
                                                            Name
                                                            :</label>

                                                        <form:input
                                                                path="spouseMotherName"
                                                                id="spouseMotherName"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="spouseMotherName"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="spouseNativeAddress">
                                                        <label class="control-label">Spouse
                                                            Native
                                                            Address
                                                            :</label>

                                                        <form:input
                                                                path="spouseNativeAddress"
                                                                id="spouseNativeAddress"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="spouseNativeAddress"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="spouseNationality">
                                                        <label class="control-label">Spouse
                                                            Nationality
                                                            :</label>

                                                        <form:input
                                                                path="spouseNationality"
                                                                id="spouseNationality"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="spouseNationality"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="betrothalWitnessOne">
                                                        <label class="control-label">Betrothal
                                                            Witness-1
                                                            :</label>

                                                        <form:input
                                                                path="betrothalWitnessOne"
                                                                id="betrothalWitnessOne"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="betrothalWitnessOne"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="betrothalWitnessTwo">
                                                        <label class="control-label">Betrothal
                                                            Witness-2
                                                            :</label>

                                                        <form:input
                                                                path="betrothalWitnessTwo"
                                                                id="betrothalWitnessTwo"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="betrothalWitnessTwo"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="tab-pane" id="member9">
                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        Marriage</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group"
                                                         id="dateOfMarriage">
                                                        <label class="control-label">Date
                                                            of
                                                            Marriage
                                                            :</label>

                                                        <form:input
                                                                path="dateOfMarriage"
                                                                id="dateOfMarriage"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="dateOfMarriage"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="churchOfMarriage">
                                                        <label class="control-label">Place/Church
                                                            of
                                                            Marriage
                                                            :</label>
                                                        <form:input
                                                                path="churchOfMarriage"
                                                                id="churchOfMarriage"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="churchOfMarriage"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="priestOfMarriage">
                                                        <label class="control-label">Priest/Bishop
                                                            of
                                                            Marriage
                                                            :</label>

                                                        <form:input
                                                                path="priestOfMarriage"
                                                                id="priestOfMarriage"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="priestOfMarriage"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="marriageWitnessOne">
                                                        <label class="control-label">Marriage
                                                            Witness-1
                                                            :</label>

                                                        <form:input
                                                                path="marriageWitnessOne"
                                                                id="marriageWitnessOne"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="marriageWitnessOne"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="marriageWitnessTwo">
                                                        <label class="control-label">Marriage
                                                            Witness-2
                                                            :</label>

                                                        <form:input
                                                                path="marriageWitnessTwo"
                                                                id="marriageWitnessTwo"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="marriageWitnessTwo"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="tab-pane" id="member10">
                                        <div class="col-md-12">
                                            <div class="panel panel-grape">
                                                <div class="panel-heading">
                                                    <h4>
                                                        Death</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div class="control-group"
                                                         id="dateOfDeath">
                                                        <label class="control-label">Date
                                                            of
                                                            Death
                                                            :</label>

                                                        <form:input
                                                                path="dateOfDeath"
                                                                id="dateOfDeath"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="dateOfDeath"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="placeOfDeath">
                                                        <label class="control-label">Place
                                                            of
                                                            Death
                                                            :</label>

                                                        <form:input
                                                                path="placeOfDeath"
                                                                id="placeOfDeath"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="placeOfDeath"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="funeralDate">
                                                        <label class="control-label">Funeral
                                                            Date
                                                            :</label>

                                                        <form:input
                                                                path="funeralDate"
                                                                id="funeralDate"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="funeralDate"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="buriedChurch">
                                                        <label class="control-label">Buried
                                                            Church
                                                            :</label>

                                                        <form:input
                                                                path="buriedChurch"
                                                                id="buriedChurch"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="buriedChurch"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="ministerOfDeath">
                                                        <label class="control-label">Minister
                                                            of
                                                            Death
                                                            :</label>

                                                        <form:input
                                                                path="ministerOfDeath"
                                                                id="ministerOfDeath"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="ministerOfDeath"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="placeOfCemetery">
                                                        <label class="control-label">Place
                                                            of
                                                            Cemetery
                                                            :</label>

                                                        <form:input
                                                                path="placeOfCemetery"
                                                                id="placeOfCemetery"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="placeOfCemetery"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="tombNo">
                                                        <label class="control-label">Tomb
                                                            No.
                                                            :</label>

                                                        <form:input
                                                                path="tombNo"
                                                                id="tombNo"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="tombNo"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="confession">
                                                        <label class="control-label">Confession
                                                            :</label>

                                                        <form:input
                                                                path="confession"
                                                                id="confession"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="confession"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="communion">
                                                        <label class="control-label">Communion
                                                            :</label>

                                                        <form:input
                                                                path="communion"
                                                                id="communion"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="communion"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="anointingTheSick">
                                                        <label class="control-label">Anointing
                                                            the
                                                            Sick
                                                            :</label>

                                                        <form:input
                                                                path="anointingTheSick"
                                                                id="anointingTheSick"
                                                                class="textBox"/><span
                                                            class="help-inline"><form:errors
                                                            path="anointingTheSick"/></span>
                                                    </div>
                                                    <div class="control-group"
                                                         id="ministerOfAnointingTheSick">
                                                        <label class="control-label">Minister
                                                            of
                                                            Anointing
                                                            the
                                                            Sick
                                                            :</label>

                                                        <div class="controls">
                                                            <form:input
                                                                    path="ministerOfAnointingTheSick"
                                                                    id="ministerOfAnointingTheSick"
                                                                    class="textBox"/><span
                                                                class="help-inline"><form:errors
                                                                path="ministerOfAnointingTheSick"/> </span>
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