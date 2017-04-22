package com.nikart.screens.splash;

import android.util.Log;

import com.nikart.model.DaggerModelComponent;
import com.nikart.model.Model;
import com.nikart.model.api.ApiRepository;
import com.nikart.myshows.R;
import com.nikart.screens.BaseActivity;
import com.nikart.screens.launch.LaunchActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 23.03.2017.
 */

public class SplashActivity extends BaseActivity {

    @Inject
    public Model model;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initActivity() {
        DaggerModelComponent.create().inject(this);
        String login = PreferencesWorker.getInstance().getLogin();
        String password = PreferencesWorker.getInstance().getPassword();
        if (login != null
                && password != null
                && PreferencesWorker.getInstance().isSignedIn()) {
            model.signIn(login, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(isSuccessful -> {
                                PreferencesWorker.getInstance().saveSignedIn(isSuccessful);
                                MainActivity.start(SplashActivity.this);
                            },
                            e -> {
                                Log.d("RX_AUTH", e.toString());
                                MainActivity.start(SplashActivity.this);
                            },
                            () -> Log.d("RX_AUTH", "Complete authorization")
                    );
        } else {
            LaunchActivity.start(this);
        }
    }

    @Override
    public <T> void showData(T data) {

    }

    @Override
    public void showError(Throwable t) {

    }
}
