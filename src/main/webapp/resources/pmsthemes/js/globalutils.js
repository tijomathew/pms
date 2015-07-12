/**
 * Created by tijo on 1/7/15.
 */
//get the form name and submit the form value and show the respective validation or success or failure or exception message in UI.

function globalSubmissionOfForms(formId,formAction,gridId) {
    var $form = $('#'+formId);
    $form.bind('submit', function (e) {

        $.post(formAction, $form.serializeArray(), function (response) {

            if (response.statusMessage == 'FAIL') {

                    for (var i = 0; i < response.customErrorMessages.length; i++) {
                        var item = response.customErrorMessages[i];
                        var $field = $('#' + item.fieldName);
                        $('label[for= '+item.fieldName+']').addClass('labelErrorAlert');
                        $field.addClass('borderRed');
                        $field.attr('title', item.message);
                        $field.tooltip({
                            placement: "top",
                            trigger: "hover",
                            template:'<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner tooltip-error"></div></div>'
                        });
                        $field.change(function(){
                            $('label[for= '+$(this).attr('name')+']').removeClass('labelErrorAlert');
                            $(this).removeClass('borderRed');
                            $(this).removeAttr('title');
                            $(this).tooltip('destroy');
                        });
                    }
                    return [true,"",""];

                }
              else if(response.statusMessage == 'SUCCESS'){
                jQuery.jqGrowl.timeout = 500;
                jQuery.jqGrowl.init( { right: '8px', bottom: '', top: '8px',left: ''});
                jQuery.jqGrowl.msg(response.customErrorMessages[0].message ,response.customErrorMessages[0].fieldName);

                /*if(JSON.parse(response.customErrorMessages[0].fieldName) == "error")
                    jQuery('#jqgrowlContainer > ul').addClass('errorContainer');
                else if(JSON.parse(response.customErrorMessages[0].fieldName) == "warn"){
                    jQuery('#jqgrowlContainer > ul').addClass('warnContainer');
                }
                else if(JSON.parse(response.customErrorMessages[0].fieldName) == "success"){
                    jQuery('#jqgrowlContainer > ul').addClass('successContainer');
                }*/
                $('#'+formId)[0].reset();
                jQuery('#'+gridId).trigger('reloadGrid');
            }
            else {
                var $alert = $('<div class="alert alert-success"></div>');
                $alert.html(response.customErrorMessages[0].message);
                $alert.prependTo($form);
                $('#'+formId)[0].reset();
            }
        }, 'json');

        e.preventDefault();
        return false;
    });
}

function addJqgridCustomButtons(gridId,formId){

    jQuery('#'+gridId).jqGrid('navGrid', '#'+gridId+'Pager', {
        edit: false,
        add: false,
        del: true,
        search:true,
        refresh: false
    }).navButtonAdd('#'+gridId+'Pager', {
        caption: "",
        buttonicon: "ui-icon-refrsh",
        onClickButton: function () {

            jQuery('#'+gridId).jqGrid('resetSelection');
            $('#'+formId+ ' input').removeAttr('disabled');
            $('#'+formId+ ' select').removeAttr("disabled");
            $('#'+formId).find('input[type="button"][value="SAVE"]').addClass('hidedisplay');
            $('#'+formId).find('input[type="reset"]').addClass('hidedisplay');

            $(':input', '#'+formId)
                .not(':button, :submit, :reset, :checkbox')
                .attr('value', '')
                .removeAttr('checked')
                .removeAttr('selected');

            jQuery('form').trigger('reset');
            $('.actionSpan').text("View");
        },
        position: "first"
    }).navButtonAdd('#'+gridId+'Pager', {
        caption: "",
        buttonicon: "ui-icon-edit",
        onClickButton: function () {

            if(!jQuery("#"+gridId).jqGrid('getGridParam', 'selrow')){
                $.jgrid.viewModal("#alertmod_" + this.id, {toTop: true, jqm: true});
            }
            else{
                $('#'+formId+ ' input').removeAttr('disabled');
                $('#'+formId+ ' select').removeAttr("disabled");
                $('#'+formId).find('input[type="button"][value="SAVE"]').removeClass('hidedisplay');
                $('#'+formId).find('input[type="reset"]').removeClass('hidedisplay');

                //  jQuery('form').trigger('reset');
                $('.actionSpan').text("Edit");
            }
        },
        position: "first"
    }).navButtonAdd('#'+gridId+'Pager', {
        caption: "",
        buttonicon: "ui-icon-add",
        onClickButton: function () {

            jQuery('#'+gridId).jqGrid('resetSelection'); //to reset the selected row

            $('#'+formId+ ' input').removeAttr('disabled');
            $('#'+formId+ ' select').removeAttr("disabled");

            $(':input', '#'+formId)
                .not(':button, :submit, :reset, :checkbox')
                .attr('value', '')
                .removeAttr('checked')
                .removeAttr('selected');

            $('#'+formId).find('input[type="button"][value="SAVE"]').removeClass('hidedisplay');
            $('#'+formId).find('input[type="reset"]').removeClass('hidedisplay');

            jQuery('form').trigger('reset');
            $('.actionSpan').text("Add");
        },
        position: "first"
    });
}
