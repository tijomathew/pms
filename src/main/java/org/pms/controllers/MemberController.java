package org.pms.controllers;

import org.pms.displaywrappers.MemberWrapper;
import org.pms.dtos.MemberDto;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.models.Family;
import org.pms.models.Member;
import org.pms.models.SelectBox;
import org.pms.services.FamilyService;
import org.pms.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    private ServletContext servletContext;

    @RequestMapping(value = "/viewMember.action", method = RequestMethod.GET)
    public String memberPageDisplay(Model model) {
        model.addAttribute("member", new Member());
        return "member";
    }

    @RequestMapping(value = "/createFamilySelectBox.action", method = RequestMethod.GET)
    public
    @ResponseBody
    String generateFamilySelectBox() {
        List<Family> familyList = familyService.getAllFamilySM();
        List<SelectBox<String>> selectBoxList = new ArrayList<SelectBox<String>>();
        for (Family family : familyList) {
            SelectBox<String> selectBox = new SelectBox<String>(String.valueOf(family.getId()), family.getFamilyName());
            selectBoxList.add(selectBox);
        }
        return new SelectBox<String>().getJsonForSelectBoxCreation(selectBoxList);
    }

    @RequestMapping(value = "/addMember.action", method = RequestMethod.POST)
    public String addMember(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", new Member());
        Family family = familyService.getFamilyForID(member.getFamilyId());
        family.addMemberForFamily(member);
        member.setFamilyMember(family);
        memberService.addMemberSM(member);
        return "member";
    }

    @RequestMapping(value = "/displayMemberGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForMembers() {
        List<Member> allMembers = memberService.getAllMember();
        List<MemberDto> memberDtoList = memberService.createMemberDto(allMembers);
        List<GridRow> memberGridRows = new ArrayList<GridRow>(memberDtoList.size());
        for (MemberDto memberDto : memberDtoList) {
            memberGridRows.add(new MemberWrapper(memberDto));
        }

        HttpServletRequest curRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(10, 2, 20, memberGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }


}
