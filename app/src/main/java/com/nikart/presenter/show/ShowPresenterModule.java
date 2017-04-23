package com.nikart.presenter.show;

import com.nikart.screens.show.ShowActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 23.04.2017.
 */

@Module
public class ShowPresenterModule {

    @Provides
    public ShowPresenter providePresenter() {
        return new ShowPresenter();
    }
}
