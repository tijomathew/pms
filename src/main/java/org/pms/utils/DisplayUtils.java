package org.pms.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by febin on 4/3/15.
 */
public final class DisplayUtils {

    public static <T> String getEmbeddedObjectString(T o , String ... args){
        String [] beanProperties = args;
        String localAddress = "";
        for(int i = 0 ; i<beanProperties.length;i++){
            try {
            if(i == beanProperties.length - 1){
                    localAddress = localAddress.concat(BeanUtils.getProperty(o, beanProperties[i]).toString());
            }
            else{
                localAddress = localAddress.concat(BeanUtils.getProperty(o, beanProperties[i]).toString()).concat(",").concat(System.lineSeparator());
            }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return localAddress;
    }
}
