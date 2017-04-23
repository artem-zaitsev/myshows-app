package com.nikart.presenter.soon_episodes;

import com.nikart.presenter.shows.ShowListPresenter;
import com.nikart.presenter.splash.SplashPresenter;
import com.nikart.screens.soon_episodes.SoonEpisodesFragment;
import com.nikart.screens.splash.SplashActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 23.04.2017.
 */

@Module
public class SoonEpisodesPresenterModule {

    @Provides
    public SoonEpisodesPresenter providePresenter() {
        return new SoonEpisodesPresenter();
    }
}
