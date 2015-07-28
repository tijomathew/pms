/**
 * Created by tijo on 2/12/14.
 */

function loadSelectBox(contextPath) {
    $.getJSON(contextPath + '/createfamilyselectbox.action',
        {},
        function (data) {
            var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
            var len = data.length;
            for (var i = 0; i < len; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
            }
            $('#familySelectBox').append(html);
        });
}

