/**
 * Created by tijo on 22/1/15.
 */
function loadSelectBox(contextPath){
    $('#parishSelectBox').change(function () {
            $.getJSON(contextPath + "/createPriestSelectBox.action",
                {selectedParishId: $('#parishSelectBox').val()},
                function (data) {
                    $('#priestSelectBox checkbox').each(function () {
                        if ($(this).val() != '0') {
                            $(this).remove();
                        }
                    });
                    var html = '<option value="' + 0 + '">' + "Please select" + '</option>';
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        html += '<option value="' + data[i].displayName + '">' + data[i].value + '</option>';
                    }
                    $('#massCenterSelectBox').append(html);
                });
        }

    );
}
