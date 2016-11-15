package com.nikart.myshows;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    static final int NUM_PAGES = 2;
    private Button registerButton;
    private Button loginButton;

    //Pager
    private ViewPager mPager;

    //PagerAdapter
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        registerButton = (Button) findViewById(R.id.register_btn);
        loginButton = (Button) findViewById(R.id.login_btn);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        mPager = (ViewPager) findViewById(R.id.launch_pager);
        mPagerAdapter = new WelcomePagesSlideAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn: {
                LoginActivity.start(this);
                break;
            }
            case R.id.register_btn: {
                SignupActivity.start(this);
                break;
            }
        }
    }

    // Делаем внутренний класс для адаптера фрагментов. Возможно нужно
    // сделать FragmentAdapter, так как страниц всего 3.
    private class WelcomePagesSlideAdapter extends FragmentStatePagerAdapter {
        WelcomePagesSlideAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new WelcomeFragment();
            Bundle args = new Bundle();
            // Put a position of item, i.e number of page.
            args.putInt(WelcomeFragment.ARG_KEY, position );
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
