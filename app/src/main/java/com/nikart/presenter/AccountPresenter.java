package com.nikart.presenter;

import android.util.Log;
import android.widget.Toast;

import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.model.ApiModel;
import com.nikart.model.Model;
import com.nikart.screens.account.AccountFragment;

import java.sql.SQLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Artem on 15.04.2017.
 * Presenter for AccountFragment
 */

public class AccountPresenter implements Presenter {

    private AccountFragment view;
    private Disposable disposable = Disposables.empty();
    private Model model;

    public AccountPresenter(AccountFragment view) {
        this.view = view;
        model = new ApiModel();
    }

    public void loadData() {
        Observable<UserProfile> userProfileObservable = model.getUserInfo();
        userProfileObservable
                .subscribe(
                        user -> view.loadUserInfo(user),
                        view::showErrorSnackbar,
                        () -> Log.d("RX_ACCOUNT", "Completed")
                );

        Observable<List<Show>> showListObservable = model.getShows();
        disposable = showListObservable
                .onErrorResumeNext(
                        error -> {
                            Log.d("RX_ACCOUNT", error.toString());
                            try {
                                view.initRecycler(HelperFactory.getHelper().getShowDAO().getAllShows());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                )
                .subscribe(
                        view::initRecycler,
                        e -> Log.d("RX_ACCOUNT", e.toString()),
                        () -> Log.d("RX_ACCOUNT", "Complete load show list")
                );
    }

    public void rateUpdate(int showId, int rate) {
        Observable<Response<ResponseBody>> rateUpdateObservable =
                model.updateRateShow(showId, rate);
        rateUpdateObservable
                .map(Response::isSuccessful)
                .subscribe(
                        is -> Log.d("RX_RATE_UPDATE", is.toString()),
                        e -> Log.d("RX_RATE_UPDATE", e.toString()),
                        () -> Log.d("RX_RATE_UPDATE", "Complete")
                );
    }

    @Override
    public void onStop() {
        disposable.dispose();
    }
}
