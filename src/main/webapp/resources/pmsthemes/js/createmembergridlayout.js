/**
 * Created by tijo on 6/7/15.
 */
function loadMemberGrid() {
    var subGridData = "";
    $("#memberGrid").jqGrid(
        {
            jsonreader: {
                root: "rows",
                repeatitems: true,
                cell: "cells",
                id: "id"


            },
            url: 'displaymembergrid.action',
            autoencode: true,
            mtype: 'GET',
            datatype: 'json',
            rowList: [2, 4, 6],
            colNames: ['MemberID', 'Member Name', 'Date of Birth', 'placeOfBirth', 'gender', 'nationality', 'jobDetails', 'personalStatus', 'bloodGroup', 'carNumber', 'liveStatus', 'personalRemarks', 'piousAssociation', 'sundayCatechism', 'sacramentalLife', 'churchRemarks', 'email', 'mobNo', 'landLineNo', 'faxNo', 'dateOfBaptism', 'churchOfBaptism', 'countryOfBaptism', 'baptismName', 'ministerOfBaptism', 'baptismGodFather', 'baptismGodMother', 'patronSaint', 'patronSaintFeastDay', "dateOfConfirmation", "churchOfConfirmation", "countryOfConfirmation", "ministerOfConfirmation", "confirmationGodFather", "confirmationGodMother", "dateOfFirstCommunion", "churchOfHolyCommunion", "countryOfHolyCommunion", "ministerOfHolyCommunion", "dateOfBetrothal", "churchOfBetrothal", "countryOfBetrothal", "priestOfBetrothal", "spouseName", "spouseBaptismName", "spouseNativeParish", "spouseNativeDiocese", "spouseFatherName", "spouseMotherName", "spouseNativeAddress", "spouseNationality", "betrothalWitnessOne", "betrothalWitnessTwo", "dateOfMarriage", "churchOfMarriage", "priestOfMarriage", "marriageWitnessOne", "marriageWitnessTwo", "dateOfDeath", "placeOfDeath", "funeralDate", "buriedChurch", "ministerOfDeath", "placeOfCemetery", "tombNo", "confession", "communion", "anointingTheSick", "ministerOfAnointingTheSick"],
            colModel: [

                {name: 'memberID', index: 'memberID', width: 90},
                {name: 'name', index: 'name', width: 100},
                {name: 'dob', index: 'dob', width: 100},
                {name: 'placeOfBirth', index: 'placeOfBirth', width: 100},
                {name: 'gender', index: 'gender', width: 100},
                {name: 'nationality', index: 'nationality', width: 100},
                {name: 'jobDetails', index: 'jobDetails', width: 100},
                {name: 'personalStatus', index: 'personalStatus', width: 100},
                {name: 'bloodGroup', index: 'bloodGroup', width: 100},
                {name: 'carNumber', index: 'carNumber', width: 100},
                {name: 'liveStatus', index: 'liveStatus', width: 100},
                {name: 'personalRemarks', index: 'personalRemarks', width: 100},
                {name: 'piousAssociation', index: 'piousAssociation', width: 100},
                {name: 'sundayCatechism', index: 'sundayCatechism', width: 100},
                {name: 'sacramentalLife', index: 'sacramentalLife', width: 100},
                {name: 'churchRemarks', index: 'churchRemarks', width: 100},
                {name: 'email', index: 'email', width: 100},
                {name: 'mobNo', index: 'mobNo', width: 100},
                {name: 'landLineNo', index: 'landLineNo', width: 100},
                {name: 'faxNo', index: 'faxNo', width: 100},
                {name: 'dateOfBaptism', index: 'dateOfBaptism', width: 100},
                {name: 'churchOfBaptism', index: 'churchOfBaptism', width: 100},
                {name: 'countryOfBaptism', index: 'countryOfBaptism', width: 100},
                {name: 'baptismName', index: 'baptismName', width: 100},
                {name: 'ministerOfBaptism', index: 'ministerOfBaptism', width: 100},
                {name: 'baptismGodFather', index: 'baptismGodFather', width: 100},
                {name: 'baptismGodMother', index: 'baptismGodMother', width: 100},
                {name: 'patronSaint', index: 'patronSaint', width: 100},
                {name: 'patronSaintFeastDay', index: 'patronSaintFeastDay', width: 100},
                {name: 'dateOfConfirmation', index: 'dateOfConfirmation', width: 100},
                {name: 'churchOfConfirmation', index: 'churchOfConfirmation', width: 100},
                {name: 'countryOfConfirmation', index: 'countryOfConfirmation', width: 100},
                {name: 'ministerOfConfirmation', index: 'ministerOfConfirmation', width: 100},
                {name: 'confirmationGodFather', index: 'confirmationGodFather', width: 100},
                {name: 'confirmationGodMother', index: 'confirmationGodMother', width: 100},
                {name: 'dateOfFirstCommunion', index: 'dateOfFirstCommunion', width: 90},
                {name: 'churchOfHolyCommunion', index: 'churchOfHolyCommunion', width: 100},
                {name: 'countryOfHolyCommunion', index: 'countryOfHolyCommunion', width: 100},
                {name: 'ministerOfHolyCommunion', index: 'ministerOfHolyCommunion', width: 100},
                {
                    name: 'dateOfBetrothal',
                    index: 'dateOfBetrothal',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'churchOfBetrothal',
                    index: 'churchOfBetrothal',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'countryOfBetrothal',
                    index: 'countryOfBetrothal',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'priestOfBetrothal',
                    index: 'priestOfBetrothal',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {name: 'spouseName', index: 'spouseName', width: 100, hidedlg: true, hidden: true},
                {
                    name: 'spouseBaptismName',
                    index: 'spouseBaptismName',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'spouseNativeParish',
                    index: 'spouseNativeParish',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'spouseNativeDiocese',
                    index: 'spouseNativeDiocese',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'spouseFatherName',
                    index: 'spouseFatherName',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'spouseMotherName',
                    index: 'spouseMotherName',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'spouseNativeAddress',
                    index: 'spouseNativeAddress',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'spouseNationality',
                    index: 'spouseNationality',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'betrothalWitnessOne',
                    index: 'betrothalWitnessOne',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'betrothalWitnessTwo',
                    index: 'betrothalWitnessTwo',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {name: 'dateOfMarriage', index: 'dateOfMarriage', width: 100, hidedlg: true, hidden: true},
                {
                    name: 'churchOfMarriage',
                    index: 'churchOfMarriage',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'priestOfMarriage',
                    index: 'priestOfMarriage',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'marriageWitnessOne',
                    index: 'marriageWitnessOne',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'marriageWitnessTwo',
                    index: 'marriageWitnessTwo',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {name: 'dateOfDeath', index: 'dateOfDeath', width: 100, hidedlg: true, hidden: true},
                {name: 'placeOfDeath', index: 'placeOfDeath', width: 100, hidedlg: true, hidden: true},
                {name: 'funeralDate', index: 'funeralDate', width: 100, hidedlg: true, hidden: true},
                {name: 'buriedChurch', index: 'buriedChurch', width: 100, hidedlg: true, hidden: true},
                {
                    name: 'ministerOfDeath',
                    index: 'ministerOfDeath',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'placeOfCemetery',
                    index: 'placeOfCemetery',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {name: 'tombNo', index: 'tombNo', width: 100, hidedlg: true, hidden: true},
                {name: 'confession', index: 'confession', width: 100, hidedlg: true, hidden: true},
                {name: 'communion', index: 'communion', width: 100, hidedlg: true, hidden: true},
                {
                    name: 'anointingTheSick',
                    index: 'anointingTheSick',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
                {
                    name: 'ministerOfAnointingTheSick',
                    index: 'ministerOfAnointingTheSick',
                    width: 100,
                    hidedlg: true,
                    hidden: true
                },
            ],
            rowNum: 2,
            pager: '#memberGridPager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "Members",
            autowidth: true,
            shrinkToFit: false,
            subGrid: true,
            height: 'auto',
            width: 'auto',
            subGridRowExpanded: function (subgrid_id, row_id) {
                var subgrid_table_id = subgrid_id + "_t";
                $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "'></table>");
                var subGridData = [$('#memberGrid').jqGrid('getRowData', row_id)];
                $("#" + subgrid_table_id).jqGrid({
                    datatype: "local",
                    data: subGridData,
                    colNames: ["dateOfBetrothal", "churchOfBetrothal", "countryOfBetrothal", "priestOfBetrothal", "spouseName", "spouseBaptismName", "spouseNativeParish", "spouseNativeDiocese", "spouseFatherName", "spouseMotherName", "spouseNativeAddress", "spouseNationality", "betrothalWitnessOne", "betrothalWitnessTwo", "dateOfMarriage", "churchOfMarriage", "priestOfMarriage", "marriageWitnessOne", "marriageWitnessTwo", "dateOfDeath", "placeOfDeath", "funeralDate", "buriedChurch", "ministerOfDeath", "placeOfCemetery", "tombNo", "confession", "communion", "anointingTheSick", "ministerOfAnointingTheSick"],
                    colModel: [
                        {name: 'dateOfBetrothal', index: 'dateOfBetrothal', width: 120},
                        {name: 'churchOfBetrothal', index: 'churchOfBetrothal', width: 120},
                        {name: 'countryOfBetrothal', index: 'countryOfBetrothal', width: 120},
                        {name: 'priestOfBetrothal', index: 'priestOfBetrothal', width: 120},
                        {name: 'spouseName', index: 'spouseName', width: 120},
                        {name: 'spouseBaptismName', index: 'spouseBaptismName', width: 120},
                        {name: 'spouseNativeParish', index: 'spouseNativeParish', width: 120},
                        {name: 'spouseNativeDiocese', index: 'spouseNativeDiocese', width: 120},
                        {name: 'spouseFatherName', index: 'spouseFatherName', width: 120},
                        {name: 'spouseMotherName', index: 'spouseMotherName', width: 120},
                        {name: 'spouseNativeAddress', index: 'spouseNativeAddress', width: 120},
                        {name: 'spouseNationality', index: 'spouseNationality', width: 120},
                        {name: 'betrothalWitnessOne', index: 'betrothalWitnessOne', width: 120},
                        {name: 'betrothalWitnessTwo', index: 'betrothalWitnessTwo', width: 120},
                        {name: 'dateOfMarriage', index: 'dateOfMarriage', width: 120},
                        {name: 'churchOfMarriage', index: 'churchOfMarriage', width: 120},
                        {name: 'priestOfMarriage', index: 'priestOfMarriage', width: 120},
                        {name: 'marriageWitnessOne', index: 'marriageWitnessOne', width: 120},
                        {name: 'marriageWitnessTwo', index: 'marriageWitnessTwo', width: 120},
                        {name: 'dateOfDeath', index: 'dateOfDeath', width: 120},
                        {name: 'placeOfDeath', index: 'placeOfDeath', width: 120},
                        {name: 'funeralDate', index: 'funeralDate', width: 120},
                        {name: 'buriedChurch', index: 'buriedChurch', width: 120},
                        {name: 'ministerOfDeath', index: 'ministerOfDeath', width: 120},
                        {name: 'placeOfCemetery', index: 'placeOfCemetery', width: 120},
                        {name: 'tombNo', index: 'tombNo', width: 120},
                        {name: 'confession', index: 'confession', width: 120},
                        {name: 'communion', index: 'communion', width: 120},
                        {name: 'anointingTheSick', index: 'anointingTheSick', width: 120},
                        {
                            name: 'ministerOfAnointingTheSick',
                            index: 'ministerOfAnointingTheSick',
                            width: 120
                        }],
                    rowNum: 1,
                    height: 'auto',
                    shrinkToFit: false,
                    width: 'auto',
                    loadComplete: function () {
                        jQuery("#" + subgrid_table_id).jqGrid('setGroupHeaders', {
                            useColSpanStyle: true,
                            groupHeaders: [
                                {
                                    startColumnName: 'dateOfBetrothal',
                                    numberOfColumns: 14,
                                    titleText: 'Bethrotal Details'
                                },
                                {
                                    startColumnName: 'dateOfMarriage',
                                    numberOfColumns: 5,
                                    titleText: 'Marriage Details'
                                },
                                {
                                    startColumnName: 'dateOfDeath',
                                    numberOfColumns: 11,
                                    titleText: 'Death Details'
                                },
                            ]
                        });
                    }
                });
            }
        });

    jQuery("#memberGrid").jqGrid('navGrid', '#memberGridPager', {edit: true, add: true, del: true});

    //to solve groupheader duplicate issue on clicking #tab-2 more than once
    jQuery("#memberGrid").jqGrid('destroyGroupHeader');

    jQuery("#memberGrid").jqGrid('setGroupHeaders', {
        useColSpanStyle: true,
        groupHeaders: [
            {startColumnName: 'email', numberOfColumns: 4, titleText: 'Contact Details'},
            {startColumnName: 'dateOfBaptism', numberOfColumns: 9, titleText: 'Baptism Details'},
            {startColumnName: 'dateOfConfirmation', numberOfColumns: 6, titleText: 'Confirmation Details'},
            {startColumnName: 'dateOfFirstCommunion', numberOfColumns: 4, titleText: 'HolyCommunion Details'},
        ]
    });
}