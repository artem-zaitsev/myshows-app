package com.nikart.presenter.shows;

import android.util.Log;
import android.view.View;

import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.presenter.BasePresenter;
import com.nikart.presenter.Presenter;
import com.nikart.screens.shows.MyShowsFragment;
import com.nikart.util.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Artem on 18.04.2017.
 */

public class ShowListPresenter extends BasePresenter {

    private MyShowsFragment view;
    private Disposable disposable = Disposables.empty();

    public ShowListPresenter(MyShowsFragment view) {
        super();
        this.view = view;
    }

    @Override
    public void loadData() {
        disposable = model.getShows()
                .onErrorResumeNext(throwable -> {
                    Log.d("RX_SHOW_LIST", throwable.toString());
                    try {
                        view.loadContentFromDb();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                })
                .subscribe(
                        shows -> {
                            view.updateRecycler(shows);
                            Log.d("RX_SHOW_LIST", "Load finished. Shows count: " + shows.size() + " " + shows.size());
                        },
                        e -> {
                            view.showErrorSnackbar(e);
                            Log.d("RX_SHOW_LIST", e.toString());
                        },
                        () -> Log.d("RX_SHOW_FRAGMENT", "Complete load show list")
                );
        //addDisposable(disposable);
    }
}
