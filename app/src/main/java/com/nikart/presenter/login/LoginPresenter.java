package com.nikart.presenter.login;

import android.util.Log;
import android.widget.Toast;

import com.nikart.model.Model;
import com.nikart.myshows.R;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.auth.signin.LoginActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 18.04.2017.
 * Presenter for LoginActivity.
 */

public class LoginPresenter extends BasePresenter {

    private Disposable disposable = Disposables.empty();
    private IView view;

    public LoginPresenter(LoginActivity view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        signIn(((LoginActivity) view).getLogin(), ((LoginActivity) view).getPassword());
    }

    public void signIn(String login, String password) {
        if (!login.equals("") && !password.equals("")) {
            disposable = model.signIn(login, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(isSuccessful -> view.showData(isSuccessful),
                            e -> Log.d("RX_AUTH", e.toString()),
                            () -> Log.d("RX_AUTH", "Complete authorization"));
        } else {
            Toast.makeText(((LoginActivity) view).getApplicationContext(),
                    ((LoginActivity) view).getString(R.string.empty_fields), Toast.LENGTH_SHORT)
                    .show();
        }
        addDisposable(disposable);
    }

}
