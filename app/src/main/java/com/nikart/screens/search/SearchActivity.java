package com.nikart.screens.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.nikart.data.HelperFactory;
import com.nikart.model.dto.Show;
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

    private SearchView searchView;
    private RecyclerView resultRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private List<ShowTmp> list;

    @Inject
    public SearchPresenter presenter;

   public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
        context.startActivity(starter);
    }

    @Override
    public <T> void showData(T data) {
        updateRecycler((List<ShowTmp>)data);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    protected void injectPresenter() {
        DaggerPresenterComponent.create()
                .inject(this);
        presenter.onCreate(this);
        setPresenter(presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initActivity() {
        searchView = (SearchView) findViewById(R.id.activity_search_input);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().loadData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.setSearchQuery(newText);
                return true;
            }
        });
        resultRecycler = (RecyclerView) findViewById(R.id.activity_search_result_rv);
        initRecycler();
    }

    private void initRecycler() {
        list = new ArrayList<>();
        SearchShowAdapter adapter = new SearchShowAdapter(list);
        layoutManager = new LinearLayoutManager(this);
        resultRecycler.setAdapter(adapter);
        resultRecycler.setLayoutManager(layoutManager);
    }

    private void updateRecycler(List<ShowTmp> list) {
        this.list.clear();
        this.list.addAll(list);
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
        ;
    }
}