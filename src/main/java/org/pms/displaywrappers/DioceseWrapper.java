package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.Diocese;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 19/03/17.
 */
public class DioceseWrapper implements GridRow {

    private Diocese dioceseBean;

    private String[] VALID_BEAN_PROPERTIES = {"dioceseNo", "dioceseName", "place", "patronName", "registeredDate", "landLineNo", "mobileNo", "faxNo",
            "localAddress.addressLineOne", "localAddress.addressLineTwo", "localAddress.addressLineThree", "localAddress.town", "localAddress.county", "localAddress.pin", "localAddress.country", "id"};

    public DioceseWrapper(Diocese dioceseBean) {
        this.dioceseBean = dioceseBean;
    }

    @Override
    public Long getId() {
        return dioceseBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                String assignedValue = "";
                if (BeanUtils.getProperty(this.dioceseBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    assignedValue = BeanUtils.getProperty(this.dioceseBean, VALID_BEAN_PROPERTIES[i]).toString();
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