/**
 * Created by tijo on 1/7/15.
 */
//get the form name and submit the form value and show the respective validation or success or failure or exception message in UI.

function globalSubmissionOfForms(formId,formAction) {
    var $form = $('#'+formId);
    $form.bind('submit', function (e) {

        $.post(formAction, $form.serializeArray(), function (response) {
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
            }  else if(response.statusMessage == 'SUCCESS'){
                jQuery.jqGrowl.timeout = 500;
                jQuery.jqGrowl.init( { right: '8px', bottom: '8px', top: '',left: ''});
                jQuery.jqGrowl.msg(response.customErrorMessages[0].message ,response.customErrorMessages[0].fieldName);

                if(JSON.parse(response.customErrorMessages[0].fieldName) == "error")
                    jQuery('#jqgrowlContainer > ul').addClass('errorContainer');
                else if(JSON.parse(response.customErrorMessages[0].fieldName) == "warn"){
                    jQuery('#jqgrowlContainer > ul').addClass('warnContainer');
                }
                else if(JSON.parse(response.customErrorMessages[0].fieldName) == "success"){
                    jQuery('#jqgrowlContainer > ul').addClass('successContainer');
                }
                $('#'+formId)[0].reset();
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
