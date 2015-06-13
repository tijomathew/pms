/**
 * Created with IntelliJ IDEA.
 * User: tijo
 * Date: 24/10/14
 */
$(document).ready(function () {
    $('#parishForm').validate({
        rules: {
            parishID: "required",
            churchName: "required",
            riteName: "required",
            archDioceseName: "required",
            dioceseName: "required",
            foraneName: "required",
            parishName: "required",
            mobileNo: {
                number: true,
                required: true,
                maxlength: 10
            },
            email: {
                email: true,
                required: true
            },
            landNo: {
                number: true,
                required: true,
                maxlength: 12
            },
            faxNo: {
                number: true,
                required: true,
                maxlength: 5
            },
            "irishAddress.houseName": "required",
            "irishAddress.houseNumber": "required",
            "irishAddress.street": "required",
            "irishAddress.town": "required",
            "irishAddress.county": "required",
            "irishAddress.country": "required"
        },
        messages: {
            parishID: "Please Enter Parish ID",
            churchName: "Please Enter Church Name",
            riteName: "Please Enter Rite Name",
            archDioceseName: "Please Enter Arch Diocese Name",
            dioceseName: "Please Enter Diocese Name",
            foraneName: "Please Enter Forane Name",
            parishName: "Please Enter Parish Name",
            mobileNo: "Please Enter Mobile No",
            email: "Please Enter Email",
            landNo: "Please Enter Land Line No",
            faxNo: "Please Enter Fax No",
            "irishAddress.houseName": "Please Enter Irish House Name",
            "irishAddress.houseNumber": "Please Enter Irish House No",
            "irishAddress.street": "Please Enter Irish Street",
            "irishAddress.town": "Please Enter Irish Town",
            "irishAddress.county": "Please Enter Irish County",
            "irishAddress.country": "Please Enter Irish Country"
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
    $("#registeredDate").datepicker({
        changeMonth: true,
        changeYear: true
    });
});
