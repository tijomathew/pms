package org.pms.serviceImpls;

import org.pms.daos.MemberDao;
import org.pms.enums.*;
import org.pms.models.Family;
import org.pms.models.Member;
import org.pms.models.Person;
import org.pms.models.User;
import org.pms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class is the implementation for the Member Service contract.
 * User: tijo
 */

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private FamilyService familyService;

    @Override
    public boolean addMemberSM(Member member) {
        memberDao.addMemberDM(member);
        return true;
    }

    @Override
    public List<Member> getAllMember() {
        return memberDao.getAllMembers();
    }

    @Override
    public Long getMemberCountForParish(List<Long> familyIdsList) {
        Long countOfMembers = 0l;
        if (!familyIdsList.isEmpty()) {
            countOfMembers = memberDao.getMemberCountForParish(familyIdsList);
        }
        return countOfMembers;
    }

    @Override
    public List<Member> getAllMembersForUserRole(User currentUser) {
        final List<Member> allMembers = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allMembers.addAll(getAllMember());
                break;
            case PARISH_ADMIN:
                List<Family> allFamiliesUnderParish = familyService.getAllFamilyForParishID(currentUser.getUsersOfParishes().getId());
                if (!allFamiliesUnderParish.isEmpty()) {
                    allFamiliesUnderParish.stream().forEach((family) -> {
                        allMembers.addAll(family.getMemberList());
                    });
                }
                break;
            case MASS_CENTER_ADMIN:
                List<Family> allFamiliesUnderMassCenter = familyService.getAllFamilyForMassCenterID(currentUser.getUsersOfMassCenters().getId());
                if (!allFamiliesUnderMassCenter.isEmpty()) {
                    allFamiliesUnderMassCenter.stream().forEach((family) -> {
                        allMembers.addAll(family.getMemberList());
                    });
                }
                break;
            case PRAYER_UNIT_ADMIN:
                List<Family> allFamiliesUnderPrayerUnit = familyService.getAllFamilyForPrayerUnitID(currentUser.getUsersOfPrayerUnits().getId());
                if (!allFamiliesUnderPrayerUnit.isEmpty()) {
                    allFamiliesUnderPrayerUnit.stream().forEach((family) -> {
                        allMembers.addAll(family.getMemberList());
                    });
                }
                break;
            case FAMILY_USER:
                allMembers.addAll(getAllMembersForFamilyID(currentUser.getUserOfFamily().getId()));
                break;
        }
        return allMembers;
    }

    @Override
    public Model createMemberFormBackObject(Model model) {
        Predicate<PersonSalutation> excludeRevAndRevDr = p -> !(p.name().equalsIgnoreCase(PersonSalutation.REV.toString())) && !(p.name().equalsIgnoreCase(PersonSalutation.REV_DR.toString()));

        model.addAttribute("sex", Arrays.stream(Gender.values()).collect(Collectors.toMap(Gender::name, Gender::getUIDisplayValue)));

        model.addAttribute("salutation", Arrays.stream(PersonSalutation.values()).filter(excludeRevAndRevDr).collect(Collectors.toMap(PersonSalutation::name, PersonSalutation::getUIDisplayValue)));

        model.addAttribute("lifeStatus", Arrays.stream(LifeStatus.values()).collect(Collectors.toMap(LifeStatus::name, LifeStatus::getUIDisplayValue)));

        model.addAttribute("personalStatus", Arrays.stream(PersonalStatus.values()).collect(Collectors.toMap(PersonalStatus::name, PersonalStatus::getUIDisplayValue)));

        model.addAttribute("relationshipInFamily", Arrays.stream(RelationShipInFamily.values()).collect(Collectors.toMap(RelationShipInFamily::name, RelationShipInFamily::getUIDisplayValue)));

        model.addAttribute("bloodGroup", Arrays.stream(BloodGroup.values()).collect(Collectors.toMap(BloodGroup::name, BloodGroup::getUIDisplayValue)));

        return model;
    }

    @Override
    public List<Member> getAllMembersForFamilyID(Long familyId) {
        return memberDao.getAllMembersForFamilyID(familyId);
    }

    @Override
    public Boolean verifyIsFamilyHeadMemberAddedForFamily(Long familyId) {
        return memberDao.verifyIsFamilyHeadMemberAddedForFamily(familyId);
    }
}
