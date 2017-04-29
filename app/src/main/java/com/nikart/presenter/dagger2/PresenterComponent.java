package com.nikart.presenter.dagger2;

import com.nikart.presenter.account.dagger2.AccountPresenterModule;
import com.nikart.presenter.login.dagger2.LoginPresenterModule;
import com.nikart.presenter.search.SearchPresenter;
import com.nikart.presenter.search.dagger2.SearchPresenterModule;
import com.nikart.presenter.show.dagger2.ShowPresenterModule;
import com.nikart.presenter.shows.dagger2.ShowListPresenterModule;
import com.nikart.presenter.soon_episodes.dagger2.SoonEpisodesPresenterModule;
import com.nikart.presenter.splash.dagger2.SplashPresenterModule;
import com.nikart.screens.account.AccountFragment;
import com.nikart.screens.auth.signin.LoginActivity;
import com.nikart.screens.search.SearchActivity;
import com.nikart.screens.show.ShowActivity;
import com.nikart.screens.shows.MyShowsFragment;
import com.nikart.screens.soon_episodes.SoonEpisodesFragment;
import com.nikart.screens.splash.SplashActivity;

import dagger.Component;

/**
 * Created by Artem on 22.04.2017.
 */

@Component(modules = {SplashPresenterModule.class, SoonEpisodesPresenterModule.class, ShowListPresenterModule.class,
        ShowPresenterModule.class, LoginPresenterModule.class, AccountPresenterModule.class, SearchPresenterModule.class})
public interface PresenterComponent {

    void inject(SplashActivity a);

    void inject(ShowActivity a);

    void inject(LoginActivity a);

    void inject(SearchActivity a);

    void inject(MyShowsFragment f);

    void inject(SoonEpisodesFragment f);

    void inject(AccountFragment f);

}
