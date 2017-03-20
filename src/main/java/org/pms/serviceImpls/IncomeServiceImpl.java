package org.pms.serviceImpls;

import org.pms.daos.IncomeDao;
import org.pms.models.Receipt;
import org.pms.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Service
@Transactional
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeDao incomeDao;

    @Override
    public Boolean addIncome(Receipt receipt) {
        return incomeDao.addIncome(receipt);
    }

    @Override
    public Boolean updateIncome(Receipt receipt) {
        return incomeDao.updateIncome(receipt);
    }

    @Override
    public List<Receipt> getAllIncomesForParish(Long parishId) {
        return incomeDao.getAllIncomesForParish(parishId);
    }

    @Override
    public Receipt getIncomeForID(Long id) {
        return incomeDao.getIncomeForId(id);
    }
}
