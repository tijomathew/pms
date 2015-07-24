/**
 * Created by tijo on 2/12/14.
 */

function loadSelectBox(contextPath) {
    $.getJSON(contextPath + '/createparishselectbox.action',
        {},
        function (data) {
            $('#massCenterSelectBox').empty();
            $('#wardSelectBox').empty();
            var html = '<option value="' + 0 + '">' + "--Please select--" + '</option>';
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
                    $('#wardSelectBox').empty();
                    var html = '<option value="' + 0 + '">' + "--Please select--" + '</option>';
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
                    $('#wardSelectBox').empty();
                    var html = '<option value="' + 0 + '">' + "--Please select--" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#wardSelectBox').append(html);
                });
        }
    );
}
