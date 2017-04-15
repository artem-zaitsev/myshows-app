package com.nikart.presenter;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.model.Model;
import com.nikart.model.ModelImp;
import com.nikart.screens.account.AccountFragment;
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
        model = new ModelImp();
    }

    public void loadData() {
        Observable<UserProfile> userProfileObservable = model.getUserInfo();
        userProfileObservable
                .subscribe(
                        user -> {
                            if (user != null) {
                                view.loadUserInfo(user);
                            } else {
                                Toast.makeText(view.getContext(), "Sorry. There are some problems.", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        },
                        Throwable::toString,
                        () -> Log.d("RX_ACCOUNT", "Completed")
                );

        Observable<List<Show>> showListObservable = model.getShows();
        disposable = showListObservable
                .subscribe(
                        view::initRecycler,
                        e -> {
                            Log.d("RX_ACCOUNT", e.toString());
                            view.initRecycler(HelperFactory.getHelper().getShowDAO().getAllShows());
                        },
                        () -> Log.d("RX_ACCOUNT", "Complete load show list")
                );
    }

    public void rateUpdate(int showId, int rate) {
        Observable<Response<ResponseBody>> rateUpdateObservable =
                App.getInstance().getApi().updateShowRate(showId, rate);
        rateUpdateObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(
                        Response::isSuccessful
                )
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
