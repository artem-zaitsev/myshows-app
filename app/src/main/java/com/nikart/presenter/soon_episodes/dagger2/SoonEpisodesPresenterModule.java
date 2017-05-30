package com.nikart.presenter.soon_episodes.dagger2;

import com.nikart.presenter.soon_episodes.SoonEpisodesPresenter;

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
