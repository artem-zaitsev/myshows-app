package com.nikart.screens.shows;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nikart.database.HelperFactory;
import com.nikart.model.dto.Show;
import com.nikart.myshows.R;
import com.nikart.presenter.dagger2.DaggerPresenterComponent;
import com.nikart.presenter.shows.ShowListPresenter;
import com.nikart.screens.BaseFragment;
import com.nikart.screens.search.SearchActivity;
import com.nikart.screens.util.LayoutSwitcherDialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Fragment class for MyShows fragment.
 * There is information about user's shows.
 */
public class MyShowsFragment extends BaseFragment
        implements LayoutSwitcherDialog.LayoutSwitcherDialogListener {

    static public boolean IS_GRID; // для смены layout'ов в адаптере
    @Inject
    public ShowListPresenter presenter;
    private RecyclerView recyclerView;
    private ShowsAdapter showsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FrameLayout progressLoadFrame;
    private List<Show> shows;

    @Override
    public void onStart() {
        super.onStart();
        setPresenter(presenter);
        getPresenter().loadData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_search: {

                return true;
            }
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
        SearchView searchView = (SearchView) menu.findItem(R.id.show_search).getActionView();
        searchView.setQueryHint("Search shows..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.start(MyShowsFragment.this.getContext(), query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public boolean isGridLayoutManager() {
        return layoutManager.getClass().equals(GridLayoutManager.class);
    }

    @Override
    protected void injectPresenter() {
        DaggerPresenterComponent.create().inject(this);
        presenter.onCreate(this);
    }

    @Override
    protected void initFragment(View rootView) {
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
        initRecycler();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_shows;
    }

    private void initRecycler() {
        shows = new ArrayList<>();
        layoutManager = new GridLayoutManager(getContext(), 2); // two columns

        IS_GRID = true;

        showsAdapter = new ShowsAdapter(shows);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }

    public void loadContentFromDb() throws SQLException {
        updateRecycler(HelperFactory.getHelper().getShowDAO().getAllShows());
    }

    public void updateRecycler(List<Show> shws) {
        shows.clear();
        shows.addAll(shws);
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
        Log.d("RX_ACCOUNT", e.toString());
    }

    @Override
    public void OnItemClickListener(int i) {
        layoutManager = (i == 1)
                ? new LinearLayoutManager(getContext())
                : new GridLayoutManager(getContext(), 2);
        IS_GRID = isGridLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }

    @Override
    public <T> void showData(T data) {
        if (data != null) {
            updateRecycler((List<Show>) data);
        } else try {
            loadContentFromDb();
        } catch (SQLException e) {
            Log.d("RX_SHOWS_LIST", "Can not load from database. " + e.toString());
        }

    }

    @Override
    public void showError(Throwable t) {
        showErrorSnackbar(t);
    }
}
