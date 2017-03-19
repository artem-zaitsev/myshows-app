package com.nikart.screens.account;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nikart.data.dto.Show;
import com.nikart.data.dto.UserProfile;
import com.nikart.interactor.Answer;
import com.nikart.interactor.loaders.UserProfileLoader;
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
    private ImageView accountPic;
    private TextView usernameTextView;

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
        initFragment(rootView);
        loadData();
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
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(ACCOUNT_FRAGMENT_TITLE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }

        accountPic = (ImageView) rootView.findViewById(R.id.fragment_account_userpic);
        usernameTextView =
                (TextView) rootView.findViewById(R.id.fragment_account_username_text_view);
    }

    private void initRecycler(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_account_rv);
        layoutManager = new LinearLayoutManager(this.getContext()); // two columns

        showsAdapter = new AccountShowAdapter(shows);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showsAdapter);
    }

    private void loadData() {
        LoaderManager.LoaderCallbacks loaderCallbacks =
                new LoaderManager.LoaderCallbacks<Answer>() {

                    @Override
                    public Loader<Answer> onCreateLoader(int id, Bundle args) {
                        return new UserProfileLoader(getContext());
                    }

                    @Override
                    public void onLoadFinished(Loader<Answer> loader, Answer data) {
                        UserProfile user = data.getTypedAnswer();
                        if (user != null) {
                            usernameTextView.setText((String) user.getLogin());
                            Glide.with(AccountFragment.this)
                                    .load("https://api.myshows.me/shared/img/fe/default-user-avatar-normal.png")
                                    .into(accountPic);
                        } else {
                            usernameTextView.setText(" ");
                            Glide.with(AccountFragment.this)
                                    .load("https://api.myshows.me/shared/img/fe/default-user-avatar-normal.png")
                                    .into(accountPic);
                            Toast.makeText(getContext(), "Sorry. There are some problems.", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                    @Override
                    public void onLoaderReset(Loader<Answer> loader) {

                    }
                };
        getActivity().getSupportLoaderManager().initLoader(0, null, loaderCallbacks);

    }
}
