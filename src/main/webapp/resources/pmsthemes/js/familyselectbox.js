/**
 * Created by tijo on 2/12/14.
 */

function loadSelectBox(contextPath) {
    $('#parishSelectBox').change(function () {
            $.getJSON(contextPath + "/createmasscentreselectbox.action",
                {selectedParishId: $('#parishSelectBox').val()},
                function (data) {
                    $('#massCentreSelectBox').find('option').remove();
                    $('#prayerUnitSelectBox').find('option').remove();
                    var html = '<option value="' + 0 + '">' + "--Select--" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#massCentreSelectBox').append(html);
                });
        }
    );

    $('#massCentreSelectBox').change(function () {
            $.getJSON(contextPath + "/createprayerunitselectbox.action",
                {selectedMassCentreId: $('#massCentreSelectBox').val()},
                function (data) {
                    $('#prayerUnitSelectBox').find('option').remove();
                    var html = '<option value="' + 0 + '">' + "--Select--" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#prayerUnitSelectBox').append(html);
                });
        }
    );
}
