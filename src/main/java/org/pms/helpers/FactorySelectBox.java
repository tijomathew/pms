package org.pms.helpers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.enums.SystemRole;
import org.pms.models.*;
import org.pms.services.FamilyService;
import org.pms.services.MassCentreService;
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
    private MassCentreService massCentreService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private FamilyService familyService;

    private List<Parish> parishList;
    private List<MassCentre> massCentreList;
    private List<PrayerUnit> prayerUnitList;
    private Map<Long, String> parishMap;
    private Map<Long, String> massCentreMap;
    private Map<Long, String> prayerUnitMap;

    public FactorySelectBox() {
        this.parishList = new ArrayList<>();
        this.massCentreList = new ArrayList<>();
        this.prayerUnitList = new ArrayList<>();
        this.parishMap = new HashMap<>();
        this.massCentreMap = new HashMap<>();
        this.prayerUnitMap = new HashMap<>();
    }

    public Model generateSelectBoxInModel(Model model, User currentUser) {
        Family modelObjectOfFamily = new Family();
        modelObjectOfFamily.setDateOfRegistration(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("family", modelObjectOfFamily);
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
        massCentreList.addAll(massCentreService.getAllMassCentresForUserRole(currentUser));
        prayerUnitList.addAll(prayerUnitService.getAllPrayerUnitsForUserRole(currentUser));
        if (!parishList.isEmpty()) {
            parishMap = parishList.stream().collect(Collectors.toMap(Parish::getId, Parish::getParishName));
        }
        if (!massCentreList.isEmpty()) {
            massCentreMap = massCentreList.stream().collect(Collectors.toMap(MassCentre::getId, MassCentre::getMassCentreName));
        }
        if (!prayerUnitList.isEmpty()) {
            prayerUnitMap = prayerUnitList.stream().collect(Collectors.toMap(PrayerUnit::getId, PrayerUnit::getPrayerUnitName));
        }
        parishMap.put(0l, "--Please Select--");
        massCentreMap.put(0l, "--Please Select--");
        prayerUnitMap.put(0l, "--Please Select--");

        model.addAttribute("parishList", parishMap);
        model.addAttribute("massCentreList", massCentreMap);
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
        if (!massCentreList.isEmpty()) {
            massCentreList.clear();
        }
        if (!prayerUnitList.isEmpty()) {
            prayerUnitList.clear();
        }
        if (!parishMap.isEmpty()) {
            parishMap.clear();
        }
        if (!massCentreMap.isEmpty()) {
            massCentreMap.clear();
        }
        if (!prayerUnitMap.isEmpty()) {
            prayerUnitMap.clear();
        }
    }


}
