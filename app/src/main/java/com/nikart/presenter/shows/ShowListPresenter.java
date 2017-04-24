package com.nikart.presenter.shows;

import android.util.Log;

import com.nikart.data.HelperFactory;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.util.PreferencesWorker;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 18.04.2017.
 */

public class ShowListPresenter extends BasePresenter {

    private IView view;

    @Override
    public void onCreate(IView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        if (!PreferencesWorker.getInstance().isSignedIn()) signIn();
        Disposable disposable = model.getShows()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorResumeNext(throwable -> {
                    Log.d("RX_SHOW_LIST", throwable.toString());
                    return Observable.fromArray(HelperFactory.getHelper().getShowDAO().getAllShows());
                })
                .observeOn(AndroidSchedulers.mainThread())
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
