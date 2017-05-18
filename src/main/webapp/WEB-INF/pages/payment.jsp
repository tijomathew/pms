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

  <spring:url value="/addpayment.action" var="paymentActionURL"/>
  <spring:url value="/resources/js/createpaymentgridlayout.js" var="paymentGrid"/>

  <script src="${paymentGrid}" type="text/javascript"
          language="javascript"></script>

    <spring:url value="/resources/css/select2.min.css" var="select2css"/>
    <spring:url value="/resources/js/select2.min.js" var="select2js"/>

    <link href="${select2css}" rel="stylesheet"/>
    <script src="${select2js}"></script>

  <script type="text/javascript">
    jQuery(document).ready(function () {

      backToTop();
      loadPaymentGrid();
      $(".js-example-basic-single").select2();

      $('#paymentDate').datepicker({
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
                            <h4>Payments</h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><span>Total Balance - <span
                                  id="totalBalance"></span></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>Cash In Hand - <span
                                  id="cashInHand"></span></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>Bank Balance - <span
                                  id="bankBalance"></span></span></b>
                          </div>


                          <div class="panel-body">

                            <table id="paymentGrid"></table>
                            <div id="paymentGridPager"></div>

                            <div class="tab-content" style="padding: 10px;">

                              <div class="tab-pane active">


                                <div class="panel hidedisplay" id="panelDiv">

                                  <div class="panel-heading">
                                    <h4>
                                      <ul class="nav nav-tabs">
                                        <li class="active">
                                          <a href="#payment1"
                                             data-toggle="tab"><i
                                                  class="fa fa-list visible-xs icon-scale"></i><span
                                                  class="hidden-xs">Payment Details</span></a>
                                        </li>
                                      </ul>
                                    </h4>
                                  </div>

                                  <div class="panel-body">
                                    <form:form modelAttribute="cashFlowPayment"
                                               action="${paymentActionURL}"
                                               method="post"
                                               id="paymentForm"
                                               cssClass="form-horizontal">

                                      <div class="tab-content">

                                        <div class="tab-pane active"
                                             id="payment1">

                                          <div class="col-md-12">
                                            <div class="panel">
                                              <div class="form-group">
                                                  <label for="associatedParish"
                                                         class="col-sm-2 control-label required">Parish</label>

                                                  <div class="col-sm-3">
                                                      <form:select
                                                              path="associatedParish"
                                                              id="associatedParish"
                                                              items="${parishMap}"
                                                              class="form-control"/>
                                                  </div>
                                                <label for="cashFlowDate"
                                                       class="col-sm-2 control-label required">Payment Date</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="cashFlowDate"
                                                          id="cashFlowDate"
                                                          class="form-control"/>
                                                  <form:hidden
                                                          path="id"/>
                                                </div>

                                              </div>
                                              <div class="form-group">
                                                  <label for="category"
                                                         class="col-sm-2 control-label required">Category</label>

                                                  <div class="col-sm-3">
                                                      <form:select
                                                              path="category"
                                                              id="category"
                                                              class="form-control js-example-basic-single" items="${categoryMap}">
                                                      </form:select>
                                                  </div>
                                                <label for="amount"
                                                       class="col-sm-2 control-label required">Amount</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="amount"
                                                          id="amount"
                                                          class="form-control"/>
                                                </div>

                                              </div>
                                              <div class="form-group">
                                                  <label for="cashFlowType"
                                                         class="col-sm-2 control-label required">Payment Type</label>

                                                  <div class="col-sm-3">
                                                      <form:radiobutton path="cashFlowType" value="CASH" id="paymentType" class="form-control"/>Cash
                                                      <form:radiobutton path="cashFlowType" value="BANK" id="paymentType" class="form-control"/>Bank
                                                  </div>

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

                                              </div>
                                                <div class="form-group">
                                                    <label for="description"
                                                           class="col-sm-2 control-label">Description</label>

                                                    <div class="col-sm-3">
                                                        <form:textarea
                                                                path="description"
                                                                id="description"
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