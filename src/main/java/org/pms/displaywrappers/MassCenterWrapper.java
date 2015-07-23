package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.MassCenterDto;
import org.pms.helpers.GridRow;
import org.pms.models.MassCenter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class MassCenterWrapper implements GridRow {

    private MassCenter massCenterBean;

    private String[] VALID_BEAN_PROPERTIES = {"name", "centerCode", "massCenterID", "patronName", "place", "facebookPage", "registeredDate", "drivingRoute", "map", "landLineNo", "mobileNo", "email", "faxNo",
            "localAddress.addressLineOne", "localAddress.addressLineTwo", "localAddress.addressLineThree", "localAddress.town", "localAddress.county", "localAddress.pin", "localAddress.country"};

    public MassCenterWrapper(MassCenter massCenterBean) {
        this.massCenterBean = massCenterBean;
    }

    @Override
    public Long getId() {
        return massCenterBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                if (BeanUtils.getProperty(this.massCenterBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    convertedResult.add(BeanUtils.getProperty(this.massCenterBean, VALID_BEAN_PROPERTIES[i]).toString());
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
