package com.nikart.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikart.myshows.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment class for MyShows fragment.
 * There is information about user's shows.
 */
public class MyShowsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ShowsAdapter showsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Show> shows;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        shows = new ArrayList<>(30);
        for(int i = 0; i< 30; i++) {
            shows.add(i,new Show());
        }
        View rootView = inflater.inflate(R.layout.fragment_my_shows,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_my_show_recycler_view);
        showsAdapter = new ShowsAdapter(shows);
        layoutManager = new GridLayoutManager(container.getContext(),2); // two columns

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
        return rootView;
    }
}
