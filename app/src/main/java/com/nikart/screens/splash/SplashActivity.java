package com.nikart.screens.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.nikart.interactor.Answer;
import com.nikart.interactor.loaders.AuthLoader;
import com.nikart.myshows.R;
import com.nikart.screens.launch.LaunchActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

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
        if ( login != null
                &&  password != null
                && PreferencesWorker.getInstance().isSignedIn()) {
            getSupportLoaderManager().restartLoader(0, AuthLoader.args(login, password),
                    new LoaderManager.LoaderCallbacks<Answer>() {
                        @Override
                        public Loader<Answer> onCreateLoader(int id, Bundle args) {
                            String l = args.getStringArray(AuthLoader.AUTH_ARGS)[0];
                            String p = args.getStringArray(AuthLoader.AUTH_ARGS)[1];
                            return new AuthLoader(SplashActivity.this, l, p) ;
                        }

                        @Override
                        public void onLoadFinished(Loader<Answer> loader, Answer data) {
                            Response response = data.getTypedAnswer();
                            if (response.isSuccessful()) {
                                MainActivity.start(SplashActivity.this);
                            }
                        }

                        @Override
                        public void onLoaderReset(Loader<Answer> loader) {

                        }
                    });
        } else {
            LaunchActivity.start(this);
        }
    }
}
