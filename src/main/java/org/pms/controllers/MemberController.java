package org.pms.controllers;

import org.pms.custompropertyeditors.FamilyCustomPropertyEditor;
import org.pms.enums.*;
import org.pms.displaywrappers.MemberWrapper;
import org.pms.error.AbstractErrorAndGridHandler;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the controller of the Member Controller module.
 * User: tijo
 */

@Controller
public class MemberController extends AbstractErrorAndGridHandler {

    @Autowired
    private MemberService memberService;

    @Autowired
    private FamilyService familyService;

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

        memberService.createMemberFormBackObject(model);

        return PageName.MEMBER.toString();
    }

    @RequestMapping(value = "/addmember.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addMember(@ModelAttribute("member") @Valid Member member, BindingResult result) {

        if (!result.hasErrors()) {

            Family family = familyService.getFamilyForID(member.getFamilyId());
            family.addMemberForFamily(member);
            member.setFamilyMember(family);

            List<Long> allFamiliesIDUnderParish = familyService.getAllFamiliesIDForParishID(member.getFamilyMember().getFamilyParish().getParishNo());
            Long memberCountForParish = memberService.getMemberCountForParish(allFamiliesIDUnderParish);

            member.setMemberNo(++memberCountForParish);

            memberService.addMemberSM(member);
            customResponse = createSuccessMessage(StatusCode.SUCCESS, member.getMemberAsPerson().getFirstName(), SUCCESS_MESSAGE_DISPLAY);

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
        List<Member> allMembers = memberService.getAllMembersForUserRole(currentUser);
        Integer totalMembersCount = allMembers.size();

        List<GridRow> memberGridRows = new ArrayList<GridRow>(allMembers.size());
        List<Member> allMemberSubList = new ArrayList<>();

        if (totalMembersCount > 0) {
            allMemberSubList = JsonBuilder.generateSubList(page, rows, totalMembersCount, allMembers);
        }

        if (!allMemberSubList.isEmpty()) {
            memberGridRows = allMemberSubList.stream().map(member -> new MemberWrapper(member)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalMembersCount, page, rows, memberGridRows));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
               binder.registerCustomEditor(Family.class, new FamilyCustomPropertyEditor(familyService));
    }

}
