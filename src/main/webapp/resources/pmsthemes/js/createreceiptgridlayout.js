/**
 * Created by tijo on 6/7/15.
 */

function loadReceiptGrid() {

    jQuery("#receiptGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displayreceiptgrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['No.', 'Receipt Date', 'Added Date', 'Parish Name', 'Receipt Type', 'Amount', 'Category Name', 'Category Group'],
            colModel: [
                {name: 'id', index: 'id', width: 90, sortable: true},
                {name: 'receiptDate', index: 'receiptDate', width: 100, sortable: false},
                 {name: 'addedDate', index: 'addedDate', width: 90, sortable: false},
                 {name: 'associatedParish.parsihName', index: 'associatedParish.parsihName', width: 90, sortable: false},
                 {name: 'receiptType', index: 'receiptType', width: 90, sortable: false},
                 {name: 'receiptAmount', index: 'receiptAmount', width: 90, sortable: false},
                 {
                 name: 'category.categoryName',
                 index: 'category.categoryName',
                 width: 80,
                 align: "right",
                 sortable: false
                 },
                 {
                 name: 'category.categoryGroup.categoryGroupName',
                 index: 'category.categoryGroup.categoryGroupName',
                 width: 80,
                 align: "right",
                 sortable: false
                 }
            ],
            rowNum: 10,
            pager: '#receiptGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "asc",
            caption: "Receipts",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'No data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#receiptGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#receiptGrid").jqGrid('getGridParam', 'selrow');
                $('#receiptForm').loadJSON(jQuery("#receiptGrid").getRowData(rowId));

            }
        });
    jQuery("#receiptGrid").jqGrid('navGrid', '#receiptGridPager', {
        edit: false, add: false, del: false,
        search: true, refresh: false
    });

    addJqgridCustomButtons("receiptGrid", "receiptForm");
    replaceDefaultGridCss();
}
