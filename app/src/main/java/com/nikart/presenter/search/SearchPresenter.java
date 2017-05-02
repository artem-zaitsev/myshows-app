package com.nikart.presenter.search;

import android.util.Log;

import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 29.04.2017.
 */

public class SearchPresenter extends BasePresenter {

    private IView view;
    private String query;

    @Override
    public void onCreate(IView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        Disposable disposable = model.findShowByName(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shows -> {
                            Log.d("SEARCH_SHOWS", shows.toString());
                            view.showData(shows);
                        },
                        error -> Log.d("SEARCH_SHOWS", error.toString() + " " + error.getMessage()),
                        () -> Log.d("SEARCH_SHOWS", "Complete!"));
        addDisposable(disposable);
    }

    public void setSearchQuery(String query) {
        this.query = query;
    }
}
