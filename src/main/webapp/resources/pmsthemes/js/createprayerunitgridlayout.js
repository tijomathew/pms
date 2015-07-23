/**
 * Created by tijo on 6/7/15.
 */

function loadPrayerUnitGrid() {

    jQuery("#prayerUnitGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displayprayerunitgrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['prayerUnitName', 'prayerUnitCode', 'prayerUnitPlace', 'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country'],
            colModel: [

                {name: 'prayerUnitName', index: 'prayerUnitName', width: 90, sortable: false},
                {name: 'prayerUnitCode', index: 'prayerUnitCode', width: 90, sortable: false},
                {name: 'prayerUnitPlace', index: 'prayerUnitPlace', width: 100, sortable: false},
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
            pager: '#prayerUnitGridPager',
            //sortname: 'id',
            viewrecords: true,
            //sortorder: "desc",
            caption: "Prayer Unit",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto'
        });
    jQuery("#prayerUnitGrid").jqGrid('navGrid', '#prayerUnitGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("prayerUnitGrid", "prayerUnitForm");
    replaceDefaultGridCss();
}
