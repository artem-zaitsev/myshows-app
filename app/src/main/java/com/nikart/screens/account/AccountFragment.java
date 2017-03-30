package com.nikart.screens.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.internal.LinkedTreeMap;
import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.myshows.R;
import com.nikart.util.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Фрагмент для отображения информации об аккаунте
 */

public class AccountFragment extends Fragment implements AccountShowAdapter.RateShowChangedListener {

    private RecyclerView recyclerView;
    private AccountShowAdapter showsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Show> shows;
    private ViewGroup container;
    private ImageView accountPic;
    private TextView usernameTextView;
    private TextView watchedEpisodes;
    private TextView watchedDays;
    private TextView watchedHours;
    private FrameLayout progressLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.container = container;
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        initFragment(rootView);
        loadData();
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_account, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_open_link: {
                // открываем профиль на сайте
                Uri address = Uri.parse("https://myshows.me/profile/");
                Intent intent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(intent);
            }
        }
        return true;
    }

    private void initFragment(View rootView) {
        progressLayout = (FrameLayout) rootView.findViewById(R.id.fragment_account_progress);
        progressLayout.setVisibility(View.VISIBLE);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(getString(R.string.my_account));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }

        accountPic = (ImageView) rootView.findViewById(R.id.fragment_account_userpic);
        usernameTextView =
                (TextView) rootView.findViewById(R.id.fragment_account_username_text_view);
        watchedEpisodes =
                (TextView) rootView.findViewById(R.id.fragment_account_episodes_count_text_view);
        watchedHours =
                (TextView) rootView.findViewById(R.id.fragment_account_hours_count_text_view);
        watchedDays = (TextView) rootView.findViewById(R.id.fragment_account_days_count_text_view);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_account_rv);
    }

    private void initRecycler(List shows) {
        layoutManager = new LinearLayoutManager(this.getContext());
        showsAdapter = new AccountShowAdapter(shows, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }

    private void loadData() {
        Observable<UserProfile> userProfileObservable = App.getInstance().getApi().getUserProfile();
        userProfileObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            if (user != null) {
                                usernameTextView.setText((String) user.getLogin());

                                //Надо сделать объект Stats. Чтобы распарсить ответ на запрос профиля.
                                watchedEpisodes.setText(
                                        ((LinkedTreeMap) user.getStats()).get("watchedEpisodes").toString()
                                );
                                watchedHours.setText(
                                        ((LinkedTreeMap) user.getStats()).get("watchedHours").toString()
                                );
                                watchedDays.setText(
                                        ((LinkedTreeMap) user.getStats()).get("watchedDays").toString()
                                );

                                Glide.with(AccountFragment.this)
                                        .load("https://api.myshows.me/shared/img/fe/default-user-avatar-normal.png")
                                        .into(accountPic);
                                progressLayout.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(getContext(), "Sorry. There are some problems.", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        },
                        Throwable::toString,
                        () -> Log.d("RX_ACCOUNT", "Completed")
                );

        Observable<ResponseBody> showListObservable = App.getInstance().getApi().getShows();
        showListObservable
                .map(
                        responseBody -> {
                            JsonParser<Show> parser = new JsonParser<>(responseBody);
                            List<Show> shows = null;
                            try {
                                shows = parser.getParsedList(Show.class);
                                HelperFactory.getHelper().getShowDAO().createInDataBase(shows);
                            } catch (IOException | JSONException | SQLException e) {
                                e.printStackTrace();
                            }
                            return shows;
                        }
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::initRecycler,
                        e -> {
                            Log.d("RX_ACCOUNT", e.toString());
                            initRecycler(HelperFactory.getHelper().getShowDAO().getAllShows());
                        },
                        () -> Log.d("RX_ACCOUNT", "Complete load show list")
                );
    }

    @Override
    public void rateUpdate(int showId, int rate) {
        Observable<Response<ResponseBody>> rateUpdateObservable =
                App.getInstance().getApi().updateShowRate(showId, rate);
        rateUpdateObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(
                        Response::isSuccessful
                )
                .subscribe(
                        is -> Log.d("RX_RATE_UPDATE", is.toString()),
                        e -> Log.d("RX_RATE_UPDATE", e.toString()),
                        () -> Log.d("RX_RATE_UPDATE", "Complete")
                );
    }
}
