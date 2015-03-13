/**
 * Created by cufa-01 on 24/10/14.
 */

$(document).ready(function(){
    $('#familyForm').validate({
        rules:{
            familyID:"required",
            familyName:"required",
            parishIndia:"required",
            dioceseIndia:"required",
            dateOfRegisteration:"required",
            "irishAddress.houseName":"required",
            "irishAddress.houseNumber":"required",
            "irishAddress.street":"required",
            "irishAddress.town":"required",
            "irishAddress.county":"required",
            "irishAddress.country":"required",
            "indiaAddress.houseName":"required",
            "indiaAddress.place":"required",
            "indiaAddress.postOffice":"required",
            "indiaAddress.district":"required",
            "indiaAddress.state":"required",
            "indiaAddress.country":"required",
            "indiaAddress.pincode":"required"

        },
        messages:{
            familyID:"Please Enter Family ID",
            familyName:"Please Enter Family Name",
            parishIndia:"Please Enter Parish India",
            dioceseIndia:"Please Enter Diocese India",
            dateOfRegisteration:"Please Enter Date Of Registeration",
            "irishAddress.houseName":"Please Enter Irish House Name",
            "irishAddress.houseNumber":"Please Enter Irish House No",
            "irishAddress.street":"Please Enter Irish Street",
            "irishAddress.town":"Please Enter Irish Town",
            "irishAddress.county":"Please Enter Irish county",
            "irishAddress.country":"Please Enter Irish country",
            "indiaAddress.houseName":"Please Enter India houseName",
            "indiaAddress.place":"Please Enter India place",
            "indiaAddress.postOffice":"Please Enter India postOffice",
            "indiaAddress.district":"Please Enter India district",
            "indiaAddress.state":"Please Enter India state",
            "indiaAddress.country":"Please Enter India country",
            "indiaAddress.pincode":"Please Enter India pincode"
        },
        submitHandler:function(form){
            form.submit();
        }
    });
    $("#dateOfRegistration").datepicker({
        changeMonth: true,
        changeYear: true
    });
});