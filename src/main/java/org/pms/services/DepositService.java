package org.pms.services;

import org.pms.models.Deposit;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
public interface DepositService {

    Boolean addDeposit(Deposit deposit);

    List<Deposit> getAllDepositsForParish(Long parishId);

    Boolean updateDeposit(Deposit deposit);
}
