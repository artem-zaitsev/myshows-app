package com.nikart.interactor.loaders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.nikart.app.App;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Artem on 22.03.2017.
 */

public class RateUpdateLoader extends AsyncTaskLoader<Boolean> {

    private int id;
    private int rate;

    public static Bundle args(int id, int rate) {
        Bundle args = new Bundle();
        int [] rateUpdate = {id , rate};
        args.putIntArray("RATE", rateUpdate);
        return args;
    }

    public RateUpdateLoader(Context context, int id, int rate) {
        super(context);
        this.id = id;
        this.rate = rate;
    }

    @Override
    public Boolean loadInBackground() {
        Boolean success = Boolean.FALSE;
        try {
            success = App.getInstance().getApi().updateShowRate(id, rate).execute().isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
}
