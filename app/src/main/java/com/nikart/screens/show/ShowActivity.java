package com.nikart.screens.show;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nikart.model.dto.Show;
import com.nikart.myshows.R;
import com.nikart.presenter.dagger2.DaggerPresenterComponent;
import com.nikart.presenter.show.ShowPresenter;
import com.nikart.screens.BaseActivity;

import javax.inject.Inject;


public class ShowActivity extends BaseActivity {

    @Inject
    public ShowPresenter presenter;
    private int id;
    private Show show;
    private TextView titleTextView,
            informationTextView, descriptionTextView;
    private FloatingActionButton watchingFab;
    private TextView rateTextView;
    private ImageView showImageView;

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.putExtra("ID", id);
        context.startActivity(intent);
    }

    @Override
    protected void injectPresenter() {
        DaggerPresenterComponent.create().inject(this);
        presenter.onCreate(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void onStart() {
        super.onStart();
        setPresenter(presenter);
        getPresenter().loadData();
    }

    private void setShowWatching() {
        show.setWatchStatus(isShowWatching()
                ? "not watching"
                : "watching");
    }

    private boolean isShowWatching() {
        return (show.getWatchStatus() != null && show.getWatchStatus().equals("watching"));
    }

    public void initActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_show_toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        showImageView = (ImageView) findViewById(R.id.activity_show_image);
        titleTextView = (TextView) findViewById(R.id.activity_show_toolbar_title_textview);
        informationTextView = (TextView) findViewById(R.id.activity_show_information_textview);
        descriptionTextView = (TextView) findViewById(R.id.activity_show_description);
        watchingFab = (FloatingActionButton) findViewById(R.id.activity_show_fab);
        rateTextView = (TextView) findViewById(R.id.activity_show_rate_view);

        id = getIntent().getIntExtra("ID", 0);
    }

    public int getShowId() {
        return id;
    }

    @Override
    public <T> void showData(T data) {
        this.show = (Show) data;
        Glide.with(this)
                .load(show.getImageUrl())
                .centerCrop()
                .into(showImageView);
        informationTextView.setText(
                show.getTitleOriginal() + "\n"
                        + show.getCountry() + "\n"
                        + show.getStatus() + "\n"
                        + show.getYear());
        titleTextView.setText(show.getTitle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            descriptionTextView.setText(Html.fromHtml(show.getDescription() != null
                            ? show.getDescription()
                            : "",
                    Html.FROM_HTML_MODE_COMPACT));
        } else {
            descriptionTextView.setText(Html.fromHtml(show.getDescription() != null
                    ? show.getDescription()
                    : ""));
        }
        // спорное решение
        watchingFab.setImageResource(isShowWatching()
                ? R.drawable.check_mark
                : R.drawable.eye);

        watchingFab.setOnClickListener(view -> {
            setShowWatching();
            Toast.makeText(ShowActivity.this, "Show is " + show.getWatchStatus(), Toast.LENGTH_SHORT)
                    .show();
            watchingFab.setImageResource(isShowWatching()
                    ? R.drawable.check_mark
                    : R.drawable.eye);
        });
        rateTextView.setText(String.valueOf(show.getRating()));
        ((FrameLayout) findViewById(R.id.activity_show_progress_load)).setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!isShowWatching()) {
            presenter.deleteShow();
        }
    }

}
