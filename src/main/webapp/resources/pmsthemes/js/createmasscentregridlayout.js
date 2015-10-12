/**
 * Created by tijo on 6/7/15.
 */

function loadMassCentreGrid() {

    jQuery("#massCentreGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displaymasscentregrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['MC No.', 'MC Name', 'place', 'patronName', 'Parish No.', 'Parish Name', 'registeredDate', 'landLineNo', 'mobileNo', 'faxNo',
                'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country', 'id', 'mappedParish'],
            colModel: [
                {name: 'massCentreNo', index: 'massCentreNo', width: 90, sortable: true},
                {name: 'massCentreName', index: 'massCentreName', width: 90, sortable: false},
                {name: 'place', index: 'place', width: 90, sortable: false, hidden: true},
                {name: 'patronName', index: 'patronName', width: 90, sortable: false},
                {name: 'parishNumber', index: 'parishNumber', width: 90, sortable: false},
                {name: 'parishName', index: 'parishName', width: 90, sortable: false},
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
                },
                {
                    name: 'mappedParish',
                    index: 'mappedParish',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                }
            ],
            rowNum: 10,
            pager: '#massCentreGridPager',
            sortname: 'massCentreNo',
            viewrecords: true,
            sortorder: "asc",
            caption: "Mass Centres",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'no data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#massCentreGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#massCentreGrid").jqGrid('getGridParam', 'selrow');
                $('#massCentreForm').loadJSON(jQuery("#massCentreGrid").getRowData(rowId));

            }
        });
    jQuery("#massCentreGrid").jqGrid('navGrid', '#massCentreGridPager', {
        edit: false, add: false, del: false,
        search: true, refresh: false
    });

    addJqgridCustomButtons("massCentreGrid", "massCentreForm");
    replaceDefaultGridCss();
}