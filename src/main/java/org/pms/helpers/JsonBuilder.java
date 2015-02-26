package org.pms.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.pms.serializers.ResultSerializer;

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
}
