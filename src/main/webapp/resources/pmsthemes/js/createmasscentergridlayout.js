/**
 * Created by tijo on 6/7/15.
 */

function loadMassCenterGrid() {

    jQuery("#massCenterGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displaymasscentergrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            rowList: [2, 4, 6],
            colNames: ['Mass Center ID', 'Mass CenterName', 'patronName', 'place', 'facebookPage', 'registeredDate', 'drivingRoute', 'map', 'landLineNo', 'mobileNo', 'email', 'faxNo', 'parishName', 'localAddress'],
            colModel: [

                {name: 'massCenterID', index: 'massCenterID', width: 90},
                {name: 'massCenterName', index: 'massCenterName', width: 100},
                {name: 'patronName', index: 'patronName', width: 90},
                {name: 'place', index: 'place', width: 90},
                {name: 'facebookPage', index: 'facebookPage', width: 90},
                {name: 'registeredDate', index: 'registeredDate', width: 90},
                {name: 'drivingRoute', index: 'drivingRoute', width: 90},
                {name: 'map', index: 'map', width: 90},
                {name: 'landLineNo', index: 'landLineNo', width: 90},
                {name: 'mobileNo', index: 'mobileNo', width: 90},
                {name: 'email', index: 'email', width: 90},
                {name: 'faxNo', index: 'faxNo', width: 90},
                {name: 'parishName', index: 'parishName', width: 90},
                {name: 'localAddress', index: 'localAddress', width: 90},


            ],
            rowNum: 2,
            pager: '#massCenterGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Mass Centers",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto'
        });
    jQuery("#massCenterGrid").jqGrid('navGrid', '#massCenterGridPager', {edit: true, add: true, del: true});
}