package org.pms.daoImpls;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.MemberDao;
import org.pms.models.Member;
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
    public boolean addMemberDM(Member member) {
        createAndSave(member);
        return false;
    }

    @Override
    public List<Member> getAllMembers() {
        return readAllInstances();
    }

    @Override
    public Long getMemberCountForParish(List<Long> familyIdsList) {
        return (Long) getDb(false).createCriteria(Member.class, "member").setProjection(Projections.rowCount()).add(Restrictions.in("member.familyMember.id", familyIdsList)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).uniqueResult();
    }

    @Override
    public List<Member> getAllMembersForFamilyID(Long familyId) {
        return getDb(false).createCriteria(Member.class, "member").add(Restrictions.eq("member.familyMember.id", familyId)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }
}
