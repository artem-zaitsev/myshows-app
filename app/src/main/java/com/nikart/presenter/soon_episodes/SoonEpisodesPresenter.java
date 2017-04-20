package com.nikart.presenter.soon_episodes;

import android.support.v4.app.Fragment;

import com.nikart.data.dto.Episode;
import com.nikart.model.Model;
import com.nikart.model.api.ApiModel;
import com.nikart.myshows.R;
import com.nikart.presenter.BasePresenter;
import com.nikart.screens.IView;
import com.nikart.screens.soon_episodes.Month;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Artem on 16.04.2017.
 * Presenter for SoonEpisodesFragment.
 */

public class SoonEpisodesPresenter extends BasePresenter {

    private Model apiModel;
    private IView view;

    public SoonEpisodesPresenter(IView view) {
        this.view = view;
        apiModel = new ApiModel();
    }

    @Override
    public void loadData() {
        Observable<List<List<Episode>>> soonEpisodesObservable = apiModel.getNextEpisodes();
        Disposable subscription = soonEpisodesObservable
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
