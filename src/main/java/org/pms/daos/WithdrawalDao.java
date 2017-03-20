package org.pms.daos;

import org.pms.models.Withdrawal;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
public interface WithdrawalDao {

    Boolean addWithdrawal(Withdrawal withdrawal);

    List<Withdrawal> getAllWithdrawalsForParish(Long parishId);

    Boolean updateWithdrawal(Withdrawal withdrawal);
}
