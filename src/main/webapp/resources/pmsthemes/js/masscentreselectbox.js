/**
 * Created by tijo on 22/1/15.
 */
function loadSelectBox(contextPath) {
    $('#parishNo').change(function () {
            $.getJSON(contextPath + "/createmasscentreselectbox.action",
                {selectedParishId: $('#parishNo').val()},
                function (data) {
                    $('#mappedMassCentre').find('option').remove();
                    var html='<option value="' + 0 + '">' + "--Select--" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].value + '">' + data[i].displayName + '</option>';
                    }
                    $('#mappedMassCentre').append(html);
                });
        }
    );
}
