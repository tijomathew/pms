/**
 * Created by tijo on 1/7/15.
 */

jQuery(document).ready(function () {
    jQuery('#parishViewer').bind("click", function () {
        loadParishGrid();
    });

    jQuery('#editParishForm').bind("click", function () {
        var gsr = jQuery("#parishGrid").jqGrid('getGridParam', 'selrow');
        if (gsr != null) {
            var value = jQuery("#parishGrid").getRowData(gsr)['parishID'];
            window.location.replace('editparishdetails.action?parishName=' + value);
        } else {
            alert("Please select Row");
        }
    });

    jQuery('#parishUpdateButton').click(function () {
        jQuery('#parishForm1').attr('action', 'updateparishinformation.action');
        jQuery("#parishForm1").submit();
    });



});



function loadParishGrid() {

    jQuery("#parishGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"
            },
            url: 'displayparishgrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            rowList: [10, 20, 30],
            colNames: ['ID', 'Parish Name', 'Rite Name', 'Arch Diocese Name', 'Diocese Name', 'Forane Name', "Parish Facebook Page", "Parish Website", "Parish Code", "Parish Place", "Parish Driving Route", "Parish Map", "Registered Date", "Mobile No", "Parish Email", "Parish LandLineNo", "Parish FaxNo", "Parish LocalAddress"],
            colModel: [

                {name: 'parishID', index: 'parishID', width: 90},
                {name: 'parishName', index: 'parishName', width: 100},
                {name: 'riteName', index: 'riteName', width: 80, align: "right"},
                {name: 'archDioceseName', index: 'archDioceseName', width: 80, align: "right"},
                {name: 'dioceseName', index: 'dioceseName', width: 80, align: "right"},
                {name: 'foraneName', index: 'foraneName', width: 80, align: "right"},
                {name: 'parishFacebookPage', index: 'parishFacebookPage', width: 80, align: "right"},
                {name: 'parishWebsite', index: 'parishWebsite', width: 80, align: "right"},
                {name: 'parishCode', index: 'parishCode', width: 80, align: "right"},
                {name: 'parishPlace', index: 'parishPlace', width: 80, align: "right"},
                {name: 'parishDrivingRoute', index: 'parishDrivingRoute', width: 80, align: "right"},
                {name: 'parishMap', index: 'parishMap', width: 80, align: "right"},
                {name: 'registeredDate', index: 'registeredDate', width: 80, align: "right"},
                {name: 'mobileNo', index: 'mobileNo', width: 80, align: "right"},
                {name: 'parishEmail', index: 'parishEmail', width: 80, align: "right"},
                {name: 'parishLandLineNo', index: 'parishLandLineNo', width: 80, align: "right"},
                {name: 'parishFaxNo', index: 'parishFaxNo', width: 80, align: "right"},
                {name: 'localAddress', index: 'localAddress', width: 80, align: "right"}
            ],
            rowNum: 2,
            pager: '#parishGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Parishes",
            autowidth: true,
            shrinkToFit: false,
            height: 'auto',
            width: 'auto'
        });
    jQuery("#parishGrid").jqGrid('navGrid', '#parishGridPager', {
        edit: false,
        add: false,
        del: false,
        search: false
    });

}
