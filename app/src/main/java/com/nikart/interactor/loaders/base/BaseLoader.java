package com.nikart.interactor.loaders.base;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Artem on 09.03.2017.
 */

public abstract class BaseLoader<T> extends AsyncTaskLoader<T> {

    protected T data;

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (data != null) {
            deliverResult(data);
        }
        forceLoad();
    }

    @Override
    public void deliverResult(T data) {
        this.data = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
    }
}
