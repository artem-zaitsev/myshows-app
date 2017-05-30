package com.nikart.presenter.soon_episodes;

import android.support.v4.app.Fragment;

import com.nikart.model.dto.Episode;
import com.nikart.myshows.R;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.soon_episodes.Month;
import com.nikart.util.PreferencesWorker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Artem on 16.04.2017.
 * Presenter for SoonEpisodesFragment.
 */

public class SoonEpisodesPresenter extends BasePresenter {

    private IView view;

    @Override
    public void onCreate(IView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        if (!PreferencesWorker.getInstance().isSignedIn()) signIn();
        Observable<List<List<Episode>>> soonEpisodesObservable = model.getNextEpisodes();
        Disposable subscription = soonEpisodesObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::groupEpisodes,
                        e -> view.showError(e));
        addDisposable(subscription);
    }

    private void groupEpisodes(List<List<Episode>> groups) {
        final String[] monthTitle = ((Fragment) view).getResources().getStringArray(R.array.months);
        List<Month> months = new ArrayList<>();

        for (List<Episode> tmp : groups) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(tmp.get(0).getAirDate());
            months.add(new Month(monthTitle[cal.get(Calendar.MONTH)], tmp));
        }

        view.showData(months);
    }
}
