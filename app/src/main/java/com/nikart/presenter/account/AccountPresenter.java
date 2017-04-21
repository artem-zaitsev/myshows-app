package com.nikart.presenter.account;

import android.util.Log;

import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.model.Model;
import com.nikart.model.api.ApiModel;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.account.AccountFragment;
import com.nikart.util.PreferencesWorker;

import java.sql.SQLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Artem on 15.04.2017.
 * Presenter for AccountFragment
 */

public class AccountPresenter extends BasePresenter {

    private IView view;

    public AccountPresenter(AccountFragment view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        if (!PreferencesWorker.getInstance().isSignedIn()) signIn();
        Observable<UserProfile> userProfileObservable = model.getUserInfo();
        userProfileObservable
                .subscribe(
                        user -> view.showData(user),
                        view::showError,
                        () -> Log.d("RX_ACCOUNT", "Completed")
                );

        Observable<List<Show>> showListObservable = model.getShows();
        Disposable showListDisposable = showListObservable
                .onErrorResumeNext(
                        error -> {
                            Log.d("RX_ACCOUNT", error.toString());
                            try {
                                view.showData(HelperFactory.getHelper().getShowDAO().getAllShows());
                            } catch (SQLException e) {
                                Log.d("RX_ACCOUNT", "Error load from db. " + e.toString());
                            }
                        }
                )
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
                .subscribe(
                        is -> Log.d("RX_RATE_UPDATE", is.toString()),
                        e -> Log.d("RX_RATE_UPDATE", e.toString()),
                        () -> Log.d("RX_RATE_UPDATE", "Complete")
                );
        addDisposable(rateDisposable);
    }
}
