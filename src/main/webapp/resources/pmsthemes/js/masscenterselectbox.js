/**
 * Created by tijo on 22/1/15.
 */
function loadSelectBox(contextPath){
    $('#parishNo').change(function () {
            $.getJSON(contextPath + "/createmasscenterselectbox.action",
                {selectedParishId: $('#parishNo').val()},
                function (data) {
                        $('#massCenterId').find('option').remove();
                        var html;
                        var len = data.length;
                        for (var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].displayName + '">' + data[i].value + '</option>';
                        }
                        $('#massCenterId').append(html);
                });
        }
    );
}
