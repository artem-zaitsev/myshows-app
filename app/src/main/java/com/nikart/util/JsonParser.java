package com.nikart.util;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Artem on 17.03.2017.
 */

public class JsonParser<T> {

    private List<T> list;
    private Response<ResponseBody> response;

    public JsonParser(Response<ResponseBody> response) {
        this.response = response;
    }

    public List<T> getParsedList(Class<T> c) throws IOException, JSONException {
        JSONObject result = getJSONObject();
        if (result != null) {
            list = new ArrayList<>();
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
        }
        return list;
    }

    @Nullable
    private JSONObject getJSONObject() throws IOException, JSONException {
        return response.body() != null ? new JSONObject(response.body().string()) : null;
    }
}
