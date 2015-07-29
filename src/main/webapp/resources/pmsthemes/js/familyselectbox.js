/**
 * Created by tijo on 2/12/14.
 */

function loadSelectBox(contextPath) {
    $.getJSON(contextPath + '/createparishselectbox.action',
        {},
        function (data) {
            $('#massCenterSelectBox').find('option').remove();
            $('#prayerUnitSelectBox').find('option').remove();
            var html = '<option value="' + 0 + '">' + "--Please select--" + '</option>';
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
            }
            $('#parishSelectBox').append(html);
        });
    $('#parishSelectBox').change(function () {
            $.getJSON(contextPath + "/createmasscenterselectbox.action",
                {selectedParishId: $('#parishSelectBox').val()},
                function (data) {
                    $('#massCenterSelectBox').find('option').remove();
                    $('#prayerUnitSelectBox').find('option').remove();
                    var html = '<option value="' + 0 + '">' + "--Please select--" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#massCenterSelectBox').append(html);
                });
        }
    );

    $('#massCenterSelectBox').change(function () {
            $.getJSON(contextPath + "/createprayerunitselectbox.action",
                {selectedMassCenterId: $('#massCenterSelectBox').val()},
                function (data) {
                    $('#prayerUnitSelectBox').find('option').remove();
                    var html = '<option value="' + 0 + '">' + "--Please select--" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#prayerUnitSelectBox').append(html);
                });
        }
    );
}
