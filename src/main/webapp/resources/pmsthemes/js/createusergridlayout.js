/**
 * Created by tijo on 6/7/15.
 */
function loadUserGrid() {

    jQuery("#userGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displayusergrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            rowList: [10, 20, 30],
            colNames: ['id', 'User Name', 'Role', 'isActive', 'Email', 'Phone No'],
            colModel: [
                {name: 'id', index: 'id', width: 90},
                {name: 'userName', index: 'userName', width: 90},
                {name: 'systemRole', index: 'systemRole', width: 90},
                {name: 'isActive', index: 'isActive', width: 100},
                {name: 'email', index: 'email', width: 100},
                {name: 'phoneNo', index: 'phoneNo', width: 100}

            ],
            rowNum: 10,
            pager: '#userGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Users",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto'
        });
    jQuery("#userGrid").jqGrid('navGrid', '#userGridPager', {edit: true, add: true, del: true});
}
