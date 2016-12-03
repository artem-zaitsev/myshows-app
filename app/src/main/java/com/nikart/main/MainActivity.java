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

/*
*  Реализуем NavigationDrawer
*  Нужны дополнительные layouts, в них вынесем туллбар,контент, drawer
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Fragment showsFragment;
    private Fragment episodesFragment;
    private Fragment accountFragment;
    private Fragment currentFragment; // current current

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


        // тут костыль(наверно) для инициализации первого фрагмента
        // как showsFragment.
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container,showsFragment, showsFragment.toString()).commit();
        currentFragment = showsFragment;

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.main_activity_bottom_nav);
        bottomNavigationView.performClick();

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


    /* Пока не делал отдельный класс-контроллер.
    ** Такой метод работает. (Артем)
    ** В качестве тегов использовал просто инфу о фрагменте.
     */
    private void openFragment(Fragment nextFragment) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        if (nextFragment.getTag()!= null ||
                getSupportFragmentManager().findFragmentByTag(nextFragment.getTag()) != null) {
            tr.hide(currentFragment).show(nextFragment).commit();
        } else {
            tr.hide(currentFragment).add(R.id.main_container,nextFragment,nextFragment.toString()).commit();
        }
        currentFragment = nextFragment;
        Log.d("TAG","Current Fragment :" );
    }

    /*
    * Ну тут короче все ясно.
    * */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
