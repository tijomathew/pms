package org.pms.services;

import org.pms.models.CashFlow;

import java.util.List;

/**
 * Created by tijo on 15/05/17.
 */
public interface CashFlowService {

    Boolean addCashFlow(CashFlow cashFlow);

    List<CashFlow> getAllCashFlowForParish(Long parishId);

    Boolean updateCashFlow(CashFlow cashFlow);
}
