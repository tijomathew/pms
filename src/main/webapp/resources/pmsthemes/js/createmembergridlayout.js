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
            //rowList: [2, 4, 6],
            colNames: ["familyNo","memberID", "memberAsPerson.fullName", "memberAsPerson.salutation", "memberAsPerson.firstName", "memberAsPerson.middleName", "memberAsPerson.lastName", "memberAsPerson.dateOfBirth", "memberAsPerson.placeOfBirth",
                "memberAsPerson.gender", "memberAsPerson.photoPathLocation", "memberAsPerson.nationality", "memberAsPerson.personalStatus", "memberAsPerson.email", "memberAsPerson.mobileNo", "memberAsPerson.landLine",
                "memberAsPerson.faxNo", "memberAsPerson.educationQualifications", "memberAsPerson.jobDetails", "memberAsPerson.bloodGroup", "memberAsPerson.carNumber", "memberAsPerson.lifeStatus",
                "memberAsPerson.personalRemarks", "relationshipInFamily", "dateOfBaptism", "dateOfConfirmation", "dateOfFirstCommunion", "dateOfMarriage",
                "dateOfDeath", "piousAssociation", "sundayCatechism", "sacramentalLife", "churchRemarks", "churchOfBaptism", "countryOfBaptism", "baptismName", "ministerOfBaptism", "baptismGodFather",
                "baptismGodMother", "patronSaint", "patronSaintFeastDay", "churchOfConfirmation", "countryOfConfirmation",
                "ministerOfConfirmation", "confirmationGodFather", "confirmationGodMother", "churchOfHolyCommunion", "countryOfHolyCommunion",

                "ministerOfHolyCommunion", "dateOfBetrothal", "churchOfBetrothal", "countryOfBetrothal", "priestOfBetrothal", "spouseName", "spouseBaptismName", "spouseNativeParish", "spouseNativeDiocese", "spouseFatherName",
                "spouseMotherName", "spouseNativeAddress", "spouseNationality", "betrothalWitnessOne", "betrothalWitnessTwo", "churchOfMarriage", "priestOfMarriage", "marriageWitnessOne", "marriageWitnessTwo", "placeOfDeath",
                "funeralDate", "buriedChurch", "ministerOfDeath", "placeOfCemetery", "tombNo", "confession", "communion", "anointingTheSick", "ministerOfAnointingTheSick", "familyId"],
            colModel: [
                {name: 'familyNo', index: 'familyNo', width: 100, sortable: false},
                {name: 'memberID', index: 'memberID', width: 100, sortable: false},
                {name: 'memberAsPerson.fullName', index: 'memberAsPerson.fullName', width: 90, sortable: false},
                {
                    name: 'memberAsPerson.salutation',
                    index: 'memberAsPerson.salutation',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.firstName',
                    index: 'memberAsPerson.firstName',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.middleName',
                    index: 'memberAsPerson.middleName',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.lastName',
                    index: 'memberAsPerson.lastName',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {name: "memberAsPerson.dateOfBirth", index: "memberAsPerson.dateOfBirth", width: 100, sortable: false},
                {
                    name: 'memberAsPerson.placeOfBirth"',
                    index: "memberAsPerson.placeOfBirth",
                    width: 100,
                    sortable: false
                },
                {name: "memberAsPerson.gender", index: "memberAsPerson.gender", width: 100, sortable: false},
                {
                    name: 'memberAsPerson.photoPathLocation',
                    index: 'memberAsPerson.photoPathLocation',
                    width: 100,
                    sortable: false
                },
                {name: 'memberAsPerson.nationality', index: 'memberAsPerson.nationality', width: 100, sortable: false},
                {
                    name: 'memberAsPerson.personalStatus',
                    index: 'memberAsPerson.personalStatus',
                    width: 100,
                    sortable: false
                },
                {name: 'memberAsPerson.email', index: 'memberAsPerson.email', width: 100, sortable: false},
                {name: 'memberAsPerson.mobileNo', index: 'memberAsPerson.mobileNo', width: 100, sortable: false},
                {name: 'memberAsPerson.landLine', index: 'memberAsPerson.landLine', width: 100, sortable: false},
                {name: 'memberAsPerson.faxNo', index: 'memberAsPerson.faxNo', width: 100, sortable: false},
                {
                    name: 'memberAsPerson.educationQualifications',
                    index: 'memberAsPerson.educationQualifications',
                    width: 100,
                    sortable: false
                },
                {name: 'memberAsPerson.jobDetails', index: 'memberAsPerson.jobDetails', width: 100, sortable: false},
                {name: 'memberAsPerson.bloodGroup', index: 'memberAsPerson.bloodGroup', width: 100, sortable: false},
                {name: 'memberAsPerson.carNumber', index: 'memberAsPerson.carNumber', width: 100, sortable: false},
                {name: 'memberAsPerson.lifeStatus', index: 'memberAsPerson.lifeStatus', width: 100, sortable: false},
                {
                    name: 'memberAsPerson.personalRemarks',
                    index: 'memberAsPerson.personalRemarks',
                    width: 100,
                    sortable: false
                },
                {name: 'relationshipInFamily', index: 'relationshipInFamily', width: 100, sortable: false},
                {name: 'dateOfBaptism', index: 'dateOfBaptism', width: 100, sortable: false},
                {name: 'dateOfConfirmation', index: 'dateOfConfirmation', width: 100, sortable: false},
                {name: 'dateOfFirstCommunion', index: 'dateOfFirstCommunion', width: 100, sortable: false},
                {name: 'dateOfMarriage', index: 'dateOfMarriage', width: 100, sortable: false},

                {name: 'dateOfDeath', index: 'dateOfDeath', width: 100, sortable: false},

                {name: 'piousAssociation', index: 'piousAssociation', width: 100, sortable: false},
                {name: 'sundayCatechism', index: 'sundayCatechism', width: 100, sortable: false},
                {name: 'sacramentalLife', index: 'sacramentalLife', width: 100, sortable: false},
                {name: 'churchRemarks', index: 'churchRemarks', width: 100, sortable: false},
                {name: 'churchOfBaptism', index: 'churchOfBaptism', width: 100, sortable: false},

                {name: 'countryOfBaptism', index: 'countryOfBaptism', width: 100, sortable: false},
                {name: 'baptismName', index: 'baptismName', width: 100, sortable: false},

                {name: 'ministerOfBaptism', index: 'ministerOfBaptism', width: 90, sortable: false},

                {name: 'baptismGodFather', index: 'baptismGodFather', width: 100, sortable: false},
                {name: 'baptismGodMother', index: 'baptismGodMother', width: 100, sortable: false},
                {name: 'patronSaint', index: 'patronSaint', width: 100, sortable: false},
                {
                    name: 'patronSaintFeastDay',
                    index: 'patronSaintFeastDay',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'churchOfConfirmation',
                    index: 'churchOfConfirmation',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'countryOfConfirmation',
                    index: 'countryOfConfirmation',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'ministerOfConfirmation',
                    index: 'ministerOfConfirmation',
                    width: 100,
                    sortable: false
                },
                {name: 'confirmationGodFather', index: 'confirmationGodFather', width: 100, sortable: false},
                {
                    name: 'confirmationGodMother',
                    index: 'confirmationGodMother',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'churchOfHolyCommunion',
                    index: 'churchOfHolyCommunion',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'countryOfHolyCommunion',
                    index: 'countryOfHolyCommunion',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'ministerOfHolyCommunion',
                    index: 'ministerOfHolyCommunion',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'dateOfBetrothal',
                    index: 'dateOfBetrothal',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'churchOfBetrothal',
                    index: 'churchOfBetrothal',
                    width: 100

                },
                {
                    name: 'countryOfBetrothal',
                    index: 'countryOfBetrothal',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'priestOfBetrothal',
                    index: 'priestOfBetrothal',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'spouseName',
                    index: 'spouseName',
                    width: 100,
                    sortable: false
                },
                {name: 'spouseBaptismName', index: 'spouseBaptismName', width: 100, sortable: false},
                {
                    name: 'spouseNativeParish',
                    index: 'spouseNativeParish',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'spouseNativeDiocese',
                    index: 'spouseNativeDiocese',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'spouseFatherName',
                    index: 'spouseFatherName',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'spouseMotherName',
                    index: 'spouseMotherName',
                    width: 100,
                    sortable: false
                },

                {name: 'spouseNativeAddress', index: 'spouseNativeAddress', width: 100, sortable: false},
                {name: 'spouseNationality', index: 'spouseNationality', width: 100, sortable: false},
                {name: 'betrothalWitnessOne', index: 'betrothalWitnessOne', width: 100, sortable: false},
                {name: 'betrothalWitnessTwo', index: 'betrothalWitnessTwo', width: 100, sortable: false},
                {
                    name: 'churchOfMarriage',
                    index: 'churchOfMarriage',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'priestOfMarriage',
                    index: 'priestOfMarriage',
                    width: 100,
                    sortable: false
                },
                {name: 'marriageWitnessOne', index: 'marriageWitnessOne', width: 100, sortable: false},
                {
                    name: 'marriageWitnessTwo',
                    index: 'marriageWitnessTwo',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'placeOfDeath',
                    index: 'placeOfDeath',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'funeralDate',
                    index: 'funeralDate',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'buriedChurch',
                    index: 'buriedChurch',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'ministerOfDeath',
                    index: 'ministerOfDeath',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'placeOfCemetery',
                    index: 'placeOfCemetery',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'tombNo',
                    index: 'tombNo',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'confession',
                    index: 'confession',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'communion',
                    index: 'communion',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'anointingTheSick',
                    index: 'anointingTheSick',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'ministerOfAnointingTheSick',
                    index: 'ministerOfAnointingTheSick',
                    width: 100,
                    sortable: false
                },
                {
                    name: 'familyId',
                    index: 'familyId',
                    width: 100,
                    sortable: false
                },
            ],
            rowNum: 10,
            pager: '#memberGridPager',
            //sortname: 'id',
            viewrecords: true,
            //sortorder: "desc",
            caption: "Members",
            autowidth: true,
            shrinkToFit: false,
            subGrid: false,
            height: 'auto',
            width: 'auto'

        });

    replaceDefaultGridCss();

    jQuery("#memberGrid").jqGrid('navGrid', '#memberGridPager', {
        edit: false, add: false, del: true,
        search: true, refresh: false
    });

    addJqgridCustomButtons("memberGrid", "memberForm");
    replaceDefaultGridCss();

}