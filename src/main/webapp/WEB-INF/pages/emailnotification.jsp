<%--
  Created by IntelliJ IDEA.
  User: tijo
  Date: 28/4/16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagLibraryTemplate.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="scriptLibraryTemplate.jsp" %>

    <spring:url value="/resources/css/normalize.css" var="normalizecssURL"/>
    <spring:url value="/resources/css/selectize.bootstrap2.css" var="selectizeBootstrap2cssURL"/>
    <spring:url value="/resources/css/selectize.bootstrap3.css" var="selectizeBootstrap3cssURL"/>
    <spring:url value="/resources/css/selectize.default.css" var="selectizedefaultcssURL"/>
    <spring:url value="/resources/css/selectize.legacy.css" var="selectizelegacycssURL"/>
    <spring:url value="/resources/css/styles.css" var="stylescssURL"/>
    <spring:url value="/resources/css/bootstrap-wysihtml5-0.0.2.css" var="bootstrapwysihtmlcssURL"/>

    <link type="text/css" rel="stylesheet" href="${normalizecssURL}"/>
    <link type="text/css" rel="stylesheet" href="${selectizeBootstrap2cssURL}"/>
    <link type="text/css" rel="stylesheet" href="${selectizeBootstrap3cssURL}"/>
    <link type="text/css" rel="stylesheet" href="${selectizedefaultcssURL}"/>
    <link type="text/css" rel="stylesheet" href="${selectizelegacycssURL}"/>
    <link type="text/css" rel="stylesheet" href="${stylescssURL}"/>
    <link type="text/css" rel="stylesheet" href="${bootstrapwysihtmlcssURL}"/>

    <spring:url value="/resources/js/selectize.js" var="selectizeURL"/>
    <spring:url value="/resources/js/wysihtml5-0.3.0_rc2.js" var="wysihtmlmailtemplateURL"/>
    <spring:url value="/resources/js/bootstrap-wysihtml5-0.0.2.js" var="bootstrapmailtemplateURL"/>

    <script type='text/javascript' src="${selectizeURL}"></script>
    <script type='text/javascript' src="${wysihtmlmailtemplateURL}"></script>
    <script type='text/javascript' src="${bootstrapmailtemplateURL}"></script>

    <spring:url value="/addfamily.action" var="familyActionURL"/>


    <script type="text/javascript">
        $(document).ready(function () {

            backToTop();

            var REGEX_EMAIL = '([a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*@' +
                    '(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)';

            var emailDisplayData;
            $.ajax({
                type: 'GET',
                url: '${pageContext.request.contextPath}/createmailidlist.action',
                dataType: 'json',
                success: function (response) {
                    caller(response);
                    return true;
                }
            });

            /*var data = [
             {email: 'pintu@gmail.com', name: 'Pintu Jacob'},
             {email: 'bilgy@gmail.com', name: 'Bilgy Pintu'},
             {email: 'iva@gmail.com', name: 'Iva Pintu'},
             {email: 'tijo@cufa.ie', name: 'Tijo Mathew'},
             {email: 'febin@cufa.ie', name: 'Febin J'},
             {email: 'arun@cufa.ie', name: 'Arun Kumar'},
             {email: 'alex@cufa.ie', name: 'Alex Thomas'}
             ];*/



            $('.emailServiceTextArea').wysihtml5({
                "emphasis": true,
                "font-styles": true,
                "lists": true, //(Un)ordered lists, e.g. Bullets, Numbers. Default true
                "html": false, //Button which allows you to edit the generated HTML. Default false
                "link": false, //Button to insert a link. Default true
                "image": false, //Button to insert an image. Default true,
                "color": false

            });

        });

        function caller(data){
            $('#select-to').selectize({
                persist: false,
                maxItems: null,
                valueField: 'email',
                labelField: 'name',
                searchField: ['name', 'email'],
                options: data,
                render: {
                    item: function (item, escape) {
                        return '<div>' +
                                (item.name ? '<span class="name">' + escape(item.name) + '</span>' : '') +
                                (item.email ? '<span class="email">' + escape(item.email) + '</span>' : '') +
                                '</div>';
                    },
                    option: function (item, escape) {
                        var label = item.name || item.email;
                        var caption = item.name ? item.email : null;
                        return '<div>' +
                                '<span style="color:black">' + escape(label) + '</span>' +
                                (caption ? '<span class="caption">' + escape(caption) + '</span>' : '') +
                                '</div>';
                    }
                },
                createFilter: function (input) {
                    var match, regex;

                    // email@address.com
                    regex = new RegExp('^' + REGEX_EMAIL + '$', 'i');
                    match = input.match(regex);
                    if (match) return !this.options.hasOwnProperty(match[0]);

                    // name <email@address.com>
                    regex = new RegExp('^([^<]*)\<' + REGEX_EMAIL + '\>$', 'i');
                    match = input.match(regex);
                    if (match) return !this.options.hasOwnProperty(match[2]);

                    return false;
                },
                create: function (input) {
                    if ((new RegExp('^' + REGEX_EMAIL + '$', 'i')).test(input)) {
                        return {email: input};
                    }
                    var match = input.match(new RegExp('^([^<]*)\<' + REGEX_EMAIL + '\>$', 'i'));
                    if (match) {
                        return {
                            email: match[2],
                            name: $.trim(match[1])
                        };
                    }
                    alert('Invalid email address.');
                    return false;
                }
            });
        }
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
            <div class="container text-container" style="background-color: #C6ECEF !important;">

                <div class="jumbotron family-heading-text" style="background-color: #C6ECEF !important;">
                    <label for="select-to">Email:</label>
                    <select id="select-to" class="contacts selectized" placeholder="Pick some people..."
                            multiple="multiple" tabindex="-1" style="display: none;"></select>
                </div>

                <div class="form-group">
                    <input name="subject" class="form-control" placeholder="Add Subject" style=" margin-left: 5px;"
                           id="subject"/>
                </div>

                <div class="form-group">
                    <textarea name="message" class="form-control emailServiceTextArea" placeholder="Add Message Content"
                              rows="16" id="message"></textarea>
                </div>

                <div class="col-sm-2 emailServiceBtn" style="padding-right: 0px">
                    <button class="form-control btn btn-sm btn-primary" onclick="return false;" id="sendEmailButton">
                        Send Email
                    </button>
                </div>
            </div>
            <!--wrap -->
        </div>

    </div>


</div>
</body>
</html>
