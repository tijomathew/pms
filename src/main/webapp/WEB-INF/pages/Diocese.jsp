<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 19/03/17
  Time: 06:00
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="scriptLibraryTemplate.jsp" %>

  <spring:url value="/resources/js/creatediocesegridlayout.js" var="dioceseGridURL"/>
  <spring:url value="/adddiocese.action" var="dioceseActionURL"/>

  <script src="${dioceseGridURL}" type="text/javascript"
          language="javascript"></script>


  <script type="text/javascript">
    jQuery(document).ready(function () {

      backToTop();

      loadDioceseGrid();


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
  <div id="page-content">
    <div id='wrap'>
      <div class="container padding7">
        <div class="row">
          <div class="col-md-12">
            <div class="panel panel-midnightblue">
              <div class="panel-body">
                <div class="tab-content">
                  <div class="tab-pane active" id="tabs-1">
                    <div class="row">
                      <div class="col-md-12">

                        <div class="panel outer-border">
                          <div class="panel-heading">
                            <h4>Diocese</h4>
                          </div>


                          <div class="panel-body">

                            <table id="dioceseGrid"></table>
                            <div id="dioceseGridPager"></div>

                            <div class="tab-content" style="padding: 10px;">

                              <div class="tab-pane active">


                                <div class="panel hidedisplay" id="panelDiv">

                                  <div class="panel-heading">
                                    <h4>
                                      <ul class="nav nav-tabs">
                                        <li class="active">
                                          <a href="#diocese1" data-toggle="tab"><i
                                                  class="fa fa-list visible-xs icon-scale"></i><span
                                                  class="hidden-xs">Diocese Details</span></a>
                                        </li>
                                        <li>
                                          <a href="#diocese2" data-toggle="tab"><i
                                                  class="fa fa-comments visible-xs icon-scale"></i><span
                                                  class="hidden-xs">Local Address</span></a>
                                        </li>
                                      </ul>
                                    </h4>
                                  </div>

                                  <div class="panel-body">
                                    <form:form modelAttribute="diocese"
                                               action="${dioceseActionURL}" method="post"
                                               id="dioceseForm"
                                               cssClass="form-horizontal">
                                      <div class="tab-content">

                                        <div class="tab-pane active" id="diocese1">

                                          <div class="col-md-12">
                                            <div class="panel">
                                              <div class="form-group">
                                                <form:hidden
                                                        path="id"
                                                        id="id"/>
                                                <label for="dioceseName"
                                                       class="col-sm-2 control-label required">Name</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="dioceseName"
                                                          id="dioceseName"
                                                          class="form-control"/>
                                                </div>
                                                <label for="dioceseNo"
                                                       class="col-sm-2 control-label">Diocese No.</label>

                                                <div class="col-sm-3">
                                                  <form:input path="dioceseNo"
                                                              id="dioceseNo"
                                                              class="form-control"
                                                              readonly="true"/>
                                                </div>
                                              </div>

                                              <div class="form-group">
                                                <label for="place"
                                                       class="col-sm-2 control-label required">Place</label>

                                                <div class="col-sm-3">
                                                  <form:input path="place"
                                                              id="place"
                                                              class="form-control"/>
                                                </div>
                                                <label for="patronName"
                                                       class="col-sm-2 control-label">Patron
                                                  Name</label>

                                                <div class="col-sm-3">
                                                  <form:input path="patronName"
                                                              id="patronName"
                                                              class="form-control"/>
                                                </div>
                                              </div>

                                              <div class="form-group">
                                                <label for="landLineNo"
                                                       class="col-sm-2 control-label">Land Line No.</label>

                                                <div class="col-sm-3">
                                                  <form:input path="landLineNo"
                                                              id="landLineNo"
                                                              class="form-control"/>
                                                </div>
                                                <label for="mobileNo"
                                                       class="col-sm-2 control-label">Mobile No.</label>

                                                <div class="col-sm-3">
                                                  <form:input path="mobileNo"
                                                              id="mobileNo"
                                                              class="form-control"/>
                                                </div>
                                              </div>

                                              <div class="form-group">
                                                <label for="faxNo"
                                                       class="col-sm-2 control-label">Fax No.</label>

                                                <div class="col-sm-3">
                                                  <form:input path="faxNo"
                                                              id="faxNo"
                                                              class="form-control"/>
                                                </div>
                                                <label for="registeredDate"
                                                       class="col-sm-2 control-label">Registered
                                                  Date</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="registeredDate"
                                                          id="registeredDate"
                                                          class="form-control"
                                                          readonly="true"/>
                                                </div>
                                              </div>
                                            </div>
                                          </div>
                                        </div>

                                        <div class="tab-pane" id="diocese2">

                                          <div class="col-md-12">
                                            <div class="panel">
                                              <div class="form-group">
                                                <label for="localAddress.addressLineOne"
                                                       class="col-sm-2 control-label required">Address
                                                  Line 1</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="localAddress.addressLineOne"
                                                          id="localAddressaddressLineOne"
                                                          class="form-control"/>
                                                </div>
                                                <label for="localAddress.addressLineTwo"
                                                       class="col-sm-2 control-label required">Address
                                                  Line 2</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="localAddress.addressLineTwo"
                                                          id="localAddressaddressLineTwo"
                                                          class="form-control"/>
                                                </div>
                                              </div>

                                              <div class="form-group">
                                                <label for="localAddress.addressLineThree"
                                                       class="col-sm-2 control-label">Address
                                                  Line 3</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="localAddress.addressLineThree"
                                                          id="localAddressaddressLineThree"
                                                          class="form-control"/>
                                                </div>
                                                <label for="localAddress.addressLineTwo"
                                                       class="col-sm-2 control-label required">Town</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="localAddress.town"
                                                          id="localAddresstown"
                                                          class="form-control"/>
                                                </div>
                                              </div>

                                              <div class="form-group">
                                                <label for="localAddress.county"
                                                       class="col-sm-2 control-label required">County</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="localAddress.county"
                                                          id="localAddresscounty"
                                                          class="form-control"/>
                                                </div>
                                                <label for="localAddress.pin"
                                                       class="col-sm-2 control-label required">Pin
                                                  code</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="localAddress.pin"
                                                          id="localAddresspin"
                                                          class="form-control"/>
                                                </div>
                                              </div>

                                              <div class="form-group">
                                                <label for="localAddress.addressLineThree"
                                                       class="col-sm-2 control-label">Country</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="localAddress.country"
                                                          id="localAddresscountry"
                                                          class="form-control"
                                                          readonly="true"/>
                                                </div>
                                              </div>
                                            </div>
                                          </div>
                                        </div>
                                      </div>
                                    </form:form>
                                  </div>

                                </div>


                              </div>

                            </div>
                          </div>

                        </div>
                      </div>
                    </div>
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

  <%@include file="footerPanelTemplate.jsp" %>

</div>

</body>
</html>

