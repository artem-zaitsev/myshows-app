package com.nikart.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nikart.presenter.Presenter;

/**
 * Created by Artem on 18.04.2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements IView {

    private Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    protected abstract void initActivity();

    protected Presenter getPresenter() {
        return  presenter;
    }
}
