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
            //rowList: [2, 4, 6],
            colNames: ['Mass Center ID', 'Mass CenterName', 'patronName', 'place', 'facebookPage', 'registeredDate', 'drivingRoute', 'map', 'landLineNo', 'mobileNo', 'email', 'faxNo', 'parishName', 'localAddress'],
            colModel: [

                {name: 'massCenterID', index: 'massCenterID', width: 90, sortable: false},
                {name: 'massCenterName', index: 'massCenterName', width: 100, sortable: false},
                {name: 'patronName', index: 'patronName', width: 90, sortable: false},
                {name: 'place', index: 'place', width: 90, sortable: false},
                {name: 'facebookPage', index: 'facebookPage', width: 90, sortable: false},
                {name: 'registeredDate', index: 'registeredDate', width: 90, sortable: false},
                {name: 'drivingRoute', index: 'drivingRoute', width: 90, sortable: false},
                {name: 'map', index: 'map', width: 90, sortable: false},
                {name: 'landLineNo', index: 'landLineNo', width: 90, sortable: false},
                {name: 'mobileNo', index: 'mobileNo', width: 90, sortable: false},
                {name: 'email', index: 'email', width: 90, sortable: false},
                {name: 'faxNo', index: 'faxNo', width: 90, sortable: false},
                {name: 'parishName', index: 'parishName', width: 90, sortable: false},
                {name: 'localAddress', index: 'localAddress', width: 90, sortable: false},


            ],
            rowNum: 10,
            pager: '#massCenterGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Mass Centers",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords:'no data available to show!!..Please add data to view'
        });
    jQuery("#massCenterGrid").jqGrid('navGrid', '#massCenterGridPager', {edit: false, add: false, del: true,
        search: true, refresh: false});

    addJqgridCustomButtons("massCenterGrid","massCenterForm");
    replaceDefaultGridCss();
}