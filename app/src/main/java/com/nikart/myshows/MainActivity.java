package com.nikart.myshows;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    final private static int NUM_PAGES = 3;

    private ViewPager mainActivityPager;
    private PagerAdapter mainActivityPagerAdapter;

    static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(myToolbar);

        mainActivityPager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        mainActivityPagerAdapter = new MainActivityFragmentPagerAdapter(getSupportFragmentManager());
        mainActivityPager.setAdapter(mainActivityPagerAdapter);
    }

    private class MainActivityFragmentPagerAdapter extends FragmentPagerAdapter {
        public MainActivityFragmentPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return new MyShowsFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
