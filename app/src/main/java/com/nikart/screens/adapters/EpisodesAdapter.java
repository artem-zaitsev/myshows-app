package com.nikart.screens.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikart.model.dto.Episode;
import com.nikart.myshows.R;


import java.util.List;

/**
 * Created by Artem
 * Class for RecyclerView with episodes. There are
 * showTitle, airDate, seasonNumber and title(or number) of episode.
 */

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>
        implements View.OnClickListener {

    private final int TYPE_MONTH = 1;
    private final int TYPE_BASE = 0;

    private List<Episode> episodesList;

    public EpisodesAdapter(List<Episode> episodesList) {
        this.episodesList = episodesList;
    }

    @Override
    public EpisodesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_episode,
                parent, false);
        return new EpisodesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodesViewHolder holder, int position) {
        Episode ep = episodesList.get(position);

        holder.episodeTitleTextView.setText(ep.getTitle());
        holder.seasonTitleTextView.setText(String.valueOf(ep.getShortName()));
        holder.showTitleTextView.setText("BLALABALJND");
        holder.dateTextView.setText(DateFormat.format("dd.MM", ep.getAirDate()));

    }

    @Override
    public int getItemCount() {
        return episodesList.size();
    }

    @Override
    public void onClick(View view) {
        //do nothing
    }

    class EpisodesViewHolder extends RecyclerView.ViewHolder {

        public TextView episodeTitleTextView, seasonTitleTextView, showTitleTextView, dateTextView;

        EpisodesViewHolder(View itemView) {
            super(itemView);
            episodeTitleTextView = (TextView) itemView.findViewById(R.id.episode_title);
            seasonTitleTextView = (TextView) itemView.findViewById(R.id.episode_season);
            showTitleTextView = (TextView) itemView.findViewById(R.id.episode_show_title);
            dateTextView = (TextView) itemView.findViewById(R.id.episode_date);
        }
    }
}
