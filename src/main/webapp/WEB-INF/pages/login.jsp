<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 4/9/15
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!--[if IE 8]> <html class="no-js lt-ie9" lang="en" > <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en"> <!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Login"/>
    <meta name="keywords" content="Login"/>
    <meta name="author" content="Adsays"/>
    <link rel="shortcut icon" href="resources/images/favicon.png">

    <title>PMS-Parish Management System</title>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapcss"/>
    <spring:url value="/resources/css/demo.css" var="democss"/>
    <spring:url value="/resources/css/login-theme-1.css" var="loginthemecss"/>
    <spring:url value="/resources/css/animate-custom.css" var="animatecss"/>
    <spring:url value="/resources/css/loginstyle.css" var="loginStyleURL"/>

    <spring:url value="/resources/css/bootstrap.min.latest.css" var="bootstrapcsslatestUrl"/>
    <spring:url value="/resources/css/bootstrap-theme.min.css" var="bootstrapthemeUrl"/>

    <spring:url value="/resources/js/jquery-1.11.0.min.js" var="jqueryURL"/>
    <script type='text/javascript' src="${jqueryURL}"></script>

    <spring:url value="/forgotpassword.action" var="forgotPasswordActionURL"/>

    <link href="${bootstrapcsslatestUrl}" rel="stylesheet">
    <link href="${bootstrapthemeUrl}" rel="stylesheet">


    <link href="${loginStyleURL}" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="${bootstrapcss} " rel="stylesheet">

    <!-- Demo CSS -->
    <link href="${democss}" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${loginthemecss}" rel="stylesheet" id="fordemo">

    <link href="${animatecss}" rel="stylesheet">

    <script type="text/javascript">
        jQuery(document).ready(function () {
            $('#failureforgotpassword').hide();
            $('#successforgotpassword').hide();
            $('#forgotPasswordContainer').show();
            var $form = $('#forgotpasswordform');
            $form.bind('submit', function (e) {
                $.post('${forgotPasswordActionURL}', $form.serializeArray(), function (response) {

                    if (response.statusCode == 'FAIL') {
                        $('#successforgotpassword').hide();
                        $('#failureforgotpassword').show();
                        return [true, "", ""];

                    } else if (response.statusCode == 'SUCCESS') {
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


</head>
<body class="fade-in">

<div class="modal fade" id="forgotPass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Forgot password?</h4>
            </div>
            <div class="modal-body forgetPassBody">
                <div id="forgotPasswordContainer">
                    <form:form modelAttribute="loginUser"
                               action="${pageContext.request.contextPath}/forgotpassword.action"
                               id="forgotpasswordform" method="post">

                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <div class="alert alert-danger" role="alert" id="failureforgotpassword">
                                        This mail ID is invalid in our system. <br/>Please re-check the mail ID.
                                    </div>
                                    <form:input path="email" class="form-control" placeholder="Email"/>

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


<!-- start Login box -->
<div class="container" id="login-block">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
            <div class="page-icon-shadow"></div>
            <div class="login-box clearfix animated flipInY">
                <div class="page-icon">

                </div>
                <div class="login-logo">
                    <spring:url value="/resources/images/header.png" var="logo"/>
                    <a href="#"><img src="${logo}" alt="Company Logo"/></a>
                </div>
                <hr/>
                <div class="login-form">
                    <!-- Start Error box -->
                    <div class="alert alert-danger hide">
                        <button type="button" class="close" data-dismiss="alert"> &times;</button>
                        <h4>Error!</h4>
                        Your Error Message goes here
                    </div>
                    <!-- End Error box -->
                    <form:form modelAttribute="loginUser" action="${pageContext.request.contextPath}/loggedin.action"
                               id="loginForm1">
                        <c:if test="${not empty showURLAccessDenied}">
                            <span class="alert alert-danger accessDenied" id="customErrorSpan">${showURLAccessDenied}</span>
                        </c:if>
                        <form:errors class="alert alert-danger" role="alert" id="loginErrorDisplay"></form:errors>

                        <form:input path="email" placeholder="Email" class="input-field"/>
                        <form:password path="password" placeholder="Password" class="input-field"/>
                        <input type="submit" class="btn btn-login" value="Login"/>

                    </form:form>
                    <div class="login-links">
                        <a data-toggle="modal" data-target="#forgotPass" id="forgotPasswordClicker">
                            Forgot password?
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- End Login box -->
<footer class="container">
    <p id="footer-text">
        <small>PMS &copy; Shinto Jacob, Naduvilekuttu 2015</small>
    </p>
</footer>


<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapURL"/>
<spring:url value="/resources/js/custom.js" var="customjs"/>


<script type='text/javascript' src="${bootstrapURL}"></script>
<script src="${customjs}" type="text/javascript"></script>

</body>
</html>

