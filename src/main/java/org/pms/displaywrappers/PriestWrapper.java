package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.PriestDto;
import org.pms.helpers.GridRow;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class PriestWrapper implements GridRow {

    private PriestDto priestDto;

    private String[] VALID_BEAN_PROPERTIES = {"priestID", "name", "designation"};

    public PriestWrapper(PriestDto priestDto) {
        this.priestDto = priestDto;
    }

    @Override
    public Integer getId() {
        return priestDto.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                convertedResult.add(BeanUtils.getProperty(this.priestDto, VALID_BEAN_PROPERTIES[i]).toString());
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
