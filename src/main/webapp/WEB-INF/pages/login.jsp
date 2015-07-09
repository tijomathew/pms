<%--
  User: tijo
  Date: 29/9/14
  Time: 9:17 PM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>
<html>
<head>

    <title>PMS-Login</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign In</div>
                <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a>
                </div>
            </div>

            <div style="padding-top:30px" class="panel-body">

                <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

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



