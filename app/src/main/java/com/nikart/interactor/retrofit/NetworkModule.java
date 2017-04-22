package com.nikart.interactor.retrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 22.04.2017.
 */

@Module
public class NetworkModule {

    @Provides
    public NetworkHelper provideNetworkHelper() {
        return NetworkHelper.getInstance();
    }

    @Provides
    public MyShowsApi provideMyShowsApi() {
        return NetworkHelper.getInstance().getApi();
    }
}
