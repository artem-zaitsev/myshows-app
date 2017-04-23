package com.nikart.presenter.account;

import com.nikart.screens.account.AccountFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artem on 23.04.2017.
 */

@Module
public class AccountPresenterModule {

    @Provides
    public AccountPresenter providePresenter() {
        return new AccountPresenter();
    }
}
