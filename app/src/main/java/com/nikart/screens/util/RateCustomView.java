package com.nikart.screens.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.nikart.myshows.R;

/**
 * Created by Artem.
 * It's customView for rate. Consist of stars-pics.
 */

public class RateCustomView extends LinearLayout implements View.OnClickListener {

    private ImageButton[] stars;
    private int rate;
    private OnRateCustomViewClickListener clickListener;

    public RateCustomView(Context context) {
        super(context);
        rate = 0;
    }

    public RateCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.layout_custom_rate, this);
        init();
    }


    private void init() {
        rate = 0;
        stars = new ImageButton[]{(ImageButton) findViewById(R.id.rate_one),
                (ImageButton) findViewById(R.id.rate_two),
                (ImageButton) findViewById(R.id.rate_three),
                (ImageButton) findViewById(R.id.rate_four),
                (ImageButton) findViewById(R.id.rate_five)};
        fillStars(rate);
        for (int i = 0; i < 5; i++) {
            stars[i].setOnClickListener(this);
        }
    }

    private void fillStars(int rate) {
        for (int i = 0; i < rate; i++) {
            stars[i].setSelected(true);
        }
        for (int i = rate; i < 5; i++) {
            stars[i].setSelected(false);
        }
        this.rate = rate;
    }

    //OnClickListener for stars.
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rate_one: {
                fillStars(1);
                break;
            }
            case R.id.rate_two: {
                fillStars(2);
                break;
            }
            case R.id.rate_three: {
                fillStars(3);
                break;
            }
            case R.id.rate_four: {
                fillStars(4);
                break;
            }
            case R.id.rate_five: {
                fillStars(5);
                break;
            }
            default:
                break;
        }
        if (clickListener != null) {
            clickListener.onClick(rate);
        }
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
        fillStars(rate);
    }

    public void setClickListener(OnRateCustomViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //Interface for listener
    public interface OnRateCustomViewClickListener {
        void onClick(int rate);
    }
}
