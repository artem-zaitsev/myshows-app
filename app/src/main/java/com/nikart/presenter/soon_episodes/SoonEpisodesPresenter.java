package com.nikart.presenter.soon_episodes;

import android.util.Log;

import com.nikart.data.dto.Episode;
import com.nikart.model.api.ApiModel;
import com.nikart.model.Model;
import com.nikart.myshows.R;
import com.nikart.presenter.Presenter;
import com.nikart.screens.soon_episodes.Month;
import com.nikart.screens.soon_episodes.SoonEpisodesFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        Observable<List<Episode>> soonEpisodesObservable = apiModel.getNextEpisodes();
        subscription = soonEpisodesObservable
                .subscribe(
                        this::groupEpisodes,
                        e -> view.showErrorSnackbar(e),
                        () -> Log.d("RX_SOON_EPS", "Complete load episodes")
                );
    }

    private void groupEpisodes(List<Episode> episodes) {
        final String[] monthTitle = view.getResources().getStringArray(R.array.months);
        Date today = new Date();
        Date maximumDate = new Date(121212);
        List<Month> months = new ArrayList<>();

        //TODO: придумать алгоритм, как поэффективней разбивать по месяцам
        for (Episode ep : episodes) {
            maximumDate = (today.compareTo(ep.getAirDate()) < 0)
                    ? ep.getAirDate()
                    : today;
        }

        Calendar maxCalendar = Calendar.getInstance();
        Calendar airCalendar = Calendar.getInstance();
        maxCalendar.setTime(maximumDate);
        for (int i = Calendar.getInstance().get(Calendar.MONTH);
             i <= maxCalendar.get(Calendar.MONTH); i++) {
            List<Episode> tmp = new ArrayList<>();
            for (Episode ep : episodes) {
                airCalendar.setTime(ep.getAirDate());
                if (today.compareTo(ep.getAirDate()) <= 0 &&
                        airCalendar.get(Calendar.MONTH) == i) {
                    tmp.add(ep);
                }
            }
            if (tmp.isEmpty()) continue;
            months.add(new Month(monthTitle[i], tmp));
        }
        view.initRecycler(months);
    }

    @Override
    public void onStop() {
        subscription.dispose();
    }
}
