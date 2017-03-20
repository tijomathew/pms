package org.pms.services;

import org.pms.models.Receipt;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
public interface IncomeService {

    Boolean addIncome(Receipt receipt);

    List<Receipt> getAllIncomesForParish(Long parishId);

    Boolean updateIncome(Receipt receipt);

    Receipt getIncomeForID(Long id);
}
