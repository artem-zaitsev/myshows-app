package com.nikart.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.nikart.myshows.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem
 * Controller for fragments in bottomNavView.
 */

public class FragmentController {
    private Fragment currentFragment;
    private List<Fragment> fragments;
    private BottomNavigationView bttmNavView;
    private AppCompatActivity activity; // для вызова getSupportFragmentManager
    List<MenuItem> items;


    FragmentController(AppCompatActivity activity, List<Fragment> fragments ) {
        this.activity = activity;
        this.fragments = fragments;
        items = new ArrayList<>(fragments.size());
    }

    public void setupWithBottomNavigation(BottomNavigationView bottomNavigationView) {
        bttmNavView = bottomNavigationView;
        bttmNavView.performClick();
        Log.d("TAG", "It was setup");
        for (int i = 0; i < fragments.size(); i++) {
            items.add(bttmNavView.getMenu().getItem(i));
            Log.d("TAG"," Item " + items.get(i).toString());
        }
        bttmNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchFragments(item);
                return true;
            }
        });
    }

    public void initFragment() {
        activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, fragments.get(0), fragments.get(0).toString()).commit();
        currentFragment = fragments.get(0);
    }

    private void switchFragments(MenuItem item) {
        for (int i = 0; i < items.size(); i++) {
            if (item.getItemId() == items.get(i).getItemId()) {
                openFragment(fragments.get(i));
            }
        }

    }

    private void openFragment(Fragment nextFragment) {
        FragmentTransaction tr = activity.getSupportFragmentManager().beginTransaction();
        if (nextFragment.getTag()!= null ||
                activity.getSupportFragmentManager()
                        .findFragmentByTag(nextFragment.getTag()) != null) {
            tr.hide(currentFragment).show(nextFragment).commit();
        } else {
            tr.hide(currentFragment)
                    .add(R.id.main_container,nextFragment,nextFragment.toString()).commit();
        }
        currentFragment = nextFragment;
        Log.d("TAG","Current Fragment :" );
    }
}
