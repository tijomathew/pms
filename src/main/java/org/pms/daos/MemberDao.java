package org.pms.daos;

import org.pms.models.Member;

import java.util.List;

/**
 * This interface is the contract for the Member Dao.
 * User: tijo
 */
public interface MemberDao {

    boolean addMemberDM(Member member);

    List<Member> getAllMembers();

    Long getMemberCountForParish(List<Long> familiesList);

    List<Member> getAllMembersForFamilyID(Long familyId);

}
