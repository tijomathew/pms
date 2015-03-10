package org.pms.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.pms.serializers.ResultSerializer;

import java.util.List;

/**
 * Created by tijo on 8/12/14.
 */
public final class JsonBuilder {

    private Gson gson;

    public JsonBuilder() {
        gson = new GsonBuilder().registerTypeAdapter(GridRow.class,
                new ResultSerializer()).create();
    }

    public String convertToJson(Object objectToConvert) {
        return gson.toJson(objectToConvert);
    }
    public static <T> List<T> generateSubList(Integer page, Integer rows, Integer totalRecords, List<T> recordList){
        int fromIndex = 0, toIndex = 0;
        fromIndex = ((page * rows) - rows);
        toIndex = page * rows;
        if (totalRecords < toIndex) {
            toIndex = totalRecords;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
            toIndex = totalRecords;
        }
        return recordList.subList(fromIndex, toIndex);
    }
}
