package com.nikart.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private EpisodesAdapter adapter;
    private RecyclerView.LayoutManager manager;

    private EpisodesInMonthAdapter monthAdapter;

    private Toolbar toolbar;

    private final String[] monthTitle = new String[]{
            "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    };

    // Приделан ExpandedRecyclerView!!!!!!
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_episodes, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.activity_main_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_myepisodes_rv);
/*        adapter = new EpisodesAdapter(episodes);*/
        monthAdapter = new EpisodesInMonthAdapter(makeGroup());
        manager = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(monthAdapter);

        return rootView;
    }

    //Пока так. Здесь генерируем список эпизодов,
    //ищем максимальную дату, формируем группы по месяцам от текущего до
    //максимального.
    private List<Month> makeGroup() {
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

        // SimpleDateFormat format = new SimpleDateFormat("MMMM");

        List<Month> months = new ArrayList<>();
        //Использую устаревший метод, так как не сильно вдался в подробности
        //как применить Calendar к имеющейся дате.
        for (int i = Calendar.getInstance().get(Calendar.MONTH); i <= maximumDate.getMonth(); i++) {
            List<Episode> tmp = new ArrayList<>();
            for (Episode ep : episodes) {
                if (today.compareTo(ep.getAirDate()) <= 0 && ep.getAirDate().getMonth() == i) {
                    tmp.add(ep);
                }
            }
            months.add(new Month(monthTitle[i], tmp));
        }
        return months;
    }
}
