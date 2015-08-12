package org.pms.helpers;

import org.pms.enums.SystemRole;
import org.pms.models.*;
import org.pms.services.FamilyService;
import org.pms.services.MassCenterService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: tijo.
 */

@Component
@Scope("prototype")
public final class FactorySelectBox {

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private FamilyService familyService;

    private List<Parish> parishList;
    private List<MassCenter> massCenterList;
    private List<PrayerUnit> prayerUnitList;
    private Map<Long, String> parishMap;
    private Map<Long, String> massCenterMap;
    private Map<Long, String> prayerUnitMap;

    public FactorySelectBox() {
        this.parishList = new ArrayList<>();
        this.massCenterList = new ArrayList<>();
        this.prayerUnitList = new ArrayList<>();
        this.parishMap = new HashMap<>();
        this.massCenterMap = new HashMap<>();
        this.prayerUnitMap = new HashMap<>();
    }

    public Model generateSelectBoxInModel(Model model, User currentUser) {
        model.addAttribute("family", new Family());
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                cleanUpListAndMap();
                createListEntries(currentUser, model);
                break;
            case PARISH_ADMIN:
                cleanUpListAndMap();
                createListEntries(currentUser, model);
                break;
            case MASS_CENTER_ADMIN:
                cleanUpListAndMap();
                createListEntries(currentUser, model);
                break;
            case PRAYER_UNIT_ADMIN:
                cleanUpListAndMap();
                createListEntries(currentUser, model);
                break;
            case FAMILY_USER:
                cleanUpListAndMap();
                createListEntries(currentUser, model);
                break;
        }
        return model;
    }

    private void createListEntries(User currentUser, Model model) {
        parishList.addAll(parishService.getAllParishForUserRole(currentUser));
        massCenterList.addAll(massCenterService.getAllMassCentersForUserRole(currentUser));
        prayerUnitList.addAll(prayerUnitService.getAllPrayerUnitsForUserRole(currentUser));
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParishName));
        }
        if (!massCenterList.isEmpty()) {
            massCenterMap = massCenterList.stream().collect(Collectors.toMap(MassCenter::getId, MassCenter::getMassCenterName));
        }
        if (!prayerUnitList.isEmpty()) {
            prayerUnitMap = prayerUnitList.stream().collect(Collectors.toMap(PrayerUnit::getId, PrayerUnit::getPrayerUnitName));
        }
        parishMap.put(0l, "--Please Select--");
        massCenterMap.put(0l, "--Please Select--");
        prayerUnitMap.put(0l, "--Please Select--");

        model.addAttribute("parishList", parishMap);
        model.addAttribute("massCenterList", massCenterMap);
        model.addAttribute("prayerUnitList", prayerUnitMap);

    }

    public Model createSelectBox(Model model) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Family> familyList = familyService.getFamilyForFamilyID(currentUser.getUserOfFamily().getId());
        Map<Long, String> familyNameMap = new HashMap<>();
        if (!familyList.isEmpty()) {
            familyNameMap = familyList.stream().collect(Collectors.toMap(Family::getId, Family::getFamilyName));
        }
        familyNameMap.put(0l, "--Please Select--");
        model.addAttribute("familyName", familyNameMap);
        return model;
    }

    private void cleanUpListAndMap() {
        if (!parishList.isEmpty()) {
            parishList.clear();
        }
        if (!massCenterList.isEmpty()) {
            massCenterList.clear();
        }
        if (!prayerUnitList.isEmpty()) {
            prayerUnitList.clear();
        }
        if (!parishMap.isEmpty()) {
            parishMap.clear();
        }
        if (!massCenterMap.isEmpty()) {
            massCenterMap.clear();
        }
        if (!prayerUnitMap.isEmpty()) {
            prayerUnitMap.clear();
        }
    }


}
