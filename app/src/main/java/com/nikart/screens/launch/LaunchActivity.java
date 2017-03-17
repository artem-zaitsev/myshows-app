package com.nikart.screens.launch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nikart.myshows.R;
import com.nikart.screens.auth.signin.LoginActivity;
import com.nikart.screens.auth.signup.SignUpActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    static private final int NUM_PAGES = 2;
    private Button registerButton;
    private Button loginButton;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Log.d("PREFERENCES", "Cookie from prefs:" + PreferencesWorker.getInstance().getCookies());
        initActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn: {
                if (PreferencesWorker.getInstance().isSignedIn()) {
                    Log.d("PREFS", String.valueOf(PreferencesWorker.getInstance().isSignedIn()));
                    MainActivity.start(this);
                    finish();
                } else LoginActivity.start(this);
                break;
            }
            case R.id.register_btn: {
                if (PreferencesWorker.getInstance().isSignedIn()) {
                    MainActivity.start(this);
                    finish();
                } else SignUpActivity.start(this);
                break;
            }
        }
    }

    private void initActivity() {
        registerButton = (Button) findViewById(R.id.register_btn);
        loginButton = (Button) findViewById(R.id.login_btn);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        pager = (ViewPager) findViewById(R.id.launch_pager);
        pagerAdapter = new WelcomePagesSlideAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    // Делаем внутренний класс для адаптера фрагментов.
    private class WelcomePagesSlideAdapter extends FragmentPagerAdapter {
        WelcomePagesSlideAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0: {
                    fragment = new WelcomeFragment();
                    break;
                }
                case 1: {
                    fragment = new PromoFragment();
                    break;
                }
                default:
                    fragment = new WelcomeFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
