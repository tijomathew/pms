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
            } else {
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
