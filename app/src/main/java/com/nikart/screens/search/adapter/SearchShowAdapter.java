package com.nikart.screens.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nikart.model.dto.Show;
import com.nikart.model.dto.ShowTmp;
import com.nikart.myshows.R;
import com.nikart.screens.show.ShowActivity;

import java.util.List;

/**
 * Created by Artem on 29.04.2017.
 */

public class SearchShowAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {

    private List<ShowTmp> searchList;
    private Context context;

    public SearchShowAdapter(List<ShowTmp> searchList) {
        this.searchList = searchList;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_search_result, parent, false);
        return new SearchResultViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        ShowTmp show = searchList.get(position);

        Glide.with(context)
                .load(show.getImageUrl())
                .centerCrop()
                .into(holder.image);
        holder.title.setText(show.getTitle() != null && !show.getTitle().equals("")
                ? show.getTitle()
                : show.getTitleOriginal());
        holder.root.setOnClickListener(view -> ShowActivity.start(context, show.getId()));

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
