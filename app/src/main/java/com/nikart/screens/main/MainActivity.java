package com.nikart.screens.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.nikart.interactor.Answer;
import com.nikart.interactor.ShowFromWebLoader;
import com.nikart.screens.account.AccountFragment;
import com.nikart.data.dto.Episode;
import com.nikart.data.dto.Show;
import com.nikart.screens.shows.MyShowsFragment;
import com.nikart.myshows.R;
import com.nikart.screens.soon_episodes.SoonEpisodesFragment;
import com.nikart.screens.util.NavigationController;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private NavigationController controller;
    private List<Fragment> fragmentList;
    private List<Show> shows;
    private List<Episode> episodes;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TAG", "Main Activity onCreate");
        initActivity();
        loadData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "Main Activity onDestroy");
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

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                controller.switchFragments(item);
                return true;
            }
        });
    }

    private void loadData() {
        LoaderManager.LoaderCallbacks loaderCallbacks = new LoaderManager.LoaderCallbacks<Answer>() {
            @Override
            public Loader<Answer> onCreateLoader(int id, Bundle args) {
                return new ShowFromWebLoader(MainActivity.this);
            }

            @Override
            public void onLoadFinished(Loader<Answer> loader, Answer data) {
                boolean isLoaded = data.getTypedAnswer();
                if (!isLoaded) {
                    Toast.makeText(MainActivity.this, "Some troubles!", Toast.LENGTH_SHORT)
                            .show();
                }
                Log.d("LOADERS", "Load data from api finished");
            }

            @Override
            public void onLoaderReset(Loader<Answer> loader) {

            }
        };

        getSupportLoaderManager().restartLoader(0, null, loaderCallbacks);
    }
}
