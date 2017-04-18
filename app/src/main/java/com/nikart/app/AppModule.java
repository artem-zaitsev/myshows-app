package com.nikart.app;

import com.nikart.interactor.retrofit.ApiHelper;
import com.nikart.util.PreferencesWorker;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 18.04.2017.
 */

@Module
public class AppModule {

    @Provides
    ApiHelper provideApiHelper() {
        return new ApiHelper();
    }

    @Provides
    PreferencesWorker providePreferences() {
        return new PreferencesWorker();
    }
}
