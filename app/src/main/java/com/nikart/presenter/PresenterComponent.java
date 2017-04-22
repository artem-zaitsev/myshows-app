package com.nikart.presenter;

import com.nikart.model.Model;
import com.nikart.model.ModelModule;

import dagger.Component;

/**
 * Created by Artem on 22.04.2017.
 */

@Component(modules = {ModelModule.class})
public interface PresenterComponent {

    Model getModel();

}
