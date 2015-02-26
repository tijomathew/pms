package org.pms.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * User: tijo.
 */
public class PriestDesignationBox<T> {

    private T displayName;
    private T value;
    private T state;

    public PriestDesignationBox() {
    }

    public PriestDesignationBox(T displayName, T value, T state) {
        this.displayName = displayName;
        this.value = value;
        this.state = state;
    }

    public T getDisplayName() {
        return displayName;
    }

    public T getValue() {
        return value;
    }

    public T getState() {
        return state;
    }

    public String getJsonForPriestDesignationBoxCreation(List<PriestDesignationBox<T>> priestDesignationBoxList) {
        JsonArray jsonArray = new JsonArray();
        try {
            for (PriestDesignationBox<T> priestDesignationBox : priestDesignationBoxList) {
                try {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("displayName", BeanUtils.getProperty(priestDesignationBox, "displayName"));
                    jsonObject.addProperty("value", BeanUtils.getProperty(priestDesignationBox, "value"));
                    jsonObject.addProperty("state", BeanUtils.getProperty(priestDesignationBox, "state"));
                    jsonArray.add(jsonObject);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvocationTargetException e) {
            System.out.println("error in Json creation of Priest Designation Box!!!!....");
        }
        return jsonArray.toString();
    }
}
