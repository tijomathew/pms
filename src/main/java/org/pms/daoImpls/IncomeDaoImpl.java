package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.IncomeDao;
import org.pms.models.Receipt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Repository
public class IncomeDaoImpl extends GenericDaoImpl<Receipt> implements IncomeDao {

    public IncomeDaoImpl() {
        setType(Receipt.class);
    }

    @Override
    public Boolean addIncome(Receipt receipt) {
        createAndSave(receipt);
        return true;
    }

    @Override
    public List<Receipt> getAllIncomesForParish(Long parishId) {
        return getDb(false).createCriteria(Receipt.class, "receipt").createAlias("receipt.associatedParish", "incomeParish").add(Restrictions.eq("incomeParish.id", parishId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Boolean updateIncome(Receipt receipt) {
        updateInstance(receipt);
        return true;
    }

    @Override
    public Receipt getIncomeForId(Long incomeId) {
        return (Receipt) getDb(false).createCriteria(Receipt.class, "receipt").add(Restrictions.eq("receipt.id", incomeId)).uniqueResult();
    }
}
