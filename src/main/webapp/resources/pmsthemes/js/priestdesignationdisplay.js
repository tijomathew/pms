/**
 * Created by tijo on 24/1/15.
 */

function loadPriestWithDesignation(contextPath) {
    $.getJSON(contextPath + '/createpriestdesignationbox.action',
        {},
        function (data) {
            var priestCheckboxAndDesignationRadioButton = '';

            var spDesignationRadioButtonUnselectedState = '<input type="radio" value="Supporting Priest" >Supporting Priest</input>';
            var spDesignationRadioButtonSelectedState = '<input type="radio" value="Supporting Priest" selected="selected">Supporting Priest</input>';

            var coDesignationRadioButtonUnselectedState = '<input type="radio" value="Co-Ordinator" >Co-Ordinator</input>';
            var coDesignationRadioButtonSelectedState = '<input type="radio" value="Co-Ordinator" selected="selected">Co-Ordinator</input>';

            var chDesignationRadioButtonUnselectedState = '<input type="radio" value="Chaplain" >Chaplain</input><br>';
            var chDesignationRadioButtonSelectedState = '<input type="radio" value="Chaplain" selected="selected">Chaplain</input><br>';

            var concatenatedDesignationUnselectedState = spDesignationRadioButtonUnselectedState + coDesignationRadioButtonUnselectedState + chDesignationRadioButtonUnselectedState;
            var concatenatedDesignationSelectedState = spDesignationRadioButtonSelectedState + coDesignationRadioButtonSelectedState + chDesignationRadioButtonSelectedState;

            var len = data.length;
            for (var i = 0; i < len; i++) {
                priestCheckboxAndDesignationRadioButton += '<input type="checkbox" name="priest" value="' + data[i].value + '" >' + data[i].displayName + '</input>';
                if (data[i].state == "Not Selected") {
                    var spDesignationRadioButtonUnselectedState = '<input type="radio" value="Supporting Priest" name="' + data[i].value + '" >Supporting Priest</input>';
                    var coDesignationRadioButtonUnselectedState = '<input type="radio" value="Co-Ordinator" name="' + data[i].value + '">Co-Ordinator</input>';
                    var chDesignationRadioButtonUnselectedState = '<input type="radio" value="Chaplain" name="' + data[i].value + '">Chaplain</input><br>';
                    var concatenatedDesignationUnselectedState = spDesignationRadioButtonUnselectedState + coDesignationRadioButtonUnselectedState + chDesignationRadioButtonUnselectedState;
                    priestCheckboxAndDesignationRadioButton += concatenatedDesignationUnselectedState;
                } else {
                    var spDesignationRadioButtonSelectedState = '<input type="radio" value="Supporting Priest" selected="selected" name="' + data[i].value + '">Supporting Priest</input>';
                    var coDesignationRadioButtonSelectedState = '<input type="radio" value="Co-Ordinator" selected="selected" name="' + data[i].value + '">Co-Ordinator</input>';
                    var chDesignationRadioButtonSelectedState = '<input type="radio" value="Chaplain" selected="selected" name="' + data[i].value + '">Chaplain</input><br>';
                    var concatenatedDesignationSelectedState = spDesignationRadioButtonSelectedState + coDesignationRadioButtonSelectedState + chDesignationRadioButtonSelectedState;
                    priestCheckboxAndDesignationRadioButton += concatenatedDesignationSelectedState;
                }
            }
            $('#priestDesignationBoxes').append(priestCheckboxAndDesignationRadioButton);
        });

}
function loadPriestDesignationBoxes(contextPath) {
    $('#priestSelectBox').empty();
    $.getJSON(contextPath + "/createpriestdesignationboxinmasscenter.action",
        {selectedParishId: $('#parishSelectBox').val()},
        function (data) {
            $('#priestSelectBox').empty();

            var priestCheckboxAndDesignationRadioButton = '';

            var spDesignationRadioButtonUnselectedState = '<input type="radio" value="Supporting Priest" >Supporting Priest</input>';
            var spDesignationRadioButtonSelectedState = '<input type="radio" value="Supporting Priest" selected="selected">Supporting Priest</input>';

            var coDesignationRadioButtonUnselectedState = '<input type="radio" value="Co-Ordinator" >Co-Ordinator</input>';
            var coDesignationRadioButtonSelectedState = '<input type="radio" value="Co-Ordinator" selected="selected">Co-Ordinator</input>';

            var chDesignationRadioButtonUnselectedState = '<input type="radio" value="Chaplain" >Chaplain</input><br>';
            var chDesignationRadioButtonSelectedState = '<input type="radio" value="Chaplain" selected="selected">Chaplain</input><br>';

            var concatenatedDesignationUnselectedState = spDesignationRadioButtonUnselectedState + coDesignationRadioButtonUnselectedState + chDesignationRadioButtonUnselectedState;
            var concatenatedDesignationSelectedState = spDesignationRadioButtonSelectedState + coDesignationRadioButtonSelectedState + chDesignationRadioButtonSelectedState;

            var len = data.length;
            for (var i = 0; i < len; i++) {
                priestCheckboxAndDesignationRadioButton += '<input type="checkbox" name="priest" value="' + data[i].value + '" >' + data[i].displayName + '</input>';
                if (data[i].state == "Not Selected") {
                    var spDesignationRadioButtonUnselectedState = '<input type="radio" value="Supporting Priest" name="' + data[i].value + '" >Supporting Priest</input>';
                    var coDesignationRadioButtonUnselectedState = '<input type="radio" value="Co-Ordinator" name="' + data[i].value + '">Co-Ordinator</input>';
                    var chDesignationRadioButtonUnselectedState = '<input type="radio" value="Chaplain" name="' + data[i].value + '">Chaplain</input><br>';
                    var concatenatedDesignationUnselectedState = spDesignationRadioButtonUnselectedState + coDesignationRadioButtonUnselectedState + chDesignationRadioButtonUnselectedState;
                    priestCheckboxAndDesignationRadioButton += concatenatedDesignationUnselectedState;
                } else {
                    var spDesignationRadioButtonSelectedState = '<input type="radio" value="Supporting Priest" selected="selected" name="' + data[i].value + '">Supporting Priest</input>';
                    var coDesignationRadioButtonSelectedState = '<input type="radio" value="Co-Ordinator" selected="selected" name="' + data[i].value + '">Co-Ordinator</input>';
                    var chDesignationRadioButtonSelectedState = '<input type="radio" value="Chaplain" selected="selected" name="' + data[i].value + '">Chaplain</input><br>';
                    var concatenatedDesignationSelectedState = spDesignationRadioButtonSelectedState + coDesignationRadioButtonSelectedState + chDesignationRadioButtonSelectedState;
                    priestCheckboxAndDesignationRadioButton += concatenatedDesignationSelectedState;
                }
            }
            $('#priestSelectBox').append(priestCheckboxAndDesignationRadioButton);
        });
}
