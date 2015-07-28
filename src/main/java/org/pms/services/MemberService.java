package org.pms.services;

import org.pms.models.Member;
import org.pms.models.User;
import org.springframework.ui.Model;

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

    List<Member> getAllMembersForUserRole(User currentUser);

    Model createMemberFormBackObject(Model model);
}
