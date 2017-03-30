/**
 * Created by tijo on 6/7/15.
 */

function loadDepositGrid() {

    jQuery("#depositGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displaydepositgrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['No.', 'Deposit Date', 'Added Date', 'Parish Name', 'Deposit Type', 'Amount', 'Category Name', 'Category Group'],
            colModel: [
                {name: 'id', index: 'id', width: 90, sortable: true},
                {name: 'depositDate', index: 'depositDate', width: 100, sortable: false},
                {name: 'addedDate', index: 'addedDate', width: 90, sortable: false},
                {name: 'associatedParish.parsihName', index: 'associatedParish.parsihName', width: 90, sortable: false},
                {name: 'depositType', index: 'depositType', width: 90, sortable: false},
                {name: 'depositAmount', index: 'depositAmount', width: 90, sortable: false},
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
            pager: '#depositGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Deposits",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'No data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#depositGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#depositGrid").jqGrid('getGridParam', 'selrow');
                $('#depositForm').loadJSON(jQuery("#depositGrid").getRowData(rowId));

            },
            loadComplete: function () {
                $.ajax({
                    type: 'get',
                    url: 'displaysummary.action',
                    dataType: 'json',
                    success: function (response) {
                        $('#totalBalance').html(response.totalBalance);
                        $('#cashInHand').html(response.cashInHand);
                        $('#bankBalance').html(response.bankBalance);
                    }
                });
            }
        });
    jQuery("#depositGrid").jqGrid('navGrid', '#depositGridPager', {
        edit: false, add: false, del: false,
        search: true, refresh: false
    });

    addJqgridCustomButtons("depositGrid", "depositForm");
    replaceDefaultGridCss();
}
