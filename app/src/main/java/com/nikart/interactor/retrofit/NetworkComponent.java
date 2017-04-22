package com.nikart.interactor.retrofit;

import com.nikart.app.App;
import com.nikart.model.Model;
import com.nikart.model.api.ApiRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Artem on 22.04.2017.
 */

@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(ApiRepository model);

    void inject(App app);
}
