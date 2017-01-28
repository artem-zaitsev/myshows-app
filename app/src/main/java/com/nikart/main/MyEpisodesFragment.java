package com.nikart.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nikart.myshows.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Фрагмент для отображения списка серий
 */

public class MyEpisodesFragment extends Fragment {

    private RecyclerView recyclerView;
    private EpisodesAdapter adapter;
    private RecyclerView.LayoutManager manager;


    // Приделан RecyclerView.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_episodes, container, false);


        List<Episode> episodes = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            episodes.add(i, new Episode());
        }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_myepisodes_rv);
        adapter = new EpisodesAdapter(episodes);
        manager = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(manager);
        DividerItemDecoration divider =
                new DividerItemDecoration(recyclerView.getContext(),manager.getLayoutDirection());
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
