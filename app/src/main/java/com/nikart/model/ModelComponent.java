package com.nikart.model;

import com.nikart.interactor.ApiRepository;
import com.nikart.model.api.ApiModel;
import com.nikart.presenter.BasePresenter;

import dagger.Component;

/**
 * Created by Artem on 20.04.2017.
 */

@Component(modules = {ModelModule.class})
public interface ModelComponent {

    ApiRepository getApiRepository();

    ApiModel getApiModel();

    void inject(BasePresenter presenter);
}
