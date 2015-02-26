package org.pms.daos;

import org.pms.models.Member;

import java.util.List;

/**
 * This interface is the contract for the Member Dao.
 * User: tijo
 */
public interface MemberDao {

    public boolean addMemberDM(Member member);

    public List<Member> getAllMembers();
}
