package com.nikart.screens.shows;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.myshows.R;
import com.nikart.util.JsonParser;
import com.nikart.util.LayoutSwitcherDialog;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Fragment class for MyShows fragment.
 * There is information about user's shows.
 */
public class MyShowsFragment extends Fragment
        implements LayoutSwitcherDialog.LayoutSwitcherDialogListener {

    static public boolean IS_GRID; // для смены layout'ов в адаптере

    private RecyclerView recyclerView;
    private ShowsAdapter showsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ViewGroup container;
    private FrameLayout progressLoadFrame;
    private List<Show> shows;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.container = container;
        View rootView = inflater.inflate(R.layout.fragment_my_shows, container, false);
        initFragment(rootView);
        initRecycler();
        loadData();

        //Тест rxJava
        Observable<UserProfile> profileObservable = App.getInstance().getApi().getAnyUserProfile("RetAm");
        profileObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        v -> Log.d("RX", v.getLogin().toString()),
                        e -> Log.d("RX", "Exception: " + e.toString()),
                        () -> Log.d("RX", "Complete ")
                );
        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.switch_layout_style: {
                LayoutSwitcherDialog switcherDialog = new LayoutSwitcherDialog();
                switcherDialog.setListener(this);
                switcherDialog.show(getFragmentManager(), "SWITCHER");
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
        );
    }

    public boolean isGridLayoutManager() {
        return layoutManager.getClass().equals(GridLayoutManager.class);
    }

    private void initFragment(View rootView) {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(getString(R.string.my_shows));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }

        progressLoadFrame = (FrameLayout) rootView.findViewById(R.id.fragment_my_shows_progress);
        progressLoadFrame.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_my_show_recycler_view);
    }

    private void initRecycler() {
        shows = new ArrayList<>();
        layoutManager = new GridLayoutManager(container.getContext(), 2); // two columns

        IS_GRID = true;

        showsAdapter = new ShowsAdapter(shows);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }

    private void loadData() {
        Observable<ResponseBody> showListObservable = App.getInstance().getApi().getShows();
        showListObservable
                .map(
                        responseBody -> {
                            JsonParser<Show> parser = new JsonParser<>(responseBody);
                            List<Show> shows = null;
                            try {
                                shows = parser.getParsedList(Show.class);
                                if (shows != null) {
                                    HelperFactory.getHelper().getShowDAO().createInDataBase(shows);
                                } else {
                                    shows = HelperFactory.getHelper().getShowDAO().getAllShows();
                                }
                            } catch (IOException | JSONException | SQLException e) {
                                e.printStackTrace();
                            }
                            return shows;
                        }
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sh -> {
                            if (sh != null && !sh.isEmpty()) {
                                // не shows.addAll т.к. нужно не всегда добавлять новые в ресайклер
                                // а addAll вроде как просто добавляет коллекцию.
                                for (Show s : sh) {
                                    if (shows.size() < sh.size()) {
                                        shows.add(sh.indexOf(s), s);
                                    } else {
                                        shows.set(sh.indexOf(s), s);
                                    }
                                    showsAdapter.notifyDataSetChanged();
                                }
                                progressLoadFrame.setVisibility(View.GONE);
                                Log.d("LOADERS", "Load finished. Shows count: " + sh.size() + " " + sh.size());
                            } else {
                                Toast.makeText(MyShowsFragment.this.getContext(), "Troubles!", Toast.LENGTH_SHORT).show();
                            }
                        },
                        Throwable::printStackTrace,
                        () -> Log.d("RX_SHOW_FRAGMENT", "Complete load show list")
                );
    }

    @Override
    public void OnItemClickListener(int i) {
        layoutManager = (i == 1)
                ? new LinearLayoutManager(container.getContext())
                : new GridLayoutManager(container.getContext(), 2);
        IS_GRID = isGridLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);

        /*item.setIcon(!isGridLayoutManager()
                ? R.drawable.grid_layout_manager
                : R.drawable.linear_layout_manager);*/
    }
}
