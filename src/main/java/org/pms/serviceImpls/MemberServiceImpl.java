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
    public Long getMemberTotalCount() {
        return memberDao.getMemberTotalCount();
    }

    @Override
    public List<Member> getAllMembersForUserRole(User currentUser) {
        final List<Member> allMembers = new ArrayList<>();
        switch (currentUser.getSystemRole()) {
            case ADMIN:
                allMembers.addAll(getAllMember());
                break;
            case PARISH_ADMIN:
                List<Family> allFamiliesUnderParish = currentUser.getUsersOfParishes().getMappedFamilies();
                if (!allFamiliesUnderParish.isEmpty()) {
                    allFamiliesUnderParish.stream().forEach((family) -> {
                        allMembers.addAll(family.getMemberList());
                    });
                }
                break;
            case MASS_CENTER_ADMIN:
                List<Family> allFamiliesUnderMassCenter = currentUser.getUsersOfMassCenters().getMappedFamilies();
                if (!allFamiliesUnderMassCenter.isEmpty()) {
                    allFamiliesUnderMassCenter.stream().forEach((family) -> {
                        allMembers.addAll(family.getMemberList());
                    });
                }
                break;
            case PRAYER_UNIT_ADMIN:
                List<Family> allFamiliesUnderPrayerUnit = currentUser.getUsersOfPrayerUnits().getMappedFamilies();
                if (!allFamiliesUnderPrayerUnit.isEmpty()) {
                    allFamiliesUnderPrayerUnit.stream().forEach((family) -> {
                        allMembers.addAll(family.getMemberList());
                    });
                }
                break;
            case FAMILY_USER:
                allMembers.addAll(currentUser.getUserOfFamily().getMemberList());
                break;
        }
        return allMembers;
    }

    @Override
    public Model createMemberFormBackObject(Model model) {
        Predicate<PersonSalutation> excludeRevAndRevDr = p -> !(p.name().equalsIgnoreCase(PersonSalutation.REV.toString())) && !(p.name().equalsIgnoreCase(PersonSalutation.REV_DR.toString()));

        model.addAttribute("sex", Arrays.stream(Gender.values()).collect(Collectors.toMap(Gender::name, Gender::getUIDisplayValue)));

        Map<String, String> salutationMap = Arrays.stream(PersonSalutation.values()).filter(excludeRevAndRevDr).collect(Collectors.toMap(PersonSalutation::name, PersonSalutation::getUIDisplayValue));

        model.addAttribute("salutation", salutationMap);

        Map<String, String> lifeStatusMap = Arrays.stream(LifeStatus.values()).collect(Collectors.toMap(LifeStatus::name, LifeStatus::getUIDisplayValue));

        model.addAttribute("lifeStatus", lifeStatusMap);

        Map<String, String> personalStatusMap = Arrays.stream(PersonalStatus.values()).collect(Collectors.toMap(PersonalStatus::name, PersonalStatus::getUIDisplayValue));

        model.addAttribute("personalStatus", personalStatusMap);

        Map<String, String> relationshipInFamilyMap = Arrays.stream(RelationShipInFamily.values()).collect(Collectors.toMap(RelationShipInFamily::name, RelationShipInFamily::getUIDisplayValue));

        model.addAttribute("relationshipInFamily", relationshipInFamilyMap);

        Map<String, String> bloodGroupMap = Arrays.stream(BloodGroup.values()).collect(Collectors.toMap(BloodGroup::name, BloodGroup::getUIDisplayValue));
               model.addAttribute("bloodGroup", bloodGroupMap);

        return model;
    }
}
