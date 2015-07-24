package org.pms.serviceImpls;

import org.pms.daos.MemberDao;
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
}
