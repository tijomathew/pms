package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.PrayerUnitDto;
import org.pms.helpers.GridRow;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class WardWrapper implements GridRow {

    private PrayerUnitDto prayerUnitDto;

    private String[] VALID_BEAN_PROPERTIES = {"wardID", "wardCode","wardName","wardPlace","massCenterName","localAddress"};

    public WardWrapper(PrayerUnitDto wardDto) {
        this.prayerUnitDto = wardDto;
    }

    @Override
    public Integer getId() {
        return prayerUnitDto.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                convertedResult.add(BeanUtils.getProperty(this.prayerUnitDto, VALID_BEAN_PROPERTIES[i]).toString());
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
