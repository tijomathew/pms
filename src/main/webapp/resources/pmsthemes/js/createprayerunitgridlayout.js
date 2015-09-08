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
            colNames: ['PU No.', 'PU Place', 'PU Name', 'PU Patron', 'Parish No.', 'Parish Name', 'MC No.', 'MC Name', 'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country', 'parishNo', 'id', 'mappedMassCentre', 'registeredDate'],
            colModel: [
                {name: 'prayerUnitNo', index: 'prayerUnitNo', width: 90, sortable: false},
                {name: 'prayerUnitPlace', index: 'prayerUnitPlace', width: 100, sortable: false, hidden: true},
                {name: 'prayerUnitName', index: 'prayerUnitName', width: 90, sortable: false},
                {name: 'patron', index: 'patron', width: 90, sortable: false},
                {name: 'parishNumber', index: 'parishNumber', width: 90, sortable: false},
                {name: 'parishName', index: 'parishName', width: 90, sortable: false},
                {name: 'massCentreNumber', index: 'massCentreNumber', width: 90, sortable: false},
                {name: 'massCentreName', index: 'massCentreName', width: 90, sortable: false},
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
                },
                {
                    name: 'mappedParish',
                    index: 'mappedParish',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'id',
                    index: 'id',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'mappedMassCentre',
                    index: 'mappedMassCentre',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'registeredDate',
                    index: 'registeredDate',
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
            width: 'auto',
            emptyrecords: 'No data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#prayerUnitGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#prayerUnitGrid").jqGrid('getGridParam', 'selrow');
                $('#prayerUnitForm').loadJSON(jQuery("#prayerUnitGrid").getRowData(rowId));

            }
        });
    jQuery("#prayerUnitGrid").jqGrid('navGrid', '#prayerUnitGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("prayerUnitGrid", "prayerUnitForm");
    replaceDefaultGridCss();
}
