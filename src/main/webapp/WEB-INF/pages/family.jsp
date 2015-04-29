<%--  
  User: tijo
  Date: 12/10/14
  Time: 10:39 PM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title id="title">Family</title>

    <%@include file="scriptlibraryTemplate.jsp" %>

    <script src="<c:url value="/resources/js/familyValidator.js" />" type="text/javascript"
            language="javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            loadSelectBox("${pageContext.request.contextPath}");
        });
    </script>

    <script src="<c:url value="/resources/js/FamilySelectBox.js" />" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#ui-id-2').bind("click", function () {
                loadFamilyGrid();
            });

        });

        function loadFamilyGrid() {

            jQuery("#familyGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayFamilyGrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [2, 4, 6],
                        colNames: ['Family ID', 'Family Name','parishInNative','dioceseInNative','dateOfRegistration','parishLocal',"massCenter","prayerUnit","localAddress","nativeAddress"],
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
                        shrinkToFit: true
                    });
            jQuery("#familyGrid").jqGrid('navGrid', '#familyGridPager', {edit: true, add: true, del: true});
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
                    <li><a href="#tabs-1">Family Registration</a></li>
                    <li><a href="#tabs-2">Family View</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <form:form modelAttribute="family"
                               action="${pageContext.request.contextPath}/addFamily.action" method="post"
                               id="familyForm1">
                        <div class="sectionLeft">

                            <section class="contentDoc">
                                <h3>Family Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Family Name :</td>
                                            <td><form:input path="familyName" id="familyName" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Native Parish :</td>
                                            <td><form:input path="parishInNative" id="parishInNative"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Native Diocese :</td>
                                            <td><form:input path="dioceseInNative" id="dioceseInNative"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Date Of Registration :</td>
                                            <td><form:input path="dateOfRegistration" id="dateOfRegistration"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Parish :</td>
                                            <td><form:select path="parishId"
                                                             id="parishSelectBox"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center :</td>
                                            <td><form:select path="massCenterId" id="massCenterSelectBox">
                                                <option value="select">--Please select--</option>
                                            </form:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Ward :</td>
                                            <td><form:select path="prayerUnitId" id="wardSelectBox">
                                                <option value="select">--Please select--</option>
                                            </form:select>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>

                        <div class="sectionRight">
                            <section class="contentDoc">
                                <h3>Local Adderss</h3>

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
                                            <td><form:input path="localAddress.county" id="localAddresscounty"/></td>
                                        </tr>
                                        <tr>
                                            <td>Pin code:</td>
                                            <td><form:input path="localAddress.pin" id="localAddresspin"/></td>
                                        </tr>
                                        <tr>
                                            <td>Country:</td>
                                            <td><form:input path="localAddress.country" id="localAddresscountry"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>

                        <div class="clear"></div>
                        <div class="sectionLeft">
                            <section class="contentDoc">
                                <h3>Native Address</h3>

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
                                            <td><form:input path="nativeAddress.state" id="nativeAddressstate"/></td>
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
                        <div class="clear"></div>
                        <p>
                            <input type="submit" value="Add" class="filterbutton"/>
                        </p>


                    </form:form>
                </div>
                <div id="tabs-2" class="contentTabs">
                    <table id="familyGrid"></table>
                    <div id="familyGridPager"></div>
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