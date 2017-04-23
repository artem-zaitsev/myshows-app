package com.nikart.presenter.splash;

import com.nikart.screens.IView;
import com.nikart.screens.splash.SplashActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 23.04.2017.
 *
 */

@Module
public class SplashPresenterModule {

    @Provides
    public SplashPresenter providePresenter() {
        return new SplashPresenter();
    }
}
