package com.nikart.screens.shows;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

import com.nikart.data.ShowFromDataBaseLoader;
import com.nikart.data.dto.Show;
import com.nikart.myshows.R;
import com.nikart.data.HelperFactory;

import java.sql.SQLException;
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
    private ProgressBar progressLoad;

    private LoaderManager.LoaderCallbacks loaderCallbacks =
            new LoaderManager.LoaderCallbacks<List<Show>>() {
                @Override
                public Loader<List<Show>> onCreateLoader(int id, Bundle args) {
                    return new ShowFromDataBaseLoader(MyShowsFragment.this.getContext());
                }

                @Override
                public void onLoadFinished(Loader<List<Show>> loader, List<Show> data) {
                    for (Show s: data) {
                        shows.add(data.indexOf(s),s);
                        showsAdapter.notifyDataSetChanged();
                    }

                    progressLoad.setVisibility(View.GONE);

                    Log.d("LOADERS", "Load finished");
                }

                @Override
                public void onLoaderReset(Loader<List<Show>> loader) {
                    //reset
                }

            };


    private String SHOWS_FRAGMENT_TITLE;

    @Override
    public void onAttach(Context context) {
        SHOWS_FRAGMENT_TITLE = getString(R.string.my_shows);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.container = container;
        View rootView = inflater.inflate(R.layout.fragment_my_shows, container, false);

        initFragment(rootView);

        initRecycler(rootView);
        getLoaderManager().restartLoader(0, null, loaderCallbacks);
        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.switch_layout_style: {
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

        menu.getItem(0).setIcon(
                !isGridLayoutManager()
                        ? R.drawable.grid_layout_manager
                        : R.drawable.linear_layout_manager
        );   // ВНИМАНИЕ!! Перед условием стоит !
    }

    public boolean isGridLayoutManager() {
        return layoutManager.getClass().equals(GridLayoutManager.class);
    }

    private void initFragment(View rootView) {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(SHOWS_FRAGMENT_TITLE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }

        progressLoad = (ProgressBar) rootView.findViewById(R.id.fragment_my_shows_progress);
        progressLoad.setVisibility(View.VISIBLE);
    }

    private LoaderManager.LoaderCallbacks<List<Show>> loadShow() {
        return new LoaderManager.LoaderCallbacks<List<Show>>() {
            @Override
            public Loader<List<Show>> onCreateLoader(int id, Bundle args) {
                return new ShowFromDataBaseLoader(MyShowsFragment.this.getContext());
            }

            @Override
            public void onLoadFinished(Loader<List<Show>> loader, List<Show> data) {
                shows = data;
                showsAdapter.notifyDataSetChanged();
                Log.d("LOADERS", "Load finished");
            }

            @Override
            public void onLoaderReset(Loader<List<Show>> loader) {
                //reset
            }

        };
    }

    private void initRecycler(View rootView) {
        shows = new ArrayList<>(10);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_my_show_recycler_view);
        layoutManager = new GridLayoutManager(container.getContext(), 2); // two columns

        IS_GRID = true;

        showsAdapter = new ShowsAdapter(shows);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }
}
