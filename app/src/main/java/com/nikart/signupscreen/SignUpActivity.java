package com.nikart.signupscreen;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nikart.launchscreen.LaunchActivity;
import com.nikart.launchscreen.PromoFragment;
import com.nikart.launchscreen.WelcomeFragment;
import com.nikart.myshows.R;

public class SignUpActivity extends AppCompatActivity {

    static private final int NUM_PAGES = 2;

    private ViewPager pager;

    //PagerAdapter
    private PagerAdapter pagerAdapter;


    public static void start(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        pager = (ViewPager) findViewById(R.id.signup_pager);
        pagerAdapter = new SignUpPagesSlideAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

    }

    private class SignUpPagesSlideAdapter extends FragmentPagerAdapter {
        SignUpPagesSlideAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0: {
                    fragment = new FirstFragment();
                    break;
                }
                case 1: {
                    fragment = new SecondFragment();
                    break;
                }
                default:
                    fragment = new FirstFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
