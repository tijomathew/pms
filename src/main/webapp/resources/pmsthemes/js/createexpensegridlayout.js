/**
 * Created by tijo on 6/7/15.
 */

function loadExpenseGrid() {

    jQuery("#expenseGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displayexpensegrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['No.', 'Expense Date', 'Added Date', 'Parish Name', 'Expense Type', 'Amount', 'Category Name', 'Category Group'],
            colModel: [
                {name: 'id', index: 'id', width: 90, sortable: true},
                {name: 'expenseDate', index: 'expenseDate', width: 100, sortable: false},
                {name: 'addedDate', index: 'addedDate', width: 90, sortable: false},
                {name: 'associatedParish.parsihName', index: 'associatedParish.parsihName', width: 90, sortable: false},
                {name: 'expenseType', index: 'expenseType', width: 90, sortable: false},
                {name: 'expenseAmount', index: 'expenseAmount', width: 90, sortable: false},
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
            pager: '#expenseGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "asc",
            caption: "Expenses",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'No data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#expenseGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#expenseGrid").jqGrid('getGridParam', 'selrow');
                $('#expenseForm').loadJSON(jQuery("#expenseGrid").getRowData(rowId));

            }
        });
    jQuery("#expenseGrid").jqGrid('navGrid', '#expenseGridPager', {
        edit: false, add: false, del: false,
        search: true, refresh: false
    });

    addJqgridCustomButtons("expenseGrid", "expenseForm");
    replaceDefaultGridCss();
}
