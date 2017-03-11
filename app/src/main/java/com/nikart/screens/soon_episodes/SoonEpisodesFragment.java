package com.nikart.screens.soon_episodes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nikart.base.BaseAnswer;
import com.nikart.data.EpisodeFromDataBaseLoader;
import com.nikart.data.dto.Episode;
import com.nikart.myshows.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Фрагмент для отображения списка серий
 */

public class SoonEpisodesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private EpisodesInMonthAdapter monthAdapter;
    private Toolbar toolbar;
    private ProgressBar progressLoad;

    private String EPISODES_FRAGMENT_TITLE;
    private LoaderManager.LoaderCallbacks<BaseAnswer> loaderCallbacks;

    private List<Month> months;

    @Override
    public void onAttach(Context context) {
        EPISODES_FRAGMENT_TITLE = getString(R.string.my_episodes);
        super.onAttach(context);
    }

    // Приделан ExpandedRecyclerView!!!!!!
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_episodes, container, false);
        initFragment(rootView);
        initLoader();
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
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
                for (Month m : months
                        ) {
                    c = monthAdapter.toggleGroup(m)
                            ? c + 1
                            : c - 1;
                }
                item.setTitle(c <= 0
                        ? R.string.collapse
                        : R.string.expand);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFragment(View rootView) {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(EPISODES_FRAGMENT_TITLE);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_myepisodes_rv);
        progressLoad = (ProgressBar) rootView.findViewById(R.id.fragment_episodes_progress);
    }

    private void initRecycler() {
        monthAdapter = new EpisodesInMonthAdapter(months);
        manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(monthAdapter);
    }

    private void initLoader() {
        loaderCallbacks = new LoaderManager.LoaderCallbacks<BaseAnswer>() {
            @Override
            public Loader<BaseAnswer> onCreateLoader(int id, Bundle args) {
                return new EpisodeFromDataBaseLoader(SoonEpisodesFragment.this.getContext());
            }

            @Override
            public void onLoadFinished(Loader<BaseAnswer> loader, BaseAnswer data) {
                final String[] monthTitle = getResources().getStringArray(R.array.months);
                Date today = new Date();
                Date maximumDate = new Date(121212);

                months = new ArrayList<>();
                List<Episode> episodes = new ArrayList<>();
                List<Episode> fromResponse = data.getTypedAnswer();
                for (Episode e : fromResponse) {
                    episodes.add(fromResponse.indexOf(e), e);
                }
                for (int i = 0; i < episodes.size(); i++) {
                    maximumDate = (today.compareTo(episodes.get(i).getAirDate()) < 0)
                            ? episodes.get(i).getAirDate()
                            : today;
                    Log.d("TAG", "Ep date: " + episodes.get(i).getAirDate().getMonth());
                    Log.d("TAG", String.valueOf(maximumDate.getMonth()));
                }

                //Использую устаревший метод, так как не сильно вдался в подробности
                //как применить Calendar к имеющейся дате.
                for (int i = Calendar.getInstance().get(Calendar.MONTH);
                     i <= maximumDate.getMonth(); i++) {
                    List<Episode> tmp = new ArrayList<>();

                    for (Episode ep : episodes) {
                        if (today.compareTo(ep.getAirDate()) <= 0 &&
                                ep.getAirDate().getMonth() == i) {
                            tmp.add(ep);
                        }
                    }
                    if (tmp.isEmpty()) continue;
                    months.add(new Month(monthTitle[i], tmp));
                }

                Log.d("LOADERS", "Load finished on " + SoonEpisodesFragment.class.toString());
                progressLoad.setVisibility(View.GONE);
                initRecycler();
            }

            @Override
            public void onLoaderReset(Loader<BaseAnswer> loader) {

            }
        };
        getLoaderManager().restartLoader(0, null, loaderCallbacks);
    }
}
