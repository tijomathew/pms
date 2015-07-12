/**
 * Created by tijo on 6/7/15.
 */
function loadPriestGrid() {

    jQuery("#priestGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displaypriestgrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['ID', 'Name', 'Designation', 'Heavenly Patron', 'Native Diocese', 'Native Parish', 'Native Place', 'Priest Card Validity', 'Ordained To Diocese', 'Father Name', 'Mother Name', 'Priest Status', 'Congregation', 'Local Address', 'Native Address', 'Emergency Contact'],
            colModel: [

                {name: 'priestID', index: 'priestID', width: 120, sortable: false},
                {name: 'name', index: 'name', width: 150, sortable: false},
                {name: 'designation', index: 'designation', width: 150, align: "right", sortable: false},
                {name: 'heavenlyPatron', index: 'heavenlyPatron', width: 150, align: "right", sortable: false},
                {name: 'nativeDiocese', index: 'nativeDiocese', width: 150, align: "right", sortable: false},
                {name: 'nativeParish', index: 'nativeParish', width: 150, align: "right", sortable: false},
                {name: 'nativePlace', index: 'nativePlace', width: 150, align: "right", sortable: false},
                {name: 'priestCardValidity', index: 'priestCardValidity', width: 150, align: "right", sortable: false},
                {name: 'ordainedToDiocese', index: 'ordainedToDiocese', width: 150, align: "right", sortable: false},
                {name: 'fatherName', index: 'fatherName', width: 150, align: "right", sortable: false},
                {name: 'motherName', index: 'motherName', width: 150, align: "right", sortable: false},
                {name: 'priestStatus', index: 'priestStatus', width: 150, align: "right", sortable: false},
                {name: 'congregation', index: 'congregation', width: 150, align: "right", sortable: false},
                {name: 'localAddress', index: 'localAddress', width: 150, align: "right", sortable: false},
                {name: 'nativeAddress', index: 'nativeAddress', width: 150, align: "right", sortable: false},
                {name: 'emergencyContact', index: 'emergencyContact', width: 150, align: "right", sortable: false}
            ],
            rowNum: 10,
            pager: '#priestGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Priests",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            emptyrecords: "Nothing to display"

        });
    jQuery("#priestGrid").jqGrid('navGrid', '#priestGridPager', {
        edit: false,
        add: false,
        del: true,
        search: true,
        refresh: false
    });

    addJqgridCustomButtons("priestGrid", "priestForm");
    replaceDefaultGridCss();
}

