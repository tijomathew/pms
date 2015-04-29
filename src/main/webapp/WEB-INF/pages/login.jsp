<%--  
  User: tijo
  Date: 29/9/14
  Time: 9:17 PM  
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>
    <title>PMS-Login</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.10.2.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery-ui.js" />" type="text/javascript" language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery.layout-latest.min.js" />" type="text/javascript"
            language="javascript"></script>
    <script src="<c:url value="/resources/js/jquery.validate.min.js" />" type="text/javascript"
            language="javascript"></script>
    <!-- Scripts @Bilahari -->
    <%-- <script src="<c:url value="/resources/js/utility.js" />" type="text/javascript" language="javascript"></script>--%>
    <script>
        $(document).ready(function () {
            $('#loginForm').validate({
                rules: {
                    userName: {
                        minlength: 5,
                        required: true
                    },
                    password: {
                        minlength: 5,
                        required: true
                    }
                },
                messages: {
                    userName: "Please Enter User Name",
                    password: "Please Enter Password"
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });
        });
    </script>
</head>
<body>
<div class="loginWrapper">
    <div class="loginHeader">
        <h3>Syro Malabar Church - Dublin</h3>
    </div>
    <section class="loginForm">
        <div>

            <form:form modelAttribute="loginUser" action="${pageContext.request.contextPath}/loggedin.action"
                       id="loginForm1">
                <table>
                    <tr>
                        <td>USER NAME :</td>
                        <td><form:input path="userName" id="userName" class="textBox"/></td>
                        <form:errors path="userName"></form:errors>
                        <td><input type="submit" value="Login" class="loginButton"/></td>
                    </tr>
                    <tr>
                        <td>PASSWORD :</td>
                        <td><form:password path="password" id="password" class="textBox"/></td>
                        <form:errors path="password"/>
                        <td>
                            <button type="button" class="loginButton">Cancel</button>
                        </td>
                    </tr>
                </table>
                <form:errors id="loginErrorDisplay" cssClass="error"></form:errors>
            </form:form>
        </div>
    </section>
    <section class="loginContentSection">
        <div class="loginFooter loginFooterRight">
            Copyright @ Pin2.
        </div>
        <div style="clear:both"></div>
    </section>

</div>
</body>
</html>



