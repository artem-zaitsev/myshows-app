package com.nikart.presenter.search;

import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;

/**
 * Created by Artem on 29.04.2017.
 */

public class SearchPresenter extends BasePresenter {

    private IView view;

    @Override
    public void onCreate(IView view) {
        this.view = view;
    }

    @Override
    public void loadData() {

    }
}
