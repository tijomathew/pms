package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.WithdrawalDao;
import org.pms.models.Withdrawal;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Repository
public class WithdrawalDaoImpl extends GenericDaoImpl<Withdrawal> implements WithdrawalDao {

    public WithdrawalDaoImpl() {
        setType(Withdrawal.class);
    }

    @Override
    public Boolean addWithdrawal(Withdrawal withdrawal) {
        createAndSave(withdrawal);
        return true;
    }

    @Override
    public List<Withdrawal> getAllWithdrawalsForParish(Long parishId) {
        return getDb(false).createCriteria(Withdrawal.class, "withdrawal").createAlias("withdrawal.associatedParish", "withdrawalParish").add(Restrictions.eq("withdrawalParish.id", parishId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Boolean updateWithdrawal(Withdrawal withdrawal) {
        updateInstance(withdrawal);
        return true;
    }
}
