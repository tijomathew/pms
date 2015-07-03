/**
 * Created by tijo on 1/7/15.
 */
//get the form name and submit the form value and show the respective validation or success or failure or exception message in UI.
function globalSubmissionOfForms(formId) {
    var $form = $('#' + formId);
    $form.bind('submit', function (e) {
        var $inputs = $form.find('input');
        var data = collectFormData($inputs);

        jQuery.post('addParish.action', data, function (response) {
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
}

//get the fields name values from the form
function collectFormData(fields) {
    var data = {};
    for (var i = 0; i < fields.length; i++) {
        var $item = $(fields[i]);
        data[$item.attr('name')] = $item.val();
    }
    return data;
}
