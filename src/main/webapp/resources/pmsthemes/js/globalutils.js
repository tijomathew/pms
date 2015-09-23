/**
 * Created by tijo on 1/7/15.
 */
//get the form name and submit the form value and show the respective validation or success or failure or exception message in UI.

var registeredDate;
$(document).ready(function () {
    registeredDate = $('#registeredDate').val();
});

function blinker(blinkerElt) {
    blinkerElt.find('span').addClass('tabErrorHighlight').effect("pulsate", {times: 10}, 5000);
}

function globalSubmissionOfForms(formId, gridId) {
    $('#loadProgresser').removeClass('hideLoader');
    $('#successButton').hide();
    var $form = $('#' + formId);
    var data = $("#" + formId).serialize();

    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        dataType: 'json',
        data: data,
        success: function (response) {
            $('#loadProgresser').addClass('hideLoader');
            $('#successButton').show();
            if (response.statusCode == 'FAIL') {
                for (var i = 0; i < response.customErrorMessages.length; i++) {
                    var item = response.customErrorMessages[i];
                    var itemFieldName = item.fieldName.replace(/\./g, '\\.');
                    var $field = $($form).find("[name='" + itemFieldName + "']");
                    $("label[for='" + itemFieldName + "']").addClass('labelErrorAlert');
                    var tabHeaderId = $field.closest('div.tab-pane').attr("id");
                    blinker($("a[href=#" + tabHeaderId + "]"));
                    $field.addClass('borderRed');
                    $field.attr('title', item.message);
                    $field.tooltip({
                        placement: "top",
                        trigger: "hover",
                        template: '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner tooltip-error"></div></div>'
                    });
                    $field.change(function () {
                        var fieldName = $(this).attr('name').replace(/\./g, '\.');
                        $("label[for='" + fieldName + "']").removeClass('labelErrorAlert');
                        $(this).removeClass('borderRed');
                        $(this).removeAttr('title');
                        $(this).tooltip('destroy');
                    });
                }
                $("div.container").errorFieldsDialog({
                    responseData: response.customErrorMessages,
                    parentFormId: formId,
                    parentGridId: gridId
                });
                return [true, "", ""];

            }
            else if (response.statusCode == 'SUCCESS') {
                $('#' + gridId + 'Pager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').hide(500);
                jQuery.jqGrowl.timeout = 2000;
                jQuery.jqGrowl.init({right: '8px', bottom: '', top: '8px', left: ''});
                jQuery.jqGrowl.msg(response.customErrorMessages[0].fieldName + ' ' + response.customErrorMessages[0].message, 'SUCCESS');

                $('#' + formId)[0].reset();
                $(':input', '#' + formId)
                    .not(':button, :submit, :reset, :checkbox, #registeredDate, :radio, #localAddresscountry')
                    .attr('value', '')
                    .removeAttr('checked')
                    .removeAttr('selected');
                jQuery('#' + gridId).trigger('reloadGrid');
                $('ul.nav-tabs').find('span').removeClass('tabErrorHighlight');
                $('ul.nav-tabs').find('span').stop(true, true).css('opacity', 1);
            }
            else if (response.statusCode == 'FAILURE') {
                jQuery.jqGrowl.timeout = 2000;
                jQuery.jqGrowl.init({right: '8px', bottom: '8px', top: '', left: ''});
                jQuery.jqGrowl.msg(response.customErrorMessages[0].fieldName + ' ' + response.customErrorMessages[0].message, 'FAILURE');

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
            jQuery('#panelDiv').hide(500);
            jQuery('#' + gridId).jqGrid('resetSelection');
            $('#' + formId + ' input').removeAttr('disabled');
            $('#' + formId + ' select').removeAttr("disabled");
            $('#' + formId + ' radio').removeAttr("disabled");
            $('#' + formId + ' textarea').removeAttr("disabled");
            $('ul.nav-tabs').find('span').removeClass('tabErrorHighlight');
            $('ul.nav-tabs').find('span').stop(true, true).css('opacity', 1);
            $('#' + formId).find('input[type="button"][value="SAVE"]').addClass('hidedisplay');
            $('#' + formId).find('input[type="reset"]').addClass('hidedisplay');

            $('#' + formId).find(':input')
                .not(':button, :submit, :reset, :checkbox, #registeredDate,:radio, #nativeAddresscountry, #localAddresscountry')
                .val('')
                .removeAttr('checked')
                .removeAttr('selected');

            $('.actionSpan').text("View");
            $('#' + formId).find(':input').each(function () {
                if ($(this).is(':radio')) {
                    $("#" + formId).find("[name='active-btn-group']").tooltip('destroy');
                }
                else {
                    $(this).tooltip('destroy');
                }
                $("#" + formId).find("label[for='" + ($(this).attr('name')) + "']").removeClass('labelErrorAlert');
                $(this).removeClass('borderRed');
                $(this).removeAttr('title');
            });
        },
        position: "first"
    }).navButtonAdd('#' + gridId + 'Pager', {
        caption: "",
        buttonicon: "ui-icon-edit",
        onClickButton: function () {
            $('#passwordDiv').hide();
            if (!jQuery("#" + gridId).jqGrid('getGridParam', 'selrow')) {
                $.jgrid.viewModal("#alertmod_" + this.id, {toTop: true, jqm: true});
            }
            else {
                jQuery('#' + formId).show(500);
                $('#' + formId).find(':input').removeAttr('disabled');
                $('#loadProgresser').addClass('hideLoader');
                $('#' + gridId + 'Pager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').removeClass('hidedisplay');

                $('.actionSpan').text("Edit");
            }
        },
        position: "first"
    }).navButtonAdd('#' + gridId + 'Pager', {
        caption: "",
        buttonicon: "ui-icon-add",
        onClickButton: function () {
            $('#passwordDiv').show();
            jQuery('#panelDiv').show(500);
            $('#loadProgresser').addClass('hideLoader');
            $('#panelDiv').removeClass('hidedisplay');
            $('#familyHeadDiv').hide();
            jQuery('#' + gridId).jqGrid('resetSelection'); //to reset the selected row

            $('#' + gridId + 'Pager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').removeClass('hidedisplay');

            $('#' + formId).find(':input').removeAttr('disabled');

            $('#' + formId).find(':input')
                .not(':button, :submit, :reset, :checkbox,  #registeredDate,:radio, #nativeAddresscountry, #localAddresscountry,#lifeStatus,#isActive,#parishSelectBox,#massCentreSelectBox,#prayerUnitSelectBox')
                .val('')
                .removeAttr('checked')
                .removeAttr('selected');
            $('#' + formId).find('select option:first').attr('selected', 'selected');
            $('#' + formId + ' .thumbnail').hide();
            $('#registeredDate').prop('value', registeredDate);
            $('.actionSpan').text("Add");
        },
        position: "first"
    });

    var $parentContinerWidth = $('#' + gridId).closest('div.tab-content').width() - 24;
    if ($parentContinerWidth)
        $('#' + gridId).jqGrid('setGridWidth', parseInt($parentContinerWidth));

    var customButtons = $('<td/>', {
        class: "ui-pg-button ui-corner-all buttontd hidedisplay",
        width: "100%"
    }).append($('<div/>', {
        class: "btn btn-sm btn-danger", click: function () {
            cancelActions(formId, gridId)
        }, style: "float:right;font-size: 10px;", text: "CANCEL", title: "CANCEL"
    })).append($('<div/>', {
        class: "btn btn-sm btn-success", id: "successButton", click: function () {
            if (formId != 'memberForm') {
                globalSubmissionOfForms(formId, gridId)
            } else {
                callImageSubmission();
            }
        }, style: "float:right;font-size: 10px;margin-right: 5px;width:55px", text: "SAVE", title: "SAVE"
    })).append($('<span style="padding-right: 10px; color: #1192A7;float:right;" class="hideLoader" id="loadProgresser"><i class="fa fa-refresh fa-spin fa-2x"></i></span>'));

    $('#' + gridId + 'Pager').find('.ui-pg-table .navtable').find('tr:first').append(customButtons);
}

function loadDatePicker() {
    $('.date').datepicker({
        autoclose: true,
        todayHighlight: true,
        format: 'dd-mm-yyyy',
        endDate: '+0d',
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
    $(':input', '#' + formId)
        .not(':button, :submit, :reset, :checkbox, #registeredDate, :radio, #nativeAddresscountry, #localAddresscountry')
        .attr('value', '')
        .removeAttr('checked')
        .removeAttr('selected');
    jQuery('#' + gridId).jqGrid('resetSelection');
    $('#' + formId).find('textarea').text('');

    $(':input', '#' + formId).each(function () {
        if ($(this).is(':radio')) {
            $("#" + formId).find("[name='active-btn-group']").tooltip('destroy');
        }
        else {
            $(this).tooltip('destroy');
        }
        $("#" + formId).find("label[for='" + ($(this).attr('name')) + "']").removeClass('labelErrorAlert');
        $(this).removeClass('borderRed');
        $(this).removeAttr('title');
        $('ul.nav-tabs').find('span').removeClass('tabErrorHighlight');
        $('ul.nav-tabs').find('span').stop(true, true).css('opacity', 1);
    });
}
