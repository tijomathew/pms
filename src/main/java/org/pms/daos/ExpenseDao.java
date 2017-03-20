package org.pms.daos;

import org.pms.models.Expense;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
public interface ExpenseDao {

    Boolean addExpense(Expense expense);

    List<Expense> getAllExpensesForParish(Long parishId);

    Boolean updateExpense(Expense expense);
}
