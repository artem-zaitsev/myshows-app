package com.nikart.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikart.dto.Episode;
import com.nikart.myshows.R;
import com.nikart.util.EpisodesAdapter;
import com.nikart.util.EpisodesInMonthAdapter;
import com.nikart.util.Month;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Фрагмент для отображения списка серий
 */

public class MyEpisodesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private EpisodesInMonthAdapter monthAdapter;
    private Toolbar toolbar;

    private String EPISODES_FRAGMENT_TITLE;

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

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(EPISODES_FRAGMENT_TITLE);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_myepisodes_rv);
        monthAdapter = new EpisodesInMonthAdapter(makeGroup());
        manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(monthAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //Здесь генерируем список эпизодов,
    //ищем максимальную дату, формируем группы по месяцам от текущего до
    //максимального.
    private List<Month> makeGroup() {

        final List<Month> months = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String[] monthTitle = getResources().getStringArray(R.array.months);
                Date today = new Date();
                Date maximumDate = new Date(121212);

                List<Episode> episodes = new ArrayList<>();
                for (int i = 0; i < 40; i++) {
                    episodes.add(i, new Episode(i));
                    maximumDate = (today.compareTo(episodes.get(i).getAirDate()) < 0)
                            ? episodes.get(i).getAirDate()
                            : today;
                    Log.d("TAG", "Ep date: " + episodes.get(i).getAirDate().getMonth());
                    Log.d("TAG", String.valueOf(maximumDate.getMonth()));
                }

                //Использую устаревший метод, так как не сильно вдался в подробности
                //как применить Calendar к имеющейся дате.
                for (int i = Calendar.getInstance().get(Calendar.MONTH); i <= maximumDate.getMonth(); i++) {
                    List<Episode> tmp = new ArrayList<>();
                    for (Episode ep : episodes) {
                        if (today.compareTo(ep.getAirDate()) <= 0 && ep.getAirDate().getMonth() == i) {
                            tmp.add(ep);
                        }
                    }
                    if (tmp.isEmpty()) continue;
                    months.add(new Month(monthTitle[i], tmp));
                }
            }
        }).run();

        return months;
    }
}
