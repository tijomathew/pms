<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 15/7/15
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>
<html>
<head>

    <title>PMS-Login</title>

    <spring:url value="/resources/css/bootstrap.min.latest.css" var="bootstrapcsslatestUrl"/>
    <spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapthemeUrl"/>

    <link href="${bootstrapcsslatestUrl}" rel="stylesheet">
    <link href="${bootstrapthemeUrl}" rel="stylesheet">

    <spring:url value="/resources/js/jquery-1.11.0.min.js" var="jqueryURL"/>
    <spring:url value="/forgotpassword.action" var="forgotPasswordActionURL"/>

    <script type='text/javascript' src="${jqueryURL}"></script>

    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapURL"/>
    <script type='text/javascript' src="${bootstrapURL}"></script>

    <spring:url value="/changepassword.action" var="changePasswordActionURL"/>

    <script type="text/javascript">
        jQuery(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip({template : '<div class="tooltip" role="tooltip" style="width:300px"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'});
            $('#changePasswordContainer').show();
            $('#successContainer').hide();
            var $form = $('#changePasswordForm');
            $form.bind('submit', function (e) {

                $.post('${changePasswordActionURL}', $form.serializeArray(), function (response) {
                    if (response.statusCode == 'SUCCESS') {
                        $('#changePasswordContainer').hide();
                        $('#successContainer').show();
                        return [true, "", ""];
                    }
                }, 'json');
                return false;
            });
        });
    </script>

    <spring:url value="/resources/css/loginstyle.css" var="loginStyleURL"/>
    <link href="${loginStyleURL}" rel="stylesheet">

</head>
<body class="changePassBody">

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info mainPanel">
            <div class="panel-heading headerBackground">
                <h4 class="modal-title" id="myModalLabel" style="color:#fff;">Change Password</h4>
            </div>

            <div class="panel-body" id="changePasswordContainer">
                <form:form modelAttribute="changePasswordUser"
                           action="${pageContext.request.contextPath}/changepassword.action"
                           id="changePasswordForm" method="post" class="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-11" style="padding-right: 0px;">
                            <form:password path="newPassword" id="login-username" class="form-control"
                                           placeholder="New Password"/>
                            <form:hidden path="email"/>
                        </div>
                        <div class="col-sm-1" style="padding-top: 8px;padding-left: 8px;">
                            <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Tooltip on left kjaksd aslkjdkjasd lkasjdkljasd lajsdkjas dlaksjdkjasd kljasd klasdk"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-11" style="padding-right: 0px;">
                            <form:password path="confirmPassword" id="login-password" class="form-control" placeholder="Confirm Password"/>
                        </div>
                        <div class="col-sm-1" style="padding-top: 8px;padding-left: 8px;">
                            <span class="glyphicon glyphicon-question-sign" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Tooltip on left kjaksd aslkjdkjasd lkasjdkljasd lajsdkjas dlaksjdkjasd kljasd klasdk"></span>
                        </div>
                    </div>

                    <div style="margin-top:10px" class="row">
                        <div class="col-sm-12 controls text-center">
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Change Password"/>
                        </div>
                    </div>
                </form:form>

            </div>
            <div class="alert alert-success" role="alert" id="successContainer">
                    Password is updated successfully.<br/>
                    Please <a href="login.action"> re-login</a> to continue the system use.
            </div>
        </div>
    </div>

</div>


</body>
</html>
