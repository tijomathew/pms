package org.pms.controllers;

import org.pms.enums.*;
import org.pms.displaywrappers.MemberWrapper;
import org.pms.dtos.MemberDto;
import org.pms.error.CustomErrorMessage;
import org.pms.error.CustomResponse;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the controller of the Member Controller module.
 * User: tijo
 */

@Controller
public class MemberController {

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
        model.addAttribute("sex", Gender.values());
        model.addAttribute("salutation", PersonSalutation.values());
        model.addAttribute("lifeStatus", LifeStatus.values());
        model.addAttribute("personalStatus", PersonalStatus.values());
        model.addAttribute("relationshipInFamily", RelationShipInFamily.values());
        model.addAttribute("bloodGroup", BloodGroup.values());

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
        CustomResponse res = null;
        List<CustomErrorMessage> customErrorMessages = new ArrayList<CustomErrorMessage>();
        if (!result.hasErrors()) {
            model.addAttribute("member", new Member());
            Family family = familyService.getFamilyForID(member.getFamilyId());
            family.addMemberForFamily(member);
            member.setFamilyMember(family);

            List<Long> allFamiliesIDUnderParish = familyService.getAllFamiliesIDForParishID(member.getFamilyMember().getParishId());
            Long memberCountForParish = memberService.getMemberCountForParish(allFamiliesIDUnderParish);

            String attachedStringToID = family.getFamilyID() + "-M";
            Long memberCountForFamily = 0l;//memberService.getMemberCountForParish(family.getId());
            if (memberCountForFamily < 10) {
                attachedStringToID += "0";
            }
            member.setMemberID(++memberCountForParish);

            memberService.addMemberSM(member);
            customErrorMessages.add(new CustomErrorMessage("success", "successfully added"));
            res = new CustomResponse("SUCCESS", customErrorMessages);

        } else {
            List<FieldError> allErrors = result.getFieldErrors();
            for (FieldError objectError : allErrors) {
                customErrorMessages.add(new CustomErrorMessage(objectError.getField(), objectError.getField() + "  " + objectError.getDefaultMessage()));
            }
            res = new CustomResponse("FAIL", customErrorMessages);
        }
        return res;
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

        List<MemberDto> memberDtoList = memberService.createMemberDto(allMembers);
        List<GridRow> memberGridRows = new ArrayList<GridRow>(memberDtoList.size());
        List<MemberDto> allMemberSubList = new ArrayList<>();

        if (totalMembersCount > 0) {
            allMemberSubList = JsonBuilder.generateSubList(page, rows, totalMembersCount, memberDtoList);
        }

        for (MemberDto memberDto : allMemberSubList) {
            memberGridRows.add(new MemberWrapper(memberDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalMembersCount, page, rows, memberGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }


}
