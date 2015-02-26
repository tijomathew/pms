<%--  
  User: tijo
  Date: 14/10/14
  Time: 7:58 PM  
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title id="title">Ward</title>
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
    <script src="<c:url value="/resources/js/jquery-1.11.0.min.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery-ui.js" />" type="text/javascript" language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery.layout-latest.min.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery.treeview.js" />" type="text/javascript"
            language="javascript"></script>

    <!-- Scripts @Bilahari -->
    <script src="<c:url value="/resources/js/utility.js" />" type="text/javascript" language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery.jqGrid.min.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/grid.locale-en.js" />" type="text/javascript"
            language="javascript"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        $(document).ready(function () {
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
                        url: '${pageContext.request.contextPath}/showUsers.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        colNames: ['Inv No', 'Date', 'Client', 'Amount', 'Tax', 'Total', 'Notes'],
                        colModel: [
                            {name: 'id', index: 'id', width: 55},
                            {name: 'userName', index: 'userName', width: 90},
                            {name: 'password', index: 'password asc, password', width: 100},
                            {name: 'systemRole', index: 'systemRole', width: 80, align: "right"},
                            {name: 'email', index: 'email', width: 80, align: "right"},
                            {name: 'total', index: 'total', width: 80, align: "right"},
                            {name: 'note', index: 'note', width: 150, sortable: false}
                        ],
                        rowNum: 10,
                        rowList: [10, 20, 30],
                        pager: '#pager2',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Users"
                    });
            jQuery("#list2").jqGrid('navGrid', '#pager2', {edit: true, add: true, del: true});
        }


    </script>

</head>
<body>
<div class="userSettings">
    <ul>
        <li>
            <a href="#">
                <span class="text">Change Password</span>
                <span class="changePassword_ico"></span>
                <span class="clear"></span>
            </a>
        </li>
        <li>
            <a href="#">
                <span class="text">Help</span>
                <span class="help_ico"></span>
                <span class="clear"></span>
            </a>
        </li>
        <li>
            <a href="#" class="last">
                <span class="text">Logout</span>
                <span class="logout_ico"></span>
                <span class="clear"></span>
            </a>
        </li>
    </ul>
</div>

<div class="header ui-layout-north">
    <div class="header_left">
        <h3><span>PMS</span> Parish Management System</h3>
    </div>
    <div class="header_right">
        <div class="userIDName"><span>User ID</span></div>
        <div class="userIdButton"><a id="userSettings" href="#"></a></div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
</div>


<div class="outer-west">
    <div id="leftmenuAccordion">
        <h3>Admin Modules</h3>

        <div>
            <ul id="tree1" class="filetree">
                <li><span class="folder">Top Level</span>
                    <ul class="subnodes">
                        <li class="active"><a href="${pageContext.request.contextPath}/viewUser.action"><span
                                class="file">Add Admin</span></a></li>
                        <li><a href="${pageContext.request.contextPath}/viewPriest.action"><span
                                class="file">Add Priest</span></a></li>
                        <li><a href="${pageContext.request.contextPath}/viewParish.action"><span
                                class="file">Add Parish</span></a></li>
                        <li><a href="${pageContext.request.contextPath}/viewMassCenter.action"><span class="file">Add Mass Center</span></a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/viewWard.action"><span
                                class="file">Add Ward</span></a></li>
                        <li><a href="${pageContext.request.contextPath}/viewFamily.action"><span
                                class="file">Add Family</span></a></li>
                        <li><a href="${pageContext.request.contextPath}/viewMember.action"><span class="file">Add Family Members</span></a>
                        </li>
                    </ul>
                </li>
                <%--<li class="closed"><span class="folder">Analytics Category</span>--%>
                <%--<ul class="subnodes">--%>
                <%--<li><span class="file">Analysis Child Node</span></li>--%>
                <%--<li><span class="file">Analysis Child Node</span></li>--%>
                <%--<li><span class="file">Analysis Child Node</span></li>--%>
                <%--<li><span class="file">Analysis Child Node</span></li>--%>
                <%--<li><span class="file">Analysis Child Node</span></li>--%>

                <%--</ul>--%>
                <%--</li>--%>
                <%--<li class="closed"><span class="folder">Analytics Category</span>--%>
                <%--<ul class="subnodes">--%>
                <%--<li><span class="file">Analysis Child Node</span></li>--%>
                <%--<li><span class="file">Analysis Child Node</span></li>--%>
                <%--</ul>--%>
                <%--</li>--%>

            </ul>
        </div>
        <%--<h3>Analytics</h3>--%>
        <%--<div>--%>
        <%--<ul id="tree2" class="filetree">--%>
        <%--<li><span class="folder">Main Reports</span>--%>
        <%--<ul class="subnodes">--%>
        <%--<li><span class="file">Portfolio Summery</span></li>--%>
        <%--<li><span class="file">Portfolio Summery</span></li>--%>
        <%--<li><span class="file">Portfolio Summery</span></li>--%>
        <%--<li><span class="file">Portfolio Summery</span></li>--%>
        <%--<li><span class="file">Portfolio Summery</span></li>--%>
        <%--<li><span class="file">Portfolio Summery</span></li>--%>
        <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="closed"><span class="folder">Analytics Category</span>--%>
        <%--<ul class="subnodes">--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>

        <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="closed"><span class="folder">Analytics Category</span>--%>
        <%--<ul class="subnodes">--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>
        <%--<li><span class="file">Analysis Child Node</span></li>--%>

        <%--</ul>--%>
        <%--</li>--%>

        <%--</ul>--%>
        <%--</div>--%>
    </div>
</div>
<!--<div class="outer-east">hi</div>-->
<div class="outer-center">
    <div class="middle-center">
        <div class="inner-center">
            <div id="tabs" class="contentTabs">
                <ul class="tabHead">
                    <li><a href="#tabs-1">Add Admin</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <table id="list2"></table>
                    <div id="pager2"></div>
                    <div id="table_div"></div>
                </div>

            </div>
        </div>
        <div class="footer ui-layout-south">
            <h3>Filter Panel View</h3>

            <div class="filter_left">
                <table>
                    <tr>
                        <td>
                            <button type="button" class="filterButton">Find</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="filterButton">Search</button>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="filter_right">
                <table>
                    <tr>
                        <td>
                            <button type="button" class="filterButton">Find</button>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="filterButton">Search</button>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

</body>
</html>