package com.nikart.util;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Artem on 17.03.2017.
 */

public class JsonParser<T> {

    private List<T> list;

    public JsonParser(List list) {
        this.list = list;
    }

    public List<T> getParsedList(JSONObject result, Class<T> c) {
        Iterator keys = result.keys();
        while (keys.hasNext()) {
            String currentDynamicKey = (String) keys.next();
            JSONObject currentDynamicValue = null;
            try {
                currentDynamicValue = result.getJSONObject(currentDynamicKey);
                Log.d("JSON", "Current: " + currentDynamicValue + " key " + currentDynamicKey);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            T t = new Gson().fromJson(currentDynamicValue.toString(), c);
            list.add(t);
        }
        return list;
    }
}
