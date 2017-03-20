<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 11/03/17
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>

  <%@ include file="scriptLibraryTemplate.jsp" %>

  <spring:url value="/addwithdrawal.action" var="withdrawalActionURL"/>
  <spring:url value="/resources/js/createwithdrawalgridlayout.js" var="withdrawalGrid"/>

  <script src="${withdrawalGrid}" type="text/javascript"
          language="javascript"></script>

  <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

  <script type="text/javascript">
    jQuery(document).ready(function () {

      backToTop();
      loadWithdrawalGrid();
      $(".js-example-basic-single").select2();

      $('#withdrawalDate').datepicker({
        autoclose: true,
        todayHighlight: true,
        format: 'dd-mm-yyyy',
        endDate: '+0d',
        onClose: function () {
          $(this).valid();
        }
      });

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
                            <h4>Withdrawal</h4>
                          </div>


                          <div class="panel-body">

                            <table id="withdrawalGrid"></table>
                            <div id="withdrawalGridPager"></div>

                            <div class="tab-content" style="padding: 10px;">

                              <div class="tab-pane active">


                                <div class="panel hidedisplay" id="panelDiv">

                                  <div class="panel-heading">
                                    <h4>
                                      <ul class="nav nav-tabs">
                                        <li class="active">
                                          <a href="#withdrawal1"
                                             data-toggle="tab"><i
                                                  class="fa fa-list visible-xs icon-scale"></i><span
                                                  class="hidden-xs">Withdrawal Details</span></a>
                                        </li>
                                      </ul>
                                    </h4>
                                  </div>

                                  <div class="panel-body">
                                    <form:form modelAttribute="withdrawal"
                                               action="${withdrawalActionURL}"
                                               method="post"
                                               id="withdrawalForm"
                                               cssClass="form-horizontal">

                                      <div class="tab-content">

                                        <div class="tab-pane active"
                                             id="withdrawal1">

                                          <div class="col-md-12">
                                            <div class="panel">
                                              <div class="form-group">
                                                <label for="withdrawalDate"
                                                       class="col-sm-2 control-label required">Withdrawal Date</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="withdrawalDate"
                                                          id="withdrawalDate"
                                                          class="form-control"/>
                                                  <form:hidden
                                                          path="id"/>
                                                </div>
                                                <label for="category"
                                                       class="col-sm-2 control-label required">Category</label>

                                                <div class="col-sm-3">
                                                  <form:select
                                                          path="category"
                                                          id="category"
                                                          readonly="true"
                                                          class="form-control js-example-basic-single">
                                                    <form:option value="1">Cateory 1</form:option>
                                                    <form:option value="1">Cateory 2</form:option>
                                                    <form:option value="1">Cateory 3</form:option>
                                                    <form:option value="1">Cateory 4</form:option>
                                                    <form:option value="1">Cateory 5</form:option>
                                                    <form:option value="1">Cateory 6</form:option>
                                                    <form:option value="1">Cateory 7</form:option>
                                                  </form:select>
                                                </div>
                                              </div>
                                              <div class="form-group">
                                                <label for="withdrawalAmount"
                                                       class="col-sm-2 control-label required">Amount</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="withdrawalAmount"
                                                          id="withdrawalAmount"
                                                          class="form-control"/>
                                                </div>
                                                <label for="withdrawalType"
                                                       class="col-sm-2 control-label required">Withdrawal Type</label>

                                                <div class="col-sm-3">
                                                  <form:radiobutton path="withdrawalType" value="Cash" id="withdrawalType" class="form-control"/>Cash
                                                  <form:radiobutton path="withdrawalType" value="Bank" id="withdrawalType" class="form-control"/>Bank
                                                </div>

                                              </div>
                                              <div class="form-group">
                                                <label for="registeredDate"
                                                       class="col-sm-2 control-label required">Added
                                                  Date</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="registeredDate"
                                                          id="registeredDate"
                                                          class="form-control"
                                                          readonly="true"/>
                                                </div>
                                                <label for="associatedParish"
                                                       class="col-sm-2 control-label required">Parish</label>

                                                <div class="col-sm-3">
                                                  <form:select
                                                          path="associatedParish"
                                                          id="associatedParish"
                                                          items="${parishMap}"
                                                          class="form-control"/>
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
      <!--wrap -->
    </div>

  </div>

  <%@include file="footerPanelTemplate.jsp" %>
</body>
</html>
