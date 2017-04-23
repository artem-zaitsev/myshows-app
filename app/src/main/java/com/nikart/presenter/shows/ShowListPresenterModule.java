package com.nikart.presenter.shows;

import com.nikart.presenter.splash.SplashPresenter;
import com.nikart.screens.shows.MyShowsFragment;
import com.nikart.screens.splash.SplashActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 23.04.2017.
 */

@Module
public class ShowListPresenterModule {

    @Provides
    public ShowListPresenter providePresenter() {
        return new ShowListPresenter();
    }
}
