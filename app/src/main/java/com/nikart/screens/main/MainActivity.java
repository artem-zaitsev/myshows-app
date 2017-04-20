package com.nikart.screens.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.myshows.R;
import com.nikart.presenter.Presenter;
import com.nikart.screens.BaseActivity;
import com.nikart.screens.account.AccountFragment;
import com.nikart.screens.shows.MyShowsFragment;
import com.nikart.screens.soon_episodes.SoonEpisodesFragment;
import com.nikart.screens.util.NavigationController;
import com.nikart.util.PreferencesWorker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity {

    private NavigationController controller;
    private List<Fragment> fragmentList;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initActivity() {
        //так как наследуюсь от базовой, нужно проиницилизировать презентер
        setPresenter(new Presenter() {
            @Override
            public void loadData() {

            }

            @Override
            public void onStop() {

            }
        });
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyShowsFragment());
        fragmentList.add(new SoonEpisodesFragment());
        fragmentList.add(new AccountFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.main_activity_bottom_nav);

        controller = new NavigationController(getSupportFragmentManager(), fragmentList);
        controller.setupWithBottomNavigation(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            controller.switchFragments(item);
            return true;
        });
    }

    @Override
    public <T> void showData(T data) {

    }

    @Override
    public void showError(Throwable t) {

    }
}
