package org.pms.serviceImpls;

import org.pms.daos.MemberDao;
import org.pms.dtos.MemberDto;
import org.pms.models.Member;
import org.pms.models.Person;
import org.pms.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public List<MemberDto> createMemberDto(List<Member> memberList) throws IllegalArgumentException {
        List<MemberDto> memberDtoList = new ArrayList<MemberDto>(memberList.size());
        if (!memberList.isEmpty()) {
            Integer uniqueId = 0;
            for (Member member : memberList) {
                Person personIdentity = member.getMemberAsPerson();
                StringBuilder name = new StringBuilder(personIdentity.getSalutation() + " " + personIdentity.getFirstName() + " " + personIdentity.getLastName());
                MemberDto memberDto = new MemberDto(uniqueId, member.getId(), name.toString(), personIdentity.getDateOfBirth());
                memberDtoList.add(memberDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("Member List cannot be an empty List!!!...");
        }
        return memberDtoList;
    }

    @Override
    public Long getMemberCountForFamily(Long familyId) {
        return memberDao.getMemberCountForFamily(familyId);
    }
}
