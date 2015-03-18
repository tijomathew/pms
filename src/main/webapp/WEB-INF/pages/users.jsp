<%--  
  User: tijo
  Date: 13/10/14
  Time: 9:08 PM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title id="title">Users</title>

    <%@include file="scriptlibraryTemplate.jsp" %>

</head>
<body>

<%@include file="toolbarTemplate.jsp" %>

<%@include file="menupanelTemplate.jsp" %>

<div class="outer-center">
    <div class="middle-center">
        <div class="inner-center">
            <div id="tabs" class="contentTabs">
                <ul class="tabHead">
                    <li><a href="#tabs-1">Add Users</a></li>
                    <li><a href="#tabs-2">View Users</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <form:form modelAttribute="user"
                               action="${pageContext.request.contextPath}/addUser.action" method="post" id="adminForm">
                        <div class="sectionLeft">

                            <section class="contentDoc ">
                                <h3>User Details</h3>

                                <div class="mainConte">
                                    <table>
                                        <tr>
                                            <td>Email(Used as Username) :</td>
                                            <td><form:input path="email" id="email"/></td>
                                        </tr>
                                        <tr>
                                            <td>Phone No(Used as Password) :</td>
                                            <td><form:input path="phoneNo" id="phoneNo"/></td>
                                        </tr>
                                        <tr>
                                            <td>System Role :</td>
                                            <td><form:select path="systemRole" id="systemRole">
                                                <form:option value="Admin">Admin</form:option>
                                                <form:option value="Family Head">Family Head</form:option>
                                                <form:option value="Parish Admin">Parish Admin</form:option>
                                                <form:option value="Masscenter Admin">Mass Center Admin</form:option>
                                                <form:option value="Prayer Unit Admin">Prayer Unit Admin</form:option>
                                            </form:select>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>Active :</td>
                                            <td><form:select path="isActive" id="isActive">
                                                <form:option value="Active">Active</form:option>
                                                <form:option value="De-active">De-active</form:option>
                                            </form:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Parish:</td>
                                            <td><form:select path="parishId"
                                                             items="${parishList}"></form:select></td>
                                        </tr>
                                        <tr>
                                            <td>Mass Center:</td>
                                            <td><form:select path="massCenterId"
                                                             items="${massCenterList}"></form:select></td>
                                        </tr>
                                        <tr>
                                            <td>Prayer Unit:</td>
                                            <td><form:select path="prayerUnitId"
                                                             items="${prayerUnitList}"></form:select></td>
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