package com.nikart.screens;

/**
 * Created by Artem on 18.04.2017.
 */

public interface IView {

    public <T> void showData(T data);
    public void showError(Throwable t);

}
