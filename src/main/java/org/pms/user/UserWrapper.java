package org.pms.user;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.common.GridRow;
import org.pms.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class UserWrapper implements GridRow {

    private User userBean;

    private String[] VALID_BEAN_PROPERTIES = {"email", "systemRoleAsDisplayValue", "isActive", "parish", "prayerUnit", "family", "sendMailFlag", "alreadyLoggedIn", "isValidated", "createdBy", "parishId", "prayerUnitId", "familyId","id"};

    public UserWrapper(User userBean) {
        this.userBean = userBean;
    }

    @Override
    public Long getId() {
        return userBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                String assignedValue = "";
                String splited[] = new String[2];
                boolean propertyWithDotOperator = false;
                if (VALID_BEAN_PROPERTIES[i].contains(".")) {
                    splited = VALID_BEAN_PROPERTIES[i].split("\\.");
                    propertyWithDotOperator = true;
                }
                if (!propertyWithDotOperator) {
                    if (BeanUtils.getProperty(this.userBean, VALID_BEAN_PROPERTIES[i]) != null) {
                        assignedValue = BeanUtils.getProperty(this.userBean, VALID_BEAN_PROPERTIES[i]).toString();
                        if (assignedValue.isEmpty()) {
                            assignedValue = "";
                        }
                    }
                } else {
                    if (BeanUtils.getProperty(this.userBean, splited[0]) != null) {
                        assignedValue = BeanUtils.getProperty(this.userBean, VALID_BEAN_PROPERTIES[i]).toString();
                        if (assignedValue.isEmpty()) {
                            assignedValue = "";
                        }
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
