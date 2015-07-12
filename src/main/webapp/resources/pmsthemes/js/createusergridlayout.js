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
            //rowList: [10, 20, 30],
            colNames: ['Role', 'isActive', 'Email'],
            colModel: [
                {name: 'systemRole', index: 'systemRole', width: 90, sortable: false},
                {name: 'isActive', index: 'isActive', width: 100, sortable: false},
                {name: 'email', index: 'email', width: 100, sortable: false}
            ],
            rowNum: 10,
            pager: '#userGridPager',
            //sortname: 'id',
            viewrecords: true,
           // sortorder: "desc",
            caption: "Users",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto'
        });
    jQuery("#userGrid").jqGrid('navGrid', '#userGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("userGrid", "userForm");
    replaceDefaultGridCss();
}
