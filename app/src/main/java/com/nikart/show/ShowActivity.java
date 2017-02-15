package com.nikart.show;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nikart.dto.Show;
import com.nikart.myshows.R;
import com.nikart.util.RateCustomView;

public class ShowActivity extends AppCompatActivity {

    private static Show show;
    private String title;
    private TextView titleTextView,
            informationTextView, descriptionTextView;
    private FloatingActionButton watchingFab;
    private RateCustomView rateCustomView;

    public static void start(Context context, String title, Show s) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.putExtra("TITLE", title);
        show = s;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");

        titleTextView = (TextView) findViewById(R.id.activity_show_toolbar_title_textview);
        titleTextView.setText(title);

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
                /*watchingFab.hide();
                watchingFab.setVisibility(View.INVISIBLE);*/
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
            }
        });


    }

    private void setShowWatching() {
        show.setWatchStatus(isShowWatching()
                ? "not watching"
                : "watching");//???????
    }

    private boolean isShowWatching() {
        return (show.getWatchStatus() != null && show.getWatchStatus().equals("watching"));
    }
}
