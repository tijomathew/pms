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
            rowList: [2, 4, 6],
            colNames: ['PrayerUnit ID', 'PrayerUnit Code', 'PrayerUnit Name', 'PrayerUnit Place', 'MassCenter Name', 'Local address'],
            colModel: [

                {name: 'prayerUnitID', index: 'prayerUnitID', width: 90},
                {name: 'prayerUnitCode', index: 'prayerUnitCode', width: 90},
                {name: 'prayerUnitName', index: 'prayerUnitName', width: 100},
                {name: 'prayerUnitPlace', index: 'prayerUnitPlace', width: 100},
                {name: 'massCenterName', index: 'massCenterName', width: 100},
                {name: 'localAddress', index: 'localAddress', width: 100},

            ],
            rowNum: 2,
            pager: '#prayerUnitGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Prayer Unit",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto'
        });
    jQuery("#prayerUnitGrid").jqGrid('navGrid', '#prayerUnitGridPager', {edit: true, add: true, del: true});
}
