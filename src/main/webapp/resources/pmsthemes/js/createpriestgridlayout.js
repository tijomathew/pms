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
            colNames: ['priestID', 'dateOfOrdination', 'feastDay', 'heavenlyPatron', 'nativeDiocese', 'nativeParish', 'nativePlace', 'priestCardValidity', 'ordainedToDiocese', 'fatherName', 'motherName', 'priestStatus', 'congregation', 'familyName', 'priestAsPerson.salutation', 'priestAsPerson.firstName', 'priestAsPerson.middleName', 'priestAsPerson.lastName', 'priestAsPerson.dateOfBirth', 'priestAsPerson.placeOfBirth', 'priestAsPerson.gender', 'priestAsPerson.nationality', 'priestAsPerson.personalStatus', 'priestAsPerson.email', 'priestAsPerson.mobileNo', 'priestAsPerson.landLine', 'priestAsPerson.faxNo', 'priestAsPerson.educationQualifications',
                'priestAsPerson.jobDetails', 'priestAsPerson.bloodGroup', 'priestAsPerson.carNumber', 'priestAsPerson.lifeStatus', 'priestAsPerson.personalRemarks', 'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country', 'nativeAddress.addressLineOne', 'nativeAddress.addressLineTwo', 'nativeAddress.addressLineThree', 'nativeAddress.postOffice', 'nativeAddress.district', 'nativeAddress.pin', 'nativeAddress.state', 'nativeAddress.country', 'emergencyContact.name', 'emergencyContact.addressLineOne', 'emergencyContact.addressLineTwo', 'emergencyContact.addressLineThree', 'emergencyContact.mobileNo', 'emergencyContact.landLineNo', 'emergencyContact.email'],
            colModel: [

                {name: 'priestID', index: 'priestID', width: 120, sortable: false},
                {name: 'dateOfOrdination', index: 'dateOfOrdination', width: 150, sortable: false},
                {name: 'feastDay', index: 'feastDay', width: 150, align: "right", sortable: false},
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
                {name: 'familyName', index: 'familyName', width: 150, align: "right", sortable: false},
                {
                    name: 'priestAsPerson.salutation',
                    index: 'priestAsPerson.salutation',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.firstName',
                    index: 'priestAsPerson.firstName',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.middleName',
                    index: 'priestAsPerson.middleName',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.lastName',
                    index: 'priestAsPerson.lastName',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.dateOfBirth',
                    index: 'priestAsPerson.dateOfBirth',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.placeOfBirth',
                    index: 'priestAsPerson.placeOfBirth',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.gender',
                    index: 'priestAsPerson.gender',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.nationality',
                    index: 'priestAsPerson.nationality',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.personalStatus',
                    index: 'priestAsPerson.personalStatus',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.email',
                    index: 'priestAsPerson.email',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.mobileNo',
                    index: 'priestAsPerson.mobileNo',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.landLine',
                    index: 'priestAsPerson.landLine',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.faxNo',
                    index: 'priestAsPerson.faxNo',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.educationQualifications',
                    index: 'priestAsPerson.educationQualifications',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.jobDetails',
                    index: 'priestAsPerson.jobDetails',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.bloodGroup',
                    index: 'priestAsPerson.bloodGroup',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.carNumber',
                    index: 'priestAsPerson.carNumber',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.lifeStatus',
                    index: 'priestAsPerson.lifeStatus',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'priestAsPerson.personalRemarks',
                    index: 'priestAsPerson.personalRemarks',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'localAddress.addressLineOne',
                    index: 'localAddress.addressLineOne',
                    width: 80,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'localAddress.addressLineTwo',
                    index: 'localAddress.addressLineTwo',
                    width: 80,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'localAddress.addressLineThree',
                    index: 'localAddress.addressLineThree',
                    width: 80,
                    align: "right",
                    sortable: false
                },
                {name: 'localAddress.town', index: 'localAddress.town', width: 80, align: "right", sortable: false},
                {name: 'localAddress.county', index: 'localAddress.county', width: 80, align: "right", sortable: false},
                {name: 'localAddress.pin', index: 'localAddress.pin', width: 80, align: "right", sortable: false},
                {
                    name: 'localAddress.country',
                    index: 'localAddress.country',
                    width: 80,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'nativeAddress.addressLineOne',
                    index: 'nativeAddress.addressLineOne',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'nativeAddress.addressLineTwo',
                    index: 'nativeAddress.addressLineTwo',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'nativeAddress.addressLineThree',
                    index: 'nativeAddress.addressLineThree',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'nativeAddress.postOffice',
                    index: 'nativeAddress.postOffice',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'nativeAddress.district',
                    index: 'nativeAddress.district',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {name: 'nativeAddress.pin', index: 'nativeAddress.pin', width: 150, align: "right", sortable: false},
                {
                    name: 'nativeAddress.state',
                    index: 'nativeAddress.state',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'nativeAddress.country',
                    index: 'nativeAddress.country',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'emergencyContact.name',
                    index: 'emergencyContact.name',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'emergencyContact.addressLineOne',
                    index: 'emergencyContact.addressLineOne',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'emergencyContact.addressLineTwo',
                    index: 'emergencyContact.addressLineTwo',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'emergencyContact.addressLineThree',
                    index: 'emergencyContact.addressLineThree',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'emergencyContact.mobileNo',
                    index: 'emergencyContact.mobileNo',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'emergencyContact.landLineNo',
                    index: 'emergencyContact.landLineNo',
                    width: 150,
                    align: "right",
                    sortable: false
                },
                {
                    name: 'emergencyContact.email',
                    index: 'emergencyContact.email',
                    width: 150,
                    align: "right",
                    sortable: false
                }
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

