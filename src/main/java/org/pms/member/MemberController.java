package org.pms.member;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.pms.common.*;
import org.pms.family.FamilyCustomPropertyEditor;
import org.pms.common.error.AbstractErrorAndGridHandler;
import org.pms.common.error.CustomResponse;
import org.pms.user.StatusCode;
import org.pms.family.FamilyService;
import org.pms.domain.Family;
import org.pms.domain.Member;
import org.pms.domain.User;
import org.pms.user.SystemRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    public String viewMemberPageDisplay(Model model) {
        Member modelObjectMember = new Member();
        modelObjectMember.setRegisteredDate(DateTimeFormat.forPattern("dd-MM-yyyy").print(new DateTime()));
        model.addAttribute("member", modelObjectMember);

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

            if (member.getMemberNo() == null && member.getId() == null) {

                Boolean isFamilyHeadExistsForFamily = memberService.verifyIsFamilyHeadMemberAddedForFamily(member.getFamilyMember().getId());

                if (!isFamilyHeadExistsForFamily) {
                    member.setFamilyHead(Boolean.TRUE);
                }

                member.getFamilyMember().addMemberForFamily(member);
                try {
                    if (member.getMemberAsPerson().getFile() != null) {
                        member.getMemberAsPerson().setImageBytes(member.getMemberAsPerson().getFile().getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                synchronized (this) {

                    Long memberCountForParish = memberService.getMemberCountInSystem();

                    member.setMemberNo(++memberCountForParish);

                    memberService.addMember(member);
                    customResponse = createSuccessMessage(StatusCode.SUCCESS, member.getMemberAsPerson().getFullName(), SUCCESS_MESSAGE_DISPLAY);
                }


            } else {
                if (member.getFamilyHead()) {
                    Member familyHead = memberService.getFamilyHeadMember(member.getFamilyMember().getId());
                    if (familyHead != null) {
                        if (familyHead.getMemberNo() != member.getMemberNo()) {
                            familyHead.setFamilyHead(Boolean.FALSE);
                            memberService.updateMember(familyHead);
                        }
                    }
                }
                if (member.getMemberAsPerson().getFile() != null && !member.getMemberAsPerson().getFile().isEmpty()) {
                    try {
                        member.getMemberAsPerson().setImageBytes(member.getMemberAsPerson().getFile().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    member.getMemberAsPerson().setImageBytes(Base64.decodeBase64(member.getMemberAsPerson().getImageBytesAsString()));
                }
                memberService.updateMember(member);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, member.getMemberAsPerson().getFullName(), "updated successfully");
            }
        } else {
            customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
        }
        return customResponse;
    }

    @RequestMapping(value = "/displaymembergrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMembers(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Member> allMembers = memberService.getAllMembersForUserRole(currentUser);
        Integer totalMembersCount = allMembers.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<GridRow> memberGridRows = new ArrayList<GridRow>(allMembers.size());
        List<Member> allMemberSubList = new ArrayList<>();

        if (totalMembersCount > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allMembers, formatter.by(sortIndexColumnName, Member.class));
            }
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
