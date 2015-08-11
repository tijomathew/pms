/**
 * Created by tijo on 1/7/15.
 */
//get the form name and submit the form value and show the respective validation or success or failure or exception message in UI.

function globalSubmissionOfForms(formId, gridId) {

    var $form = $('#' + formId);
    var data = $("#" + formId).serialize();

    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        dataType: 'json',
        data: data,
        success: function (response) {
            if (response.statusCode == 'FAIL') {

                for (var i = 0; i < response.customErrorMessages.length; i++) {
                    var item = response.customErrorMessages[i];
                    var itemFieldName = item.fieldName
                    var $field = $($form).find("[name='" + itemFieldName + "']");
                    $("label[for='" + itemFieldName + "']").addClass('labelErrorAlert');
                    $field.addClass('borderRed');
                    $field.attr('title', item.message);
                    $field.tooltip({
                        placement: "top",
                        trigger: "hover",
                        template: '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner tooltip-error"></div></div>'
                    });
                    $field.change(function () {
                        $("label[for='" + itemFieldName + "']").removeClass('labelErrorAlert');
                        $(this).removeClass('borderRed');
                        $(this).removeAttr('title');
                        $(this).tooltip('destroy');
                    });
                }
                return [true, "", ""];

            }
            else if (response.statusCode == 'SUCCESS') {
                jQuery.jqGrowl.timeout = 2000;
                jQuery.jqGrowl.init({right: '8px', bottom: '', top: '8px', left: ''});
                jQuery.jqGrowl.msg(response.customErrorMessages[0].fieldName + ' ' + response.customErrorMessages[0].message, 'SUCCESS');

                /*if(JSON.parse(response.customErrorMessages[0].fieldName) == "error")
                 jQuery('#jqgrowlContainer > ul').addClass('errorContainer');
                 else if(JSON.parse(response.customErrorMessages[0].fieldName) == "warn"){
                 jQuery('#jqgrowlContainer > ul').addClass('warnContainer');
                 }
                 else if(JSON.parse(response.customErrorMessages[0].fieldName) == "success"){
                 jQuery('#jqgrowlContainer > ul').addClass('successContainer');
                 }*/
                $('#' + formId)[0].reset();
                jQuery('#' + gridId).trigger('reloadGrid');
            }
            else if (response.statusCode == 'FAILURE') {
                jQuery.jqGrowl.timeout = 2000;
                jQuery.jqGrowl.init({right: '8px', bottom: '', top: '8px', left: ''});
                jQuery.jqGrowl.msg(response.customErrorMessages[0].fieldName + ' ' + response.customErrorMessages[0].message, 'FAILURE');

                /*if(JSON.parse(response.customErrorMessages[0].fieldName) == "error")
                 jQuery('#jqgrowlContainer > ul').addClass('errorContainer');
                 else if(JSON.parse(response.customErrorMessages[0].fieldName) == "warn"){
                 jQuery('#jqgrowlContainer > ul').addClass('warnContainer');
                 }
                 else if(JSON.parse(response.customErrorMessages[0].fieldName) == "success"){
                 jQuery('#jqgrowlContainer > ul').addClass('successContainer');
                 }*/
                $('#' + formId)[0].reset();
                jQuery('#' + gridId).trigger('reloadGrid');
            }
            else {
                var $alert = $('<div class="alert alert-success"></div>');
                $alert.html(response.customErrorMessages[0].message);
                $alert.prependTo($form);
                $('#' + formId)[0].reset();
            }

            return false;
        }
    });
}

