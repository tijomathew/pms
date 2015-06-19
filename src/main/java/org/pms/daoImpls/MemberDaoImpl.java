package org.pms.daoImpls;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.MemberDao;
import org.pms.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public Long getMemberCountForFamily(Long familyId) {
        return (Long) getDb(false).createCriteria(Member.class, "member").setProjection(Projections.rowCount()).add(Restrictions.eq("member.familyMember.id", familyId)).uniqueResult();
    }

    @Override
    public Long getMemberTotalCount() {
        return (Long) getDb(false).createCriteria(Member.class, "member").setProjection(Projections.rowCount()).uniqueResult();
    }
}
