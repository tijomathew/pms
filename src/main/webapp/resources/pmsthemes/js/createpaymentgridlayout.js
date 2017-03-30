/**
 * Created by tijo on 6/7/15.
 */

function loadPaymentGrid() {

    jQuery("#paymentGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displaypaymentgrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['No.', 'Payment Date', 'Added Date', 'Parish Name', 'Payment Type', 'Amount', 'Category Name', 'Category Group'],
            colModel: [
                {name: 'id', index: 'id', width: 90, sortable: true},
                {name: 'paymentDate', index: 'paymentDate', width: 100, sortable: false},
                {name: 'addedDate', index: 'addedDate', width: 90, sortable: false},
                {name: 'associatedParish.parsihName', index: 'associatedParish.parsihName', width: 90, sortable: false},
                {name: 'paymentType', index: 'paymentType', width: 90, sortable: false},
                {name: 'paymentAmount', index: 'paymentAmount', width: 90, sortable: false},
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
            pager: '#paymentGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Payments",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: 'No data available to show!!..Please add data to view',
            onSelectRow: function () {
                $('#paymentGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#panelDiv').show(500);
                var rowId = jQuery("#paymentGrid").jqGrid('getGridParam', 'selrow');
                $('#paymentForm').loadJSON(jQuery("#paymentGrid").getRowData(rowId));

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
    jQuery("#paymentGrid").jqGrid('navGrid', '#paymentGridPager', {
        edit: false, add: false, del: false,
        search: true, refresh: false
    });

    addJqgridCustomButtons("paymentGrid", "paymentForm");
    replaceDefaultGridCss();
}
