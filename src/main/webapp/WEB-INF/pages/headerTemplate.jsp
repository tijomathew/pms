<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/6/15
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<header class="navbar navbar-inverse navbar-fixed-top" role="banner">
  <a id="leftmenu-trigger" class="tooltips" data-toggle="tooltip" data-placement="bottom" title="Toggle Sidebar"></a>
  <a id="rightmenu-trigger" class="tooltips" data-toggle="tooltip" data-placement="bottom" title="Toggle Infobar"></a>

  <div class="navbar-header pull-left">
    <a class="navbar-brand" href="#">PMS | <small>Parish Management System</small></a>
  </div>

  <ul class="nav navbar-nav pull-right toolbar">
    <li class="dropdown">
      <a href="#" class="dropdown-toggle username" data-toggle="dropdown">
        <span class="hidden-xs">${loggedInUserEmail} <i class="fa fa-caret-down"></i></span>
        <spring:url value="/resources/images/dangerfield.png" var="dangerimageURL"/>
        <img src="${dangerimageURL}" alt="Dangerfield"/>
      </a>
      <ul class="dropdown-menu userinfo arrow">
        <li class="username">
          <a href="#">
            <div class="pull-left"><img src="${dangerimageURL}" alt="Jeff Dangerfield"/></div>
            <div class="pull-right"><h5>${loggedInUserEmail}</h5>
              <small>Logged in as <span>${loggedInUserEmail}</span></small>
            </div>
          </a>
        </li>
        <li class="userlinks">
          <ul class="dropdown-menu">
            <%--<li><a href="#">Edit Profile <i class="pull-right fa fa-pencil"></i></a></li>
            <li><a href="#">Account <i class="pull-right fa fa-cog"></i></a></li>
            <li><a href="#">Help <i class="pull-right fa fa-question-circle"></i></a></li>--%>
            <%--<li class="divider"></li>--%>
            <li class="text-right"><button class="btn btn-primary defaultButtonWidth">Sign Out</button></li>
            <%--<li><a href="${pageContext.request.contextPath}/loggedout.action" class="text-right">Sign Out</a></li>--%>
          </ul>
        </li>
      </ul>
    </li>

  </ul>
</header>
