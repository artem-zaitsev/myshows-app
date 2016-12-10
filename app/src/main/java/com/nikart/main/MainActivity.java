package com.nikart.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.nikart.myshows.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
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

        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        //тестим некотрые фичи с контроллером
        // делаем лист из фрагиентов
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyShowsFragment());
        fragmentList.add(new MyEpisodesFragment());
        fragmentList.add(new AccountFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.main_activity_bottom_nav);

        //тестим контроллер
        // Переделать контроллер, вынести онкликлистнер, разбить транзакции на подметоды.
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

    /*
    * Ну тут короче все ясно.
    * */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
