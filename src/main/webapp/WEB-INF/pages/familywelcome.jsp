<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 8/9/15
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="scriptLibraryTemplate.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#headerText").animate({left: 200, opacity: "show"}, 2000);
        });
    </script>
</head>

<body>

<%@include file="headerTemplate.jsp" %>

<div id="page-container">

    <%@include file="leftMenuPanelTemplate.jsp" %>

    <!-- BEGIN RIGHTBAR -->
    <div id="page-rightbar">

        <div id="widgetarea">
            <div class="widget">
                <div class="widget-heading">
                    <a href="javascript:;" data-toggle="collapse" data-target="#accsummary"><h4>Search Panel</h4></a>
                </div>
                <div class="widget-body collapse in" id="accsummary">
                    Search Criteria Entries shows here in this panel
                </div>
            </div>


        </div>
    </div>
    <!-- END RIGHTBAR -->
    <div id="page-content" style="background-color: #ffffff !important;">
        <div id='wrap'>
            <div class="container text-center text-container" style="background-color: #C6ECEF !important;">

                <div class="jumbotron family-heading-text" style="background-color: #C6ECEF !important;">
                    <h1 style="font-weight: bold;font-style: oblique;text-decoration: underline;display:none;"
                        class="family-heading-text" id="headerText">Welcome to Syro Malabar, Dublin Ireland</h1>

                    <p style="font-weight: bold" class="para">1. Please fill your family details first.</p>

                    <p style="font-weight: bold" class="para">2. Add each member in your family with details.</p>

                    <p style="font-weight: bold" class="para">3. Keep your Login Name and Password for future
                        access.</p>
                </div>

            </div>
            <!--wrap -->
        </div>

    </div>

    <%@include file="footerPanelTemplate.jsp" %>
</body>
</html>
