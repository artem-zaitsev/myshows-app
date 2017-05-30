package com.nikart.screens;


/**
 * Created by Artem on 18.04.2017.
 */

public interface IView {

    <T> void showData(T data);

    void showError(Throwable t);

}
