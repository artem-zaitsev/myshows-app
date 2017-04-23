package com.nikart.presenter;

import com.nikart.screens.IView;

/**
 * Created by Artem on 15.04.2017.
 * Presenter interface
 */

public interface Presenter {

    void onCreate(IView view);

    void loadData();

    void onStop();

}
