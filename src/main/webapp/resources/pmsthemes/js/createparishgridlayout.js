/**
 * Created by tijo on 1/7/15.
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
            //rowList: [10, 20, 30],
            colNames: ['Parish No.', 'Parish Name', 'Parish Place', 'Parish Patron', 'webSite', 'facebookPage', 'registeredDate', 'mobileNo', 'landLineNo', 'faxNo', 'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country', 'id'],
            colModel: [
                {name: 'parishNo', index: 'parishNo', width: 80, align: "right", sortable: false},
                {name: 'parishName', index: 'parishName', width: 80, align: "right", sortable: false},
                {name: 'place', index: 'place', width: 80, align: "right", sortable: false},
                {name: 'patron', index: 'patron', width: 80, align: "right", sortable: false},
                {name: 'webSite', index: 'webSite', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'facebookPage', index: 'facebookPage', width: 80, align: "right", sortable: false, hidden: true},
                {
                    name: 'registeredDate',
                    index: 'registeredDate',
                    width: 80,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {name: 'mobileNo', index: 'mobileNo', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'landLineNo', index: 'landLineNo', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'faxNo', index: 'faxNo', width: 80, align: "right", sortable: false, hidden: true},
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
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Parishes",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'No data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#parishGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#parishGrid").jqGrid('getGridParam', 'selrow');
                $('#parishForm').loadJSON(jQuery("#parishGrid").getRowData(rowId));
            }
        });
    jQuery("#parishGrid").jqGrid('navGrid', '#parishGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("parishGrid", "parishForm");
    replaceDefaultGridCss();
}
