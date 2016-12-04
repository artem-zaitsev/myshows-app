package com.nikart.main;

import android.graphics.drawable.Drawable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;


import com.nikart.myshows.R;

import java.util.ArrayList;
import java.util.List;

/*
*  Реализуем NavigationDrawer
*  Нужны дополнительные layouts, в них вынесем туллбар,контент, drawer
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    FragmentController controller;
    List<Fragment> fragmentList;

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
        controller = new FragmentController(this, fragmentList);
        controller.initFragment();
        controller.setupWithBottomNavigation(bottomNavigationView);

    }

    /*
    * Ну тут короче все ясно.
    * */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
