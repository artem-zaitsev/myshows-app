package com.nikart.presenter.show;

import android.util.Log;

import com.nikart.data.HelperFactory;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.show.ShowActivity;

import java.sql.SQLException;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Artem on 18.04.2017.
 */

public class ShowPresenter extends BasePresenter {

    ShowActivity view;
    private Disposable disposable = Disposables.empty();
    int id;

    @Override
    public void loadData() {
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
                .subscribe(
                        show -> {
                            Log.d("RX_SHOW_BY_ID", "Finished load show on ShowActivity." + show.getTitle());
                            view.showData(show);
                        },
                        e -> Log.d("RX_SHOW_BY_ID", e.toString()),
                        () -> Log.d("RX_SHOW_BY_ID", "Complete")
                );
       // addDisposable(disposable);
    }

    public void getShow(int id) {
        this.id = id;
        loadData();
    }
}
