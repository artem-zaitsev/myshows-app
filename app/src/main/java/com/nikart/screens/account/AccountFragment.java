package com.nikart.screens.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

import com.bumptech.glide.Glide;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.myshows.R;
import com.nikart.presenter.account.AccountPresenter;
import com.nikart.screens.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

/**
 * Фрагмент для отображения информации об аккаунте
 */

public class AccountFragment extends BaseFragment implements AccountShowAdapter.RateShowChangedListener {

    private RecyclerView recyclerView;
    private AccountShowAdapter showsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView accountPic;
    private TextView usernameTextView;
    private TextView watchedEpisodes;
    private TextView watchedDays;
    private TextView watchedHours;
    private FrameLayout progressLayout;
    private List<Show> shows;

    @Override
    public void onStart() {
        super.onStart();
        setPresenter(new AccountPresenter(this));
        presenter.loadData();
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

    @Override
    protected void initFragment(View rootView) {
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
        initRecycler();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_account;
    }

    public void initRecycler() {
        shows = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        showsAdapter = new AccountShowAdapter(shows, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }

    private void updateRecycler(List<Show> shows) {
        this.shows.clear();
        this.shows.addAll(shows);
        progressLayout.setVisibility(View.GONE);
    }

    public void showUserInfo(UserProfile user) {
        usernameTextView.setText((String) user.getLogin());

        //Надо сделать объект Stats. Чтобы распарсить ответ на запрос профиля.
        watchedEpisodes.setText(
                user.getStats().getWatchedEpisodes().toString()
        );
        watchedHours.setText(
                String.valueOf(user.getStats().getWatchedHours().intValue())
        );
        watchedDays.setText(
                String.valueOf(user.getStats().getWatchedDays().intValue())
        );

        Glide.with(AccountFragment.this)
                .load("https://api.myshows.me/shared/img/fe/default-user-avatar-normal.png")
                .into(accountPic);

    }

    public void showErrorSnackbar(Throwable e) {
        Snackbar.make(getActivity()
                        .findViewById(R.id.main_activity_layout),
                "No Internet connection",
                Snackbar.LENGTH_INDEFINITE)
                .setAction("ON", view ->
                        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)))
                .show();
        Log.d("RX_ACCOUNT", e.toString());
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    //callback
    @Override
    public void rateUpdate(int showId, int rate) {
        ((AccountPresenter)presenter).rateUpdate(showId, rate);
    }

    @Override
    public <T> void showData(T data) {
        if (data.getClass() == UserProfile.class) {
            showUserInfo((UserProfile)data);
        } else if (data.getClass() == ArrayList.class) {
            updateRecycler((List<Show>)data);
        }
    }

    @Override
    public void showError(Throwable t) {
        showErrorSnackbar(t);
    }
}
