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
            colNames: ['systemRole', 'createdBy', 'isActive', 'email', 'alreadyLoggedIn', 'parishId', 'massCenterId', 'prayerUnitId', 'familyId', 'isValidated'],
            colModel: [
                {name: 'systemRole', index: 'systemRole', width: 90, sortable: false},
                {name: 'createdBy', index: 'createdBy', width: 100, sortable: false},
                {name: 'isActive', index: 'isActive', width: 100, sortable: false},
                {name: 'email', index: 'email', width: 100, sortable: false},
                {name: 'alreadyLoggedIn', index: 'alreadyLoggedIn', width: 100, sortable: false},
                {name: 'parishId', index: 'parishId', width: 100, sortable: false},
                {name: 'massCenterId', index: 'massCenterId', width: 100, sortable: false},
                {name: 'prayerUnitId', index: 'prayerUnitId', width: 100, sortable: false},
                {name: 'familyId', index: 'familyId', width: 100, sortable: false},
                {name: 'isValidated', index: 'isValidated', width: 100, sortable: false}
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
            width: 'auto',
            onSelectRow: function () {
                $('#userForm').loadJSON(jQuery("#userGrid").getRowData(jQuery("#userGrid").jqGrid('getGridParam', 'selrow')));
            }
        });
    jQuery("#userGrid").jqGrid('navGrid', '#userGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("userGrid", "userForm");
    replaceDefaultGridCss();
}
