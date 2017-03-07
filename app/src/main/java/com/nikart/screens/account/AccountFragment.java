package com.nikart.screens.account;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nikart.data.dto.Show;
import com.nikart.myshows.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Фрагмент для отображения информации об аккаунте
 */

public class AccountFragment extends Fragment {

    private RecyclerView recyclerView;
    private AccountShowAdapter showsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Show> shows;
    private ViewGroup container;
    private Toolbar toolbar;

    private String ACCOUNT_FRAGMENT_TITLE;

    @Override
    public void onAttach(Context context) {
        ACCOUNT_FRAGMENT_TITLE = getString(R.string.my_account);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.container = container;
        shows = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            shows.add(i, new Show());
        }
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(ACCOUNT_FRAGMENT_TITLE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }

        ImageView accountPic = (ImageView) rootView.findViewById(R.id.fragment_account_userpic);
        Glide.with(this).load("https://myshows.me/shared/img/fe/default-user-avatar-big.png")
                .into(accountPic);
        initRecycler(rootView);
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
            case R.id.menu_item_account: // открываем профиль на сайте
        }
        return true;
    }

    private void initRecycler(View rootView) {
        /*
        * Используем ShowsAdapter, но позже изменим
        * */
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_account_rv);
        layoutManager = new LinearLayoutManager(this.getContext()); // two columns

        showsAdapter = new AccountShowAdapter(shows);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }
}
