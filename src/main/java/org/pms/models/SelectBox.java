package org.pms.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by tijo on 13/11/14.
 */
public class SelectBox<T> {

    private T displayName;
    private T value;

    public SelectBox() {
    }

    public SelectBox(T displayName, T value) {
        this.displayName = displayName;
        this.value = value;
    }

    public T getDisplayName() {
        return displayName;
    }

    public void setDisplayName(T displayName) {
        this.displayName = displayName;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getJsonForSelectBoxCreation(List<SelectBox<T>> selectBoxList) {
        JsonArray jsonArray = new JsonArray();
        try {
            for (SelectBox<T> selectBox : selectBoxList) {
                try {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("displayName", BeanUtils.getProperty(selectBox, "displayName"));
                    jsonObject.addProperty("value", BeanUtils.getProperty(selectBox, "value"));
                    jsonArray.add(jsonObject);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvocationTargetException e) {
            System.out.println("error in Json creation!!!!....");
        }
        return jsonArray.toString();
    }
}
