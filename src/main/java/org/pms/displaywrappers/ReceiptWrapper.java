package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.Receipt;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 12/03/17.
 */
public class ReceiptWrapper implements GridRow {

    private Receipt receiptBean;

    private String[] VALID_BEAN_PROPERTIES = {"id", "receiptDate", "registeredDate", "associatedParish.parsihName", "receiptType", "receiptAmount", "category.categoryName", "category.categoryGroup.categoryGroupName"};


    public ReceiptWrapper(Receipt receiptBean) {
        this.receiptBean = receiptBean;
    }

    @Override
    public Long getId() {
        return receiptBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                String assignedValue = "";
                if (BeanUtils.getProperty(this.receiptBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    assignedValue = BeanUtils.getProperty(this.receiptBean, VALID_BEAN_PROPERTIES[i]).toString();
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
