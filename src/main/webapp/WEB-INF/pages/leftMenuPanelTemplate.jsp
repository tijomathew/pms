<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/6/15
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- BEGIN SIDEBAR -->


<nav id="page-leftbar" role="navigation">
    <!-- BEGIN SIDEBAR MENU -->

    <c:if test="${showForAdmin == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><i class="fa pms-users"></i>
                <span>Users</span></a></li>
            <li id="parish"><a href="${pageContext.request.contextPath}/viewparish.action"><i class="fa pms-parish"></i>
                <span>Parish</span> </a></li>
           <%-- <li id="priest"><a href="${pageContext.request.contextPath}/viewpriest.action"><i class="fa pms-priest"></i>
                <span>Priest</span> </a></li>--%>
            <li id="masscentre"><a href="${pageContext.request.contextPath}/viewmasscentre.action"><i
                    class="fa pms-massCentres"></i> <span>Mass Centres</span></a></li>
            <li id="prayerunit"><a href="${pageContext.request.contextPath}/viewprayerunit.action"><i
                    class="fa pms-prayerUnits"></i> <span>Prayer Units</span></a></li>
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa pms-family"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa pms-members"></i> <span>Members</span></a></li>
            <%--<li id="priestdesignation"><a href="#"><i class="fa pms-members"></i> <span>Assign Priest Designation</span></a>--%>
            </li>
            <%--<li><a href="javascript:;"><i class="fa pms-reports"></i> <span>Reports</span> <span
                    class="badge badge-orange">5</span></a>
                <ul class="acc-menu">
                    <li><a href="#">Birth</a></li>
                    <li><a href="#">Baptism</a></li>
                    <li><a href="#">Confirmation</a></li>
                    <li><a href="#">Marriage</a></li>
                    <li><a href="#">Death</a></li>
                </ul>
            </li>--%>
            <li class="divider"></li>
        </ul>
    </c:if>

    <c:if test="${showForParishAdmin == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><i class="fa fa-home"></i>
                <span>Users</span></a></li>
            <li id="parish"><a href="${pageContext.request.contextPath}/viewparish.action"><i class="fa fa-th"></i>
                <span>Parish</span> </a></li>
            <li id="masscentre"><a href="${pageContext.request.contextPath}/viewmasscentre.action"><i
                    class="fa fa-tasks"></i> <span>Mass Centres</span></a></li>
            <li id="prayerunit"><a href="${pageContext.request.contextPath}/viewprayerunit.action"><i
                    class="fa fa-table"></i> <span>Prayer Units</span></a></li>
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa fa-pencil"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa fa-map-marker"></i> <span>Members</span></a></li>
            <%--<li><a href="javascript:;"><i class="fa fa-flag"></i> <span>Reports</span> <span
                    class="badge badge-orange">5</span></a>
                <ul class="acc-menu">
                    <li><a href="#">Birth</a></li>
                    <li><a href="#">Baptism</a></li>
                    <li><a href="#">Confirmation</a></li>
                    <li><a href="#">Marriage</a></li>
                    <li><a href="#">Death</a></li>
                </ul>
            </li>--%>
            <li class="divider"></li>
        </ul>
    </c:if>

    <c:if test="${showForMassCentreAdmin == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><i class="fa fa-home"></i>
                <span>Users</span></a></li>
            <li id="masscentre"><a href="${pageContext.request.contextPath}/viewmasscentre.action"><i
                    class="fa fa-tasks"></i> <span>Mass Centres</span></a></li>
            <li id="prayerunit"><a href="${pageContext.request.contextPath}/viewprayerunit.action"><i
                    class="fa fa-table"></i> <span>Prayer Units</span></a></li>
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa fa-pencil"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa fa-map-marker"></i> <span>Members</span></a></li>
            <li class="divider"></li>
        </ul>
    </c:if>
    <c:if test="${showForPrayerUnitAdmin == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><i class="fa fa-home"></i>
                <span>Users</span></a></li>
            <li id="prayerunit"><a href="${pageContext.request.contextPath}/viewprayerunit.action"><i
                    class="fa fa-table"></i> <span>Prayer Units</span></a></li>
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa fa-pencil"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa fa-map-marker"></i> <span>Members</span></a></li>
            <li class="divider"></li>
        </ul>
    </c:if>
    <c:if test="${showForFamilyUser == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa fa-pencil"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa fa-map-marker"></i> <span>Members</span></a></li>
            <li class="divider"></li>
        </ul>
    </c:if>
    <!-- END SIDEBAR MENU -->
</nav>
