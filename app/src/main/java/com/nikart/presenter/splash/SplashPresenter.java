package com.nikart.presenter.splash;

import android.util.Log;

import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.splash.SplashActivity;
import com.nikart.util.PreferencesWorker;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 23.04.2017.
 * Splash presenter class.
 */

public class SplashPresenter extends BasePresenter {

    private IView view;

    @Override
    public void onCreate(IView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        model.signIn(PreferencesWorker.getInstance().getLogin(), PreferencesWorker.getInstance().getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isSuccessful -> view.showData(isSuccessful),
                        e -> view.showError(e),
                        () -> Log.d("RX_AUTH", "Complete authorization")
                );
    }
}
