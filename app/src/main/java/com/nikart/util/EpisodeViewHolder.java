package com.nikart.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikart.myshows.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by key on 26.02.2017.
 */

public class EpisodeViewHolder extends ChildViewHolder {

    public TextView episodeTitleTextView, seasonTitleTextView, showTitleTextView, dateTextView;
    public ImageView showImage;

    public EpisodeViewHolder(View itemView) {
        super(itemView);
        episodeTitleTextView = (TextView) itemView.findViewById(R.id.episode_title);
        seasonTitleTextView = (TextView) itemView.findViewById(R.id.episode_season);
        showTitleTextView = (TextView) itemView.findViewById(R.id.episode_show_title);
        dateTextView = (TextView) itemView.findViewById(R.id.episode_date);
        showImage = (ImageView) itemView.findViewById(R.id.item_episode_show_image);
    }

}
