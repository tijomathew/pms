<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 20/5/15
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <spring:url value="/resources/js/jquery-1.11.0.min.js" var="jqueryURL"/>
    <script src="${jqueryURL}" type="text/javascript"
            language="javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            window.location.replace('login.action');
        });
    </script>
</head>
<body>
</body>
</html>
