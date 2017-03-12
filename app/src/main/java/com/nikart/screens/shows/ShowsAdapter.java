package com.nikart.screens.shows;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nikart.data.dto.Show;
import com.nikart.myshows.R;
import com.nikart.screens.show.ShowActivity;

import java.util.List;

/**
 * Created by Artem
 * Adapter for shows.
 */

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ShowsViewHolder> {

    public static final String TITLE_KEY = "TITLE";

    private List<Show> showsList;

    private Context context;


    // Используется статическое поле IS_GRID для смены layout'ов

    public ShowsAdapter(List<Show> showsList) {
        Log.d("TEST", "Constructor");
        this.showsList = showsList;
    }

    @Override
    public ShowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d("TEST", String.valueOf(MyShowsFragment.IS_GRID));

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(
                MyShowsFragment.IS_GRID
                        ? R.layout.layout_item_card_show
                        : R.layout.layout_item_linear_show,
                parent, false);
        return new ShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowsViewHolder holder, int position) {
        final Show show = showsList.get(position);

        if (MyShowsFragment.IS_GRID) {
            holder.container.setRadius(5);
        }
        Glide
                .with(context)
                .load(show.getImageUrl())
                .centerCrop()
                .into(holder.image);
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.title.setText(show.getTitle());
        holder.titleOriginal.setText(show.getTitleOriginal());
        holder.episodesUnwatched.setText(context.getString(R.string.unwatched_episodes, show.getUnwatchedEpisodes()));
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowActivity.start(view.getContext(), show.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return showsList.size();
    }

    public class ShowsViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout root;

        public CardView container;

        public TextView title;
        public TextView titleOriginal;
        public TextView episodesUnwatched;
        public ImageView image;

        public ShowsViewHolder(View itemView) {
            super(itemView);
            Log.d("TEST", "ShowsVH");

            root = (LinearLayout) itemView.findViewById(R.id.item_show_root);
            if (MyShowsFragment.IS_GRID) {
                container = (CardView) itemView.findViewById(R.id.show_container);
            }

            title = (TextView) itemView.findViewById(R.id.show_title);
            titleOriginal = (TextView) itemView.findViewById(R.id.show_orig_title);
            episodesUnwatched = (TextView) itemView.findViewById(R.id.show_episodes_unwatched);
            image = (ImageView) itemView.findViewById(R.id.show_image);

        }
    }
}
