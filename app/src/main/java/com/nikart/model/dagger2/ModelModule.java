package com.nikart.model.dagger2;

import com.nikart.model.Model;
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
