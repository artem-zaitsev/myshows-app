package com.nikart.presenter.shows;

import android.util.Log;

import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.shows.MyShowsFragment;

import java.sql.SQLException;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Artem on 18.04.2017.
 */

public class ShowListPresenter extends BasePresenter {

    private IView view;

    public ShowListPresenter(MyShowsFragment view) {
        super();
        this.view = view;
    }

    @Override
    public void loadData() {
        Disposable disposable = model.getShows()
                .onErrorResumeNext(throwable -> {
                    Log.d("RX_SHOW_LIST", throwable.toString());
                    view.showData(null);
                })
                .subscribe(
                        shows -> {
                            view.showData(shows);
                            Log.d("RX_SHOW_LIST", "Load finished. Shows count: " + shows.size() + " " + shows.size());
                        },
                        e -> {
                            view.showError(e);
                            Log.d("RX_SHOW_LIST", e.toString());
                        },
                        () -> Log.d("RX_SHOW_FRAGMENT", "Complete load show list")
                );
        addDisposable(disposable);
    }
}
