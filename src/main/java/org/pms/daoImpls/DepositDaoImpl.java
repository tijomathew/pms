package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.DepositDao;
import org.pms.models.Deposit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Repository
public class DepositDaoImpl extends GenericDaoImpl<Deposit> implements DepositDao {

    public DepositDaoImpl() {
        setType(Deposit.class);
    }

    @Override
    public Boolean addDeposit(Deposit deposit) {
        createAndSave(deposit);
        return true;
    }

    @Override
    public List<Deposit> getAllDepositForParish(Long parishId) {
        return getDb(false).createCriteria(Deposit.class, "deposit").createAlias("deposit.associatedParish", "depositParish").add(Restrictions.eq("depositParish.id", parishId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Boolean updateDeposit(Deposit deposit) {
        updateInstance(deposit);
        return true;
    }
}
