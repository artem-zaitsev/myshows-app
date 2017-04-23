package com.nikart.presenter.account;

import android.util.Log;

import com.nikart.data.HelperFactory;
import com.nikart.model.dto.Show;
import com.nikart.model.dto.UserProfile;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.account.AccountFragment;
import com.nikart.util.PreferencesWorker;

import java.sql.SQLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 15.04.2017.
 * Presenter for AccountFragment
 */

public class AccountPresenter extends BasePresenter {

    private IView view;

    @Override
    public void onCreate(IView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        if (!PreferencesWorker.getInstance().isSignedIn()) signIn();

        Observable<UserProfile> userProfileObservable = model.getUserInfo();
        userProfileObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> view.showData(user),
                        view::showError,
                        () -> Log.d("RX_ACCOUNT", "Completed")
                );

        Observable<List<Show>> showListObservable = model.getShows();
        Disposable showListDisposable = showListObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorResumeNext(
                        error -> {
                            Log.d("RX_ACCOUNT1", error.toString());
                            try {
                                view.showData(HelperFactory.getHelper().getShowDAO().getAllShows());
                            } catch (SQLException e) {
                                Log.d("RX_ACCOUNT", "Error load from db. " + e.toString());
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        view::showData,
                        e -> Log.d("RX_ACCOUNT", e.toString()),
                        () -> Log.d("RX_ACCOUNT", "Complete load show list")
                );
        addDisposable(showListDisposable);
    }

    public void rateUpdate(int showId, int rate) {

        Observable<Boolean> rateUpdateObservable = model.updateRateShow(showId, rate);
        Disposable rateDisposable = rateUpdateObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        is -> Log.d("RX_RATE_UPDATE", is.toString()),
                        e -> Log.d("RX_RATE_UPDATE", e.toString()),
                        () -> Log.d("RX_RATE_UPDATE", "Complete")
                );
        addDisposable(rateDisposable);
    }
}
