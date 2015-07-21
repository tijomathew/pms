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
            $('#changePasswordContainer').show();
            $('#successContainer').hide();
            var $form = $('#changePasswordForm');
            $form.bind('submit', function (e) {

                $.post('${changePasswordActionURL}', $form.serializeArray(), function (response) {
                    if (response.statusMessage == 'SUCCESS') {
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
<body>

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info mainPanel">
            <div class="panel-heading headerBackground">
                <div class="panel-title">Change Password</div>
            </div>

            <div style="padding-top:30px" class="panel-body" id="changePasswordContainer">
                <form:form modelAttribute="changePasswordUser"
                           action="${pageContext.request.contextPath}/changepassword.action"
                           id="changePasswordForm" method="post">

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <form:password path="newPassword" id="login-username" class="form-control"
                                       placeholder="New Password"/>
                        <form:hidden path="email"/>
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <form:password path="confirmPassword" id="login-password" class="form-control"
                                       placeholder="Confirm Password"/>
                    </div>

                    <div style="margin-top:10px" class="form-group">

                        <div class="col-sm-12 controls">
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Change Password"/>
                        </div>
                    </div>
                </form:form>

            </div>
            <div class="alert alert-success" role="alert" id="successContainer">Password is updated successfully.Please
                <a href="login.action"> re-login</a> to continue the system use.
            </div>
        </div>
    </div>

</div>


</body>
</html>
