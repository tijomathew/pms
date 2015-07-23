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
            colNames: ['name', 'centerCode', 'massCenterID', 'patronName', 'place', 'facebookPage', 'registeredDate', 'drivingRoute', 'map', 'landLineNo', 'mobileNo', 'email', 'faxNo',
                'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country'],
            colModel: [

                {name: 'name', index: 'name', width: 90, sortable: false},
                {name: 'centerCode', index: 'centerCode', width: 100, sortable: false},
                {name: 'massCenterID', index: 'massCenterID', width: 90, sortable: false},
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
                {
                    name: 'localAddress.addressLineOne',
                    index: 'localAddress.addressLineOne',
                    width: 80,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'localAddress.addressLineTwo',
                    index: 'localAddress.addressLineTwo',
                    width: 80,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'localAddress.addressLineThree',
                    index: 'localAddress.addressLineThree',
                    width: 80,
                    align: "right",
                    sortable: false
                },
                {name: 'localAddress.town', index: 'localAddress.town', width: 80, align: "right", sortable: false},
                {name: 'localAddress.county', index: 'localAddress.county', width: 80, align: "right", sortable: false},
                {name: 'localAddress.pin', index: 'localAddress.pin', width: 80, align: "right", sortable: false},
                {
                    name: 'localAddress.country',
                    index: 'localAddress.country',
                    width: 80,
                    align: "right",
                    sortable: false
                }
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
            emptyrecords: 'no data available to show!!..Please add data to view'
        });
    jQuery("#massCenterGrid").jqGrid('navGrid', '#massCenterGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("massCenterGrid", "massCenterForm");
    replaceDefaultGridCss();
}