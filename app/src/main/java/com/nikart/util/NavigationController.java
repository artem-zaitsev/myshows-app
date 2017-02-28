package com.nikart.util;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class NavigationController {

    private Fragment currentFragment;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager; // для вызова getSupportFragmentManager
    private List<MenuItem> items;

    public NavigationController(FragmentManager fragmentManager, List<Fragment> fragments) {
        this.fragmentManager = fragmentManager;
        this.fragments = fragments;
        items = new ArrayList<>(fragments.size());
    }

    public void setupWithBottomNavigation(BottomNavigationView bottomNavigationView) {

        for (int i = 0; i < fragments.size(); i++) {
            items.add(bottomNavigationView.getMenu().getItem(i));
            Log.d("TAG", " Item " + items.get(i).toString());
        }

        openFragment(fragments.get(0));
    }

    public void switchFragments(MenuItem item) {
        hideCurrentFragment();
        for (int i = 0; i < items.size(); i++) {
            if (item.getItemId() == items.get(i).getItemId()) {
                openFragment(fragments.get(i));
            }
        }

    }

    private void openFragment(Fragment nextFragment) {
        if (nextFragment.getTag() != null ||
                fragmentManager
                        .findFragmentByTag(nextFragment.getTag()) != null) {
            showFragment(nextFragment);
        } else {
            addFragment(nextFragment);
        }
        currentFragment = nextFragment;
        Log.d("TAG", "Current Fragment :" + currentFragment.toString());
    }

    private FragmentTransaction startTransaction() {
        return fragmentManager.beginTransaction();
    }

    private void hideCurrentFragment() {
        startTransaction()
                .setCustomAnimations(
                        R.anim.fade_out,
                        R.anim.fade_out)
                .hide(currentFragment).commitNow();
    }

    private void showFragment(Fragment fragment) {
        startTransaction().setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_in)
                .show(fragment).commitNow();
    }

    private void addFragment(Fragment fragment) {
        startTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_in)
                .add(R.id.main_container, fragment, fragment.toString()).commitNow();
    }
}
