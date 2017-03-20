package org.pms.daoImpls;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.ExpenseDao;
import org.pms.models.Expense;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tijo on 11/03/17.
 */
@Repository
public class ExpenseDaoImpl extends GenericDaoImpl<Expense> implements ExpenseDao {

    public ExpenseDaoImpl() {
        setType(Expense.class);
    }

    @Override
    public Boolean addExpense(Expense expense) {
        createAndSave(expense);
        return true;
    }

    @Override
    public List<Expense> getAllExpensesForParish(Long parishId) {
        return getDb(false).createCriteria(Expense.class, "expense").createAlias("expense.associatedParish", "expenseParish").add(Restrictions.eq("expenseParish.id", parishId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Boolean updateExpense(Expense expense) {
        updateInstance(expense);
        return true;
    }
}
