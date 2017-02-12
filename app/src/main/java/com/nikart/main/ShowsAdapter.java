package com.nikart.main;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikart.dto.Show;
import com.nikart.myshows.R;
import com.nikart.show.ShowActivity;

import java.util.List;

/**
 * Created by Artem
 * Adapter for shows.
 */

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ShowsViewHolder> {

    public static final String TITLE_KEY = "TITLE";

    private List<Show> showsList;
    private Show show;

    // Используется статическое поле IS_GRID для смены layout'ов

    public ShowsAdapter(List<Show> showsList) {
        Log.d("TEST", "Constructor");
        this.showsList = showsList;
    }

    @Override
    public ShowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d("TEST", String.valueOf(MyShowsFragment.IS_GRID));

        View view = LayoutInflater.from(parent.getContext()).inflate(
                MyShowsFragment.IS_GRID
                        ? R.layout.layout_card_show
                        : R.layout.layout_linear_show,
                parent, false);
        return new ShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowsViewHolder holder, int position) {
        show = showsList.get(position);

        if (MyShowsFragment.IS_GRID) {
            holder.container.setRadius(20);
        }
        holder.image.setImageResource(R.drawable.sherlock);
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.title.setText(show.getTitle());
        holder.titleOriginal.setText(show.getTitleOriginal());
        holder.rating.setText(" " + show.getRating() + "%");
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*     Bundle bundle = new Bundle();
                    bundle.putString(TITLE_KEY,title.getText().toString());*/
                ShowActivity.start(view.getContext(),show.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return showsList.size();
    }

    public class ShowsViewHolder extends RecyclerView.ViewHolder {

        public CardView container;
        public TextView title;
        public TextView titleOriginal;
        public TextView rating;
        public ImageView image;

        public ShowsViewHolder(View itemView) {
            super(itemView);
            Log.d("TEST", "ShowsVH");
            if (MyShowsFragment.IS_GRID) {
                container = (CardView) itemView.findViewById(R.id.show_container);
            }

            title = (TextView) itemView.findViewById(R.id.show_title);
            titleOriginal = (TextView) itemView.findViewById(R.id.show_orig_title);
            rating = (TextView) itemView.findViewById(R.id.show_rating);
            image = (ImageView) itemView.findViewById(R.id.show_image);

        }
    }
}
