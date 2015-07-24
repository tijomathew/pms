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
            colNames: ["Family No.", "Family Name", "Member No.", "Member Name", "Gender", "Date of Birth", "Relationship", "Parish Name", "Mass Center Name", "Prayer Unit Name", "memberAsPerson.salutation", "memberAsPerson.firstName", "memberAsPerson.middleName", "memberAsPerson.lastName", "memberAsPerson.placeOfBirth",
                "memberAsPerson.photoPathLocation", "memberAsPerson.nationality", "memberAsPerson.personalStatus", "memberAsPerson.email", "memberAsPerson.mobileNo", "memberAsPerson.landLine",
                "memberAsPerson.faxNo", "memberAsPerson.educationQualifications", "memberAsPerson.jobDetails", "memberAsPerson.bloodGroup", "memberAsPerson.carNumber", "memberAsPerson.lifeStatus",
                "memberAsPerson.personalRemarks", "dateOfBaptism", "dateOfConfirmation", "dateOfFirstCommunion", "dateOfMarriage",
                "dateOfDeath", "piousAssociation", "sundayCatechism", "sacramentalLife", "churchRemarks", "churchOfBaptism", "countryOfBaptism", "baptismName", "ministerOfBaptism", "baptismGodFather",
                "baptismGodMother", "patronSaint", "patronSaintFeastDay", "churchOfConfirmation", "countryOfConfirmation",
                "ministerOfConfirmation", "confirmationGodFather", "confirmationGodMother", "churchOfHolyCommunion", "countryOfHolyCommunion",

                "ministerOfHolyCommunion", "dateOfBetrothal", "churchOfBetrothal", "countryOfBetrothal", "priestOfBetrothal", "spouseName", "spouseBaptismName", "spouseNativeParish", "spouseNativeDiocese", "spouseFatherName",
                "spouseMotherName", "spouseNativeAddress", "spouseNationality", "betrothalWitnessOne", "betrothalWitnessTwo", "churchOfMarriage", "priestOfMarriage", "marriageWitnessOne", "marriageWitnessTwo", "placeOfDeath",
                "funeralDate", "buriedChurch", "ministerOfDeath", "placeOfCemetery", "tombNo", "confession", "communion", "anointingTheSick", "ministerOfAnointingTheSick", "familyId"],
            colModel: [
                {name: 'familyNo', index: 'familyNo', width: 100, sortable: false},
                {name: 'familyName', index: 'familyName', width: 100, sortable: false},
                {name: 'memberID', index: 'memberID', width: 100, sortable: false},
                {name: 'memberAsPerson.fullName', index: 'memberAsPerson.fullName', width: 90, sortable: false},
                {name: "memberAsPerson.gender", index: "memberAsPerson.gender", width: 100, sortable: false},
                {name: "memberAsPerson.dateOfBirth", index: "memberAsPerson.dateOfBirth", width: 100, sortable: false},
                {name: 'relationshipInFamily', index: 'relationshipInFamily', width: 100, sortable: false},
                {name: 'parishName', index: 'parishName', width: 100, sortable: false},
                {name: 'massCenterName', index: 'massCenterName', width: 100, sortable: false},
                {name: 'prayerUnitName', index: 'prayerUnitName', width: 100, sortable: false},
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
                {
                    name: 'memberAsPerson.placeOfBirth"',
                    index: "memberAsPerson.placeOfBirth",
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.photoPathLocation',
                    index: 'memberAsPerson.photoPathLocation',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.nationality',
                    index: 'memberAsPerson.nationality',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.personalStatus',
                    index: 'memberAsPerson.personalStatus',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.email', index: 'memberAsPerson.email', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.mobileNo', index: 'memberAsPerson.mobileNo', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.landLine', index: 'memberAsPerson.landLine', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.faxNo', index: 'memberAsPerson.faxNo', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.educationQualifications',
                    index: 'memberAsPerson.educationQualifications',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.jobDetails', index: 'memberAsPerson.jobDetails', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.bloodGroup', index: 'memberAsPerson.bloodGroup', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.carNumber', index: 'memberAsPerson.carNumber', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.lifeStatus', index: 'memberAsPerson.lifeStatus', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'memberAsPerson.personalRemarks',
                    index: 'memberAsPerson.personalRemarks',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'dateOfBaptism', index: 'dateOfBaptism', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'dateOfConfirmation', index: 'dateOfConfirmation', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'dateOfFirstCommunion', index: 'dateOfFirstCommunion', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'dateOfMarriage', index: 'dateOfMarriage', width: 100, sortable: false,
                    hidden: true
                },

                {
                    name: 'dateOfDeath', index: 'dateOfDeath', width: 100, sortable: false,
                    hidden: true
                },

                {
                    name: 'piousAssociation', index: 'piousAssociation', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'sundayCatechism', index: 'sundayCatechism', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'sacramentalLife', index: 'sacramentalLife', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'churchRemarks', index: 'churchRemarks', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'churchOfBaptism', index: 'churchOfBaptism', width: 100, sortable: false,
                    hidden: true
                },

                {
                    name: 'countryOfBaptism', index: 'countryOfBaptism', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'baptismName', index: 'baptismName', width: 100, sortable: false,
                    hidden: true
                },

                {
                    name: 'ministerOfBaptism', index: 'ministerOfBaptism', width: 90, sortable: false,
                    hidden: true
                },

                {
                    name: 'baptismGodFather', index: 'baptismGodFather', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'baptismGodMother', index: 'baptismGodMother', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'patronSaint', index: 'patronSaint', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'patronSaintFeastDay',
                    index: 'patronSaintFeastDay',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'churchOfConfirmation',
                    index: 'churchOfConfirmation',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'countryOfConfirmation',
                    index: 'countryOfConfirmation',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'ministerOfConfirmation',
                    index: 'ministerOfConfirmation',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'confirmationGodFather', index: 'confirmationGodFather', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'confirmationGodMother',
                    index: 'confirmationGodMother',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'churchOfHolyCommunion',
                    index: 'churchOfHolyCommunion',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'countryOfHolyCommunion',
                    index: 'countryOfHolyCommunion',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'ministerOfHolyCommunion',
                    index: 'ministerOfHolyCommunion',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'dateOfBetrothal',
                    index: 'dateOfBetrothal',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'churchOfBetrothal',
                    index: 'churchOfBetrothal',
                    width: 100,
                    hidden: true

                },
                {
                    name: 'countryOfBetrothal',
                    index: 'countryOfBetrothal',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'priestOfBetrothal',
                    index: 'priestOfBetrothal',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'spouseName',
                    index: 'spouseName',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'spouseBaptismName', index: 'spouseBaptismName', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'spouseNativeParish',
                    index: 'spouseNativeParish',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'spouseNativeDiocese',
                    index: 'spouseNativeDiocese',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'spouseFatherName',
                    index: 'spouseFatherName',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'spouseMotherName',
                    index: 'spouseMotherName',
                    width: 100,
                    sortable: false,
                    hidden: true
                },

                {
                    name: 'spouseNativeAddress', index: 'spouseNativeAddress', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'spouseNationality', index: 'spouseNationality', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'betrothalWitnessOne', index: 'betrothalWitnessOne', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'betrothalWitnessTwo', index: 'betrothalWitnessTwo', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'churchOfMarriage',
                    index: 'churchOfMarriage',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'priestOfMarriage',
                    index: 'priestOfMarriage',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'marriageWitnessOne', index: 'marriageWitnessOne', width: 100, sortable: false,
                    hidden: true
                },
                {
                    name: 'marriageWitnessTwo',
                    index: 'marriageWitnessTwo',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'placeOfDeath',
                    index: 'placeOfDeath',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'funeralDate',
                    index: 'funeralDate',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'buriedChurch',
                    index: 'buriedChurch',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'ministerOfDeath',
                    index: 'ministerOfDeath',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'placeOfCemetery',
                    index: 'placeOfCemetery',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'tombNo',
                    index: 'tombNo',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'confession',
                    index: 'confession',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'communion',
                    index: 'communion',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'anointingTheSick',
                    index: 'anointingTheSick',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'ministerOfAnointingTheSick',
                    index: 'ministerOfAnointingTheSick',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
                {
                    name: 'familyId',
                    index: 'familyId',
                    width: 100,
                    sortable: false,
                    hidden: true
                },
            ],
            rowNum: 10,
            pager: '#memberGridPager',
            //sortname: 'id',
            viewrecords: true,
            //sortorder: "desc",
            caption: "Members",
            autowidth: true,
            shrinkToFit: true,
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