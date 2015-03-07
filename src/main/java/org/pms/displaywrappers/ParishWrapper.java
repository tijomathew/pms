package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.ParishDto;
import org.pms.helpers.GridRow;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class ParishWrapper implements GridRow {

    private ParishDto parishDto;

    private String[] VALID_BEAN_PROPERTIES = {"parishID", "parishName", "riteName", "archDioceseName", "dioceseName", "foraneName","parishFacebookPage","parishWebsite","parishCode","parishPlace","parishDrivingRoute","parishMap","registeredDate","mobileNo","parishEmail","parishLandLineNo","parishFaxNo"};

    public ParishWrapper(ParishDto parishDto) {
        this.parishDto = parishDto;
    }

    @Override
    public Integer getId() {
        return parishDto.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                convertedResult.add(BeanUtils.getProperty(this.parishDto, VALID_BEAN_PROPERTIES[i]).toString());
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
