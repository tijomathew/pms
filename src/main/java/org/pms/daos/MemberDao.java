package org.pms.daos;

import org.pms.models.Member;

import java.util.List;

/**
 * This interface is the contract for the Member Dao.
 * User: tijo
 */
public interface MemberDao {

    Boolean addMember(Member member);

    Boolean updateMember(Member member);

    List<Member> getAllMembers();

    Long getMemberCountForParish(List<Long> familiesList);

    List<Member> getAllMembersForFamilyID(Long familyId);

    Boolean verifyIsFamilyHeadMemberAddedForFamily(Long familyId);

    Member getFamilyHeadMember(Long familyId);

    Member getMemberForMemberNo(Long memberNo);

}
