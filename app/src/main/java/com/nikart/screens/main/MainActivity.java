package com.nikart.screens.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.nikart.screens.account.AccountFragment;
import com.nikart.screens.shows.MyShowsFragment;
import com.nikart.myshows.R;
import com.nikart.screens.soon_episodes.SoonEpisodesFragment;
import com.nikart.screens.util.NavigationController;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private NavigationController controller;
    private List<Fragment> fragmentList;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initActivity() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyShowsFragment());
        fragmentList.add(new SoonEpisodesFragment());
        fragmentList.add(new AccountFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.main_activity_bottom_nav);

        controller = new NavigationController(getSupportFragmentManager(), fragmentList);
        controller.setupWithBottomNavigation(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                controller.switchFragments(item);
                return true;
            }
        });
    }
}
