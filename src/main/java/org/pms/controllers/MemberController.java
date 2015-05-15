package org.pms.controllers;

import org.pms.constants.SystemRoles;
import org.pms.displaywrappers.MemberWrapper;
import org.pms.dtos.MemberDto;
import org.pms.helpers.*;
import org.pms.models.Family;
import org.pms.models.Member;
import org.pms.models.SelectBox;
import org.pms.models.User;
import org.pms.services.FamilyService;
import org.pms.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewmember.action", method = RequestMethod.GET)
    public String memberPageDisplay(Model model) {
        model.addAttribute("member", new Member());
        return "member";
    }

    @RequestMapping(value = "/createfamilyselectbox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateFamilySelectBox() {
        List<Family> familyList = new ArrayList<>();
        User currentUser = (User) requestResponseHolder.getCurrentSession().getAttribute(SystemRoles.PMS_CURRENT_USER);
        if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PARISH_ADMIN)) {
            familyList = familyService.getAllFamilyForParishID(currentUser.getParishId());
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.MASS_CENTER_ADMIN)) {
            familyList = familyService.getAllFamilyForMassCenterID(currentUser.getMassCenterId());
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.PRAYER_UNIT_ADMIN)) {
            familyList = familyService.getAllFamilyForPrayerUnitID(currentUser.getPrayerUnitId());
        } else if (currentUser.getSystemRole().equalsIgnoreCase(SystemRoles.FAMILY_ADMIN)) {
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
    public String addMember(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", new Member());
        Family family = familyService.getFamilyForID(member.getFamilyId());
        family.addMemberForFamily(member);
        member.setFamilyMember(family);

        String attachedStringToID = family.getFamilyID() + "-M";
        Long memberCountForFamily = memberService.getMemberCountForFamily(family.getId());
        if (memberCountForFamily < 10) {
            attachedStringToID += "0";
        }
        member.setMemberID(attachedStringToID + (++memberCountForFamily));

        memberService.addMemberSM(member);
        return "member";
    }

    @RequestMapping(value = "/displaymembergrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMembers(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<Member> allMembers = memberService.getAllMember();
        List<MemberDto> memberDtoList = memberService.createMemberDto(allMembers);
        Integer totalMembersCount = memberService.getMemberTotalCount().intValue();
        List<GridRow> memberGridRows = new ArrayList<GridRow>(memberDtoList.size());
        List<MemberDto> allMemberSubList = new ArrayList<>();

        if(totalMembersCount > 0){
            allMemberSubList = JsonBuilder.generateSubList(page,rows,totalMembersCount,memberDtoList);
        }

        for (MemberDto memberDto : allMemberSubList) {
            memberGridRows.add(new MemberWrapper(memberDto));
        }   

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalMembersCount, page, rows, memberGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }

}
