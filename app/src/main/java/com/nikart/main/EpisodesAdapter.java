package com.nikart.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikart.myshows.R;

import java.util.List;

/**
 * Created by Artem
 */

class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder> {
    private List<Episode> episodesList;

    EpisodesAdapter(List<Episode> episodesList) {
        this.episodesList = episodesList;
    }

    @Override
    public EpisodesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_episode,
                parent,false);
        return new EpisodesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodesViewHolder holder, int position) {
        Episode ep = episodesList.get(position);
        holder.episodeTitleTextView.setText(ep.getTitle());
        holder.seasonTitleTextView.setText(ep.getSeason());
        holder.showTitleTextView.setText(ep.getShowTitle());
        holder.dateTextView.setText(ep.getStartDate().toString());
    }

    @Override
    public int getItemCount() {
        return episodesList.size();
    }

    class EpisodesViewHolder extends RecyclerView.ViewHolder {
        TextView episodeTitleTextView, seasonTitleTextView, showTitleTextView, dateTextView;
        EpisodesViewHolder(View itemView) {
            super(itemView);
            episodeTitleTextView = (TextView)itemView.findViewById(R.id.episode_title);
            seasonTitleTextView = (TextView)itemView.findViewById(R.id.episode_season);
            showTitleTextView = (TextView)itemView.findViewById(R.id.episode_show_title);
            dateTextView = (TextView)itemView.findViewById(R.id.episode_date);
        }
    }
}
