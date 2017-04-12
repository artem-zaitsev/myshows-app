package com.nikart.screens.soon_episodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Episode;
import com.nikart.myshows.R;
import com.nikart.util.JsonParser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Фрагмент для отображения списка серий
 */

public class SoonEpisodesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private EpisodesInMonthAdapter monthAdapter;
    private FrameLayout progressLoadFrame;
    private Observable<List<Episode>> soonEpisodesObservable;
    private List<Month> months;

    // Приделан ExpandedRecyclerView!!!!!!
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_soon_episodes, container, false);
        initFragment(rootView);
        loadData();
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        soonEpisodesObservable
                .retry()
                .subscribe(this::doOnSubscribeNext);
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

    private void initFragment(View rootView) {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(getString(R.string.my_episodes));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_myepisodes_rv);
        progressLoadFrame = (FrameLayout) rootView.findViewById(R.id.fragment_episodes_progress);
    }

    private void initRecycler() {
        monthAdapter = new EpisodesInMonthAdapter(months);
        manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(monthAdapter);
    }

    private void loadData() {
        Observable<ResponseBody> responseBodyObservable =
                App.getInstance().getApi().getNextEpisodes();
        soonEpisodesObservable = responseBodyObservable
                .map(
                        body -> {
                            JsonParser<Episode> parser = new JsonParser<>(body);
                            List<Episode> episodes = parser.getParsedList(Episode.class);
                            HelperFactory.getHelper().getEpisodeDAO().createInDataBase(episodes);
                            return episodes;
                        }
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        soonEpisodesObservable
                .subscribe(
                        this::doOnSubscribeNext,
                        e -> {
                            Snackbar.make(getActivity()
                                            .findViewById(R.id.main_activity_layout),
                                    "No Internet connection",
                                    Snackbar.LENGTH_INDEFINITE)
                                    .setAction("ON", view ->
                                            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)))
                                    .show();
                            Log.d("RX_SOON_EPS", e.toString());
                        },
                        () -> Log.d("RX_SOON_EPS", "Complete load episodes")
                );
    }

    private void doOnSubscribeNext(List<Episode> episodes) {

        final String[] monthTitle = getResources().getStringArray(R.array.months);
        Date today = new Date();
        Date maximumDate = new Date(121212);

        months = new ArrayList<>();

        //TODO: придумать алгоритм, как поэффективней разбивать по месяцам

        for (Episode ep : episodes) {
            maximumDate = (today.compareTo(ep.getAirDate()) < 0)
                    ? ep.getAirDate()
                    : today;
        }

        Calendar maxCalendar = Calendar.getInstance();
        Calendar airCalendar = Calendar.getInstance();
        maxCalendar.setTime(maximumDate);

        for (int i = Calendar.getInstance().get(Calendar.MONTH);
             i <= maxCalendar.get(Calendar.MONTH); i++) {
            List<Episode> tmp = new ArrayList<>();

            for (Episode ep : episodes) {
                airCalendar.setTime(ep.getAirDate());
                if (today.compareTo(ep.getAirDate()) <= 0 &&
                        airCalendar.get(Calendar.MONTH) == i) {
                    tmp.add(ep);
                }
            }
            if (tmp.isEmpty()) continue;
            months.add(new Month(monthTitle[i], tmp));
        }
        progressLoadFrame.setVisibility(View.GONE);
        initRecycler();
    }
}
