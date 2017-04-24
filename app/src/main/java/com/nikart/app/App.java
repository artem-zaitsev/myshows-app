package com.nikart.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.nikart.data.HelperFactory;
import com.nikart.util.PreferencesWorker;
import com.vk.sdk.VKSdk;

/**
 * Created by Artem on 07.03.2017.
 * App class.
 */

public class App extends Application {

    private static App appInstance;

   /* @Inject
    NetworkHelper helper;*/

    public static App getInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = (App) getApplicationContext();
        initDataBaseHelper();
        initVk();
        initPreferences();
        initStetho();
    }

    private void initVk() {
        VKSdk.initialize(this);
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
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
