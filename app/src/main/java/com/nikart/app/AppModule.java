package com.nikart.app;

import com.nikart.interactor.retrofit.ApiHelper;
import com.nikart.util.PreferencesWorker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 18.04.2017.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper() {
        return ApiHelper.getInstance();
    }

    @Provides
    @Singleton
    PreferencesWorker providePreferences() {
        return PreferencesWorker.getInstance();
    }
}
