package com.nikart.app;

import com.nikart.model.retrofit.dagger2.NetworkModule;
import com.nikart.model.api.ApiRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Artem on 18.04.2017.
 */

@Component(modules = {AppModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(ApiRepository repository);

}
