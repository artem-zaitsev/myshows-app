package com.nikart.mainscreen;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebViewFragment;

import com.nikart.launchscreen.WelcomeFragment;
import com.nikart.myshows.R;

/*
*  Реализуем NavigationDrawer
*  Нужны дополнительные layouts, в них вынесем туллбар,контент, drawer
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    final private static int NUM_PAGES = 3;

    private DrawerLayout drawer;
    private Toolbar toolbar;

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


        /* Делаем drawer
        *  и сеттим на навигэйшнВью слушатель( пока нафиг не нужен, тк
        *  нет контента).
        * */
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    /* Тут реализуем метод интерфейса. Особо не реализован))
    * */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch(item.getItemId()) {
            case R.id.menu_item_my_shows : {
                transaction.replace(R.id.main_activity_fragment_container, new MyShowsFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
            case R.id.menu_item_my_episodes: {
                transaction.replace(R.id.main_activity_fragment_container, new MyShowsFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
            case R.id.menu_item_account: {
                transaction.replace(R.id.main_activity_fragment_container, new MyShowsFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
    /*
    * Ну тут короче все ясно.
    * */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
