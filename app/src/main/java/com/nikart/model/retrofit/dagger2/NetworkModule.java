package com.nikart.model.retrofit.dagger2;

import com.nikart.model.retrofit.MyShowsApi;
import com.nikart.model.retrofit.NetworkHelper;

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
