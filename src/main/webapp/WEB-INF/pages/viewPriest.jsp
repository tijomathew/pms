<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 7/12/14
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title id="title">View Priest Grid</title>

    <link rel="shortcut icon" href="#.ico">
    <!-- CSS -->
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/ui.jqgrid.css" />"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-custom.css" />"/>
    <style type="text/css">
        .middle-center, .inner-center, .ui-layout-south {
            border: 0;
        }
    </style>

    <!-- Libraries -->
    <script src="<c:url value="/resources/js/jquery-1.10.2.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery-ui.js" />" type="text/javascript" language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery.layout-latest.min.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery.treeview.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery.validate.min.js" />" type="text/javascript"
            language="javascript"></script>

    <script src="<c:url value="/resources/js/utility.js" />" type="text/javascript" language="javascript"></script>

    <script src="<c:url value="/resources/js/jquery.jqGrid.min.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/grid.locale-en.js" />" type="text/javascript"
            language="javascript"></script>


    <script type="text/javascript" language="JavaScript">
        jQuery(document).ready(function () {

            loadGrid();

        });
        function loadGrid() {

            jQuery("#list2").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayPriestGrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        colNames: ['Priest ID', 'Name', 'Designation'],
                        colModel: [

                            {name: 'priestID', index: 'priestID', width: 90},
                            {name: 'name', index: 'name', width: 100},
                            {name: 'designation', index: 'designation', width: 80, align: "right"}
                        ],
                        rowNum: 10,
                        rowList: [10, 20, 30],
                        pager: '#pager2',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Priests",
                        width: "100%"
                    });
            jQuery("#list2").jqGrid('navGrid', '#pager2', {edit: true, add: true, del: true});
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
                    <li><a href="#tabs-1">Priest Grid View</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <table id="list2"></table>
                    <div id="pager2"></div>

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
                            <button type="button" class="filterButton" onclick="loadGrid()">Print</button>
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
