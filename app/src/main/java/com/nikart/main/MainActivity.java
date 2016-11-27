package com.nikart.main;

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
import android.view.MenuItem;


import com.nikart.myshows.R;

/*
*  Реализуем NavigationDrawer
*  Нужны дополнительные layouts, в них вынесем туллбар,контент, drawer
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Fragment showsFragment;
    private Fragment episodesFragment;
    private Fragment accountFragment;


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

        showsFragment = new MyShowsFragment();
        episodesFragment = new MyEpisodesFragment();
        accountFragment = new AccountFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, showsFragment).commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.main_activity_bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.menu_item_my_shows): {
                        openFragment(showsFragment);
                        return true;
                    }
                    case (R.id.menu_item_my_episodes): {
                        openFragment(episodesFragment);
                        return true;
                    }
                    case (R.id.menu_item_account): {
                        openFragment(accountFragment);
                        return true;
                    }
                    default:
                        return false;
                }

            }
        });
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                fragment).commit();
    }

    /* Тут реализуем метод интерфейса. Особо не реализован))
    * */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    /*
    * Ну тут короче все ясно.
    * */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
