package org.pms.services;

import org.pms.dtos.MemberDto;
import org.pms.models.Member;

import java.util.List;

/**
 * This interface is the contract for the Member Service.
 * User: tijo
 */
public interface MemberService {

    boolean addMemberSM(Member member);

    List<Member> getAllMember();

    Long getMemberCountForParish(List<Long> familyIdsList);

    Long getMemberTotalCount();
}
