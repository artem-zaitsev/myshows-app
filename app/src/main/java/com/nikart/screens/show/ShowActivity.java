package com.nikart.screens.show;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nikart.data.HelperFactory;
import com.nikart.data.dto.Show;
import com.nikart.interactor.Answer;
import com.nikart.interactor.loaders.ShowByIdFromDataBaseLoader;
import com.nikart.myshows.R;
import com.nikart.screens.util.RateCustomView;

import java.sql.SQLException;

public class ShowActivity extends AppCompatActivity {

    private Show show;
    private String title;
    private TextView titleTextView,
            informationTextView, descriptionTextView;
    private FloatingActionButton watchingFab;
    private RateCustomView rateCustomView;
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

        loadFromIntent();

    }

    private void loadFromIntent() {
        LoaderManager.LoaderCallbacks showLoaderCallbacks = new LoaderManager.LoaderCallbacks<Answer>() {
            @Override
            public Loader<Answer> onCreateLoader(int id, Bundle args) {
                return new ShowByIdFromDataBaseLoader(ShowActivity.this,
                        args.getInt(ShowByIdFromDataBaseLoader.ARGS_ID));
            }

            @Override
            public void onLoadFinished(Loader<Answer> loader, Answer data) {
                show = data.getTypedAnswer();
                Log.d("LOADERS", "Finished load show on ShowActivity.");
                initActivity();
            }

            @Override
            public void onLoaderReset(Loader<Answer> loader) {

            }
        };
        getSupportLoaderManager().initLoader(0,
                ShowByIdFromDataBaseLoader.args(getIntent().getIntExtra("ID", 0)),
                showLoaderCallbacks);
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_show_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
            descriptionTextView.setText(Html.fromHtml(show.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            descriptionTextView.setText(Html.fromHtml(show.getDescription()));
        }
        Log.d("DESCRIPT", show.getDescription());

        watchingFab = (FloatingActionButton) findViewById(R.id.activity_show_fab);
        // спорное решение
        watchingFab.setImageResource(isShowWatching()
                ? R.drawable.check_mark
                : R.drawable.eye);

        watchingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setShowWatching();
                Toast.makeText(ShowActivity.this, "Show is " + show.getWatchStatus(), Toast.LENGTH_SHORT)
                        .show();
                watchingFab.setImageResource(isShowWatching()
                        ? R.drawable.check_mark
                        : R.drawable.eye);
            }
        });

        rateCustomView = (RateCustomView) findViewById(R.id.activity_show_rate_view);
        rateCustomView.setRate(show.getRating());
        rateCustomView.setClickListener(new RateCustomView.OnRateCustomViewClickListener() {
            @Override
            public void onClick(int rate) {
                show.setRating(rate);
                try {
                    HelperFactory.getHelper().getShowDAO().update(show);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
