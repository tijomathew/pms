/**
 * Created with IntelliJ IDEA.
 * User: tijo
 * Date: 24/10/14
 */
$(document).ready(function () {
    $('#massCenterForm').validate({
        rules: {
            massCenterID: "required",
            massCenterName: "required",
            massCenterPlace: "required",
            massCenterLandNo: {
                required: true,
                number: true,
                maxlength: 12
            },
            massCenterEmail: {
                required: true,
                email: true
            },
            massCenterMobileNo: {
                required: true,
                number: true,
                maxlength: 10
            },
            massCenterFaxNo: {
                required: true,
                number: true,
                maxlength: 12
            },
            "irishAddress.houseName": "required",
            "irishAddress.houseNumber": "required",
            "irishAddress.street": "required",
            "irishAddress.town": "required",
            "irishAddress.county": "required",
            "irishAddress.country": "required"
        },
        messages: {
            massCenterID: "Please Enter Mass Center ID",
            massCenterName: "Please Enter Mass Center Name",
            massCenterPlace: "Please Enter Mass Center Place",
            massCenterLandNo: "Please Enter Mass Center Land Line No",
            massCenterEmail: "Please Enter Mass Center Email",
            massCenterMobileNo: "Please Enter Mass Center Mobile No",
            massCenterFaxNo: "Please Enter Mass Center Fax No.",
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
});