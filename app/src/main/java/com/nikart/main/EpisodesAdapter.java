package com.nikart.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikart.myshows.R;

import java.util.List;

/**
 * Created by key on 01.12.2016.
 */

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder> {
    private List<Episode> episodesList;

    public EpisodesAdapter(List<Episode> episodesList) {
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
        holder.title.setText(ep.getTitle());
        holder.season.setText(ep.getSeason());
        holder.show.setText(ep.getShowTitle());
        holder.date.setText(ep.getStartDate().toString());
    }

    @Override
    public int getItemCount() {
        return episodesList.size();
    }

    public class EpisodesViewHolder extends RecyclerView.ViewHolder {
        public TextView title, season, show, date;
        public EpisodesViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.episode_title);
            season = (TextView)itemView.findViewById(R.id.episode_season);
            show = (TextView)itemView.findViewById(R.id.episode_show_title);
            date = (TextView)itemView.findViewById(R.id.episode_date);
        }
    }
}
