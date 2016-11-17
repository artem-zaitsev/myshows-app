package com.nikart.myshows;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/*
*  Реализуем NavigationDrawer
*  Нужны дополнительные layouts, в них вынесем туллбар,контент, drawer
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final private static int NUM_PAGES = 3;

    private ViewPager mainActivityPager;
    private PagerAdapter mainActivityPagerAdapter;
    private DrawerLayout drawer;
    private Toolbar myToolbar;

    static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(myToolbar);

        mainActivityPager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        mainActivityPagerAdapter = new MainActivityFragmentPagerAdapter(getSupportFragmentManager());
        mainActivityPager.setAdapter(mainActivityPagerAdapter);

        /* Делаем drawer
        *  и сеттим на навигэйшнВью слушатель( пока нафиг не нужен, тк
        *  нет контента).
        *  Дровер не совсем нормально работает - серая полоска напрягает.
        *  Видимо это полоска статус бара.
        *  */
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Tabs????
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_activity_vw_tabs);
        /*tabLayout.addTab(tabLayout.newTab().setText("MY SHOWS"));
        tabLayout.addTab(tabLayout.newTab().setText("MY EPISODES"));
        tabLayout.addTab(tabLayout.newTab().setText("NEWS"));*/
        tabLayout.setupWithViewPager(mainActivityPager);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private class MainActivityFragmentPagerAdapter extends FragmentPagerAdapter {
        MainActivityFragmentPagerAdapter(FragmentManager supportFragmentManager) {
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
