package com.nikart.model;

import com.nikart.presenter.BasePresenter;
import com.nikart.screens.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Artem on 20.04.2017.
 */

@Component(modules = {ModelModule.class})
@Singleton
public interface ModelComponent {

    void inject(BasePresenter presenter);

}
