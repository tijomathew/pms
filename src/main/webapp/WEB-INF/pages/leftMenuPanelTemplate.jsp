<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/6/15
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- BEGIN SIDEBAR -->


<nav id="page-leftbar" role="navigation">

    <c:if test="${showForAdmin == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><i class="fa pms-users"></i>
                <span>Users</span></a></li>
                <%-- <li id="priest"><a href="${pageContext.request.contextPath}/viewpriest.action"><i class="fa pms-priest"></i>
                     <span>Priest</span> </a></li>--%>
            <li id="parish"><a href="${pageContext.request.contextPath}/viewparish.action"><i
                    class="fa pms-massCentres"></i> <span>Parish</span></a></li>
            <li id="prayerunit"><a href="${pageContext.request.contextPath}/viewprayerunit.action"><i
                    class="fa pms-prayerUnits"></i> <span>Prayer Units</span></a></li>
            <li id="familywelcome"><a href="${pageContext.request.contextPath}/viewfamilywelcome.action"><i
                    class="fa pms-family"></i>
                <span>Family Home</span></a></li>
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa pms-family"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa pms-members"></i> <span>Members</span></a></li>
            <li id="emailnotification"><a href="${pageContext.request.contextPath}/viewemailnotification.action"><i
                    class="fa pms-members"></i> <span>Email Notification</span></a></li>
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
            <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><i class="fa pms-users"></i>
                <span>Users</span></a></li>
            <li id="parish"><a href="${pageContext.request.contextPath}/viewparish.action"><i
                    class="fa pms-massCentres"></i> <span>Parish</span></a></li>
            <li id="prayerunit"><a href="${pageContext.request.contextPath}/viewprayerunit.action"><i
                    class="fa pms-prayerUnits"></i> <span>Prayer Units</span></a></li>
            <li id="familywelcome"><a href="${pageContext.request.contextPath}/viewfamilywelcome.action"><i
                    class="fa pms-family"></i>
                <span>Family Home</span></a></li>
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa pms-family"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa pms-members"></i> <span>Members</span></a></li>
            <li id="emailnotification"><a href="${pageContext.request.contextPath}/viewemailnotification.action"><i
                    class="fa pms-members"></i> <span>Email Notification</span></a></li>
            <li class="divider"></li>
        </ul>
    </c:if>
    <c:if test="${showForPrayerUnitAdmin == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><i class="fa pms-users"></i>
                <span>Users</span></a></li>
            <li id="prayerunit"><a href="${pageContext.request.contextPath}/viewprayerunit.action"><i
                    class="fa pms-prayerUnits"></i> <span>Prayer Units</span></a></li>
            <li id="familywelcome"><a href="${pageContext.request.contextPath}/viewfamilywelcome.action"><i
                    class="fa pms-family"></i>
                <span>Family Home</span></a></li>
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa pms-family"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa pms-members"></i> <span>Members</span></a></li>
            <li id="emailnotification"><a href="${pageContext.request.contextPath}/viewemailnotification.action"><i
                    class="fa pms-members"></i> <span>Email Notification</span></a></li>
            <li class="divider"></li>
        </ul>
    </c:if>
    <c:if test="${showForFamilyUser == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="familywelcome"><a href="${pageContext.request.contextPath}/viewfamilywelcome.action"><i
                    class="fa pms-family"></i>
                <span>Family Home</span></a></li>
            <li id="family"><a href="${pageContext.request.contextPath}/viewfamily.action"><i class="fa pms-family"></i>
                <span>Family</span></a></li>
            <li id="member"><a href="${pageContext.request.contextPath}/viewmember.action"><i
                    class="fa pms-members"></i> <span>Members</span></a></li>
            <li class="divider"></li>
        </ul>
    </c:if>
    <c:if test="${showForFinanceAdmin == true}">
        <ul class="acc-menu" id="sidebar">
            <li id="receipt"><a href="${pageContext.request.contextPath}/viewreceipt.action"><i class="fa pms-users"></i>
                <span>Receipts</span></a></li>
            <li id="payment"><a href="${pageContext.request.contextPath}/viewpayment.action"><i
                    class="fa pms-prayerUnits"></i> <span>Payments</span></a></li>
            <li id="deposit"><a href="${pageContext.request.contextPath}/viewdeposit.action"><i
                    class="fa pms-members"></i> <span>Deposit</span></a></li>
            <li id="withdrawal"><a href="${pageContext.request.contextPath}/viewwithdrawal.action"><i
                    class="fa pms-family"></i>
                <span>Withdrawal</span></a></li>
            <%--<li id="balance"><a href="${pageContext.request.contextPath}/viewwithdrawal.action"><i
                    class="fa pms-family"></i>
                <span>Balance Sheet</span></a></li>
            <li id="journal"><a href="${pageContext.request.contextPath}/viewjournal.action"><i
                    class="fa pms-family"></i>
                <span>Journal</span></a></li>--%>
            <li><a href="javascript:;"><i class="fa pms-reports"></i> <span>Reports</span> <span
                    class="badge badge-orange">1</span></a>
                <ul class="acc-menu">
                    <li><a href="${pageContext.request.contextPath}/viewreport.action">Receipts & Payments</a></li>
                    <%--<li><a href="#">Report 2</a></li>
                    <li><a href="#">Report 3</a></li>
                    <li><a href="#">Report 4</a></li>
                    <li><a href="#">Report 5</a></li>--%>
                </ul>
            </li>
            <li class="divider"></li>

        </ul>
    </c:if>

</nav>
