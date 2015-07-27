package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.Parish;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class ParishWrapper implements GridRow {

    private Parish parishBean;

    private static final String[] VALID_BEAN_PROPERTIES = {"parishNo", "name", "place", "patron", "churchName", "riteName", "dioceseName", "foraneName", "code", "webSite", "facebookPage",
            "drivingRoute", "map", "registeredDate", "mobileNo", "email", "landLineNo", "faxNo", "localAddress.addressLineOne", "localAddress.addressLineTwo", "localAddress.addressLineThree", "localAddress.town", "localAddress.county", "localAddress.pin", "localAddress.country"};

    public ParishWrapper(Parish parishBean) {
        this.parishBean = parishBean;
    }

    @Override
    public Long getId() {
        return parishBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                if (BeanUtils.getProperty(this.parishBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    convertedResult.add(BeanUtils.getProperty(this.parishBean, VALID_BEAN_PROPERTIES[i]).toString());
                }
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
