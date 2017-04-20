package com.nikart.presenter;

import com.nikart.model.Model;
import com.nikart.model.api.ApiModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Artem on 18.04.2017.
 */

public abstract class BasePresenter implements Presenter {

    CompositeDisposable disposables = new CompositeDisposable();
    protected Model model;

    public BasePresenter() {
        this.model = new ApiModel();
    }

    protected void addDisposable(Disposable disposable){
        disposables.add(disposable);
    }

    @Override
    public void onStop() {
        disposables.dispose();
    }
}
