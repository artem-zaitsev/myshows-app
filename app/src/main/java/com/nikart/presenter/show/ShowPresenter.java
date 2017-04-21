package com.nikart.presenter.show;

import android.util.Log;

import com.nikart.data.HelperFactory;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.show.ShowActivity;
import com.nikart.util.PreferencesWorker;

import java.sql.SQLException;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Artem on 18.04.2017.
 */

public class ShowPresenter extends BasePresenter {

    private IView view;
    private Disposable disposable = Disposables.empty();
    int id;

    public ShowPresenter(ShowActivity view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        if (!PreferencesWorker.getInstance().isSignedIn()) signIn();
        id = ((ShowActivity) view).getShowId();
        disposable = model.getShowById(id)
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
