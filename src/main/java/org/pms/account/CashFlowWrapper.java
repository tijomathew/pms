package org.pms.account;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.common.GridRow;
import org.pms.domain.CashFlow;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 12/03/17.
 */
public class CashFlowWrapper implements GridRow {

    private CashFlow cashFlowBean;

    private String[] VALID_BEAN_PROPERTIES = {"id", "cashFlowDate", "registeredDate", "associatedParish.parsihName", "cashFlowType", "amount", "category.categoryName", "category.categoryGroup.categoryGroupName"};


    public CashFlowWrapper(CashFlow cashFlowBean) {
        this.cashFlowBean = cashFlowBean;
    }

    @Override
    public Long getId() {
        return cashFlowBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                String assignedValue = "";
                if (BeanUtils.getProperty(this.cashFlowBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    assignedValue = BeanUtils.getProperty(this.cashFlowBean, VALID_BEAN_PROPERTIES[i]).toString();
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
