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


    <script src="${memberValidator}" type="text/javascript"
            language="javascript"></script>
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
            jQuery('#ui-id-2').bind("click", function () {
                loadMemberGrid();
            });

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

</head>

<body>

<spring:url value="/addmember.action" var="memberActionURL"/>

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
                                            <a href="#memberRegistration" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Member Registration</span></a>
                                        </li>
                                        <li>
                                            <a href="#familyView" data-toggle="tab" id="familyViewer"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Member View</span></a>
                                        </li>
                                    </ul>
                                </h4>
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="memberRegistration">
                                        <form:form modelAttribute="member"
                                                   action="${memberActionURL}" method="post"
                                                   id="memberForm1" cssClass="form-inline">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="panel panel-indigo">
                                                        <div class="panel-heading">
                                                            <h4>Member Details</h4>
                                                        </div>
                                                        <div class="panel-body">
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