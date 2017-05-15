<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 04/05/17
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <%@ include file="scriptLibraryTemplate.jsp" %>

    <spring:url value="/processbanktransfer.action" var="realexPaymentActionURL"/>

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
                                                        <h4>Realex Payment</h4>
                                                    </div>


                                                    <div class="panel-body">
                                                        <div class="col-md-12">
                                                            <div class="panel">
                                                                <form:form modelAttribute="bankTransfer" method="post"
                                                                           action="${realexPaymentActionURL}">
                                                                <div class="form-group">
                                                                    <label for="transferAmount"
                                                                           class="col-sm-2 control-label required">Transfer
                                                                        Amount</label>

                                                                    <div class="col-sm-3">
                                                                        <form:input path="transferAmount"
                                                                                    class="form-control"/>
                                                                    </div>
                                                                    <label for="category"
                                                                           class="col-sm-2 control-label required">Category </label>

                                                                    <div class="col-sm-3">
                                                                        <form:select path="category"
                                                                                     class="form-control js-example-basic-single"
                                                                                     items="${categoryMap}"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="comment"
                                                                           class="col-sm-2 control-label required">Comment</label>

                                                                    <div class="col-sm-3">
                                                                        <form:input path="comment"
                                                                                    class="form-control"/>
                                                                    </div>

                                                                </div>


                                                            </div>
                                                        </div>
                                                        <input type="submit" class="btn btn-success"
                                                               value="Click To Pay">
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
            <!--wrap -->
        </div>

    </div>

    <%@include file="footerPanelTemplate.jsp" %>
</body>

</html>
