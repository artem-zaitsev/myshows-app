package com.nikart.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikart.dto.Episode;
import com.nikart.myshows.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Artem
 */

public class EpisodesInMonthAdapter
        extends ExpandableRecyclerViewAdapter<MonthViewHolder, EpisodeViewHolder> {

    private Context context;

    public EpisodesInMonthAdapter(List list) {
        super(list);
    }

    @Override
    public MonthViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_group_month,
                parent, false);
        return new MonthViewHolder(view);
    }

    @Override
    public EpisodeViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_item_episode,
                parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(EpisodeViewHolder holder,
                                      int flatPosition, ExpandableGroup group, int childIndex) {
        Episode ep = ((Month) group).getItems().get(childIndex);

        holder.showTitleTextView.setText(String.format(
                context.getString(R.string.fragment_episodes_show_title),
                ep.getShowTitle()));
        holder.seasonTitleTextView.setText(String.format(
                context.getString(R.string.fragment_episodes_short_name),
                ep.getShortName()));
        holder.episodeTitleTextView.setText(ep.getTitle());

        holder.dateTextView.setText(DateFormat.format("dd.MM.yy, EE ", ep.getAirDate()));

        Resources res = context.getResources();

        Calendar calendarEpisodes = Calendar.getInstance();
        calendarEpisodes.setTime(ep.getAirDate());
        int daysCount =
                calendarEpisodes.get(Calendar.DAY_OF_YEAR) -
                        Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        holder.daysLeftTextView.setText(res.getQuantityString(R.plurals.days_left,
                daysCount, daysCount));

    }

    @Override
    public void onBindGroupViewHolder(MonthViewHolder holder,
                                      int flatPosition, ExpandableGroup group) {
        holder.setMonthTitle(group);
        holder.episodeCountTextView.setText(String.format(context
                .getResources()
                .getQuantityString(
                        R.plurals.series,
                        group.getItemCount(), group.getItemCount())));
    }

}
