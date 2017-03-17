package com.nikart.interactor;

import android.content.Context;

import com.google.gson.Gson;
import com.nikart.base.BaseLoader;
import com.nikart.data.dto.Show;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nikita on 17.03.2017.
 */

public class ShowsListLoader extends BaseLoader<Answer> {

    public ShowsListLoader(Context context) { super(context); }

    @Override
    public Answer loadInBackground() {
        List<Show> shows = new ArrayList<>();

        data = new Answer();

        JSONObject result = null;
        try {
            result = new JSONObject("{\"7718\":{\"showId\":7718,\"title\":\"Sherlock\",\"ruTitle\":\"\\u0428\\u0435\\u0440\\u043b\\u043e\\u043a\",\"runtime\":90,\"showStatus\":\"TBD\\/On The Bubble\",\"watchStatus\":\"watching\",\"watchedEpisodes\":12,\"totalEpisodes\":12,\"rating\":5,\"image\":\"https:\\/\\/media.myshows.me\\/shows\\/small\\/9\\/94\\/9492ce09d3a31c32ba559f5936dac888.jpg\"},\"331\":{\"showId\":331,\"title\":\"Doctor Who\",\"ruTitle\":\"\\u0414\\u043e\\u043a\\u0442\\u043e\\u0440 \\u041a\\u0442\\u043e\",\"runtime\":50,\"showStatus\":\"Returning Series\",\"watchStatus\":\"watching\",\"watchedEpisodes\":115,\"totalEpisodes\":115,\"rating\":0,\"image\":\"https:\\/\\/media.myshows.me\\/shows\\/small\\/8\\/81\\/8107adbada5f9efc1da4a722f4990e9f.jpg\"},\"8\":{\"showId\":8,\"title\":\"Lost\",\"ruTitle\":\"\\u041e\\u0441\\u0442\\u0430\\u0442\\u044c\\u0441\\u044f \\u0432 \\u0436\\u0438\\u0432\\u044b\\u0445\",\"runtime\":43,\"showStatus\":\"Canceled\\/Ended\",\"watchStatus\":\"later\",\"watchedEpisodes\":0,\"totalEpisodes\":121,\"rating\":0,\"image\":\"https:\\/\\/media.myshows.me\\/shows\\/small\\/7\\/74\\/749eec18eb58e856bdea8e19833d2fa1.jpg\"}}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Iterator keys = result.keys();

        while (keys.hasNext()) {
            String currentDynamicKey = (String)keys.next();
            JSONObject currentDynamicValue = null;
            try {
                currentDynamicValue = result.getJSONObject(currentDynamicKey);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Show show = new Gson().fromJson(currentDynamicValue.toString(), Show.class);
            shows.add(show);
        }
        data.setAnswer(shows);
        return data;
    }
}
