/**
 * Created by tijo on 8/6/15.
 */

function loadSelectBox(contextPath) {
    $.getJSON(contextPath + '/createparishselectbox.action',
        {},
        function (data) {
            $('#massCenterSelectBox').empty();
            $('#prayerUnitSelectBox').empty();
            //var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
            var html;
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].displayName + '">' + data[i].value + '</option>';
            }
            $('#parishSelectBox').append(html);
        });
    $('#parishSelectBox').change(function () {
            $.getJSON(contextPath + "/createmasscenterselectbox.action",
                {selectedParishId: $('#parishSelectBox').val()},
                function (data) {
                    $('#massCenterSelectBox').empty();
                    $('#prayerUnitSelectBox').empty();
                    //var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                    var html;
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].displayName + '">' + data[i].value + '</option>';
                    }
                    $('#massCenterSelectBox').append(html);
                });
        }
    );

    $('#massCenterSelectBox').change(function () {
            $.getJSON(contextPath + "/createprayerunitselectbox.action",
                {selectedMassCenterId: $('#massCenterSelectBox').val()},
                function (data) {
                    $('#prayerUnitSelectBox').empty();
                    //var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                    var html;
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#prayerUnitSelectBox').append(html);
                });
        }
    );

    $('#prayerUnitSelectBox').change(function () {
            $.getJSON(contextPath + "/createfamilyselectboxofusers.action",
                {selectedPrayerUnitId: $('#prayerUnitSelectBox').val()},
                function (data) {
                    $('#familySelectBox').empty();
                    //var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                    var html;
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#familySelectBox').append(html);
                });
        }
    );
}
