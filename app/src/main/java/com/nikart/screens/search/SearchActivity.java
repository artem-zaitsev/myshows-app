package com.nikart.screens.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.nikart.database.HelperFactory;
import com.nikart.model.dto.ShowTmp;
import com.nikart.myshows.R;
import com.nikart.presenter.dagger2.DaggerPresenterComponent;
import com.nikart.presenter.search.SearchPresenter;
import com.nikart.screens.BaseActivity;
import com.nikart.screens.search.adapter.SearchShowAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Artem on 29.04.2017.
 */

public class SearchActivity extends BaseActivity {

    public static final String ACTION_QUERY = "QUERY";

    @Inject
    public SearchPresenter presenter;
    private SearchView searchView;
    private RecyclerView resultRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private SearchShowAdapter adapter;
    private List<ShowTmp> list;

    public static void start(Context context, String query) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra(ACTION_QUERY, query);
        context.startActivity(starter);
    }

    @Override
    public <T> void showData(T data) {
        updateRecycler((List<ShowTmp>) data);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    protected void injectPresenter() {
        DaggerPresenterComponent.create()
                .inject(this);
        presenter.onCreate(this);

        Intent intent = getIntent();
        presenter.setSearchQuery(intent.getStringExtra(ACTION_QUERY));
        setPresenter(presenter);
        getPresenter().loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initActivity() {
        searchView = (SearchView) findViewById(R.id.activity_search_input);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().loadData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.setSearchQuery(newText);
                return false;
            }
        });
        resultRecycler = (RecyclerView) findViewById(R.id.activity_search_result_rv);
        initRecycler();
    }

    private void initRecycler() {
        list = new ArrayList<>();
        adapter = new SearchShowAdapter(list);
        layoutManager = new LinearLayoutManager(this);
        resultRecycler.setAdapter(adapter);
        resultRecycler.setLayoutManager(layoutManager);
    }

    private void updateRecycler(List<ShowTmp> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //позже перенесу в презентер и в поток другой
        try {
            HelperFactory.getHelper().getShowTmpDAO().deleteAll();
        } catch (SQLException e) {
            Log.d("DB", "Delete incomplete");
        }
    }
}
