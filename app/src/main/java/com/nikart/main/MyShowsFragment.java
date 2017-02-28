package com.nikart.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikart.dto.Show;
import com.nikart.myshows.R;
import com.nikart.util.ShowsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment class for MyShows fragment.
 * There is information about user's shows.
 */
public class MyShowsFragment extends Fragment {

    static public boolean IS_GRID; // для смены layout'ов в адаптере

    private RecyclerView recyclerView;
    private ShowsAdapter showsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Show> shows;
    private ViewGroup container;
    private Toolbar toolbar;

    private String SHOWS_FRAGMENT_TITLE;

    @Override
    public void onAttach(Context context) {
        SHOWS_FRAGMENT_TITLE = getString(R.string.my_shows);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {

        this.container = container;
        shows = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            shows.add(i, new Show());
        }
        View rootView = inflater.inflate(R.layout.fragment_my_shows, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(SHOWS_FRAGMENT_TITLE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }
        initRecycler(rootView);

        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.switch_layout_style: {
                // Тут все лаконично, правда не могу объективно оценить насколько читабельно.
                layoutManager = isGridLayoutManager()
                        ? new LinearLayoutManager(container.getContext())
                        : new GridLayoutManager(container.getContext(), 2);
                IS_GRID = isGridLayoutManager();
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(showsAdapter);

                item.setIcon(!isGridLayoutManager()
                        ? R.drawable.grid_layout_manager
                        : R.drawable.linear_layout_manager);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_my_shows, menu);

        //Здесь код пока дублируется.
        menu.getItem(0).setIcon(
                !isGridLayoutManager()
                        ? R.drawable.grid_layout_manager
                        : R.drawable.linear_layout_manager
        );   // ВНИМАНИЕ!! Перед условием стоит !
    }

    public boolean isGridLayoutManager() {
        return layoutManager.getClass().equals(GridLayoutManager.class);
    }

    private void initRecycler(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_my_show_recycler_view);
        layoutManager = new GridLayoutManager(container.getContext(), 2); // two columns

        IS_GRID = true;

        showsAdapter = new ShowsAdapter(shows);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }

}
