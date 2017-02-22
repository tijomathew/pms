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

    Boolean addMember(Member member);

    Boolean updateMember(Member member);

    List<Member> getAllMember();

    Long getMemberCountForParish(List<Long> familyIdsList);

    List<Member> getAllMembersForUserRole(User currentUser);

    Model createMemberFormBackObject(Model model);

    List<Member> getAllMembersForFamilyID(Long familyId);

    Boolean verifyIsFamilyHeadMemberAddedForFamily(Long familyId);

    Member getFamilyHeadMember(Long familyId);
}
