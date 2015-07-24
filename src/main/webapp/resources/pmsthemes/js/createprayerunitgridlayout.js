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
            colNames: ['PU NO.', 'PU Place', 'PU Name', 'Parish No.', 'Parish Name', 'MC No.', 'MC Place', 'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country'],
            colModel: [
                {name: 'prayerUnitCode', index: 'prayerUnitCode', width: 90, sortable: false},
                {name: 'prayerUnitPlace', index: 'prayerUnitPlace', width: 100, sortable: false},
                {name: 'prayerUnitName', index: 'prayerUnitName', width: 90, sortable: false},
                {name: 'parishNumber', index: 'parishNumber', width: 90, sortable: false},
                {name: 'parishName', index: 'parishName', width: 90, sortable: false},
                {name: 'massCenterNumber', index: 'massCenterNumber', width: 90, sortable: false},
                {name: 'massCenterPlace', index: 'massCenterPlace', width: 90, sortable: false},
                {
                    name: 'localAddress.addressLineOne',
                    index: 'localAddress.addressLineOne',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'localAddress.addressLineTwo',
                    index: 'localAddress.addressLineTwo',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'localAddress.addressLineThree',
                    index: 'localAddress.addressLineThree',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'localAddress.town',
                    index: 'localAddress.town',
                    width: 80,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'localAddress.county',
                    index: 'localAddress.county',
                    width: 80,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'localAddress.pin',
                    index: 'localAddress.pin',
                    width: 80,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'localAddress.country',
                    index: 'localAddress.country',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
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
