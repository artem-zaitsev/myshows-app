package com.nikart.presenter.soon_episodes;

import com.nikart.data.dto.Episode;
import com.nikart.model.Model;
import com.nikart.model.api.ApiModel;
import com.nikart.myshows.R;
import com.nikart.presenter.Presenter;
import com.nikart.screens.soon_episodes.Month;
import com.nikart.screens.soon_episodes.SoonEpisodesFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Artem on 16.04.2017.
 * Presenter for SoonEpisodesFragment.
 */

public class SoonEpisodesPresenter implements Presenter {

    private Disposable subscription = Disposables.empty();
    private Model apiModel;
    private SoonEpisodesFragment view;

    public SoonEpisodesPresenter(SoonEpisodesFragment view) {
        this.view = view;
        apiModel = new ApiModel();
    }

    public void loadData() {
        Observable<List<List<Episode>>> soonEpisodesObservable = apiModel.getNextEpisodes();
        subscription = soonEpisodesObservable
                .subscribe(
                        this::groupEpisodes,
                        e -> view.showErrorSnackbar(e)
                );
    }

    private void groupEpisodes(List<List<Episode>> groups) {
        final String[] monthTitle = view.getResources().getStringArray(R.array.months);
        List<Month> months = new ArrayList<>();

        for (List<Episode> tmp : groups) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(tmp.get(0).getAirDate());
            months.add(new Month(monthTitle[cal.get(Calendar.MONTH)], tmp));
        }

        view.initRecycler(months);
    }

    @Override
    public void onStop() {
        subscription.dispose();
    }
}
