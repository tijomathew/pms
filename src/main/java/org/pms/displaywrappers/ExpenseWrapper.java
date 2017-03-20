package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.Expense;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 18/03/17.
 */
public class ExpenseWrapper implements GridRow {

    private Expense expenseBean;

    private String[] VALID_BEAN_PROPERTIES = {"id", "expenseDate", "registeredDate", "associatedParish.parsihName", "expenseType", "expenseAmount"/*, "category.categoryName", "category.categoryGroup.categoryGroupName"*/};


    public ExpenseWrapper(Expense expenseBean) {
        this.expenseBean = expenseBean;
    }

    @Override
    public Long getId() {
        return expenseBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                String assignedValue = "";
                if (BeanUtils.getProperty(this.expenseBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    assignedValue = BeanUtils.getProperty(this.expenseBean, VALID_BEAN_PROPERTIES[i]).toString();
                    if (assignedValue.isEmpty()) {
                        assignedValue = "";
                    }
                }
                convertedResult.add(assignedValue);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return convertedResult;
    }
}
