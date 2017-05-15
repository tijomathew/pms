package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.CashFlowDao;
import org.pms.models.CashFlow;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tijo on 15/05/17.
 */
@Repository
public class CashFlowDaoImpl extends GenericDaoImpl<CashFlow> implements CashFlowDao {

    public CashFlowDaoImpl() {
        setType(CashFlow.class);
    }

    @Override
    public Boolean addCashFlow(CashFlow cashFlow) {
        createAndSave(cashFlow);
        return true;
    }

    @Override
    public List<CashFlow> getAllCashFlowForParish(Long parishId) {
        return getDb(false).createCriteria(CashFlow.class, "cashFlow").createAlias("cashFlow.associatedParish", "cashFlowParish").add(Restrictions.eq("cashFlowParish.id", parishId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Boolean updateCashFlow(CashFlow cashFlow) {
        updateInstance(cashFlow);
        return true;
    }
}
