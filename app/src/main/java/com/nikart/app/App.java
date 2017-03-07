package com.nikart.app;

import android.app.Application;

import com.nikart.data.HelperFactory;

/**
 * Created by Artem on 07.03.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
