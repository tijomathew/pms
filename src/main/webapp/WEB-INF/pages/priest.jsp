<%--  
  User: tijo
  Date: 12/10/14
  Time: 5:46 PM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title id="title">Priest</title>

    <%@include file="scriptlibraryTemplate.jsp" %>

    <script src="<c:url value="/resources/js/priestValidator.js" />" type="text/javascript"
            language="javascript"></script>
    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#ui-id-2').bind("click", function () {
                loadPriestGrid();
            });
            $("#priestUnitAccordion").accordion();
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

</head>
<body>

<%@include file="toolbarTemplate.jsp" %>

<%@include file="menupanelTemplate.jsp" %>

<div class="outer-center">
    <div class="middle-center">
        <div class="inner-center">

            <div id="tabs" class="contentTabs">
                <ul class="tabHead">
                    <li><a href="#tabs-1">Priest Registration</a></li>
                    <li><a href="#tabs-2">Priest View</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <form:form modelAttribute="priest"
                               action="${pageContext.request.contextPath}/addpriest.action" method="post"
                               id="priestForm1">

                        <div id="priestUnitAccordion">
                            <h3>Personal Details</h3>

                            <div>
                                <section class="contentDoc ">
                                    <div class="mainConte">
                                        <table>
                                            <tr>
                                                <td>Priest Status :</td>
                                                <td><form:select path="priestStatus" id="priestStatus">
                                                    <form:option value="Active" selected="selected">Active</form:option>
                                                    <form:option value="Transferred">Transferred</form:option>
                                                    <form:option value="Not-Active">Not-Active</form:option>
                                                </form:select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Priest ID:</td>
                                                <td><form:input path="priestID"
                                                                id="priestID" readonly="true"
                                                                placeholder="auto generated value"/></td>
                                            </tr>
                                            <tr>
                                                <td>Salutation :</td>
                                                <td><form:select path="priestAsPerson.salutation"
                                                                 id="priestAsPerson.salutation">
                                                    <form:option value="Rev." selected="selected">Rev.</form:option>
                                                    <form:option value="Rev. Dr.">Rev. Dr.</form:option>
                                                </form:select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>First Name :</td>
                                                <td><form:input path="priestAsPerson.firstName"
                                                                id="priestAsPersonfirstName"/></td>
                                            </tr>
                                            <tr>
                                                <td>Middle Name :</td>
                                                <td><form:input path="priestAsPerson.middleName"
                                                                id="priestAsPersonmiddleName"/></td>
                                            </tr>
                                            <tr>
                                                <td>Last Name :</td>
                                                <td><form:input path="priestAsPerson.lastName"
                                                                id="priestAsPersonlastName"/></td>
                                            </tr>
                                            <tr>
                                                <td>Date of Birth :</td>
                                                <td><form:input path="priestAsPerson.dateOfBirth"
                                                                id="priestAsPersondateOfBirth"/></td>
                                            </tr>
                                            <tr>
                                                <td>Place of Birth :</td>
                                                <td><form:input path="priestAsPerson.placeOfBirth"
                                                                id="priestAsPersonplaceOfBirth"/></td>
                                            </tr>
                                            <tr>
                                                <td>Gender :</td>
                                                <td><form:radiobutton path="priestAsPerson.gender"
                                                                      id="priestAsPersongender" value="Male"/>Male
                                                </td>
                                                <td><form:radiobutton path="priestAsPerson.gender"
                                                                      id="priestAsPersongender" value="Female"/>Female
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Nationality :</td>
                                                <td><form:input path="priestAsPerson.nationality"
                                                                id="priestAsPersonnationality"/></td>
                                            </tr>
                                            <tr>
                                                <td>Designation:</td>
                                                <td><form:select path="designation"
                                                                 items="${priestDesignation}"></form:select></td>
                                            </tr>
                                            <tr>
                                                <td>Parish:</td>
                                                <td><form:select path="parishId"
                                                                 items="${parishList}"></form:select></td>
                                            </tr>
                                            <tr>
                                                <td>Education Qualifications :</td>
                                                <td><form:textarea path="priestAsPerson.educationQualifications"
                                                                   id="priestAsPersoneducationQualifications"/></td>
                                            </tr>
                                            <tr>
                                                <td>Job Details :</td>
                                                <td><form:textarea path="priestAsPerson.jobDetails"
                                                                   id="priestAsPersonjobDetails"/></td>
                                            </tr>
                                            <tr>
                                                <td>Personal Status :</td>
                                                <td><form:select path="priestAsPerson.personalStatus"
                                                                 id="personalStatus">
                                                    <form:option value="Priest" selected="selected">Priest</form:option>
                                                </form:select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Blood Group :</td>
                                                <td><form:select path="priestAsPerson.bloodGroup" id="bloodGroup">
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
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Car Number :</td>
                                                <td><form:input path="priestAsPerson.carNumber" id="carNumber"/></td>
                                            </tr>
                                            <tr>
                                                <td>Life Status :</td>
                                                <td><form:select path="priestAsPerson.lifeStatus" id="lifeStatus">
                                                    <form:option value="Live" selected="selected">Live</form:option>
                                                    <form:option value="Late">Late</form:option>
                                                </form:select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Personal Remarks :</td>
                                                <td><form:textarea path="priestAsPerson.personalRemarks"
                                                                   id="personalRemarks"/></td>
                                            </tr>
                                            <tr>
                                                <td>Date of Ordination :</td>
                                                <td><form:input path="dateOfOrdination" id="dateOfOrdination"/></td>
                                            </tr>
                                            <tr>
                                                <td>Feast Day :</td>
                                                <td><form:input path="feastDay" id="feastDay"/></td>
                                            </tr>
                                            <tr>
                                                <td>Heavenly Patron :</td>
                                                <td><form:input path="heavenlyPatron" id="heavenlyPatron"/></td>
                                            </tr>
                                            <tr>
                                                <td>Native Diocese :</td>
                                                <td><form:input path="nativeDiocese" id="nativeDiocese"/></td>
                                            </tr>
                                            <tr>
                                                <td>Congregation :</td>
                                                <td><form:input path="congregation" id="congregation"/></td>
                                            </tr>
                                            <tr>
                                                <td>Native Parish :</td>
                                                <td><form:input path="nativeParish" id="nativeParish"/></td>
                                            </tr>
                                            <tr>
                                                <td>Native Place :</td>
                                                <td><form:input path="nativePlace" id="nativePlace"/></td>
                                            </tr>
                                            <tr>
                                                <td>Card Validity :</td>
                                                <td><form:input path="priestCardValidity" id="priestCardValidity"/></td>
                                            </tr>
                                            <tr>
                                                <td>Ordained To Diocese :</td>
                                                <td><form:input path="ordainedToDiocese" id="ordainedToDiocese"/></td>
                                            </tr>
                                            <tr>
                                                <td>Father's Name :</td>
                                                <td><form:input path="fatherName" id="fatherName"/></td>
                                            </tr>
                                            <tr>
                                                <td>Mother's Name :</td>
                                                <td><form:input path="motherName" id="motherName"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </section>
                            </div>
                            <h3>Contact Details</h3>

                            <div>
                                <section class="contentDoc ">
                                    <div class="mainConte">
                                        <table>
                                            <tr>
                                                <td>Email :</td>
                                                <td><form:input path="priestAsPerson.email"
                                                                id="priestAsPersonemail"/></td>
                                            </tr>
                                            <tr>
                                                <td>Mobile No. :</td>
                                                <td><form:input path="priestAsPerson.mobileNo"
                                                                id="priestAsPersonmobileNo"
                                                                value=""/></td>
                                            </tr>
                                            <tr>
                                                <td>Land Line No. :</td>
                                                <td><form:input path="priestAsPerson.landLine"
                                                                id="priestAsPersonlandLine"/></td>
                                            </tr>
                                            <tr>
                                                <td>Fax No. :</td>
                                                <td><form:input path="priestAsPerson.faxNo"
                                                                id="priestAsPersonfaxNo"/></td>
                                            </tr>
                                            <tr>
                                                <td>Name :</td>
                                                <td><form:input path="emergencyContact.name"
                                                                id="emergencyContactname"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 1 :</td>
                                                <td><form:input path="emergencyContact.addressLineOne"
                                                                id="emergencyContactaddressLineOne"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 2 :</td>
                                                <td><form:input path="emergencyContact.addressLineTwo"
                                                                id="emergencyContactaddressLineTwo"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 3 :</td>
                                                <td><form:input path="emergencyContact.addressLineThree"
                                                                id="emergencyContactaddressLineThree"/></td>
                                            </tr>
                                            <tr>
                                                <td>Email :</td>
                                                <td><form:input path="emergencyContact.email"
                                                                id="emergencyContactemail"/></td>
                                            </tr>
                                            <tr>
                                                <td>Mobile No. :</td>
                                                <td><form:input path="emergencyContact.mobileNo"
                                                                id="emergencyContactmobileNo"
                                                                value=""/></td>
                                            </tr>
                                            <tr>
                                                <td>Land Line No. :</td>
                                                <td><form:input path="emergencyContact.landLineNo"
                                                                id="emergencyContactlandLineNo"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </section>
                            </div>
                            <h3>Local Address</h3>

                            <div>
                                <section class="contentDoc ">
                                    <div class="mainConte">
                                        <table>
                                            <tr>
                                                <td>Address Line 1 :</td>
                                                <td><form:input path="localAddress.addressLineOne"
                                                                id="localAddressaddressLineOne"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 2 :</td>
                                                <td><form:input path="localAddress.addressLineTwo"
                                                                id="localAddressaddressLineTwo"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 3 :</td>
                                                <td><form:input path="localAddress.addressLineThree"
                                                                id="localAddressaddressLineThree"/></td>
                                            </tr>
                                            <tr>
                                                <td>Town:</td>
                                                <td><form:input path="localAddress.town" id="localAddresstown"/></td>
                                            </tr>
                                            <tr>
                                                <td>County:</td>
                                                <td><form:input path="localAddress.county"
                                                                id="localAddresscounty"/></td>
                                            </tr>
                                            <tr>
                                                <td>Pin code:</td>
                                                <td><form:input path="localAddress.pin" id="localAddresspin"/></td>
                                            </tr>
                                            <tr>
                                                <td>Country:</td>
                                                <td><form:input path="localAddress.country"
                                                                id="localAddresscountry"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </section>
                            </div>
                            <h3>Native Address</h3>

                            <div>
                                <section class="contentDoc ">
                                    <div class="mainConte">
                                        <table>
                                            <tr>
                                                <td>Address Line 1 :</td>
                                                <td><form:input path="nativeAddress.addressLineOne"
                                                                id="nativeAddressaddressLineOne"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 2 :</td>
                                                <td><form:input path="nativeAddress.addressLineTwo"
                                                                id="nativeAddressaddressLineTwo"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 3 :</td>
                                                <td><form:input path="nativeAddress.addressLineThree"
                                                                id="nativeAddressaddressLineThree"/></td>
                                            </tr>
                                            <tr>
                                                <td>Post Office :</td>
                                                <td><form:input path="nativeAddress.postOffice"
                                                                id="nativeAddresspostOffice"/></td>
                                            </tr>
                                            <tr>
                                                <td>District :</td>
                                                <td><form:input path="nativeAddress.district"
                                                                id="nativeAddressdistrict"/></td>
                                            </tr>
                                            <tr>
                                                <td>State :</td>
                                                <td><form:input path="nativeAddress.state"
                                                                id="nativeAddressstate"/></td>
                                            </tr>
                                            <tr>
                                                <td>Country :</td>
                                                <td><form:input path="nativeAddress.country"
                                                                id="nativeAddresscountry"/></td>
                                            </tr>
                                            <tr>
                                                <td>Pin code :</td>
                                                <td><form:input path="nativeAddress.pin" id="nativeAddresspin"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </section>
                            </div>
                        </div>
                        <p>
                            <input type="submit" value="Add" class="filterbutton"/>
                        </p>


                    </form:form>
                </div>
                <div id="tabs-2" class="contentTabs">
                    <table id="priestGrid"></table>
                    <div id="priestGridPager"></div>
                </div>

            </div>
        </div>
        <div class="footer ui-layout-south">
            <h3>SEARCH PANEL</h3>

            <div class="filter_left">
            </div>
            <div class="filter_left bottomElementsPanel">
                <table>
                    <tr>
                        <td>
                            <button type="button" class="filterButton">Find</button>
                        </td>
                        <%--<td>
                            Test
                        </td>
                        <td>
                            <select>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                            </select>
                        </td>
                        <td>
                            Test
                        </td>
                        <td>
                            <input type="text"/>
                        </td>--%>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="filterButton">Print</button>
                        </td>
                        <%-- <td>
                             Test
                         </td>
                         <td>
                             <select>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                             </select>
                         </td>
                         <td>
                             Test
                         </td>
                         <td>
                             <input type="text"/>
                         </td>--%>
                    </tr>
                </table>
            </div>
            <div class="bottomRight">

            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

</body>
</html>
