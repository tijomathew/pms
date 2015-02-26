/**
 * Created with IntelliJ IDEA.
 * User: tijo
 * Date: 24/10/14
 */
$(document).ready(function () {
    $("#priestForm").validate({

        // Specify the validation rules
        rules: {
            priestID: "required",
            priestStatus:"required",
            "priestAsPerson.salutation":"required",
            "priestAsPerson.firstName":"required",
            "priestAsPerson.lastName":"required",
            "priestAsPerson.dateOfBirth":"required",
            "priestAsPerson.placeOfBirth":"required",
            "priestAsPerson.mothersMaidenName":"required",
            "priestAsPerson.maritalStatus":"required",
            "priestAsPerson.email":{
                required: true,
                email: true
            },
            "priestAsPerson.mobileNo":{
                required:true,
                number:true,
                maxlength:10
            },
            "priestAsPerson.landLine": {
                required: true,
                number: true,
                maxlength:12
            },
            "priestAsPerson.nationality":"required",
            "priestAsPerson.educationQualifications":"required",
            "priestAsPerson.jobDetails":"required",
            designation:"required",
            congregation:"required",
            bornDiocese:"required",
            memberOfIndiaDiocese:"required",
            "irishAddress.houseName":"required",
            "irishAddress.houseNumber":"required",
            "irishAddress.street":"required",
            "irishAddress.town":"required",
            "irishAddress.county":"required",
            "indiaAddress.houseName":"required",
            "indiaAddress.place":"required",
            "indiaAddress.postOffice":"required",
            "indiaAddress.district":"required",
            "indiaAddress.state":"required",
            "indiaAddress.country":"required",
            "indiaAddress.pincode": {
                required: true,
                number: true,
                maxlength: 6
            }
        },

        // Specify the validation error messages
        messages: {
            priestID: "Please enter your Priest ID",
            priestStatus: "Please enter your Priest Status",
            "priestAsPerson.salutation":"Please enter your Salutation",
            "priestAsPerson.firstName":"Please enter your First Name",
            "priestAsPerson.lastName":"Please enter your Last Name",
            "priestAsPerson.dateOfBirth":"Please enter your Date of Birth",
            "priestAsPerson.placeOfBirth":"Please enter your Place of Birth",
            "priestAsPerson.mothersMaidenName":"Please enter your Mother's Maiden Name",
            "priestAsPerson.maritalStatus":"Please enter your Marital Status",
            "priestAsPerson.email": "Please enter a valid Email",
            "priestAsPerson.mobileNo":"Please enter your Mobile No",
            "priestAsPerson.landLine":"Please enter your Land Line No",
            "priestAsPerson.nationality":"Please enter your Nationality",
            "priestAsPerson.educationQualifications":"Please enter your Education Qualifications",
            "priestAsPerson.jobDetails":"Please enter your Job Details",
            designation:"Please enter your Designation",
            congregation:"Please enter your Congregation",
            bornDiocese:"Please enter your Born Diocese",
            memberOfIndiaDiocese:"Please enter your Member of India Diocese",
            "irishAddress.houseName":"Please enter your Irish House Name",
            "irishAddress.houseNumber":"Please enter your Irish House No",
            "irishAddress.street":"Please enter your Irish Street",
            "irishAddress.town":"Please enter your Irish Town",
            "irishAddress.county":"Please enter your Irish County",
            "indiaAddress.houseName":"Please enter your India House Name",
            "indiaAddress.place":"Please enter your India Place",
            "indiaAddress.postOffice":"Please enter your India Post Office",
            "indiaAddress.district":"Please enter your India District",
            "indiaAddress.state":"Please enter your India State",
            "indiaAddress.country":"Please enter your India Country",
            "indiaAddress.pincode":"Please enter your India Pincode"


            /*password: {
             required: "Please provide a password",
             minlength: "Your password must be at least 5 characters long"
             },*/

            /* agree: "Please accept our policy"*/
        },

        submitHandler: function (form) {
            form.submit();
        }
    });
    $("#priestAsPersondateOfBirth").datepicker({
        changeMonth: true,
        changeYear: true
    });
});
