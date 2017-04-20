package com.nikart.model;

import com.nikart.interactor.ApiRepository;
import com.nikart.model.api.ApiModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 20.04.2017.
 */

@Module
public class ModelModule {

    @Provides
    public ApiRepository provideRepository() {
        return ApiRepository.getInstance();
    }

    @Provides
    public  ApiModel provideApiModel() {
        return ApiModel.getInstance();
    }


}
