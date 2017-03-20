package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.Withdrawal;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 18/03/17.
 */
public class WithdrawalWrapper implements GridRow {

    private Withdrawal withdrawalBean;

    private String[] VALID_BEAN_PROPERTIES = {"id", "withdrawalDate", "registeredDate", "associatedParish.parsihName", "withdrawalType", "withdrawalAmount"/*, "category.categoryName", "category.categoryGroup.categoryGroupName"*/};


    public WithdrawalWrapper(Withdrawal withdrawalBean) {
        this.withdrawalBean = withdrawalBean;
    }

    @Override
    public Long getId() {
        return withdrawalBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                String assignedValue = "";
                if (BeanUtils.getProperty(this.withdrawalBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    assignedValue = BeanUtils.getProperty(this.withdrawalBean, VALID_BEAN_PROPERTIES[i]).toString();
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
