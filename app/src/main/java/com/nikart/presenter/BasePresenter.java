package com.nikart.presenter;

import com.nikart.model.Model;
import com.nikart.model.ModelComponent;
import com.nikart.model.ModelModule;
import com.nikart.model.api.ApiModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Artem on 18.04.2017.
 * Base Presenter class.
 */

public abstract class BasePresenter implements Presenter {

    CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    protected ApiModel model;

    public BasePresenter() {
        ApiModel.getComponent().inject(this);
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onStop() {
        disposables.dispose();
    }
}
