package com.nikart.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikart.myshows.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Artem
 * Adapter for shows.
 */

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ShowsViewHolder> {

    private List<Show> showsList;

    public ShowsAdapter(List<Show> showsList) {
        this.showsList = showsList;
    }

    @Override
    public ShowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_show,
                parent, false);
        return new ShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowsViewHolder holder, int position) {
        Show show = showsList.get(position);
        holder.container.setRadius(20);
        holder.image.setImageResource(R.drawable.sherlock);
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.title.setText(show.getTitle());
        holder.dates.setText(" " + show.getStarted() + " - " + show.getEnded());
    }

    @Override
    public int getItemCount() {
        return showsList.size();
    }

    public class ShowsViewHolder extends RecyclerView.ViewHolder {

        public CardView container;
        public TextView title;
        public TextView dates;
        public ImageView image;

        public ShowsViewHolder(View itemView) {
            super(itemView);
            container = (CardView) itemView.findViewById(R.id.show_container);
            title = (TextView) itemView.findViewById(R.id.show_title);
            dates = (TextView) itemView.findViewById(R.id.show_date);
            image = (ImageView) itemView.findViewById(R.id.show_image);
        }
    }
}
