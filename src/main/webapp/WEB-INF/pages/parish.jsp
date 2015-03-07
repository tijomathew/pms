<%--  
  User: tijo
  Date: 12/10/14
  Time: 11:01 PM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title id="title">Parish</title>

    <%@include file="scriptlibraryTemplate.jsp" %>

    <script src="<c:url value="/resources/js/parishValidator.js" />" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            setTimeout(loadPriestWithDesignation("${pageContext.request.contextPath}"), 500000);
            //loadPriestWithDesignation("${pageContext.request.contextPath}");
        });
    </script>

    <script src="<c:url value="/resources/js/PriestDesignationDisplay.js" />" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#ui-id-2').bind("click", function () {
                loadParishGrid();
            });

        });

        function loadParishGrid() {

            jQuery("#parishGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayParishGrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [2, 4, 6],
                        colNames: ['Parish ID', 'Parish Name', 'Rite Name', 'Arch Diocese Name', 'Diocese Name', 'Forane Name',"parishFacebookPage","parishWebsite","parishCode","parishPlace","parishDrivingRoute","parishMap","registeredDate","mobileNo","parishEmail","parishLandLineNo","parishFaxNo"],
                        colModel: [

                            {name: 'parishID', index: 'parishID', width: 90},
                            {name: 'parishName', index: 'parishName', width: 100},
                            {name: 'riteName', index: 'riteName', width: 80, align: "right"},
                            {name: 'archDioceseName', index: 'archDioceseName', width: 80, align: "right"},
                            {name: 'dioceseName', index: 'dioceseName', width: 80, align: "right"},
                            {name: 'foraneName', index: 'foraneName', width: 80, align: "right"},
                            {name: 'parishFacebookPage', index: 'parishFacebookPage', width: 80, align: "right"},
                            {name: 'parishWebsite', index: 'parishWebsite', width: 80, align: "right"},
                            {name: 'parishCode', index: 'parishCode', width: 80, align: "right"},
                            {name: 'parishPlace', index: 'parishPlace', width: 80, align: "right"},
                            {name: 'parishDrivingRoute', index: 'parishDrivingRoute', width: 80, align: "right"},
                            {name: 'parishMap', index: 'parishMap', width: 80, align: "right"},
                            {name: 'registeredDate', index: 'registeredDate', width: 80, align: "right"},
                            {name: 'mobileNo', index: 'mobileNo', width: 80, align: "right"},
                            {name: 'parishEmail', index: 'parishEmail', width: 80, align: "right"},
                            {name: 'parishLandLineNo', index: 'parishLandLineNo', width: 80, align: "right"},
                            {name: 'parishFaxNo', index: 'parishFaxNo', width: 80, align: "right"}
                        ],
                        rowNum: 2,
                        pager: '#parishGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Parishes",
                        autowidth: true,
                        shrinkToFit: false
                    });
            jQuery("#parishGrid").jqGrid('navGrid', '#parishGridPager', {edit: true, add: true, del: true});
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
                    <li><a href="#tabs-1">Parish Registration</a></li>
                    <li><a href="#tabs-2">Parish View</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <form:form modelAttribute="parish"
                               action="${pageContext.request.contextPath}/addParish.action" method="post"
                               id="parishForm1">
                        <div class="sectionLeft">

                            <section class="contentDoc">
                                <h3>Parish Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Church Name :</td>
                                            <td><form:input path="churchName" id="churchName" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Rite Name :</td>
                                            <td><form:input path="riteName" id="riteName" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Diocese Name :</td>
                                            <td><form:input path="dioceseName" id="dioceseName"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Forane Name :</td>
                                            <td><form:input path="foraneName" id="foraneName" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Parish ID :</td>
                                            <td><form:input path="parishID" id="parishID" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Parish Name :</td>
                                            <td><form:input path="name" id="name" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Parish Place :</td>
                                            <td><form:input path="place" id="place" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Parish Code :</td>
                                            <td><form:input path="code" id="code" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Parish Web-site :</td>
                                            <td><form:input path="webSite" id="webSite"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Parish Facebook Page :</td>
                                            <td><form:input path="facebookPage" id="facebookPage"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Driving Route :</td>
                                            <td><form:input path="drivingRoute" id="drivingRoute" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Map :</td>
                                            <td><form:input path="map" id="map" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Registered Date :</td>
                                            <td><form:input path="registeredDate" id="registeredDate"
                                                            class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Priest :</td>
                                            <td>
                                                <div id="priestDesignationBoxes"></div>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>

                        <div class="sectionRight">
                            <section class="contentDoc">
                                <h3>Contact Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Mobile No. :</td>
                                            <td><form:input path="mobileNo" id="mobileNo" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Email :</td>
                                            <td><form:input path="email" id="email" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Land Line No. :</td>
                                            <td><form:input path="landLineNo" id="landLineNo" class="textBox"/></td>
                                        </tr>
                                        <tr>
                                            <td>Fax No. :</td>
                                            <td><form:input path="faxNo" id="faxNo" class="textBox"/></td>
                                        </tr>
                                    </table>
                                </div>
                            </section>
                        </div>

                        <div class="clear"></div>

                        <div class="sectionLeft">
                            <section class="contentDoc">
                                <h3>Address</h3>

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
                    <table id="parishGrid"></table>
                    <div id="parishGridPager"></div>
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