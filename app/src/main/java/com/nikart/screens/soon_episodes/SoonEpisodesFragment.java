package com.nikart.screens.soon_episodes;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nikart.myshows.R;
import com.nikart.presenter.soon_episodes.SoonEpisodesPresenter;
import com.nikart.screens.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Фрагмент для отображения списка серий
 */

public class SoonEpisodesFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private EpisodesInMonthAdapter monthAdapter;
    private FrameLayout progressLoadFrame;
    private List<Month> months;


    // Приделан ExpandedRecyclerView!!!!!!
    @Override
    public void onStart() {
        super.onStart();
        setPresenter(new SoonEpisodesPresenter(this));
        getPresenter().loadData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_soon_episodes, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int c = 0;
        switch (item.getItemId()) {
            case R.id.item_toggle_group: {
                for (Month m : months) {
                    // надо настроить поведение на раскрыть/свернуть все
                    c = monthAdapter.toggleGroup(m)
                            ? (c + 1)
                            : (c - 1);

                }
                item.setTitle(c <= 0
                        ? R.string.collapse
                        : R.string.expand);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initFragment(View rootView) {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(getString(R.string.my_episodes));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_myepisodes_rv);
        progressLoadFrame = (FrameLayout) rootView.findViewById(R.id.fragment_episodes_progress);
       // initRecycler();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_soon_episodes;
    }

    private void initRecycler() {
        monthAdapter = new EpisodesInMonthAdapter(months);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(monthAdapter);
    }

    public void updateRecycler(List<Month> months) {
        this.months = months;
        initRecycler();
        progressLoadFrame.setVisibility(View.GONE);
    }

    public void showErrorSnackbar(Throwable e) {
        Snackbar.make(getActivity()
                        .findViewById(R.id.main_activity_layout),
                "No Internet connection",
                Snackbar.LENGTH_INDEFINITE)
                .setAction("ON", view ->
                        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)))
                .show();
        Log.d("RX_SOON_EPS", e.toString());
    }

    @Override
    public <T> void showData(T data) {
        updateRecycler((List<Month>) data);
    }

    @Override
    public void showError(Throwable t) {
        showErrorSnackbar(t);
    }
}
