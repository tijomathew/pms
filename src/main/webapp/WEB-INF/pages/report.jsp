<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 25/03/17
  Time: 05:17
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>

  <%@ include file="scriptLibraryTemplate.jsp" %>



  <script type="text/javascript">
    jQuery(document).ready(function () {

      backToTop();

      $('#receiptDate').datepicker({
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
                            <h4>Report</h4>
                          </div>


                          <div class="panel-body">

                            <h2>Receipts & Payments Account For The Year Ended 31st December 2016</h2>

                            <table class="table table-bordered">
                              <tr>
                                <td rowspan="2"></td>
                                <td style="text-align: center;font-weight: bold">2016</td>
                                <td style="text-align: center;font-weight: bold">2015</td>
                              </tr>

                              <tr>
                                <td style="text-align: center;font-weight: bold">&euro;</td>
                                <td style="text-align: center;font-weight: bold">&euro;</td>
                              </tr>

                              <tr>
                                <th colspan="5" class="success">Receipts</th>
                              </tr>

                              <tbody>
                              <tr>
                                <td>Church Collections</td>
                                <td style="text-align: right">5000</td>
                                <td style="text-align: right">5100</td>
                              </tr>
                              <tr>
                                <td>Annual Collections</td>
                                <td style="text-align: right">3100</td>
                                <td style="text-align: right">3000</td>
                              </tr>
                              <tr>
                                <td>Christmas Carol Collections</td>
                                <td style="text-align: right">600</td>
                                <td style="text-align: right">650</td>
                              </tr>
                              <tr>
                                <td>Annual Feast Collections</td>
                                <td style="text-align: right">400</td>
                                <td style="text-align: right">300</td>
                              </tr>
                              <tr>
                                <td style="text-align: right;font-weight: bold">Total</td>
                                <td style="text-align: right;font-weight: bold">9100</td>
                                <td style="text-align: right;font-weight: bold">9050</td>
                              </tr>

                              <tr>
                                <th colspan="3" class="danger">Payments</th>
                              </tr>

                              <tr>
                                <td>Church Rent</td>
                                <td style="text-align: right">600</td>
                                <td style="text-align: right">600</td>
                              </tr>
                              <tr>
                                <td>Obligations for Masses</td>
                                <td style="text-align: right">300</td>
                                <td style="text-align: right">400</td>
                              </tr>
                              <tr>
                                <td>Altar Boys Training</td>
                                <td style="text-align: right">100</td>
                                <td style="text-align: right">150</td>
                              </tr>
                              <tr>
                                <td>Rent Music Equipment (Choir Expenses)</td>
                                <td style="text-align: right">0</td>
                                <td style="text-align: right">100</td>
                              </tr>
                              <tr>
                                <td>Refreshments</td>
                                <td style="text-align: right">300</td>
                                <td style="text-align: right">400</td>
                              </tr>

                              <tr>
                                <td style="text-align: right;font-weight: bold">Total</td>
                                <td style="text-align: right;font-weight: bold">1300</td>
                                <td style="text-align: right;font-weight: bold">1650</td>
                              </tr>

                              <tr>
                                <td style="text-align: right;font-weight: bold">Excess/(Deficit) of Receipts over Payments made</td>
                                <td style="text-align: right;font-weight: bold">7800</td>
                                <td style="text-align: right;font-weight: bold">7400</td>
                              </tr>

                              <tr>
                                <td style="text-align: right;font-weight: bold">Opening Balances Cash and Bank</td>
                                <td style="text-align: right;font-weight: bold">2000</td>
                                <td style="text-align: right;font-weight: bold">1500</td>
                              </tr>

                              <tr>
                                <td style="text-align: right;font-weight: bold">Closing Balances Cash and Bank</td>
                                <td style="text-align: right;font-weight: bold">9800</td>
                                <td style="text-align: right;font-weight: bold">8900</td>
                              </tr>

                              </tbody>
                            </table>

                            <%--<table id="receiptGrid"></table>
                            <div id="receiptGridPager"></div>--%>

                            <%--<div class="tab-content" style="padding: 10px;">

                              <div class="tab-pane active">


                                <div class="panel hidedisplay" id="panelDiv">

                                  <div class="panel-heading">
                                    <h4>
                                      <ul class="nav nav-tabs">
                                        <li class="active">
                                          <a href="#receipt1"
                                             data-toggle="tab"><i
                                                  class="fa fa-list visible-xs icon-scale"></i><span
                                                  class="hidden-xs">Receipt Details</span></a>
                                        </li>
                                      </ul>
                                    </h4>
                                  </div>

                                  <div class="panel-body">
                                    <form:form modelAttribute="receipt"
                                               action="${receiptActionURL}"
                                               method="post"
                                               id="receiptForm"
                                               cssClass="form-horizontal">

                                      <div class="tab-content">

                                        <div class="tab-pane active"
                                             id="receipt1">

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
                                                <label for="receiptDate"
                                                       class="col-sm-2 control-label required">Receipt
                                                  Date</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="receiptDate"
                                                          id="receiptDate"
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
                                                <label for="receiptAmount"
                                                       class="col-sm-2 control-label required">Amount</label>

                                                <div class="col-sm-3">
                                                  <form:input
                                                          path="receiptAmount"
                                                          id="receiptAmount"
                                                          class="form-control"/>
                                                </div>
                                              </div>
                                              <div class="form-group">
                                                <label for="receiptType"
                                                       class="col-sm-2 control-label required">Receipt
                                                  Type</label>

                                                <div class="col-sm-3">
                                                  <form:radiobutton
                                                          path="receiptType"
                                                          value="Cash"
                                                          id="receiptType"
                                                          class="form-control"/>Cash
                                                  <form:radiobutton
                                                          path="receiptType"
                                                          value="Bank"
                                                          id="receiptType"
                                                          class="form-control"/>Bank
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

                            </div>--%>

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