package com.nikart.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nikart.presenter.Presenter;

/**
 * Created by Artem on 18.04.2017.
 * Base Activity class.
 */

public abstract class BaseActivity extends AppCompatActivity implements IView {

    protected Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        initActivity();
    }

    protected abstract void setLayout();
    protected abstract void initActivity();

    protected void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    protected Presenter getPresenter() {
        return  presenter;
    }
}
