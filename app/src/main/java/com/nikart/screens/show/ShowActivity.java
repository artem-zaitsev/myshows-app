package com.nikart.screens.show;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nikart.app.App;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.myshows.R;

import java.sql.SQLException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ShowActivity extends AppCompatActivity {

    private int id;
    private Show show;
    private String title;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        id = getIntent().getIntExtra("ID", 0);
        loadData(id);
    }

    private void loadData(int id) {
        // Проблема с этим запросом. Обнуляет аргумент.
        int tstId = 7;
        Observable<Show> showObservable = App.getInstance().getApi().getShowById(id);
        showObservable
                .subscribeOn(Schedulers.io())
                .map(
                        sh -> {
                            Show tmpShow = null;
                            try {
                                tmpShow = HelperFactory.getHelper().getShowDAO().queryForId(id);
                                String watchStatus = tmpShow.getWatchStatus();
                                sh.setId(id);
                                sh.setWatchStatus(watchStatus);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            return sh;
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        show -> {
                            this.show = show;
                            Log.d("LOADERS", "Finished load show on ShowActivity." + show.getTitle());
                            initActivity();
                        },
                        e -> Log.d("RX_SHOW_BY_ID", e.toString()),
                        () -> Log.d("RX_SHOW_BY_ID", "Complete")
                );
    }

    private void setShowWatching() {
        show.setWatchStatus(isShowWatching()
                ? "not watching"
                : "watching");//???????
    }

    private boolean isShowWatching() {
        return (show.getWatchStatus() != null && show.getWatchStatus().equals("watching"));
    }

    private void initActivity() {
        ((FrameLayout)findViewById(R.id.activity_show_progress_load)).setVisibility(View.GONE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_show_toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        showImageView = (ImageView) findViewById(R.id.activity_show_image);
        Glide.with(this)
                .load(show.getImageUrl())
                .centerCrop()
                .into(showImageView);
        titleTextView = (TextView) findViewById(R.id.activity_show_toolbar_title_textview);
        titleTextView.setText(show.getTitle());

        informationTextView = (TextView) findViewById(R.id.activity_show_information_textview);
        informationTextView.setText(
                show.getTitleOriginal() + "\n"
                        + show.getCountry() + "\n"
                        + show.getStatus() + "\n"
                        + show.getYear());

        descriptionTextView = (TextView) findViewById(R.id.activity_show_description);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            descriptionTextView.setText(Html.fromHtml(show.getDescription() != null
                            ? show.getDescription()
                            : "No connection, sorry.",
                    Html.FROM_HTML_MODE_COMPACT));
        } else {
            descriptionTextView.setText(Html.fromHtml(show.getDescription() != null
                    ? show.getDescription()
                    : "No connection, sorry."));
        }

        watchingFab = (FloatingActionButton) findViewById(R.id.activity_show_fab);
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

        rateTextView = (TextView) findViewById(R.id.activity_show_rate_view);
        rateTextView.setText(String.valueOf(show.getRating()));
    }
}
