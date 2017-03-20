package org.pms.serviceImpls;

import org.pms.daos.ExpenseDao;
import org.pms.models.Expense;
import org.pms.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Service
@Transactional
public class ExpensiveServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public Boolean addExpense(Expense expense) {
        return expenseDao.addExpense(expense);
    }

    @Override
    public List<Expense> getAllExpensesForParish(Long parishId) {
        return expenseDao.getAllExpensesForParish(parishId);
    }

    @Override
    public Boolean updateExpense(Expense expense) {
        return expenseDao.updateExpense(expense);
    }
}
