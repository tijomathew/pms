/**
 * Created with IntelliJ IDEA.
 * User: tijo
 * Date: 24/10/14
 */
$(document).ready(function(){
    $('#addWardForm').validate({
        rules:{
            wardID:"required",
            wardName:"required",
            "irishAddress.houseName":"required",
            "irishAddress.houseNumber":"required",
            "irishAddress.street":"required",
            "irishAddress.town":"required",
            "irishAddress.county":"required",
            "irishAddress.country":"required"
        },
        messages:
        {
            wardID:"Please Enter PrayerUnit ID",
            wardName:"Please Enter PrayerUnit Name",
            "irishAddress.houseName":"Please Enter Irish House Name",
            "irishAddress.houseNumber":"Please Enter Irish House No",
            "irishAddress.street":"Please Enter Irish Street",
            "irishAddress.town":"Please Enter Irish Town",
            "irishAddress.county":"Please Enter Irish County",
            "irishAddress.country":"Please Enter irishAddress Country"
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
});
