<%--  
  User: tijo
  Date: 5/10/14
  Time: 11:49 AM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title id="title">Mass Center</title>

    <%@include file="scriptlibraryTemplate.jsp" %>

    <script src="<c:url value="/resources/js/massCenterValidator.js" />" type="text/javascript"
            language="javascript"></script>

    <script src="<c:url value="/resources/js/PriestDesignationDisplay.js" />" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#ui-id-2').bind("click", function () {
                loadMassCenterGrid();
            });
            jQuery('#parishSelectBox').change(function () {
                loadPriestDesignationBoxes('${pageContext.request.contextPath}');
            });

        });

        function loadMassCenterGrid() {

            jQuery("#massCenterGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayMassCenterGrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [10, 20, 30],
                        colNames: ['Mass Center ID', 'Mass CenterName'],
                        colModel: [

                            {name: 'massCenterID', index: 'massCenterID', width: 90},
                            {name: 'massCenterName', index: 'massCenterName', width: 100}
                        ],
                        rowNum: 10,
                        pager: '#massCenterGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Mass Centers",
                        autowidth: true,
                        shrinkToFit: true
                    });
            jQuery("#massCenterGrid").jqGrid('navGrid', '#massCenterGridPager', {edit: true, add: true, del: true});
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
                    <li><a href="#tabs-1">Mass Center Registration</a></li>
                    <li><a href="#tabs-2">Mass Center View</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <form:form modelAttribute="massCenter"
                               action="${pageContext.request.contextPath}/addmasscenter.action" method="post"
                               id="massCenterForm1">
                        <div class="sectionLeft">

                            <section class="contentDoc ">
                                <h3>Mass Center Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Mass Center Name :</td>
                                            <td><form:input path="name" id="name"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center Code :</td>
                                            <td><form:input path="centerCode" id="centerCode"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center ID :</td>
                                            <td><form:input path="massCenterID" id="massCenterID" readonly="true"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center Place :</td>
                                            <td><form:input path="place" id="place"/></td>
                                        </tr>
                                        <tr>
                                            <td>Patron Name :</td>
                                            <td><form:input path="patronName" id="patronName"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center Land Line No. :</td>
                                            <td><form:input path="landLineNo" id="landLineNo"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center Mobile No. :</td>
                                            <td><form:input path="mobileNo" id="mobileNo"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center Email :</td>
                                            <td><form:input path="email" id="email"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center Fax No. :</td>
                                            <td><form:input path="faxNo" id="faxNo"/></td>
                                        </tr>
                                        <tr>
                                            <td>Facebook Page :</td>
                                            <td><form:input path="facebookPage" id="facebookPage"/></td>
                                        </tr>
                                        <tr>
                                            <td>Registered Date :</td>
                                            <td><form:input path="registeredDate" id="registeredDate"/></td>
                                        </tr>
                                        <tr>
                                            <td>Driving Route :</td>
                                            <td><form:input path="drivingRoute" id="drivingRoute"/></td>
                                        </tr>
                                        <tr>
                                            <td>Map :</td>
                                            <td><form:input path="map" id="map"/></td>
                                        </tr>
                                        <tr>
                                            <td>Parish:</td>
                                            <td><form:select path="parish" items="${parishList}"
                                                             id="parishSelectBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Priest :</td>
                                            <td id="priestSelectBox">
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>

                        <div class="sectionRight">
                            <section class="contentDoc">
                                <h3>Local Address</h3>

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
                    <table id="massCenterGrid"></table>
                    <div id="massCenterGridPager"></div>
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
