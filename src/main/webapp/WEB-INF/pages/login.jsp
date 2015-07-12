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


    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <style>
        body{
            background-color: #CECECE;
        }
        .mainPanel{
            border-color: #314D68;
            background-color: #F4F4F4;

        }
        .mainPanel .panel-title{
            color:#fff;
        }
        .mainPanel .panel-heading a{
            color:#fff;
        }



        #loginbox .headerBackground, .headerBackground {
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#003466+0,314d68+100 */
            background: rgb(0,52,102); /* Old browsers */
            background: -moz-linear-gradient(top,  rgba(0,52,102,1) 0%, rgba(49,77,104,1) 100%); /* FF3.6+ */
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(0,52,102,1)), color-stop(100%,rgba(49,77,104,1))); /* Chrome,Safari4+ */
            background: -webkit-linear-gradient(top,  rgba(0,52,102,1) 0%,rgba(49,77,104,1) 100%); /* Chrome10+,Safari5.1+ */
            background: -o-linear-gradient(top,  rgba(0,52,102,1) 0%,rgba(49,77,104,1) 100%); /* Opera 11.10+ */
            background: -ms-linear-gradient(top,  rgba(0,52,102,1) 0%,rgba(49,77,104,1) 100%); /* IE10+ */
            background: linear-gradient(to bottom,  rgba(0,52,102,1) 0%,rgba(49,77,104,1) 100%); /* W3C */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#003466', endColorstr='#314d68',GradientType=0 ); /* IE6-9 */
        }
        #loginbox .borderColor{
            border-color: #314D68;
        }
        #forgotPass .modal-header{
            background: #003466;
            color:#fff;
        }
        #forgotPass .modal-content{background-color: #F4F4F4;}
        .noborder{border: 0 !important;}
        .buttonWidth{width:130px;}
        button.close{color:#fff;  opacity: .7;}
    </style>
</head>
<body>


<!-- Modal -->
<div class="modal fade" id="forgotPass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Forgot password?</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" placeholder="Email">
                            <p class="text-danger" style="margin: 0;padding-top:3px;">Invalid Email</p>
                        </div>
                    </div>


                </div>

            </div>
            <div class="modal-footer noborder" style="padding-top:0">
                <button type="button" class="btn btn-primary buttonWidth" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary buttonWidth">Save changes</button>
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
                    <a data-toggle="modal" data-target="#forgotPass">Forgot password?</a>
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



