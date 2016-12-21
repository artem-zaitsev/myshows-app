package com.nikart.main;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikart.myshows.R;


import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Artem
 * Class for RecyclerView with episodes. There are
 * showTitle, airDate, seasonNumber and title(or number) of episode.
 */

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder> {

    private List<Episode> episodesList;

    EpisodesAdapter(List<Episode> episodesList) {
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
        DateFormat df = new DateFormat(); // для форматирования даты, пока здесь оставил
        Episode ep = episodesList.get(position);
        holder.episodeTitleTextView.setText(ep.getTitle());
        holder.seasonTitleTextView.setText(String.valueOf(ep.getSeasonNumber()));
        holder.showTitleTextView.setText(ep.getShowTitle());
        holder.dateTextView.setText(df.format("dd.MM.yyyy",ep.getAirDate()));
    }

    @Override
    public int getItemCount() {
        return episodesList.size();
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
