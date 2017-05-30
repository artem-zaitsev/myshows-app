package com.nikart.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikart.presenter.Presenter;

/**
 * Created by Artem on 18.04.2017.
 */

public abstract class BaseFragment extends Fragment implements IView {

    protected Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initFragment(rootView);
        setHasOptionsMenu(true);
        injectPresenter();
        return rootView;
    }

    protected abstract void injectPresenter();

    protected abstract void initFragment(View rootView);

    public abstract int getLayoutId(); // просто возвращяет лэйаут

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) presenter.onStop();
        presenter = null;
    }
}
