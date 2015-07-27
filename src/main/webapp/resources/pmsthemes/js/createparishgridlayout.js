/**
 * Created by tijo on 1/7/15.
 */

jQuery(document).ready(function () {


    jQuery('#editParishForm').bind("click", function () {
        var gsr = jQuery("#parishGrid").jqGrid('getGridParam', 'selrow');
        if (gsr != null) {
            var value = jQuery("#parishGrid").getRowData(gsr)['parishID'];
            window.location.replace('editparishdetails.action?parishName=' + value);
        } else {
            alert("Please select Row");
        }
    });

    jQuery('#parishUpdateButton').click(function () {
        jQuery('#parishForm1').attr('action', 'updateparishinformation.action');
        jQuery("#parishForm1").submit();
    });


});


function loadParishGrid() {

    jQuery("#parishGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"
            },
            url: 'displayparishgrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            //rowList: [10, 20, 30],
            colNames: ['Parish No.', 'Parish Name', 'Parish Place', 'Parish Patron', 'churchName', 'riteName', 'dioceseName', 'foraneName', 'code', 'webSite', 'facebookPage', 'drivingRoute', 'map', 'registeredDate', 'mobileNo', 'email', 'landLineNo', 'faxNo', 'localAddress.addressLineOne', 'localAddress.addressLineTwo', 'localAddress.addressLineThree', 'localAddress.town', 'localAddress.county', 'localAddress.pin', 'localAddress.country'],
            colModel: [
                {name: 'parishNo', index: 'parishNo', width: 80, align: "right", sortable: false},
                {name: 'name', index: 'name', width: 80, align: "right", sortable: false},
                {name: 'place', index: 'place', width: 80, align: "right", sortable: false},
                {name: 'patron', index: 'patron', width: 80, align: "right", sortable: false},
                {name: 'churchName', index: 'churchName', width: 90, sortable: false, hidden: true},
                {name: 'riteName', index: 'riteName', width: 100, sortable: false, hidden: true},
                {name: 'dioceseName', index: 'dioceseName', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'foraneName', index: 'foraneName', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'code', index: 'code', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'webSite', index: 'webSite', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'facebookPage', index: 'facebookPage', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'drivingRoute', index: 'drivingRoute', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'map', index: 'map', width: 80, align: "right", sortable: false, hidden: true},
                {
                    name: 'registeredDate',
                    index: 'registeredDate',
                    width: 80,
                    align: "right",
                    sortable: false,
                    hidden: true
                },
                {name: 'mobileNo', index: 'mobileNo', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'email', index: 'email', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'landLineNo', index: 'landLineNo', width: 80, align: "right", sortable: false, hidden: true},
                {name: 'faxNo', index: 'faxNo', width: 80, align: "right", sortable: false, hidden: true},
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
                }
            ],
            rowNum: 10,
            pager: '#parishGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Parishes",
            autowidth: true,
            shrinkToFit: true,
            height: 'auto',
            width: 'auto',
            onSelectRow: function () {
                $('#parishForm').loadJSON(jQuery("#parishGrid").getRowData(jQuery("#parishGrid").jqGrid('getGridParam', 'selrow')));
            }
        });
    jQuery("#parishGrid").jqGrid('navGrid', '#parishGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("parishGrid", "parishForm");
    replaceDefaultGridCss();
}
