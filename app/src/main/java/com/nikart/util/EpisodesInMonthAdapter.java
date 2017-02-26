package com.nikart.util;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nikart.dto.Episode;
import com.nikart.myshows.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

/**
 * Created by key on 26.02.2017.
 */

public class EpisodesInMonthAdapter
        extends ExpandableRecyclerViewAdapter<MonthViewHolder,EpisodeViewHolder> {

    private List<Episode> episodesList;
    private int episodeRate;
    private Context context;

    public EpisodesInMonthAdapter(List list) {
        super(list);
    }

    @Override
    public MonthViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_group_month,
                parent,false);
        return new MonthViewHolder(view);
    }

    @Override
    public EpisodeViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_episode,
                parent,false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(EpisodeViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        DateFormat df = new DateFormat(); // для форматирования даты, пока здесь оставил


        Episode ep = ((Month)group).getItems().get(childIndex);

        Glide
                .with(context)
                .load("https://media.myshows.me/shows/normal/9/94/9492ce09d3a31c32ba559f5936dac888.jpg")
                .centerCrop()
                .into(holder.showImage);

//        holder.showImage.setImageResource(R.drawable.sherlock);
        holder.episodeTitleTextView.setText(ep.getTitle());
        holder.seasonTitleTextView.setText(String.valueOf(ep.getSeasonNumber()));
        holder.showTitleTextView.setText(ep.getShowTitle());
        holder.dateTextView.setText(df.format("dd.MM", ep.getAirDate()));

    }

    @Override
    public void onBindGroupViewHolder(MonthViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setMonthTitle(group);
        holder.setEpisodeCount(group);
    }

}
