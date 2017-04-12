package com.nikart.screens.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.myshows.R;
import com.nikart.screens.launch.LaunchActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.Md5Converter;
import com.nikart.util.PreferencesWorker;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 23.03.2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initActivity();
    }

    private void initActivity() {
        String login = PreferencesWorker.getInstance().getLogin();
        String password = PreferencesWorker.getInstance().getPassword();
        if (login != null
                && password != null
                && PreferencesWorker.getInstance().isSignedIn()) {
            Observable
                    .just(App.getInstance().getClient().newCall(
                            new Request.Builder()
                                    .url("https://api.myshows.me/profile/login?login="
                                            + login + "&password=" +
                                            Md5Converter.Md5Hash(password))
                                    .build()
                            )
                    )
                    .subscribeOn(Schedulers.io())
                    .map(Call::execute)
                    .map(Response::isSuccessful)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            isSuccessful -> {
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
}
