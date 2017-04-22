package com.nikart.model;

import com.nikart.model.api.ApiRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 20.04.2017.
 */

@Module
public class ModelModule {

    @Provides
    public Model provideRepository() {
        return ApiRepository.getInstance();
    }

}
