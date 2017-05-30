package com.nikart.presenter;

import com.nikart.model.Model;
import com.nikart.model.dagger2.DaggerModelComponent;
import com.nikart.util.PreferencesWorker;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Artem on 18.04.2017.
 * Base Presenter class.
 */

public abstract class BasePresenter implements Presenter {

    @Inject
    protected Model model;
    CompositeDisposable disposables = new CompositeDisposable();

    public BasePresenter() {
        DaggerModelComponent.create().inject(this);
    }

    protected void signIn() {
        switch (PreferencesWorker.getInstance().getSignInFlag()) {
            case PreferencesWorker.VK_SIGN_IN: model.signInVk(PreferencesWorker.getInstance().getPassword(),
                    PreferencesWorker.getInstance().getLogin());
                break;
            default:  model.signIn(PreferencesWorker.getInstance().getLogin(),
                    PreferencesWorker.getInstance().getPassword());
        }
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onStop() {
        disposables.dispose();
    }
}
