package com.nikart.presenter.login;

import android.util.Log;
import android.widget.Toast;

import com.nikart.model.Model;
import com.nikart.model.api.ApiModel;
import com.nikart.myshows.R;
import com.nikart.presenter.Presenter;
import com.nikart.screens.auth.signin.LoginActivity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Artem on 18.04.2017.
 * Presenter for LoginActivity.
 */

public class LoginPresenter implements Presenter {

    private Disposable disposable = Disposables.empty();
    private Model model;
    private LoginActivity view;

    public LoginPresenter(LoginActivity view) {
        this.model = new ApiModel();
        this.view = view;
    }

    public void signIn(String login, String password) {
        Observable<Boolean> authObservable = Observable.empty();
        if (!login.equals("")
                && !password.equals("")) {
           authObservable = model.signIn(login, password);
        } else {
            Toast.makeText(view, view.getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        }
        authObservable
                .subscribe(
                        isSuccessful -> view.startActivityIfSignedIn(isSuccessful),
                        e -> Log.d("RX_AUTH", e.toString()),
                        () -> Log.d("RX_AUTH", "Complete authorization")
                );
    }
    @Override
    public void onStop() {
        disposable.dispose();
    }
}
