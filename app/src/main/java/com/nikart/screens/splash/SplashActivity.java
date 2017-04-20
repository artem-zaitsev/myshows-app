package com.nikart.screens.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nikart.model.api.ApiModel;
import com.nikart.myshows.R;
import com.nikart.screens.BaseActivity;
import com.nikart.screens.launch.LaunchActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

/**
 * Created by Artem on 23.03.2017.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initActivity() {
        String login = PreferencesWorker.getInstance().getLogin();
        String password = PreferencesWorker.getInstance().getPassword();
        if (login != null
                && password != null
                && PreferencesWorker.getInstance().isSignedIn()) {
            ApiModel model = new ApiModel();
            model.signIn(login, password)
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
