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
                html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
            }
            $('#parishSelectBox').append(html);
        });
    $('#parishSelectBox').change(function () {
            var systemRole = $("#systemRole option:selected").val();

            if (systemRole != 'PARISH_ADMIN') {
                $.getJSON(contextPath + "/createmasscenterselectbox.action",
                    {selectedParishId: $('#parishSelectBox').val()},
                    function (data) {
                        $('#massCenterSelectBox').find('option').remove();
                        $('#prayerUnitSelectBox').find('option').remove();
                        $('#familySelectBox').find('option').remove();
                        var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                        var len = data.length;
                        for (var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                        }
                        $('#massCenterSelectBox').append(html);
                    });
            }
        }
    );

    $('#massCenterSelectBox').change(function () {
            var systemRole = $("#systemRole option:selected").val();
            if (systemRole != 'MASS_CENTER_ADMIN') {
                $.getJSON(contextPath + "/createprayerunitselectbox.action",
                    {selectedMassCenterId: $('#massCenterSelectBox').val()},
                    function (data) {
                        $('#prayerUnitSelectBox').find('option').remove();
                        $('#familySelectBox').find('option').remove();
                        var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                        var len = data.length;
                        for (var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                        }
                        $('#prayerUnitSelectBox').append(html);
                    });
            }
        }
    );

    $('#prayerUnitSelectBox').change(function () {
            var systemRole = $("#systemRole option:selected").val();
            if (systemRole != 'PRAYER_UNIT_ADMIN') {
                $.getJSON(contextPath + "/createfamilyselectbox.action",
                    {selectedPrayerUnitId: $('#prayerUnitSelectBox').val()},
                    function (data) {
                        $('#familySelectBox').find('option').remove();
                        var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                        var len = data.length;
                        for (var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                        }
                        $('#familySelectBox').append(html);
                    });
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
