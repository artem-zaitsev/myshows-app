package com.nikart.presenter.shows.dagger2;

import com.nikart.presenter.shows.ShowListPresenter;

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
