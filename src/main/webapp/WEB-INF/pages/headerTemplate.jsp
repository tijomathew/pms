<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/6/15
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#logoutIcon').click(function () {
            $('#logoutForm').submit();
        });
    });
</script>
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
                <span class="hidden-xs"><i class="fa fa-sign-out fa-2x" id="logoutIcon"></i></span>
                <form:form method="post" action="loggedout.action" id="logoutForm">
                </form:form>
            </a>

        </li>
        <li>
            <a href="#" class="username">
                <span class="hidden-xs usernameBold">${loggedInUserEmail}</span>
            </a>
        </li>


    </ul>
</header>
