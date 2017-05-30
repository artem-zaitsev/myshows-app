package com.nikart.presenter.search.dagger2;

import com.nikart.presenter.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 29.04.2017.
 */

@Module
public class SearchPresenterModule {

    @Provides
    public SearchPresenter provideSearchPresenter() {
        return new SearchPresenter();
    }
}
