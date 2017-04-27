package com.nikart.presenter.login.dagger2;

import com.nikart.presenter.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 23.04.2017.
 */

@Module
public class LoginPresenterModule {

    @Provides
    public LoginPresenter providePresenter() {
        return new LoginPresenter();
    }
}
