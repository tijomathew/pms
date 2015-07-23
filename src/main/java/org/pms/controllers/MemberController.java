package org.pms.controllers;

import org.pms.enums.*;
import org.pms.displaywrappers.MemberWrapper;
import org.pms.dtos.MemberDto;
import org.pms.error.AbstractErrorHandler;
import org.pms.error.CustomResponse;
import org.pms.enums.StatusCode;
import org.pms.helpers.*;
import org.pms.models.Family;
import org.pms.models.Member;
import org.pms.models.SelectBox;
import org.pms.models.User;
import org.pms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class is the controller of the Member Controller module.
 * User: tijo
 */

@Controller
public class MemberController extends AbstractErrorHandler {

    @Autowired
    private MemberService memberService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private ParishService parishService;

    @Autowired
    private MassCenterService massCenterService;

    @Autowired
    private PrayerUnitService prayerUnitService;

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private FactorySelectBox factorySelectBox;

    @RequestMapping(value = "/viewmember.action", method = RequestMethod.GET)
    public String memberPageDisplay(Model model) {
        model.addAttribute("member", new Member());
        if (requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class).getSystemRole() == SystemRole.FAMILY_USER) {
            factorySelectBox.createSelectBox(model);
        }

        Predicate<PersonSalutation> excludeRev = p -> !(p.name().equalsIgnoreCase(PersonSalutation.REV.toString()));
        Predicate<PersonSalutation> excludeRevDr = p -> !(p.name().equalsIgnoreCase(PersonSalutation.REV_DR.toString()));

        Predicate<PersonSalutation> excludePriestSalutation = excludeRev.and(excludeRevDr);

        model.addAttribute("sex", Arrays.stream(Gender.values()).collect(Collectors.toMap(Gender::name, Gender::getUIDisplayValue)));
        model.addAttribute("salutation", Arrays.stream(PersonSalutation.values()).filter(excludePriestSalutation).collect(Collectors.toMap(PersonSalutation::name, PersonSalutation::getUIDisplayValue)));
        model.addAttribute("lifeStatus", Arrays.stream(LifeStatus.values()).collect(Collectors.toMap(LifeStatus::name, LifeStatus::getUIDisplayValue)));
        model.addAttribute("personalStatus", Arrays.stream(PersonalStatus.values()).collect(Collectors.toMap(PersonalStatus::name, PersonalStatus::getUIDisplayValue)));
        model.addAttribute("relationshipInFamily", Arrays.stream(RelationShipInFamily.values()).collect(Collectors.toMap(RelationShipInFamily::name, RelationShipInFamily::getUIDisplayValue)));
        model.addAttribute("bloodGroup", Arrays.stream(BloodGroup.values()).collect(Collectors.toMap(BloodGroup::name, BloodGroup::getUIDisplayValue)));

        return PageName.MEMBER.toString();
    }

    @RequestMapping(value = "/createfamilyselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateFamilySelectBox() {
        List<Family> familyList = new ArrayList<>();
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRole.PMS_CURRENT_USER.toString());
        if (currentUser.getSystemRole() == SystemRole.PARISH_ADMIN) {
            familyList = familyService.getAllFamilyForParishID(currentUser.getParishId());
        } else if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
            familyList = familyService.getAllFamilyForMassCenterID(currentUser.getMassCenterId());
        } else if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
            familyList = familyService.getAllFamilyForPrayerUnitID(currentUser.getPrayerUnitId());
        } else if (currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
            familyList = familyService.getFamilyForFamilyID(currentUser.getFamilyId());
        } else {
            familyList = familyService.getAllFamilySM();
        }
        List<SelectBox<String>> selectBoxList = new ArrayList<SelectBox<String>>();
        for (Family family : familyList) {
            SelectBox<String> selectBox = new SelectBox<String>(String.valueOf(family.getId()), family.getFamilyName());
            selectBoxList.add(selectBox);
        }
        return new SelectBox<String>().getJsonForSelectBoxCreation(selectBoxList);
    }

    @RequestMapping(value = "/addmember.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addMember(Model model, @ModelAttribute("member") @Valid Member member, BindingResult result) {

        if (!result.hasErrors()) {
            model.addAttribute("member", new Member());
            Family family = familyService.getFamilyForID(member.getFamilyId());
            family.addMemberForFamily(member);
            member.setFamilyMember(family);

            List<Long> allFamiliesIDUnderParish = familyService.getAllFamiliesIDForParishID(member.getFamilyMember().getParishId());
            Long memberCountForParish = memberService.getMemberCountForParish(allFamiliesIDUnderParish);

            member.setMemberID(++memberCountForParish);

            memberService.addMemberSM(member);
            customResponse = createSuccessMessage(StatusCode.SUCCESS, member.getMemberAsPerson().getFirstName(), "added in to the system");

        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }
        return customResponse;
    }

    @RequestMapping(value = "/displaymembergrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMembers(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Member> allMembers = new ArrayList<>();
        Integer totalMembersCount = 0;
        if (currentUser.getSystemRole() == SystemRole.ADMIN) {
            allMembers = memberService.getAllMember();
            totalMembersCount = memberService.getMemberTotalCount().intValue();
        } else if (currentUser.getSystemRole() == SystemRole.PARISH_ADMIN) {
            List<Family> allFamiliesUnderParish = parishService.getParishForIDSM(currentUser.getParishId()).getMappedFamilies();
            for (Family family : allFamiliesUnderParish) {
                allMembers.addAll(family.getMemberList());
            }
            totalMembersCount = allMembers.size();
        } else if (currentUser.getSystemRole() == SystemRole.MASS_CENTER_ADMIN) {
            List<Family> allFamiliesUnderMassCenter = massCenterService.getMassCenterForIDSM(currentUser.getMassCenterId()).getMappedFamilies();
            for (Family family : allFamiliesUnderMassCenter) {
                allMembers.addAll(family.getMemberList());
            }
            totalMembersCount = allMembers.size();
        } else if (currentUser.getSystemRole() == SystemRole.PRAYER_UNIT_ADMIN) {
            List<Family> allFamiliesUnderPrayerUnit = prayerUnitService.getPrayerUnitForIDSM(currentUser.getPrayerUnitId()).getMappedFamilies();
            for (Family family : allFamiliesUnderPrayerUnit) {
                allMembers.addAll(family.getMemberList());
            }
            totalMembersCount = allMembers.size();
        } else if (currentUser.getSystemRole() == SystemRole.FAMILY_USER) {
            allMembers.addAll(familyService.getFamilyForID(currentUser.getFamilyId()).getMemberList());
            totalMembersCount = allMembers.size();
        }

        List<GridRow> memberGridRows = new ArrayList<GridRow>(allMembers.size());
        List<Member> allMemberSubList = new ArrayList<>();

        if (totalMembersCount > 0) {
            allMemberSubList = JsonBuilder.generateSubList(page, rows, totalMembersCount, allMembers);
        }

        for (Member member : allMemberSubList) {
            memberGridRows.add(new MemberWrapper(member));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalMembersCount, page, rows, memberGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }


}
