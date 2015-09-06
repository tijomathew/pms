<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 27/7/15
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>

  <%@ include file="scriptLibraryTemplate.jsp" %>

  <spring:url value="/addpriestdesignation.action" var="priestDesignationActionURL"/>
  <spring:url value="/resources/js/createprayerunitgridlayout.js" var="prayerUnitGrid"/>

  <script src="${prayerUnitGrid}" type="text/javascript"
          language="javascript"></script>

  <script type="text/javascript">
    $(document).ready(function () {

      backToTop();
      loadPrayerUnitGrid();
      globalSubmissionOfForms('prayerUnitForm', '${prayerUnitActionURL}','prayerUnitGrid');
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
            <div class="panel panel-midnightblue nomargin">
              <div class="panel-body noborder nopadding">
                <div class="tab-content">
                  <div class="tab-pane active" id="tabs-1">
                    <div class="row">
                      <div class="col-md-12">
                        <div class="panel panel-grape nomargin">
                          <div class="panel-heading">
                            <h4>Priest Designation Information</h4>
                          </div>
                          <div class="panel-body nopadding">
                            <table id="prayerUnitGrid"></table>
                            <div id="prayerUnitGridPager"></div>
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


      <div class="container padding7 paddingTop0">
        <div class="row">
          <div class="col-md-12">
            <div class="panel panel-midnightblue nomargin">
              <div class="panel-heading">
                <h4>
                  <ul class="nav nav-tabs">
                    <li class="active">
                      <a href="#priestDesignation" data-toggle="tab"><i
                              class="fa fa-list visible-xs icon-scale"></i><span
                              class="hidden-xs">Assign Priest Designation</span></a>
                    </li>
                  </ul>
                </h4>
              </div>
              <div class="panel-body padding7">

                <form:form modelAttribute="priestDesignation"
                           action="${prayerUnitActionURL}" method="post"
                           id="priestDesignationForm" cssClass="form-horizontal nomargin">

                  <div class="tab-content">

                    <div class="tab-pane active" id="priestDesignation">

                      <div class="col-md-12">
                        <div class="panel panel-grape marginBottom7">
                          <div class="panel-heading">
                            <h4>Assign Priest Designation</h4>
                          </div>
                          <div class="panel-body padding7">
                            <div class="form-group">
                              <label for="prayerUnitName"
                                     class="col-sm-2 control-label">Prayer Unit
                                Name</label>

                              <div class="col-sm-3">
                                <form:input path="prayerUnitName"
                                            id="prayerUnitName" class="form-control"/>
                              </div>
                              <label for="prayerUnitCode" class="col-sm-2 control-label">Prayer
                                Unit Code</label>

                              <div class="col-sm-3">
                                <form:input path="prayerUnitCode"
                                            id="prayerUnitCode"
                                            readonly="true" class="form-control"/>
                              </div>
                            </div>
                            <div class="form-group">
                              <label for="prayerUnitPlace"
                                     class="col-sm-2 control-label">Prayer Unit
                                Place</label>

                              <div class="col-sm-3">
                                <form:input path="prayerUnitPlace"
                                            id="prayerUnitPlace" class="form-control"/>
                              </div>
                              <label for="massCentreId" class="col-sm-2 control-label">Mass
                                Center</label>

                              <div class="col-sm-3">
                                <form:select path="massCentreId"
                                             items="${massCentreMap}"
                                             id="massCentreId" class="form-control"/>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="row nomargin">
                      <div class="col-md-12 text-left">
                        <button type="submit" value="Save"
                                class="btn btn-primary defaultButtonWidth">SAVE
                        </button>
                        <button type="submit" value="Save"
                                class="btn btn-primary defaultButtonWidth">RESET
                        </button>
                      </div>
                    </div>
                  </div>
                </form:form>
              </div>
            </div>
          </div>
        </div>
        <!-- container -->
      </div>
      <!--wrap -->
    </div>

  </div>

  <%@include file="footerPanelTemplate.jsp" %>
</body>
</html>
