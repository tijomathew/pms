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
            rowList: [2, 4, 6],
            colNames: ['ID', 'Name', 'Designation', 'Heavenly Patron', 'Native Diocese', 'Native Parish', 'Native Place', 'Priest Card Validity', 'Ordained To Diocese', 'Father Name', 'Mother Name', 'Priest Status', 'Congregation', 'Local Address', 'Native Address', 'Emergency Contact'],
            colModel: [

                {name: 'priestID', index: 'priestID', width: 120},
                {name: 'name', index: 'name', width: 150},
                {name: 'designation', index: 'designation', width: 150, align: "right"},
                {name: 'heavenlyPatron', index: 'heavenlyPatron', width: 150, align: "right"},
                {name: 'nativeDiocese', index: 'nativeDiocese', width: 150, align: "right"},
                {name: 'nativeParish', index: 'nativeParish', width: 150, align: "right"},
                {name: 'nativePlace', index: 'nativePlace', width: 150, align: "right"},
                {name: 'priestCardValidity', index: 'priestCardValidity', width: 150, align: "right"},
                {name: 'ordainedToDiocese', index: 'ordainedToDiocese', width: 150, align: "right"},
                {name: 'fatherName', index: 'fatherName', width: 150, align: "right"},
                {name: 'motherName', index: 'motherName', width: 150, align: "right"},
                {name: 'priestStatus', index: 'priestStatus', width: 150, align: "right"},
                {name: 'congregation', index: 'congregation', width: 150, align: "right"},
                {name: 'localAddress', index: 'localAddress', width: 150, align: "right"},
                {name: 'nativeAddress', index: 'nativeAddress', width: 150, align: "right"},
                {name: 'emergencyContact', index: 'emergencyContact', width: 150, align: "right"}
            ],
            rowNum: 2,
            pager: '#priestGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Priests",
            autowidth: true,
            shrinkToFit: false,
            emptyrecords: "Nothing to display"

        });
    jQuery("#priestGrid").jqGrid('navGrid', '#priestGridPager', {edit: true, add: true, del: true});
}

