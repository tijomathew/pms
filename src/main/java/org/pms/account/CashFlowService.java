package org.pms.account;

import org.pms.domain.CashFlow;

import java.util.List;

/**
 * Created by tijo on 15/05/17.
 */
public interface CashFlowService {

    Boolean addCashFlow(CashFlow cashFlow);

    List<CashFlow> getAllCashFlowForParish(Long parishId);

    Boolean updateCashFlow(CashFlow cashFlow);
}
