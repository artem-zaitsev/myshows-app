package com.nikart.screens.soon_episodes;

import android.view.View;
import android.widget.TextView;

import com.nikart.myshows.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Artem
 */

public class EpisodeViewHolder extends ChildViewHolder {

    public TextView episodeTitleTextView, seasonTitleTextView, showTitleTextView, dateTextView;
    public TextView daysLeftTextView;

    public EpisodeViewHolder(View itemView) {
        super(itemView);
        episodeTitleTextView = (TextView) itemView.findViewById(R.id.episode_title);
        seasonTitleTextView = (TextView) itemView.findViewById(R.id.episode_season);
        showTitleTextView = (TextView) itemView.findViewById(R.id.episode_show_title);
        dateTextView = (TextView) itemView.findViewById(R.id.episode_date);
        daysLeftTextView = (TextView) itemView.findViewById(R.id.layout_item_episode_days_left);
    }

}
