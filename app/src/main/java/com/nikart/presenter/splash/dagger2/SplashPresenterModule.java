package com.nikart.presenter.splash.dagger2;

import com.nikart.presenter.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 23.04.2017.
 */

@Module
public class SplashPresenterModule {

    @Provides
    public SplashPresenter providePresenter() {
        return new SplashPresenter();
    }
}
