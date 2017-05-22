package org.pms.member;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.common.dao.GenericDaoImpl;
import org.pms.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the implementation for the Member Dao contract.
 * User: tijo
 */

@Repository
public class MemberDaoImpl extends GenericDaoImpl<Member> implements MemberDao {

    public MemberDaoImpl() {
        setType(Member.class);
    }

    @Override
    public Boolean addMember(Member member) {
        createAndSave(member);
        return true;
    }

    @Override
    public Boolean updateMember(Member member) {
        updateInstance(member);
        return true;
    }

    @Override
    public List<Member> getAllMembers() {
        return readAllInstances();
    }

    @Override
    public Long getMemberCountInSystem() {
        return (Long) getDb(false).createCriteria(Member.class, "member").setProjection(Projections.max("member.memberNo")).uniqueResult();
    }

    @Override
    public List<Member> getAllMembersForFamilyID(Long familyId) {
        return getDb(false).createCriteria(Member.class, "member").add(Restrictions.eq("member.familyMember.id", familyId)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Boolean verifyIsFamilyHeadMemberAddedForFamily(Long familyId) {
        Long familyHeadMemberCount = (Long) getDb(false).createCriteria(Member.class, "member").setProjection(Projections.rowCount()).add(Restrictions.eq("member.familyMember.id", familyId)).add(Restrictions.eq("member.familyHead", Boolean.TRUE)).uniqueResult();
        return familyHeadMemberCount > 0;
    }

    @Override
    public Member getFamilyHeadMember(Long familyId) {
        return (Member) getDb(false).createCriteria(Member.class, "member").add(Restrictions.eq("member.familyMember.id", familyId)).add(Restrictions.eq("member.familyHead", Boolean.TRUE)).uniqueResult();
    }

}