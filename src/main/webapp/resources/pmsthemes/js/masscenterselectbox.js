/**
 * Created by tijo on 22/1/15.
 */
function loadSelectBox(contextPath) {
    $('#parishNo').change(function () {
            $.getJSON(contextPath + "/createmasscenterselectbox.action",
                {selectedParishId: $('#parishNo').val()},
                function (data) {
                    $('#massCenterNo').find('option').remove();
                    var html='<option value="' + 0 + '">' + "Please select" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#mappedMassCenter').append(html);
                });
        }
    );
}
