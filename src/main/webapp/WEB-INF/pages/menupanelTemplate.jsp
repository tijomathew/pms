<%--  
  User: tijo
  Date: 24/10/14
  Time: 10:41 AM  
--%>
<script>
    jQuery(document).ready(function () {
        <c:if test="${f:contains(pageContext.request.requestURI, 'priest')}">
        jQuery('#priest').addClass('menubtnstyle');
        </c:if>
        <c:if test="${f:contains(pageContext.request.requestURI, 'parish')}">
        jQuery('#parish').addClass('menubtnstyle');
        </c:if>
        <c:if test="${f:contains(pageContext.request.requestURI, 'massCenter')}">
        jQuery('#masscenter').addClass('menubtnstyle');
        </c:if>
        <c:if test="${f:contains(pageContext.request.requestURI, 'ward')}">
        jQuery('#ward').addClass('menubtnstyle');
        </c:if>
        <c:if test="${f:contains(pageContext.request.requestURI, 'family')}">
        jQuery('#family').addClass('menubtnstyle');
        </c:if>
        <c:if test="${f:contains(pageContext.request.requestURI, 'member')}">
        jQuery('#member').addClass('menubtnstyle');
        </c:if>
        <c:if test="${f:contains(pageContext.request.requestURI, 'users')}">
        jQuery('#users').addClass('menubtnstyle');
        </c:if>
    });
</script>
<c:set var="showForAdmin" value="${sessionScope.adminRole}"/>
<c:set var="showForParishAdmin" value="${sessionScope.parishAdminRole}"/>
<c:set var="showForMassCenterAdmin" value="${sessionScope.massCenterAdminRole}"/>
<c:set var="showForPrayerUnitAdmin" value="${sessionScope.prayerUnitAdminRole}"/>
<div class="outer-west">
    <div id="leftmenuAccordion">
        <h3>Menu</h3>

        <div>
            <ul class="leftMenu">
                <c:if test="${showForAdmin == true}">
                    <li id="priest"><a href="${pageContext.request.contextPath}/viewPriest.action"><span
                            class="file">Priest</span></a></li>
                    <li id="parish"><a href="${pageContext.request.contextPath}/viewParish.action"><span
                            class="file">Parish</span></a></li>
                    <li id="masscenter"><a href="${pageContext.request.contextPath}/viewMassCenter.action"><span
                            class="file">Mass Center</span></a></li>
                    <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><span
                            class="file">Users</span></a></li>
                    <li id="ward"><a href="${pageContext.request.contextPath}/viewWard.action"><span
                            class="file">Prayer Unit</span></a></li>
                    <li id=family><a href="${pageContext.request.contextPath}/viewFamily.action"><span
                            class="file">Family</span></a></li>
                    <li id="member"><a href="${pageContext.request.contextPath}/viewMember.action"><span
                            class="file">Family Members</span></a></li>
                </c:if>
                <c:if test="${showForParishAdmin == true}">
                    <li id="parish"><a href="${pageContext.request.contextPath}/viewParish.action"><span
                            class="file">Parish</span></a></li>
                    <li id="masscenter"><a href="${pageContext.request.contextPath}/viewMassCenter.action"><span
                            class="file">Mass Center</span></a></li>
                    <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><span
                            class="file">Users</span></a></li>
                    <li id="ward"><a href="${pageContext.request.contextPath}/viewWard.action"><span
                            class="file">Prayer Unit</span></a></li>
                    <li id=family><a href="${pageContext.request.contextPath}/viewFamily.action"><span
                            class="file">Family</span></a></li>
                    <li id="member"><a href="${pageContext.request.contextPath}/viewMember.action"><span
                            class="file">Family Members</span></a></li>
                </c:if>
                <c:if test="${showForMassCenterAdmin == true}">
                    <li id="masscenter"><a href="${pageContext.request.contextPath}/viewMassCenter.action"><span
                            class="file">Mass Center</span></a></li>
                    <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><span
                            class="file">Users</span></a></li>
                    <li id="ward"><a href="${pageContext.request.contextPath}/viewWard.action"><span
                            class="file">Prayer Unit</span></a></li>
                    <li id=family><a href="${pageContext.request.contextPath}/viewFamily.action"><span
                            class="file">Family</span></a></li>
                    <li id="member"><a href="${pageContext.request.contextPath}/viewMember.action"><span
                            class="file">Family Members</span></a></li>
                </c:if>
                <c:if test="${showForPrayerUnitAdmin == true}">
                    <li id="users"><a href="${pageContext.request.contextPath}/viewusers.action"><span
                            class="file">Users</span></a></li>
                    <li id="ward"><a href="${pageContext.request.contextPath}/viewWard.action"><span
                            class="file">Prayer Unit</span></a></li>
                    <li id=family><a href="${pageContext.request.contextPath}/viewFamily.action"><span
                            class="file">Family</span></a></li>
                    <li id="member"><a href="${pageContext.request.contextPath}/viewMember.action"><span
                            class="file">Family Members</span></a></li>
                </c:if>
            </ul>
        </div>

        <h3>Certificates</h3>

        <div>
            <ul class="leftMenu">
                <li class="active"><span class="file">Birth</span></li>
                <li><span class="file">Baptism</span></li>
                <li><span class="file">Confirmation</span></li>
                <li><span class="file">Marriage</span></li>
                <li><span class="file">Death</span></li>
            </ul>
        </div>

    </div>
</div>