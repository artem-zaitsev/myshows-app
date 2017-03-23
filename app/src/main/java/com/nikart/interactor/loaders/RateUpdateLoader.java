package com.nikart.interactor.loaders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.nikart.app.App;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Artem on 22.03.2017.
 */

public class RateUpdateLoader extends AsyncTaskLoader<Boolean> {

    public static final String ARGS_RATE = "RATE";
    private int id;
    private int rate;
    Boolean data;

    public static Bundle args(int id, int rate) {
        Bundle args = new Bundle();
        int [] rateUpdate = {id , rate};
        args.putIntArray(ARGS_RATE, rateUpdate);
        return args;
    }

    public RateUpdateLoader(Context context, int id, int rate) {
        super(context);
        this.id = id;
        this.rate = rate;
    }

    @Override
    protected void onStartLoading() {
        if (data != null) {
            deliverResult(data);
        }
        forceLoad();
    }

    @Override
    public Boolean loadInBackground() {
        Boolean success = Boolean.FALSE;
        try {
            success = App.getInstance().getApi().updateShowRate(id, rate).execute().isSuccessful();
            Log.d("LOADERS", "Update " + success);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void deliverResult(Boolean data) {
        this.data = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
    }
}
