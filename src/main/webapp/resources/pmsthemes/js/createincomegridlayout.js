/**
 * Created by tijo on 6/7/15.
 */

function loadIncomeGrid() {

    jQuery("#incomeGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displayincomegrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['No.', 'Income Date', 'Added Date', 'Parish Name', 'Income Type', 'Amount', 'Category Name', 'Category Group'],
            colModel: [
                {name: 'id', index: 'id', width: 90, sortable: true},
                {name: 'incomeDate', index: 'incomeDate', width: 100, sortable: false},
                 {name: 'addedDate', index: 'addedDate', width: 90, sortable: false},
                 {name: 'associatedParish.parsihName', index: 'associatedParish.parsihName', width: 90, sortable: false},
                 {name: 'incomeType', index: 'incomeType', width: 90, sortable: false},
                 {name: 'incomeAmount', index: 'incomeAmount', width: 90, sortable: false},
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
            pager: '#incomeGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "asc",
            caption: "Incomes",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'No data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#incomeGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#incomeGrid").jqGrid('getGridParam', 'selrow');
                $('#incomeForm').loadJSON(jQuery("#incomeGrid").getRowData(rowId));

            }
        });
    jQuery("#incomeGrid").jqGrid('navGrid', '#incomeGridPager', {
        edit: false, add: false, del: false,
        search: true, refresh: false
    });

    addJqgridCustomButtons("incomeGrid", "incomeForm");
    replaceDefaultGridCss();
}
