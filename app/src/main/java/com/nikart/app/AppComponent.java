package com.nikart.app;

import com.nikart.interactor.ApiManager;
import com.nikart.interactor.retrofit.ApiHelper;
import com.nikart.util.PreferencesWorker;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Artem on 18.04.2017.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    ApiHelper getApiHelper();

    PreferencesWorker getPreferences();

    void inject(ApiManager manager);
}
