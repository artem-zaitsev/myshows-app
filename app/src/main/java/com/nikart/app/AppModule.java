package com.nikart.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 18.04.2017.
 * App module. Dagger2.@
 */

@Module
public class AppModule {

    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

}
