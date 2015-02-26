package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.FamilyDto;
import org.pms.helpers.GridRow;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class FamilyWrapper implements GridRow {

    private FamilyDto familyDto;

    private String[] VALID_BEAN_PROPERTIES = {"familyID", "familyName"};

    public FamilyWrapper(FamilyDto familyDto) {
        this.familyDto = familyDto;
    }

    @Override
    public Integer getId() {
        return familyDto.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                convertedResult.add(BeanUtils.getProperty(this.familyDto, VALID_BEAN_PROPERTIES[i]).toString());
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
