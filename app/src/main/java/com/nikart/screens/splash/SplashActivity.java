package com.nikart.screens.splash;

import android.util.Log;

import com.nikart.myshows.R;
import com.nikart.presenter.DaggerPresenterComponent;
import com.nikart.presenter.splash.SplashPresenter;
import com.nikart.screens.BaseActivity;
import com.nikart.screens.launch.LaunchActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

import javax.inject.Inject;

/**
 * Created by Artem on 23.03.2017.
 * Splash.
 */

public class SplashActivity extends BaseActivity {

    @Inject
    public SplashPresenter presenter;


    @Override
    protected void injectPresenter() {
        DaggerPresenterComponent.create().inject(this);
        presenter.onCreate(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initActivity() {
        setPresenter(presenter);
        String login = PreferencesWorker.getInstance().getLogin();
        String password = PreferencesWorker.getInstance().getPassword();
        if (login != null
                && password != null
                && PreferencesWorker.getInstance().isSignedIn()) {
            getPresenter().loadData();
        } else {
            LaunchActivity.start(this);
        }
    }

    @Override
    public <T> void showData(T data) {
        PreferencesWorker.getInstance().saveSignedIn((Boolean) data);
        MainActivity.start(this);
    }

    @Override
    public void showError(Throwable t) {
        Log.d("RX_SPLASH", t.toString());
        MainActivity.start(this);
    }
}
