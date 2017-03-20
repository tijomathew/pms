package org.pms.serviceImpls;

import org.pms.daos.WithdrawalDao;
import org.pms.models.Withdrawal;
import org.pms.services.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Service
@Transactional
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    private WithdrawalDao withdrawalDao;

    @Override
    public Boolean addWithdrawal(Withdrawal withdrawal) {
        return withdrawalDao.addWithdrawal(withdrawal);
    }

    @Override
    public List<Withdrawal> getAllWithdrawalsForParish(Long parishId) {
        return withdrawalDao.getAllWithdrawalsForParish(parishId);
    }

    @Override
    public Boolean updateWithdrawal(Withdrawal withdrawal) {
        return withdrawalDao.updateWithdrawal(withdrawal);
    }
}
