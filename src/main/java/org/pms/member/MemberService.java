package org.pms.member;

import org.pms.domain.Member;
import org.pms.domain.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * This interface is the contract for the Member Service.
 * User: tijo
 */
public interface MemberService {

    Boolean addMember(Member member);

    Boolean updateMember(Member member);

    List<Member> getAllMember();

    Long getMemberCountInSystem();

    List<Member> getAllMembersForUserRole(User currentUser);

    Model createMemberFormBackObject(Model model);

    List<Member> getAllMembersForFamilyID(Long familyId);

    Boolean verifyIsFamilyHeadMemberAddedForFamily(Long familyId);

    Member getFamilyHeadMember(Long familyId);
}
