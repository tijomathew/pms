<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/6/15
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="newscriptLibraryTemplate.jsp" %>
</head>

<body>

<%@include file="newheaderTemplate.jsp"%>

<div id="page-container">

<%@include file="newleftMenuPanelTemplate.jsp"%>

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
    <div id="page-content">
        <div id='wrap'>
            <div id="page-heading">
                <!--<h1>Dashboard</h1>-->
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-midnightblue">
                            <div class="panel-heading">
                                <h4>
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a href="#familyRegistration" data-toggle="tab"><i
                                                    class="fa fa-list visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Family Registration</span></a>
                                        </li>
                                        <li>
                                            <a href="#familyView" data-toggle="tab"><i
                                                    class="fa fa-comments visible-xs icon-scale"></i><span
                                                    class="hidden-xs">Family View</span></a>
                                        </li>
                                    </ul>
                                </h4>
                                <!-- <div class="options">
                                  <a href="javascript:;"><i class="fa fa-cog"></i></a>
                                  <a href="javascript:;"><i class="fa fa-wrench"></i></a>
                                </div> -->
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="familyRegistration">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="panel panel-indigo">
                                                    <div class="panel-heading">
                                                        <h4>Family Details</h4>
                                                        <!--<div class="options">
                                                            <a href="javascript:;"><i class="fa fa-cog"></i></a>
                                                            <a href="javascript:;"><i class="fa fa-wrench"></i></a>
                                                            <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                                                        </div>
                                                        -->
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="form-group has-error">
                                                            <label for="inputEmail3" class="col-sm-2 control-label">Email</label>

                                                            <div class="col-sm-10">
                                                                <input type="email" class="form-control"
                                                                       id="inputEmail3" placeholder="Email">
                                                            </div>
                                                            <p class=""></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>Local Adderss</h4>
                                                        <!-- <div class="options">
                                                          <a href="javascript:;"><i class="fa fa-cog"></i></a>
                                                          <a href="javascript:;"><i class="fa fa-wrench"></i></a>
                                                          <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                                                        </div> -->
                                                    </div>
                                                    <div class="panel-body">

                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="panel panel-grape">
                                                    <div class="panel-heading">
                                                        <h4>Native Address</h4>
                                                        <!-- <div class="options">
                                                          <a href="javascript:;"><i class="fa fa-cog"></i></a>
                                                          <a href="javascript:;"><i class="fa fa-wrench"></i></a>
                                                          <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                                                        </div> -->
                                                    </div>
                                                    <div class="panel-body">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane " id="familyView">

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
            <!-- container -->
        </div>
        <!--wrap -->
    </div>
    <!-- page-content -->

<%@include file="newfooterPanelTemplate.jsp"%>

</div>

</body>
</html>
