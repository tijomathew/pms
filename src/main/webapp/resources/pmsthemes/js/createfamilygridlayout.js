/**
 * Created by tijo on 6/7/15.
 */

function loadFamilyGrid() {

    $("#familyGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displayfamilygrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['Family ID', 'Family Name', 'parishInNative', 'dioceseInNative', 'dateOfRegistration', 'parishLocal', "massCenter", "prayerUnit", "localAddress", "nativeAddress"],
            colModel: [

                {name: 'familyID', index: 'familyID', width: 90, sortable: false},
                {name: 'familyName', index: 'familyName', width: 100, sortable: false},
                {name: 'parishInNative', index: 'parishInNative', width: 100, sortable: false},
                {name: 'dioceseInNative', index: 'dioceseInNative', width: 100, sortable: false},
                {name: 'dateOfRegistration', index: 'dateOfRegistration', width: 100, sortable: false},
                {name: 'parishLocal', index: 'parishLocal', width: 100, sortable: false},
                {name: 'massCenter', index: 'massCenter', width: 100, sortable: false},
                {name: 'prayerUnit', index: 'prayerUnit', width: 100, sortable: false},
                {name: 'localAddress', index: 'localAddress', width: 100, sortable: false},
                {name: 'nativeAddress', index: 'nativeAddress', width: 100, sortable: false},
            ],
            rowNum: 10,
            pager: '#familyGridPager',
            //sortname: 'id',
            viewrecords: true,
            //sortorder: "desc",
            caption: "Families",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto'
        });
    jQuery("#familyGrid").jqGrid('navGrid', '#familyGridPager', {
        edit: false, add: false, del: false,
        search: false, refresh: false
    });

    replaceDefaultGridCss();
    addJqgridCustomButtons("familyGrid", "familyForm");
}
