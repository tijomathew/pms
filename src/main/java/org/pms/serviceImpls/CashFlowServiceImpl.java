package org.pms.serviceImpls;

import org.pms.daos.CashFlowDao;
import org.pms.models.CashFlow;
import org.pms.services.CashFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tijo on 15/05/17.
 */
@Service
@Transactional
public class CashFlowServiceImpl implements CashFlowService {

    @Autowired
    private CashFlowDao cashFlowDao;

    @Override
    public Boolean addCashFlow(CashFlow cashFlow) {
        return cashFlowDao.addCashFlow(cashFlow);
    }

    @Override
    public List<CashFlow> getAllCashFlowForParish(Long parishId) {
        return cashFlowDao.getAllCashFlowForParish(parishId);
    }

    @Override
    public Boolean updateCashFlow(CashFlow cashFlow) {
        return cashFlowDao.updateCashFlow(cashFlow);
    }
}
