package com.nikart.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.nikart.data.HelperFactory;
import com.nikart.interactor.retrofit.ApiHelper;
import com.nikart.util.PreferencesWorker;

/**
 * Created by Artem on 07.03.2017.
 */

public class App extends Application {

    private static App appInstance;

    public static App getInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        initRetrofit();
        initDataBaseHelper();
        initPreferences();
        initStetho();
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }

    private void initRetrofit() {
        ApiHelper.getInstance().initRetrofit();
    }

    private void initDataBaseHelper() {
        HelperFactory.setHelper(getApplicationContext());
    }

    private void initPreferences() {
        //Инициализируем статическую sharedPreferences в PrefWorker
        PreferencesWorker.getInstance().initSharedPreferences(
                getSharedPreferences(PreferencesWorker.PREF_SIGN_IN, MODE_PRIVATE));
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }
}
