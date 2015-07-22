/**
 * Created by tijo on 24/1/15.
 */

function loadPriestDesignationBoxes(contextPath) {
    $('#priestSelectBox').empty();
    $.getJSON(contextPath + "/createpriestdesignationboxinmasscenter.action",
        {selectedParishId: $('#parishSelectBox').val()},
        function (data) {
            $('#priestSelectBox').empty();

            var priestCheckboxAndDesignationRadioButton = '';

            var spDesignationRadioButtonUnselectedState = '<input type="radio" value="In-Charge" >In-Charge</input>';
            var spDesignationRadioButtonSelectedState = '<input type="radio" value="In-Charge" selected="selected">In-Charge</input>';

            var coDesignationRadioButtonUnselectedState = '<input type="radio" value="Assistant" >Assistant</input>';
            var coDesignationRadioButtonSelectedState = '<input type="radio" value="Assistant" selected="selected">Assistant</input>';


            var concatenatedDesignationUnselectedState = spDesignationRadioButtonUnselectedState + coDesignationRadioButtonUnselectedState;
            var concatenatedDesignationSelectedState = spDesignationRadioButtonSelectedState + coDesignationRadioButtonSelectedState;

            var len = data.length;
            for (var i = 0; i < len; i++) {
                priestCheckboxAndDesignationRadioButton += '<input type="checkbox" name="priest" value="' + data[i].value + '" >' + data[i].displayName + '</input>';
                if (data[i].state == "Not Selected") {
                    var spDesignationRadioButtonUnselectedState = '<input type="radio" value="In-Charge" name="' + data[i].value + '" >In-Charge</input>';
                    var coDesignationRadioButtonUnselectedState = '<input type="radio" value="Assistant" name="' + data[i].value + '">Assistant</input><br>';
                    var concatenatedDesignationUnselectedState = spDesignationRadioButtonUnselectedState + coDesignationRadioButtonUnselectedState;
                    priestCheckboxAndDesignationRadioButton += concatenatedDesignationUnselectedState;
                } else {
                    var spDesignationRadioButtonSelectedState = '<input type="radio" value="In-Charge" selected="selected" name="' + data[i].value + '">In-Charge</input>';
                    var coDesignationRadioButtonSelectedState = '<input type="radio" value="Assistant" selected="selected" name="' + data[i].value + '">Assistant</input><br>';
                    var concatenatedDesignationSelectedState = spDesignationRadioButtonSelectedState + coDesignationRadioButtonSelectedState;
                    priestCheckboxAndDesignationRadioButton += concatenatedDesignationSelectedState;
                }
            }
            $('#priestSelectBox').append(priestCheckboxAndDesignationRadioButton);
        });
}
