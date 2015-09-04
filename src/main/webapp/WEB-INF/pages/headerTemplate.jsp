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
        <a class="navbar-brand" href="#"><span style="text-shadow: 1px 1px #9999A3;">PMS</span> |
            <small>Parish Management System</small>
        </a>
    </div>

    <ul class="nav navbar-nav pull-right toolbar">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle username">
                <span class="hidden-xs"><i class="fa fa-sign-out fa-2x"></i></span>
            </a>

        </li>
        <li>
            <a href="#" class="username">
                <span class="hidden-xs">${loggedInUserEmail} <!--<i class="fa fa-caret-down"></i>--></span>
                <spring:url value="/resources/images/dangerfield.png" var="dangerimageURL"/>
                <!--<img src="${dangerimageURL}" alt="Dangerfield"/>-->
            </a>
            <!--<ul class="dropdown-menu userinfo arrow">
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
                        <li class="text-right">
                            <form:form method="post" action="loggedout.action" >
                            <input type="submit" class="btn btn-primary defaultButtonWidth" id="logoutButton" value="Sign Out"></input>
                            </form:form>
                        </li>
                    </ul>
                </li>
            </ul>
            -->
        </li>


    </ul>
</header>
