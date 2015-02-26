/**
 * Created by cufa-01 on 24/10/14.
 */
$(document).ready(function() {
    $('#memberForm').validate({
        rules: {
            memberID: "required",
            "memberAsPerson.salutation": "required",
            "memberAsPerson.firstName": "required",
            "memberAsPerson.lastName": "required",
            "memberAsPerson.dateOfBirth": "required",
            "memberAsPerson.placeOfBirth": "required",
            "memberAsPerson.mothersMaidenName": "required",
            "memberAsPerson.maritalStatus": "required",
            "memberAsPerson.email": {
                required: true,
                email: true
            },
            "memberAsPerson.mobileNo": {
                required: true,
                number: true,
                maxlength: 10
            },
            "memberAsPerson.landLine": {
                number: true,
                required: true,
                maxlength: 12
            },
            "memberAsPerson.nationality": "required",
            "memberAsPerson.educationQualifications": "required",
            "memberAsPerson.jobDetails": "required",
            statusInLife: "required",
            relationInFamily: "required",
            statusInChurch: "required",
            dateOfBaptism: "required",
            dateOfConfirmation: "required",
            dateOfFirstCommunion: "required",
            dateOfMarriage: "required",
            dateOfDeath: "required",
            bornDiocese: "required",
            piousAssociation: "required",
            catechismQualification: "required",
            sacramentalLife: "required",
            carRegistration: "required"
        },
        messages: {
            memberID: "Please Enter memberID",
            "memberAsPerson.salutation": "Please Enter Salutation",
            "memberAsPerson.firstName": "Please Enter First Name",
            "memberAsPerson.lastName": "Please Enter Last Name",
            "memberAsPerson.dateOfBirth": "Please Enter Date of Birth",
            "memberAsPerson.placeOfBirth": "Please Enter Place of Birth",
            "memberAsPerson.mothersMaidenName": "Please Enter Mother's Maiden Name",
            "memberAsPerson.maritalStatus": "Please Enter Marital Status ",
            "memberAsPerson.email": "Please Enter Email",
            "memberAsPerson.mobileNo": "Please Enter Mobile No",
            "memberAsPerson.landLine": "Please Enter LandLine",
            "memberAsPerson.nationality": "Please Enter Nationality",
            "memberAsPerson.educationQualifications": "Please Enter Education Qualifications",
            "memberAsPerson.jobDetails": "Please Enter Job Details",
            statusInLife: "Please Enter Status In Life",
            relationInFamily: "Please Enter Relation In Family",
            statusInChurch: "Please Enter Status In Church",
            dateOfBaptism: "Please Enter Date of Baptism",
            dateOfConfirmation: "Please Enter Date of Confirmation",
            dateOfFirstCommunion: "Please Enter Date of First Communion",
            dateOfMarriage: "Please Enter Date of Marriage",
            dateOfDeath: "Please Enter Date of Death",
            bornDiocese: "Please Enter Born Diocese",
            piousAssociation: "Please Enter Pious Association",
            catechismQualification: "Please Enter Catechism Qualification",
            sacramentalLife: "Please Enter Sacramental Life",
            carRegistration: "Please Enter Car Registration"
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
    $(document).ready(function () {
        $("#memberAsPerson.dateOfBirth").datepicker({
            changeMonth: true,
            changeYear: true
        });

    });
});