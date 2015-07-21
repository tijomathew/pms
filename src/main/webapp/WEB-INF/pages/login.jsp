<%--
  User: tijo
  Date: 29/9/14
  Time: 9:17 PM  
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

    <script type="text/javascript">
        jQuery(document).ready(function () {
            $('#failureforgotpassword').hide();
            $('#successforgotpassword').hide();
            $('#forgotPasswordContainer').show();
            var $form = $('#forgotpasswordform');
            $form.bind('submit', function (e) {
                $.post('${forgotPasswordActionURL}', $form.serializeArray(), function (response) {

                    if (response.statusMessage == 'FAIL') {
                        $('#successforgotpassword').hide();
                        $('#failureforgotpassword').show();
                        return [true, "", ""];

                    } else if (response.statusMessage == 'SUCCESS') {
                        $('#failureforgotpassword').hide();
                        $('#forgotPasswordContainer').hide();
                        $('#successforgotpassword').show();
                        return [true, "", ""];

                    }
                }, 'json');
                return false;
            });
            $('#forgotPasswordClicker').click(function () {
                $('#failureforgotpassword').hide();
                $('#successforgotpassword').hide();
                $('#forgotPasswordContainer').show();
            });
        });
    </script>

    <spring:url value="/resources/css/loginstyle.css" var="loginStyleURL"/>
    <link href="${loginStyleURL}" rel="stylesheet">

</head>
<body>

<div class="modal fade" id="forgotPass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Forgot password?</h4>
            </div>
            <div class="modal-body">
                <div id="forgotPasswordContainer">
                    <form:form modelAttribute="loginUser"
                               action="${pageContext.request.contextPath}/forgotpassword.action"
                               id="forgotpasswordform" method="post">

                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Email</label>

                                <div class="col-sm-10">
                                    <form:input path="email" class="form-control" placeholder="Email"/>
                                    <div class="alert alert-danger" role="alert" id="failureforgotpassword">This mail ID
                                        is
                                        invalid in our system. Please re-check the mail ID.
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="modal-footer noborder" style="padding-top:0">
                            <button type="button" class="btn btn-primary buttonWidth" data-dismiss="modal">Close
                            </button>
                            <button type="submit" class="btn btn-primary buttonWidth">Send Mail</button>
                        </div>
                    </form:form>
                </div>
                <div class="alert alert-success" role="alert" id="successforgotpassword">Password sent
                    to your registered mail ID at our system.
                </div>
            </div>


        </div>
    </div>
</div>


<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info mainPanel">
            <div class="panel-heading headerBackground">
                <div class="panel-title">Sign In</div>
                <div style="float:right; font-size: 80%; position: relative; top:-10px">
                    <a data-toggle="modal" data-target="#forgotPass" id="forgotPasswordClicker">Forgot password?</a>
                </div>
            </div>

            <div style="padding-top:30px" class="panel-body">

                <form:form modelAttribute="loginUser" action="${pageContext.request.contextPath}/loggedin.action"
                           id="loginForm1">

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:input path="email" id="login-username" class="form-control" placeholder="email"/>
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <form:password path="password" id="login-password" class="form-control" placeholder="password"/>
                    </div>


                    <div class="input-group">
                        <div class="checkbox">
                            <label>
                                <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                            </label>
                        </div>
                    </div>
                    <form:errors class="alert alert-danger" role="alert" id="loginErrorDisplay" ></form:errors>

                    <div style="margin-top:10px" class="form-group">

                        <div class="col-sm-12 controls">
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

</div>

</body>
</html>