function addJqgridCustomButtons(gridId, formId) {

    jQuery('#' + gridId).jqGrid('navGrid', '#' + gridId + 'Pager', {
        edit: false,
        add: false,
        del: true,
        search: true,
        refresh: false
    }).navButtonAdd('#' + gridId + 'Pager', {
        caption: "",
        buttonicon: "ui-icon-refrsh",
        onClickButton: function () {
            jQuery('#' + formId).hide(500);
            jQuery('#' + gridId).jqGrid('resetSelection');
            $('#' + formId + ' input').removeAttr('disabled');
            $('#' + formId + ' select').removeAttr("disabled");
            $('#' + formId + ' radio').removeAttr("disabled");
            $('#' + formId + ' textarea').removeAttr("disabled");
            $('#' + formId).find('input[type="button"][value="SAVE"]').addClass('hidedisplay');
            $('#' + formId).find('input[type="reset"]').addClass('hidedisplay');

            $(':input', '#' + formId)
                .not(':button, :submit, :reset, :checkbox, #registeredDate, :radio, #nativeAddresscountry, #localAddresscountry')
                .attr('value', '')
                .removeAttr('checked')
                .removeAttr('selected');

            jQuery('form').trigger('reset');
            $('.actionSpan').text("View");
        },
        position: "first"
    }).navButtonAdd('#' + gridId + 'Pager', {
        caption: "",
        buttonicon: "ui-icon-edit",
        onClickButton: function () {

            if (!jQuery("#" + gridId).jqGrid('getGridParam', 'selrow')) {
                $.jgrid.viewModal("#alertmod_" + this.id, {toTop: true, jqm: true});
            }
            else {
                jQuery('#' + formId).show(500);
                $('#' + formId + ' input').removeAttr('disabled');
                $('#' + formId + ' select').removeAttr("disabled");
                $('#' + formId + ' radio').removeAttr("disabled");
                $('#' + formId + ' textarea').removeAttr("disabled");

                $('#' + gridId + 'Pager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').removeClass('hidedisplay');
                $('.actionSpan').text("Edit");
            }
        },
        position: "first"
    }).navButtonAdd('#' + gridId + 'Pager', {
        caption: "",
        buttonicon: "ui-icon-add",
        onClickButton: function () {
            jQuery('#' + formId).show(500);
            $('#' + formId).removeClass('hidedisplay');
            jQuery('#' + gridId).jqGrid('resetSelection'); //to reset the selected row

            $('#' + gridId + 'Pager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').removeClass('hidedisplay');

            $('#' + formId + ' input').removeAttr('disabled');
            $('#' + formId + ' select').removeAttr("disabled");
            $('#' + formId + ' radio').removeAttr("disabled");
            $('#' + formId + ' textarea').removeAttr("disabled");

            $(':input', '#' + formId)
                .not(':button, :submit, :reset, :checkbox, #registeredDate, :radio,#nativeAddresscountry, #localAddresscountry')
                .attr('value', '')
                .removeAttr('checked')
                .removeAttr('selected');

            jQuery('form').trigger('reset');
            $('.actionSpan').text("Add");
        },
        position: "first"
    });

    var $parentContinerWidth = $('#' + gridId).closest('div.tab-content').width() - 24;
    // var $gridWidth = $('#' +  gridId).width();
    if ($parentContinerWidth)
        $('#' + gridId).jqGrid('setGridWidth', parseInt($parentContinerWidth));

    // var customButtons = $('<td/>', {class:"ui-pg-button ui-corner-all buttontd hidedisplay", width:"100%"}).append($('<div/>',{class:"btn btn-sm btn-success", click:function(){updateFormInfo(formId, gridId)}, style:"float:right;", text :"SAVE"}).append($('<span/>',{class:"fa fa-floppy-o"}))).append($('<div/>',{class:"btn btn-sm btn-danger",click :function(){cancelActions(formId, gridId)}, style:"float:right;margin-right: 5px;",text:"CANCEL"}).append($('<span/>',{class:"fa fa-times"})));
    var customButtons = $('<td/>', {
        class: "ui-pg-button ui-corner-all buttontd hidedisplay",
        width: "100%"
    }).append($('<div/>', {
        class: "btn btn-sm btn-danger", click: function () {
            cancelActions(formId, gridId)
        }, style: "float:right;font-size: 10px;", text: "CANCEL", title: "CANCEL"
    })).append($('<div/>', {
        class: "btn btn-sm btn-success", click: function () {
            if (formId != 'memberForm') {
                globalSubmissionOfForms(formId, gridId)
            } else {
                callImageSubmission();
            }
        }, style: "float:right;font-size: 10px;margin-right: 5px;width:55px", text: "SAVE", title: "SAVE"
    }));

    $('#' + gridId + 'Pager').find('.ui-pg-table .navtable').find('tr:first').append(customButtons);
}

function loadDatePicker() {
    $('.date').datepicker({
        autoclose: true,
        todayHighlight: true,
        onClose: function () {
            $(this).valid();
        }
    });
}
function backToTop() {
    $('#back-to-top').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 500);
        return false;
    });
}


function cancelActions(formId, gridId) {
    //on cancel button click we reset form and grid selection .
    // jQuery("#cancelUser").click(function () {
    $(':input', '#' + formId)
        .not(':button, :submit, :reset, :checkbox, #registeredDate, :radio, #nativeAddresscountry, #localAddresscountry')
        .attr('value', '')
        .removeAttr('checked')
        .removeAttr('selected');
    jQuery('#' + gridId).jqGrid('resetSelection');
    jQuery('#' + formId).trigger('reset');
    $('#' + formId).find('textarea').text('');

    //});
}
