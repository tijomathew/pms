<%--  
  User: tijo
  Date: 12/10/14
  Time: 11:01 PM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title id="title">Members</title>

    <%@include file="scriptlibraryTemplate.jsp" %>

    <script src="<c:url value="/resources/js/memberVaildator.js" />" type="text/javascript"
            language="javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            loadSelectBox("${pageContext.request.contextPath}");
        });
    </script>

    <script src="<c:url value="/resources/js/MemberSelectBox.js" />" type="text/javascript"
            language="javascript"></script>


    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#ui-id-2').bind("click", function () {
                loadMemberGrid();
            });

        });

        function loadMemberGrid() {

            jQuery("#memberGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayMemberGrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [10, 20, 30],
                        colNames: ['MemberID', 'Member Name', 'Date of Birth'],
                        colModel: [

                            {name: 'memberID', index: 'memberID', width: 90},
                            {name: 'name', index: 'name', width: 100},
                            {name: 'dob', index: 'dob', width: 100}
                        ],
                        rowNum: 10,
                        pager: '#memberGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Members",
                        autowidth: true,
                        shrinkToFit: true
                    });
            jQuery("#memberGrid").jqGrid('navGrid', '#memberGridPager', {edit: true, add: true, del: true});
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
                    <li><a href="#tabs-1">Member Registration</a></li>
                    <li><a href="#tabs-2">Member View</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <form:form modelAttribute="member"
                               action="${pageContext.request.contextPath}/addMember.action" method="post"
                               id="memberForm1">
                        <div class="sectionLeft">

                            <section class="contentDoc">
                                <h3>Member Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Family :</td>
                                            <td><form:select path="familyId" id="familySelectBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Salutation :</td>
                                            <td><form:select path="memberAsPerson.salutation"
                                                             id="memberAsPerson.salutation">
                                                <form:option value="Mr." selected="selected">Mr.</form:option>
                                                <form:option value="Mrs.">Mrs.</form:option>
                                                <form:option value="Master">Master</form:option>
                                                <form:option value="Miss.">Miss.</form:option>
                                            </form:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>First Name :</td>
                                            <td><form:input path="memberAsPerson.firstName"
                                                            id="memberAsPersonfirstName"/></td>
                                        </tr>
                                        <tr>
                                            <td>Middle Name :</td>
                                            <td><form:input path="memberAsPerson.middleName"
                                                            id="memberAsPersonmiddleName"/></td>
                                        </tr>
                                        <tr>
                                            <td>Last Name :</td>
                                            <td><form:input path="memberAsPerson.lastName"
                                                            id="memberAsPersonlastName"/></td>
                                        </tr>
                                        <tr>
                                            <td>Relationship In Family :</td>
                                            <td><form:select path="relationshipInFamily"
                                                             id="relationshipInFamily">
                                                <form:option value="Head of Family"
                                                             selected="selected">Family Head</form:option>
                                                <form:option value="Spouse">Husband</form:option>
                                                <form:option value="Son">Wife</form:option>
                                                <form:option value="Daughter">Son</form:option>
                                                <form:option value="Cousin">Daughter</form:option>
                                                <form:option value="Relative">Father</form:option>
                                                <form:option value="Friend">Mother</form:option>
                                            </form:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Date of Birth :</td>
                                            <td><form:input path="memberAsPerson.dateOfBirth"
                                                            id="memberAsPersondateOfBirth"/></td>
                                        </tr>
                                        <tr>
                                            <td>Place of Birth :</td>
                                            <td><form:input path="memberAsPerson.placeOfBirth"
                                                            id="memberAsPersonplaceOfBirth"/></td>
                                        </tr>
                                        <tr>
                                            <td>Gender :</td>
                                            <td><form:radiobutton path="memberAsPerson.gender"
                                                                  id="memberAsPersongender" value="Male"/>Male
                                            </td>
                                            <td><form:radiobutton path="memberAsPerson.gender"
                                                                  id="memberAsPersongender" value="Female"/>Female
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Nationality :</td>
                                            <td><form:input path="memberAsPerson.nationality"
                                                            id="memberAsPersonnationality"/></td>
                                        </tr>
                                        <tr>
                                            <td>Education Qualifications :</td>
                                            <td><form:textarea path="memberAsPerson.educationQualifications"
                                                               id="memberAsPersoneducationQualifications"/></td>
                                        </tr>
                                        <tr>
                                            <td>Job Details :</td>
                                            <td><form:textarea path="memberAsPerson.jobDetails"
                                                               id="memberAsPersonjobDetails"/></td>
                                        </tr>
                                        <tr>
                                            <td>Personal Status :</td>
                                            <td><form:select path="memberAsPerson.personalStatus" id="personalStatus">
                                                <form:option value="Single" selected="selected">Single</form:option>
                                                <form:option value="Married">Married</form:option>
                                                <form:option value="Student">Student</form:option>
                                                <form:option value="Divorsed">Divorsed</form:option>
                                                <form:option value="Other">Other</form:option>
                                            </form:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Blood Group :</td>
                                            <td><form:select path="memberAsPerson.bloodGroup" id="bloodGroup">
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
                                            <td><form:input path="memberAsPerson.carNumber" id="carNumber"/></td>
                                        </tr>
                                        <tr>
                                            <td>Life Status :</td>
                                            <td><form:select path="memberAsPerson.lifeStatus" id="lifeStatus">
                                                <form:option value="Live" selected="selected">Live</form:option>
                                                <form:option value="Late">Late</form:option>
                                            </form:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Personal Remarks :</td>
                                            <td><form:textarea path="memberAsPerson.personalRemarks"
                                                               id="personalRemarks"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>

                        <div class="sectionRight">
                            <section class="contentDoc">
                                <h3>Member Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Member ID :</td>
                                            <td><form:input path="memberID" id="memberID"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Pious Association :</td>
                                            <td><form:input path="piousAssociation" id="piousAssociation"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Catechism Qualification :</td>
                                            <td><form:input path="sundayCatechism" id="sundayCatechism"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Sacramental Life Remarks :</td>
                                            <td><form:textarea path="sacramentalLife" id="sacramentalLife"
                                                               class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Church Remarks :</td>
                                            <td><form:textarea path="churchRemarks" id="churchRemarks"
                                                               class="textBox"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>

                        <div class="clear"></div>

                        <div class="sectionLeft">
                            <section class="contentDoc">
                                <h3>Contact Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Email :</td>
                                            <td><form:input path="memberAsPerson.email" id="memberAsPersonemail"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mobile No. :</td>
                                            <td><form:input path="memberAsPerson.mobileNo" id="memberAsPersonmobileNo"
                                                            value=""/></td>
                                        </tr>
                                        <tr>
                                            <td>Land Line No. :</td>
                                            <td><form:input path="memberAsPerson.landLine"
                                                            id="memberAsPersonlandLine"/></td>
                                        </tr>
                                        <tr>
                                            <td>Fax No. :</td>
                                            <td><form:input path="memberAsPerson.faxNo"
                                                            id="memberAsPersonfaxNo"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>
                        <div class="sectionRight">
                            <section class="contentDoc">
                                <h3>Baptism</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Date of Baptism :</td>
                                            <td><form:input path="dateOfBaptism" id="dateOfBaptism"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Place/Church of Baptism :</td>
                                            <td><form:input path="churchOfBaptism" id="churchOfBaptism"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Country of Baptism :</td>
                                            <td><form:input path="countryOfBaptism" id="countryOfBaptism"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Baptism Name :</td>
                                            <td><form:input path="baptismName" id="baptismName"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Minister of Baptism :</td>
                                            <td><form:input path="ministerOfBaptism" id="ministerOfBaptism"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>God Father :</td>
                                            <td><form:input path="baptismGodFather" id="baptismGodFather"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>God Mother :</td>
                                            <td><form:input path="baptismGodMother" id="baptismGodMother"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Patron Saint :</td>
                                            <td><form:input path="patronSaint" id="patronSaint"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Patron Saint Feast Day :</td>
                                            <td><form:input path="patronSaintFeastDay" id="patronSaintFeastDay"
                                                            class="textBox"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>
                        <div class="clear"></div>
                        <div class="sectionLeft">
                            <section class="contentDoc">
                                <h3>Confirmation</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Date of Confirmation :</td>
                                            <td><form:input path="dateOfConfirmation" id="dateOfConfirmation"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Place/Church of Confirmation :</td>
                                            <td><form:input path="churchOfConfirmation" id="churchOfConfirmation"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Country of Confirmation :</td>
                                            <td><form:input path="countryOfConfirmation" id="countryOfConfirmation"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Minister of Confirmation :</td>
                                            <td><form:input path="ministerOfConfirmation" id="ministerOfConfirmation"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>God Father :</td>
                                            <td><form:input path="confirmationGodFather" id="confirmationGodFather"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>God Mother :</td>
                                            <td><form:input path="confirmationGodMother" id="confirmationGodMother"
                                                            class="textBox"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>
                        <div class="sectionRight">
                            <section class="contentDoc">
                                <h3>First Holy Communion</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Date of Holy Communion :</td>
                                            <td><form:input path="dateOfFirstCommunion" id="dateOfFirstCommunion"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Place/Church of Holy Communion :</td>
                                            <td><form:input path="churchOfHolyCommunion" id="churchOfHolyCommunion"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Country of Holy Communion :</td>
                                            <td><form:input path="countryOfHolyCommunion" id="countryOfHolyCommunion"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Minister of Holy Communion :</td>
                                            <td><form:input path="ministerOfHolyCommunion" id="ministerOfHolyCommunion"
                                                            class="textBox"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>
                        <div class="clear"></div>
                        <div class="sectionLeft">
                            <section class="contentDoc">
                                <h3>Betrothal</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Date of Betrothal :</td>
                                            <td><form:input path="dateOfBetrothal" id="dateOfBetrothal"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Place/Church of Betrothal :</td>
                                            <td><form:input path="churchOfBetrothal" id="churchOfBetrothal"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Country of Betrothal :</td>
                                            <td><form:input path="countryOfBetrothal" id="countryOfBetrothal"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Priest/Bishop of Betrothal :</td>
                                            <td><form:input path="priestOfBetrothal" id="priestOfBetrothal"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Spouse Name :</td>
                                            <td><form:input path="spouseName" id="spouseName"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Spouse Baptism Name :</td>
                                            <td><form:input path="spouseBaptismName" id="spouseBaptismName"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Spouse Native Parish :</td>
                                            <td><form:input path="spouseNativeParish" id="spouseNativeParish"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Spouse Native Diocese :</td>
                                            <td><form:input path="spouseNativeDiocese" id="spouseNativeDiocese"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Spouse Father Name :</td>
                                            <td><form:input path="spouseFatherName" id="spouseFatherName"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Spouse Mother Name :</td>
                                            <td><form:input path="spouseMotherName" id="spouseMotherName"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Spouse Native Address :</td>
                                            <td><form:input path="spouseNativeAddress" id="spouseNativeAddress"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Spouse Nationality :</td>
                                            <td><form:input path="spouseNationality" id="spouseNationality"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Betrothal Witness-1 :</td>
                                            <td><form:input path="betrothalWitnessOne" id="betrothalWitnessOne"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Betrothal Witness-2 :</td>
                                            <td><form:input path="betrothalWitnessTwo" id="betrothalWitnessTwo"
                                                            class="textBox"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>
                        <div class="sectionRight">
                            <section class="contentDoc">
                                <h3>Marriage</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Date of Marriage :</td>
                                            <td><form:input path="dateOfMarriage" id="dateOfMarriage"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Place/Church of Marriage :</td>
                                            <td><form:input path="churchOfMarriage" id="churchOfMarriage"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Priest/Bishop of Marriage :</td>
                                            <td><form:input path="priestOfMarriage" id="priestOfMarriage"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Marriage Witness-1 :</td>
                                            <td><form:input path="marriageWitnessOne" id="marriageWitnessOne"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Marriage Witness-2 :</td>
                                            <td><form:input path="marriageWitnessTwo" id="marriageWitnessTwo"
                                                            class="textBox"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>
                        <div class="clear"></div>
                        <div class="sectionLeft">
                            <section class="contentDoc">
                                <h3>Death</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Date of Death :</td>
                                            <td><form:input path="dateOfDeath" id="dateOfDeath" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Place of Death :</td>
                                            <td><form:input path="placeOfDeath" id="placeOfDeath"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Funeral Date :</td>
                                            <td><form:input path="funeralDate" id="funeralDate"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Buried Church :</td>
                                            <td><form:input path="buriedChurch" id="buriedChurch"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Minister of Death :</td>
                                            <td><form:input path="ministerOfDeath" id="ministerOfDeath"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Place of Cemetery :</td>
                                            <td><form:input path="placeOfCemetery" id="placeOfCemetery"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Tomb No. :</td>
                                            <td><form:input path="tombNo" id="tombNo"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Confession :</td>
                                            <td><form:input path="confession" id="confession"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Communion :</td>
                                            <td><form:input path="communion" id="communion"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Anointing the Sick :</td>
                                            <td><form:input path="anointingTheSick" id="anointingTheSick"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Minister of Anointing the Sick :</td>
                                            <td><form:input path="ministerOfAnointingTheSick"
                                                            id="ministerOfAnointingTheSick"
                                                            class="textBox"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>
                        <div class="clear"></div>
                        <p>
                            <input type="submit" value="Add" class="filterbutton"/>
                        </p>
                    </form:form>
                </div>
                <div id="tabs-2" class="contentTabs">
                    <table id="memberGrid"></table>
                    <div id="memberGridPager"></div>
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