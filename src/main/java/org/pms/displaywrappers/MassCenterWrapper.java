package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.MassCenterDto;
import org.pms.helpers.GridRow;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class MassCenterWrapper implements GridRow {

    private MassCenterDto massCenterDto;

    private String[] VALID_BEAN_PROPERTIES = {"massCenterID", "massCenterName","patronName","place","facebookPage","registeredDate","drivingRoute","map","landLineNo","mobileNo","email","faxNo","parishName","localAddress"};

    public MassCenterWrapper(MassCenterDto massCenterDto) {
        this.massCenterDto = massCenterDto;
    }

    @Override
    public Integer getId() {
        return massCenterDto.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                convertedResult.add(BeanUtils.getProperty(this.massCenterDto, VALID_BEAN_PROPERTIES[i]).toString());
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
