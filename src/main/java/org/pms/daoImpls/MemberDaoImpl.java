package org.pms.daoImpls;

import org.hibernate.SessionFactory;
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
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addMemberDM(Member member) {
        sessionFactory.getCurrentSession().save(member);
        return false;
    }

    @Override
    public List<Member> getAllMembers() {
        return sessionFactory.getCurrentSession().createCriteria(Member.class).list();
    }
}
