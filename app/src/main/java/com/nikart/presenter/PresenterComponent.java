package com.nikart.presenter;

import com.nikart.presenter.account.AccountPresenterModule;
import com.nikart.presenter.login.LoginPresenterModule;
import com.nikart.presenter.show.ShowPresenterModule;
import com.nikart.presenter.shows.ShowListPresenterModule;
import com.nikart.presenter.soon_episodes.SoonEpisodesPresenterModule;
import com.nikart.presenter.splash.SplashPresenterModule;
import com.nikart.screens.BaseFragment;
import com.nikart.screens.account.AccountFragment;
import com.nikart.screens.auth.signin.LoginActivity;
import com.nikart.screens.show.ShowActivity;
import com.nikart.screens.shows.MyShowsFragment;
import com.nikart.screens.soon_episodes.SoonEpisodesFragment;
import com.nikart.screens.splash.SplashActivity;

import dagger.Component;

/**
 * Created by Artem on 22.04.2017.
 */

@Component(modules = {SplashPresenterModule.class, SoonEpisodesPresenterModule.class, ShowListPresenterModule.class,
        ShowPresenterModule.class, LoginPresenterModule.class, AccountPresenterModule.class})
public interface PresenterComponent {

    void inject(SplashActivity a);

    void inject(ShowActivity a);

    void inject(LoginActivity a);

    void inject(MyShowsFragment f);

    void inject(SoonEpisodesFragment f);

    void inject(AccountFragment f);

}
