/**
 * Created by tijo on 6/7/15.
 */

function loadWithdrawalGrid() {

    jQuery("#withdrawalGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displaywithdrawalgrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['No.', 'Withdrawal Date', 'Added Date', 'Parish Name', 'Withdrawal Type', 'Amount', 'Category Name', 'Category Group'],
            colModel: [
                {name: 'id', index: 'id', width: 90, sortable: true},
                {name: 'withdrawalDate', index: 'withdrawalDate', width: 100, sortable: false},
                {name: 'addedDate', index: 'addedDate', width: 90, sortable: false},
                {name: 'associatedParish.parsihName', index: 'associatedParish.parsihName', width: 90, sortable: false},
                {name: 'withdrawalType', index: 'withdrawalType', width: 90, sortable: false},
                {name: 'withdrawalAmount', index: 'withdrawalAmount', width: 90, sortable: false},
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
            pager: '#withdrawalGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Withdrawals",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'No data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#withdrawalGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#withdrawalGrid").jqGrid('getGridParam', 'selrow');
                $('#withdrawalForm').loadJSON(jQuery("#withdrawalGrid").getRowData(rowId));

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
    jQuery("#withdrawalGrid").jqGrid('navGrid', '#withdrawalGridPager', {
        edit: false, add: false, del: false,
        search: true, refresh: false
    });

    addJqgridCustomButtons("withdrawalGrid", "withdrawalForm");
    replaceDefaultGridCss();
}
