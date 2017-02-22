/**
 * Created by tijo on 6/7/15.
 */

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
            //rowList: [2, 4, 6],
            colNames: ['Parish No.', 'Parish Name', 'place', 'patronName', 'registeredDate', 'landLineNo', 'mobileNo', 'faxNo',
                'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country', 'id'],
            colModel: [
                {name: 'parishNo', index: 'parishNo', width: 90, sortable: true},
                {name: 'parsihName', index: 'parsihName', width: 90, sortable: false},
                {name: 'place', index: 'place', width: 90, sortable: false, hidden: true},
                {name: 'patronName', index: 'patronName', width: 90, sortable: false},
                {name: 'registeredDate', index: 'registeredDate', width: 90, sortable: false, hidden: true},
                {name: 'landLineNo', index: 'landLineNo', width: 90, sortable: false, hidden: true},
                {name: 'mobileNo', index: 'mobileNo', width: 90, sortable: false, hidden: true},
                {name: 'faxNo', index: 'faxNo', width: 90, sortable: false, hidden: true},
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
                    name: 'id',
                    index: 'id',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                }
            ],
            rowNum: 10,
            pager: '#parishGridPager',
            sortname: 'parishNo',
            viewrecords: true,
            sortorder: "asc",
            caption: "Parish",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'no data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#parishGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#parishGrid").jqGrid('getGridParam', 'selrow');
                $('#parishForm').loadJSON(jQuery("#parishGrid").getRowData(rowId));

            }
        });
    jQuery("#parishGrid").jqGrid('navGrid', '#parishGridPager', {
        edit: false, add: false, del: false,
        search: true, refresh: false
    });

    addJqgridCustomButtons("parishGrid", "parishForm");
    replaceDefaultGridCss();
}