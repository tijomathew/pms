package org.pms.serviceImpls;

import org.pms.daos.DepositDao;
import org.pms.models.Deposit;
import org.pms.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Service
@Transactional
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositDao depositDao;

    @Override
    public Boolean addDeposit(Deposit deposit) {
        return depositDao.addDeposit(deposit);
    }

    @Override
    public List<Deposit> getAllDepositsForParish(Long parishId) {
        return depositDao.getAllDepositForParish(parishId);
    }

    @Override
    public Boolean updateDeposit(Deposit deposit) {
        return depositDao.updateDeposit(deposit);
    }
}
