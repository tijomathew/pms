/**
 * Created by tijo on 8/6/15.
 */

function loadSelectBox(contextPath) {
    $.getJSON(contextPath + '/createparishselectbox.action',
        {},
        function (data) {
            $('#massCenterSelectBox').find('option').remove();
            $('#prayerUnitSelectBox').find('option').remove();
            $('#familySelectBox').find('option').remove();
            var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' +data[i].value + '">' +  data[i].displayName + '</option>';
            }
            $('#parishSelectBox').append(html);
        });
    $('#parishSelectBox').change(function () {
            $.getJSON(contextPath + "/createmasscenterselectbox.action",
                {selectedParishId: $('#parishSelectBox').val()},
                function (data) {
                    var systemRole = $("#systemRole option:selected").val();
                    if (systemRole != 'Parish Admin') {
                        $('#massCenterSelectBox').find('option').remove();
                        $('#prayerUnitSelectBox').find('option').remove();
                        $('#familySelectBox').find('option').remove();
                        var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                        var len = data.length;
                        for (var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                        }
                        $('#massCenterSelectBox').append(html);
                    }
                });
            var systemRole = $("#systemRole option:selected").val();
            if (systemRole == 'Parish Admin') {
                $('#extensionOfMail').removeClass('hideClass');
                var parish = $("#parishSelectBox option:selected").text();
                var displayValue = "@" + parish + ".pms";
                $('#extensionOfMail').val(displayValue);

                $('#massCenterSelectBox').find('option').remove();
                $('#prayerUnitSelectBox').find('option').remove();
                $('#familySelectBox').find('option').remove();

                $('#massCenterSelectBox').addClass('hideSelectImage');
                $('#prayerUnitSelectBox').addClass('hideSelectImage');
                $('#familySelectBox').addClass('hideSelectImage');


                $('#massCenterSelectBox').prop('disabled', true);
                $('#prayerUnitSelectBox').prop('disabled', true);
                $('#familySelectBox').prop('disabled', true);
            }
        }
    );

    $('#massCenterSelectBox').change(function () {
            $.getJSON(contextPath + "/createprayerunitselectbox.action",
                {selectedMassCenterId: $('#massCenterSelectBox').val()},
                function (data) {
                    var systemRole = $("#systemRole option:selected").val();
                    if (systemRole != 'Mass Center Admin') {
                        $('#prayerUnitSelectBox').find('option').remove();
                        $('#familySelectBox').find('option').remove();
                        var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                        var len = data.length;
                        for (var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                        }
                        $('#prayerUnitSelectBox').append(html);
                    }
                });
            var systemRole = $("#systemRole option:selected").val();
            if (systemRole == 'Mass Center Admin') {
                $('#extensionOfMail').removeClass('hideClass');
                var massCenter = $("#massCenterSelectBox option:selected").text();
                var displayValue = "@" + massCenter + ".pms";
                $('#extensionOfMail').val(displayValue);

                $('#prayerUnitSelectBox').find('option').remove();
                $('#familySelectBox').find('option').remove();

                $('#prayerUnitSelectBox').addClass('hideSelectImage');
                $('#familySelectBox').addClass('hideSelectImage');

                $('#prayerUnitSelectBox').prop('disabled', true);
                $('#familySelectBox').prop('disabled', true);
            }
        }
    );

    $('#prayerUnitSelectBox').change(function () {
            $.getJSON(contextPath + "/createfamilyselectbox.action",
                {selectedPrayerUnitId: $('#prayerUnitSelectBox').val()},
                function (data) {
                    var systemRole = $("#systemRole option:selected").val();
                    if (systemRole != 'Prayer Unit Admin') {
                    $('#familySelectBox').find('option').remove();
                    var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#familySelectBox').append(html);
                }
                });
            var systemRole = $("#systemRole option:selected").val();
            if (systemRole == 'Prayer Unit Admin') {
                $('#extensionOfMail').removeClass('hideClass');
                var prayerUnit = $("#prayerUnitSelectBox option:selected").text();
                var displayValue = "@" + prayerUnit + ".pms";
                $('#extensionOfMail').val(displayValue);

                $('#familySelectBox').find('option').remove();

                $('#familySelectBox').addClass('hideSelectImage');

                $('#familySelectBox').prop('disabled', true);
            }
        }
    );

    //Family Select Box creation for the Member Page.

    $.getJSON(contextPath + '/createfamilyselectbox.action',
        {},
        function (data) {
            var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
            }
            $('#familySelectBoxofMember').append(html);
        });
}
