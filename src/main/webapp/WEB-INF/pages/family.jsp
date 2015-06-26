<%--  
  User: tijo
  Date: 12/10/14
  Time: 10:39 PM  
--%>
<%@include file="tagLibraryTemplate.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title id="title">Family</title>

    <spring:url value="/resources/js/familyvalidator.js" var="familyValidator"/>
    <spring:url value="/resources/js/familyselectbox.js" var="familySelectBox"/>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapUrl"/>

    <spring:url value="/addfamily.action" var="familyActionURL"/>

    <link href="${bootstrapUrl}" rel="stylesheet">

    <%@include file="scriptlibraryTemplate.jsp" %>

    <script src="${familyValidator}" type="text/javascript"
            language="javascript"></script>
    <script src="${familySelectBox}" type="text/javascript"
            language="javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#familyAccordian").accordion();
            <c:if test="${showForPrayerUnitAdmin == false && showForFamilyUser == false}">
            loadSelectBox("${pageContext.request.contextPath}");
            </c:if>
        });
    </script>

    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('#ui-id-2').bind("click", function () {
                loadFamilyGrid();
            });

        });

        function loadFamilyGrid() {

            jQuery("#familyGrid").jqGrid(
                    {
                        jsonreader: {
                            root: "rows",
                            repeatitems: true,
                            cell: "cells",
                            id: "id"


                        },
                        url: '${pageContext.request.contextPath}/displayfamilygrid.action',
                        autoencode: true,
                        mtype: 'GET',
                        datatype: 'json',
                        rowList: [2, 4, 6],
                        colNames: ['Family ID', 'Family Name', 'parishInNative', 'dioceseInNative', 'dateOfRegistration', 'parishLocal', "massCenter", "prayerUnit", "localAddress", "nativeAddress"],
                        colModel: [

                            {name: 'familyID', index: 'familyID', width: 90},
                            {name: 'familyName', index: 'familyName', width: 100},
                            {name: 'parishInNative', index: 'parishInNative', width: 100},
                            {name: 'dioceseInNative', index: 'dioceseInNative', width: 100},
                            {name: 'dateOfRegistration', index: 'dateOfRegistration', width: 100},
                            {name: 'parishLocal', index: 'parishLocal', width: 100},
                            {name: 'massCenter', index: 'massCenter', width: 100},
                            {name: 'prayerUnit', index: 'prayerUnit', width: 100},
                            {name: 'localAddress', index: 'localAddress', width: 100},
                            {name: 'nativeAddress', index: 'nativeAddress', width: 100},
                        ],
                        rowNum: 2,
                        pager: '#familyGridPager',
                        sortname: 'id',
                        viewrecords: true,
                        sortorder: "desc",
                        caption: "Families",
                        autowidth: true,
                        shrinkToFit: true,
                        height: 'auto',
                        width: 'auto'
                    });
            jQuery("#familyGrid").jqGrid('navGrid', '#familyGridPager', {edit: true, add: true, del: true});
        }


    </script>


</head>
<body>
<%@include file="toolbarTemplate.jsp" %>


<%@include file="menupanelTemplate.jsp" %>

