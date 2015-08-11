/**
 * Created by tijo on 6/7/15.
 */

function loadFamilyGrid() {

    $("#familyGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displayfamilygrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [2, 4, 6],
            colNames: ['Family No.', 'Family Name', 'Parish No.', 'Parish Name', 'MC No.', 'MC Name', 'PU No.', 'PU Name', 'Native Parish', 'Native Diocese', 'dateOfRegistration', 'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country', 'nativeAddress.addressLineOne', 'nativeAddress.addressLineTwo', 'nativeAddress.addressLineThree', 'nativeAddress.postOffice', 'nativeAddress.district', 'nativeAddress.pin', 'nativeAddress.state', 'nativeAddress.country', 'id', 'familyParish', 'familyMassCenter', 'familyPrayerUnit', 'emergencynameaddress', 'emergencyphoneno', 'emergencyalternativeno', 'emeregencyemail'],
            colModel: [
                {name: 'familyNo', index: 'familyNo', width: 100, sortable: false},
                {name: 'familyName', index: 'familyName', width: 90, sortable: false},
                {name: 'parishNumber', index: 'parishNumber', width: 100, sortable: false},
                {name: 'parishName', index: 'parishName', width: 100, sortable: false},
                {name: 'massCenterNumber', index: 'massCenterNumber', width: 100, sortable: false},
                {name: 'massCenterName', index: 'massCenterName', width: 100, sortable: false},
                {name: 'prayerUnitNumber', index: 'prayerUnitNumber', width: 100, sortable: false},
                {name: 'prayerUnitName', index: 'prayerUnitName', width: 100, sortable: false},
                {name: 'parishInNative', index: 'parishInNative', width: 100, sortable: false},
                {name: 'dioceseInNative', index: 'dioceseInNative', width: 100, sortable: false},
                {name: 'dateOfRegistration', index: 'dateOfRegistration', width: 100, sortable: false, hidden: true},
                {
                    name: 'localAddress.addressLineOne',
                    index: 'localAddress.addressLineOne',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'localAddress.addressLineTwo',
                    index: 'localAddress.addressLineTwo',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'localAddress.addressLineThree',
                    index: 'localAddress.addressLineThree',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'localAddress.town',
                    index: 'localAddress.town',
                    width: 80,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'localAddress.county',
                    index: 'localAddress.county',
                    width: 80,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'localAddress.pin',
                    index: 'localAddress.pin',
                    width: 80,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'localAddress.country',
                    index: 'localAddress.country',
                    width: 80,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'nativeAddress.addressLineOne',
                    index: 'nativeAddress.addressLineOne',
                    width: 150,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'nativeAddress.addressLineTwo',
                    index: 'nativeAddress.addressLineTwo',
                    width: 150,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'nativeAddress.addressLineThree',
                    index: 'nativeAddress.addressLineThree',
                    width: 150,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'nativeAddress.postOffice',
                    index: 'nativeAddress.postOffice',
                    width: 150,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'nativeAddress.district',
                    index: 'nativeAddress.district',
                    width: 150,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'nativeAddress.pin',
                    index: 'nativeAddress.pin',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'nativeAddress.state',
                    index: 'nativeAddress.state',
                    width: 150,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'nativeAddress.country',
                    index: 'nativeAddress.country',
                    width: 150,
                    align: "right",
                    sortable: false, hidden: true
                },
                {
                    name: 'id',
                    index: 'id',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'familyParish',
                    index: 'familyParish',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'familyMassCenter',
                    index: 'familyMassCenter',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'familyPrayerUnit',
                    index: 'familyPrayerUnit',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'emergencyContact.nameAddress',
                    index: 'emergencyContact.nameAddress',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'emergencyContact.phoneNo',
                    index: 'emergencyContact.phoneNo',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'emergencyContact.alternativePhoneNo',
                    index: 'emergencyContact.alternativePhoneNo',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'emergencyContact.email',
                    index: 'emergencyContact.email',
                    width: 150,
                    align: "right",
                    sortable: false,
                    hidden: true
                }
            ],
            rowNum: 10,
            pager: '#familyGridPager',
            //sortname: 'id',
            viewrecords: true,
            //sortorder: "desc",
            caption: "Families",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            onSelectRow: function () {
                $('#familyGridPager').find('.ui-pg-table .navtable').find('tr:first').find('.buttontd').addClass('hidedisplay');
                jQuery('#familyForm').show(500);
                var rowId = jQuery("#familyGrid").jqGrid('getGridParam', 'selrow');
                $('#familyForm').loadJSON(jQuery("#familyGrid").getRowData(rowId));

            }
        });
    jQuery("#familyGrid").jqGrid('navGrid', '#familyGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("familyGrid", "familyForm");
    replaceDefaultGridCss();

}
