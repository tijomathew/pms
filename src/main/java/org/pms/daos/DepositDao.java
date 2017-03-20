package org.pms.daos;

import org.pms.models.Deposit;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
public interface DepositDao {

    Boolean addDeposit(Deposit deposit);

    List<Deposit> getAllDepositForParish(Long parishId);

    Boolean updateDeposit(Deposit deposit);
}