<div class="outer-center container">
    <div class="middle-center">
        <div class="inner-center">
            <div id="tabs" class="contentTabs">
                <ul class="tabHead">
                    <li><a href="#tabs-1">Family Registration</a></li>
                    <li><a href="#tabs-2">Family View</a></li>
                </ul>
                <div id="tabs-1" class="contentTabs">
                    <form:form modelAttribute="family"
                               action="${familyActionURL}" method="post"
                               id="familyForm1" cssClass="form-inline">
                        <div id="familyAccordian">
                            <h3>Family Details</h3>

                            <div>
                                <section class="contentDoc">
                                    <div class="mainConte">

                                            <div class="control-group" id="familyName">
                                                <label class="control-label">Family Name :</label>

                                                <div class="controls">
                                                    <form:input path="familyName" id="familyName"
                                                                class="textBox"/>
                                                            <span class="help-inline"><form:errors
                                                                    path="familyName"/></span>
                                                </div>
                                            </div>

                                            <div class="control-group" id="parishInNative">
                                                <label class="control-label">Native Parish :</label>

                                                <form:input path="parishInNative"
                                                                                  id="parishInNative"/>
                                                            <span class="help-inline"><form:errors
                                                                    path="parishInNative"/></span>

                                            </div>

                                            <div class="control-group" id="dioceseInNative">
                                                <label class="control-label">Native Diocese :</label>

                                                <div class="controls"><form:input path="dioceseInNative"
                                                                                  id="dioceseInNative"/>
                                                    <span class="help-inline"><form:errors
                                                            path="dioceseInNative"/> </span>
                                                </div>
                                            </div>

                                            <div class="control-group" id="dateOfRegistration">
                                                <label class="control-label">Date Of Registration :</label>

                                                <div class="controls"><form:input path="dateOfRegistration"
                                                                                  id="dateOfRegistration"/>
                                                    <span class="help-inline"><form:errors
                                                            path="dateOfRegistration"/> </span>
                                                </div>
                                            </div>

                                            <c:if test="${showForPrayerUnitAdmin == false && showForFamilyUser == false}">

                                                <div class="control-group" id="parishId">
                                                    <label class="control-label">Parish :</label>

                                                    <div class="controls"><form:select path="parishId"
                                                                                       id="parishSelectBox">
                                                        <option value="select">--Please select--</option>
                                                    </form:select>
                                                        <span class="help-inline"><form:errors path="parishId"/> </span>
                                                    </div>
                                                </div>

                                                <div class="control-group" id="massCenterId">
                                                    <label class="control-label">Mass Center :</label>

                                                    <div class="controls"><form:select path="massCenterId"
                                                                                       id="massCenterSelectBox">
                                                        <option value="select">--Please select--</option>
                                                    </form:select>
                                                        <span class="help-inline"><form:errors
                                                                path="massCenterId"/> </span>
                                                    </div>
                                                </div>

                                                <div class="control-group" id="prayerUnitId">
                                                    <label class="control-label">Prayer Unit :</label>

                                                    <div class="controls"><form:select path="prayerUnitId"
                                                                                       id="wardSelectBox">
                                                        <option value="select">--Please select--</option>
                                                    </form:select>
                                                        <span class="help-inline"><form:errors
                                                                path="prayerUnitId"/> </span>
                                                    </div>
                                                </div>

                                            </c:if>

                                            <c:if test="${(showForPrayerUnitAdmin == true && showForFamilyUser == false)||(showForPrayerUnitAdmin == false && showForFamilyUser == true)}">

                                                <div class="control-group" id="parishId">
                                                    <label class="control-label">Parish :</label>

                                                    <div class="controls"><form:select path="parishId"
                                                                                       items="${parishList}"></form:select>
                                                        <span class="help-inline"><form:errors path="parishId"/> </span>
                                                    </div>
                                                </div>

                                                <div class="control-group" id="massCenterId">
                                                    <label class="control-label">Mass Center :</label>

                                                    <div class="controls"><form:select path="massCenterId"
                                                                                       items="${massCenterList}"></form:select>
                                                        <span class="help-inline"><form:errors
                                                                path="massCenterId"/> </span>
                                                    </div>
                                                </div>

                                                <div class="control-group" id="prayerUnitId">
                                                    <label class="control-label">Prayer Unit :</label>

                                                    <div class="controls"><form:select path="prayerUnitId"
                                                                                       items="${prayerUnitList}"></form:select>
                                                        <span class="help-inline"><form:errors
                                                                path="prayerUnitId"/> </span>
                                                    </div>
                                                </div>

                                            </c:if>
                                    </div>
                                </section>
                            </div>
                            <h3>Local Adderss</h3>

                            <div>
                                <section class="contentDoc">
                                    <div class="mainConte">
                                        <table>
                                            <tr>
                                                <td>Address Line 1 :</td>
                                                <td><form:input path="localAddress.addressLineOne"
                                                                id="localAddressaddressLineOne"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 2 :</td>
                                                <td><form:input path="localAddress.addressLineTwo"
                                                                id="localAddressaddressLineTwo"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 3 :</td>
                                                <td><form:input path="localAddress.addressLineThree"
                                                                id="localAddressaddressLineThree"/></td>
                                            </tr>
                                            <tr>
                                                <td>Town:</td>
                                                <td><form:input path="localAddress.town" id="localAddresstown"/></td>
                                            </tr>
                                            <tr>
                                                <td>County:</td>
                                                <td><form:input path="localAddress.county"
                                                                id="localAddresscounty"/></td>
                                            </tr>
                                            <tr>
                                                <td>Pin code:</td>
                                                <td><form:input path="localAddress.pin" id="localAddresspin"/></td>
                                            </tr>
                                            <tr>
                                                <td>Country:</td>
                                                <td><form:input path="localAddress.country"
                                                                id="localAddresscountry"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </section>
                            </div>
                            <h3>Native Address</h3>

                            <div>
                                <section class="contentDoc">
                                    <div class="mainConte">
                                        <table>
                                            <tr>
                                                <td>Address Line 1 :</td>
                                                <td><form:input path="nativeAddress.addressLineOne"
                                                                id="nativeAddressaddressLineOne"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 2 :</td>
                                                <td><form:input path="nativeAddress.addressLineTwo"
                                                                id="nativeAddressaddressLineTwo"/></td>
                                            </tr>
                                            <tr>
                                                <td>Address Line 3 :</td>
                                                <td><form:input path="nativeAddress.addressLineThree"
                                                                id="nativeAddressaddressLineThree"/></td>
                                            </tr>
                                            <tr>
                                                <td>Post Office :</td>
                                                <td><form:input path="nativeAddress.postOffice"
                                                                id="nativeAddresspostOffice"/></td>
                                            </tr>
                                            <tr>
                                                <td>District :</td>
                                                <td><form:input path="nativeAddress.district"
                                                                id="nativeAddressdistrict"/></td>
                                            </tr>
                                            <tr>
                                                <td>State :</td>
                                                <td><form:input path="nativeAddress.state"
                                                                id="nativeAddressstate"/></td>
                                            </tr>
                                            <tr>
                                                <td>Country :</td>
                                                <td><form:input path="nativeAddress.country"
                                                                id="nativeAddresscountry"/></td>
                                            </tr>
                                            <tr>
                                                <td>Pin code :</td>
                                                <td><form:input path="nativeAddress.pin" id="nativeAddresspin"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </section>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button type="submit" value="Save" class="btn btn-primary"/>
                        </div>


                    </form:form>
                </div>
                <div id="tabs-2" class="contentTabs">
                    <table id="familyGrid"></table>
                    <div id="familyGridPager"></div>
                </div>
            </div>
        </div>
        <div class="footer ui-layout-south">
            <h3>SEARCH PANEL</h3>

            <div class="filter_left">
            </div>
            <div class="filter_left bottomElementsPanel">
                <table>
                    <tr>
                        <td>
                            <button type="button" class="filterButton">Find</button>
                        </td>
                        <%--<td>
                            Test
                        </td>
                        <td>
                            <select>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                                <option>sdfjsdkf</option>
                            </select>
                        </td>
                        <td>
                            Test
                        </td>
                        <td>
                            <input type="text"/>
                        </td>--%>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="filterButton">Print</button>
                        </td>
                        <%-- <td>
                             Test
                         </td>
                         <td>
                             <select>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                                 <option>sdfjsdkf</option>
                             </select>
                         </td>
                         <td>
                             Test
                         </td>
                         <td>
                             <input type="text"/>
                         </td>--%>
                    </tr>
                </table>
            </div>
            <div class="bottomRight">

            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function collectFormData(fields) {
        var data = {};
        for (var i = 0; i < fields.length; i++) {
            var $item = $(fields[i]);
            data[$item.attr('name')] = $item.val();
        }
        return data;
    }

    $(document).ready(function () {
        var $form = $('#familyForm1');
        $form.bind('submit', function (e) {
            // Ajax validation
            var $inputs = $form.find('input');
            var data = collectFormData($inputs);

            $.post('${familyActionURL}', data, function (response) {
                $form.find('.control-group').removeClass('error');
                $form.find('.help-inline').empty();
                $form.find('.alert').remove();

                if (response.statusMessage == 'FAIL') {
                    for (var i = 0; i < response.customErrorMessages.length; i++) {
                        var item = response.customErrorMessages[i];
                        var $controlGroup = $('#' + item.fieldName);
                        $controlGroup.addClass('error');
                        $controlGroup.find('.help-inline').html(item.message);
                    }
                } else {
                    var $alert = $('<div class="alert alert-success"></div>');
                    $alert.html(response.customErrorMessages);
                    $alert.prependTo($form);
                }
            }, 'json');

            e.preventDefault();
            return false;
        });
    });
</script>
</body>
</html>