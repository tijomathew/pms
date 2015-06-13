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
            "localAddress.houseName":"required",
            "localAddress.houseNumber":"required",
            "localAddress.street":"required",
            "localAddress.town":"required",
            "localAddress.county":"required",
            "localAddress.country":"required",
            "nativeAddress.houseName":"required",
            "nativeAddress.place":"required",
            "nativeAddress.postOffice":"required",
            "nativeAddress.district":"required",
            "nativeAddress.state":"required",
            "nativeAddress.country":"required",
            "nativeAddress.pincode":"required"

        },
        messages:{
            familyID:"Please Enter Family ID",
            familyName:"Please Enter Family Name",
            parishIndia:"Please Enter Parish India",
            dioceseIndia:"Please Enter Diocese India",
            dateOfRegisteration:"Please Enter Date Of Registeration",
            "localAddress.houseName":"Please Enter Irish House Name",
            "localAddress.houseNumber":"Please Enter Irish House No",
            "localAddress.street":"Please Enter Irish Street",
            "localAddress.town":"Please Enter Irish Town",
            "localAddress.county":"Please Enter Irish county",
            "localAddress.country":"Please Enter Irish country",
            "nativeAddress.houseName":"Please Enter India houseName",
            "nativeAddress.place":"Please Enter India place",
            "nativeAddress.postOffice":"Please Enter India postOffice",
            "nativeAddress.district":"Please Enter India district",
            "nativeAddress.state":"Please Enter India state",
            "nativeAddress.country":"Please Enter India country",
            "nativeAddress.pincode":"Please Enter India pincode"
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