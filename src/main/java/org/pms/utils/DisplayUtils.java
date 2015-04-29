package org.pms.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * This class is used for concatenating different properties of the class as a single string to show in th UI Display
 * Created by febin on 4/3/15.
 */
public final class DisplayUtils {

    public static <T> String getEmbeddedObjectPropertyValueAsSingleString(T propertyClass, int countOfProperties, String... tobeConcatenatedProperties) {
        String embeddedPropertyValueAsSingleString = "";
        for (int i = 0; i < tobeConcatenatedProperties.length; i++) {
            try {
                if (BeanUtils.getProperty(propertyClass, tobeConcatenatedProperties[i]) != null && !BeanUtils.getProperty(propertyClass, tobeConcatenatedProperties[i]).isEmpty()) {
                    if (i == tobeConcatenatedProperties.length - 1) {
                        embeddedPropertyValueAsSingleString = embeddedPropertyValueAsSingleString.concat(BeanUtils.getProperty(propertyClass, tobeConcatenatedProperties[i]).toString());
                    } else {
                        int remainder = (countOfProperties - 1) % 2;
                        if (remainder != 0) {
                            embeddedPropertyValueAsSingleString = embeddedPropertyValueAsSingleString.concat(BeanUtils.getProperty(propertyClass, tobeConcatenatedProperties[i]).toString()).concat(",");
                        } else {
                            embeddedPropertyValueAsSingleString = embeddedPropertyValueAsSingleString.concat(BeanUtils.getProperty(propertyClass, tobeConcatenatedProperties[i]).toString()).concat(",").concat(System.lineSeparator());
                        }
                        --countOfProperties;
                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return embeddedPropertyValueAsSingleString;
    }
}