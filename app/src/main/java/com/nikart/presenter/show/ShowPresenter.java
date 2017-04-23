package com.nikart.presenter.show;

import android.util.Log;

import com.nikart.data.HelperFactory;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.show.ShowActivity;
import com.nikart.util.PreferencesWorker;

import java.sql.SQLException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 18.04.2017.
 */

public class ShowPresenter extends BasePresenter {

    int id;
    private IView view;
    private Disposable disposable = Disposables.empty();

    @Override
    public void onCreate(IView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        if (!PreferencesWorker.getInstance().isSignedIn()) signIn();
        id = ((ShowActivity) view).getShowId();
        disposable = model.getShowById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                            Log.d("RX_SHOW_BY_ID", throwable.toString());
                            try {
                                view.showData(HelperFactory.getHelper().getShowDAO().queryForId(id));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                )
                .subscribe(show -> {
                            Log.d("RX_SHOW_BY_ID", "Finished load show on ShowActivity." + show.getTitle());
                            view.showData(show);
                        },
                        e -> Log.d("RX_SHOW_BY_ID", e.toString()),
                        () -> Log.d("RX_SHOW_BY_ID", "Complete")
                );
        addDisposable(disposable);
    }
}
