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
        Log.d("PREFERENCES", "MainActivity. Cookie from prefs:" + PreferencesWorker.getInstance().getCookies());
        initActivity();
        int id = 8;
        Observable<Show> showObservable = App.getInstance().getApi().getShowById(id);
        showObservable
                .subscribeOn(Schedulers.io())
                .map(
                        sh -> {
                            Show tmpShow = null;
                            try {
                                tmpShow = HelperFactory.getHelper().getShowDAO().queryForId(8);
                                String watchStatus = tmpShow.getWatchStatus();
                                sh.setId(8);
                                sh.setWatchStatus(watchStatus);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            return sh;
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        show -> {
                            Log.d("RX_SHOW_BY_ID", "Finished load show on ShowActivity." + show.getTitle());
                        },
                        e -> Log.d("RX_SHOW_BY_ID", e.toString()),
                        () -> Log.d("RX_SHOW_BY_ID", "Complete")
                );
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

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            controller.switchFragments(item);
            return true;
        });
    }

}
