<%--  
  User: tijo
  Date: 5/10/14
  Time: 11:49 AM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title id="title">Ward</title>

    <%@include file="scriptlibraryTemplate.jsp" %>

    <script src="<c:url value="/resources/js/wardValidator.js" />" type="text/javascript"
            language="javascript"></script>
    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#ui-id-2').bind("click", function () {
                loadWardGrid();
            });

        });

        function loadWardGrid() {

            jQuery("#wardGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayWardGrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [2, 4, 6],
                        colNames: ['PrayerUnit ID','PrayerUnit Code' ,'PrayerUnit Name','PrayerUnit Place','MassCenter Name','Local address'],
                        colModel: [

                            {name: 'wardID', index: 'wardID', width: 90},
                            {name: 'wardCode', index: 'wardCode', width: 90},
                            {name: 'wardName', index: 'wardName', width: 100},
                            {name: 'wardPlace', index: 'wardPlace', width: 100},
                            {name: 'massCenterName', index: 'massCenterName', width: 100},
                            {name: 'localAddress', index: 'localAddress', width: 100},

                        ],
                        rowNum: 2,
                        pager: '#wardGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Wards",
                        autowidth: true,
                        shrinkToFit: true
                    });
            jQuery("#wardGrid").jqGrid('navGrid', '#wardGridPager', {edit: true, add: true, del: true});
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
                    <li><a href="#tabs-1">Prayer Unit Registration</a></li>
                    <li><a href="#tabs-2">Prayer Unit View</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <form:form modelAttribute="prayerUnit"
                               action="${pageContext.request.contextPath}/addprayerunit.action" method="post"
                               id="addWardForm">
                        <div class="sectionLeft">

                            <section class="contentDoc ">
                                <h3>Prayer Unit Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Prayer Unit Name :</td>
                                            <td><form:input path="prayerUnitName" id="prayerUnitName"/></td>
                                        </tr>
                                        <tr>
                                            <td>Prayer Unit Code :</td>
                                            <td><form:input path="prayerUnitCode" id="prayerUnitCode"/></td>
                                        </tr>
                                        <tr>
                                            <td>Prayer Unit Place :</td>
                                            <td><form:input path="prayerUnitPlace" id="prayerUnitPlace"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center:</td>
                                            <td><form:select path="massCenterId" items="${massCenterMap}"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>

                        <div class="sectionRight">
                            <section class="contentDoc">
                                <h3>Ireland Address</h3>

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
                        <p>
                            <input type="submit" value="Add" class="filterbutton"/>
                        </p>


                    </form:form>
                </div>
                <div id="tabs-2" class="contentTabs">
                    <table id="wardGrid"></table>
                    <div id="wardGridPager"></div>
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
