package com.nikart.screens.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nikart.myshows.R;

/**
 * Created by Artem on 29.04.2017.
 */

public class SearchResultViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView title;
    public LinearLayout root;

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        root = (LinearLayout) itemView.findViewById(R.id.item_search_root);
        image = (ImageView) itemView.findViewById(R.id.item_search_img);
        title = (TextView) itemView.findViewById(R.id.item_search_title);
    }

}
