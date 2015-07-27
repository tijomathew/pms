package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class UserWrapper implements GridRow {

    private User userBean;

    private String[] VALID_BEAN_PROPERTIES = {"email", "systemRole", "isActive", "parish", "massCenter", "prayerUnit", "family", "alreadyLoggedIn", "isValidated", "createdBy", "parishId", "massCenterId", "prayerUnitId", "familyId"};

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
                String assignedValue = "N/A";
                if (BeanUtils.getProperty(this.userBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    assignedValue = BeanUtils.getProperty(this.userBean, VALID_BEAN_PROPERTIES[i]).toString();
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
