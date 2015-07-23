package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.FamilyDto;
import org.pms.helpers.GridRow;
import org.pms.models.Family;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class FamilyWrapper implements GridRow {

    private Family familyBean;

    private String[] VALID_BEAN_PROPERTIES = {"familyName", "familyID", "parishInNative", "dioceseInNative", "dateOfRegistration", "parishId", "massCenterId", "prayerUnitId", "localAddress.addressLineOne", "localAddress.addressLineTwo", "localAddress.addressLineThree", "localAddress.town", "localAddress.county", "localAddress.pin", "localAddress.country", "nativeAddress.addressLineOne", "nativeAddress.addressLineTwo", "nativeAddress.addressLineThree", "nativeAddress.postOffice", "nativeAddress.district", "nativeAddress.pin", "nativeAddress.state", "nativeAddress.country"};

    public FamilyWrapper(Family familyBean) {
        this.familyBean = familyBean;
    }

    @Override
    public Long getId() {
        return familyBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                if (BeanUtils.getProperty(this.familyBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    convertedResult.add(BeanUtils.getProperty(this.familyBean, VALID_BEAN_PROPERTIES[i]).toString());
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