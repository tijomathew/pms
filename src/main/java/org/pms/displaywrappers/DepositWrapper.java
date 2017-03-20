package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.Deposit;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 19/03/17.
 */
public class DepositWrapper implements GridRow {

    private Deposit depositBean;

    private String[] VALID_BEAN_PROPERTIES = {"id", "depositDate", "registeredDate", "associatedParish.parsihName", "depositType", "depositAmount"/*, "category.categoryName", "category.categoryGroup.categoryGroupName"*/};


    public DepositWrapper(Deposit depositBean) {
        this.depositBean = depositBean;
    }

    @Override
    public Long getId() {
        return depositBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                String assignedValue = "";
                if (BeanUtils.getProperty(this.depositBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    assignedValue = BeanUtils.getProperty(this.depositBean, VALID_BEAN_PROPERTIES[i]).toString();
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
