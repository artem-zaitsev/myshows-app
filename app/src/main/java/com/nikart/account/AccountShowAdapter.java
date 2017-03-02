package com.nikart.account;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikart.dto.Show;
import com.nikart.myshows.R;
import com.nikart.shows.ShowsAdapter;
import com.nikart.util.RateCustomView;

import java.util.List;

/**
 * Created by key on 02.03.2017.
 */

public class AccountShowAdapter extends RecyclerView.Adapter<AccountShowAdapter.ShowViewHolder> {

    private List<Show> showList;

    public AccountShowAdapter(List list) {
        this.showList = list;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_account_show,
                parent,false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        final Show show = showList.get(position);

        holder.showTitle.setText(show.getTitle());
        holder.showTitleOriginal.setText(show.getTitleOriginal());
        holder.ratingView.setRate(show.getRating());
        holder.ratingView.setClickListener(new RateCustomView.OnRateCustomViewClickListener() {
            @Override
            public void onClick(int rate) {
                show.setRating(rate);
            }
        });

    }


    @Override
    public int getItemCount() {
        return showList.size();
    }

    class ShowViewHolder extends RecyclerView.ViewHolder{

        public TextView showTitle;
        public TextView showTitleOriginal;
        public RateCustomView ratingView;

        public ShowViewHolder(View itemView) {
            super(itemView);
            showTitle = (TextView) itemView.findViewById(R.id.layout_item_account_show_title);
            showTitleOriginal =
                    (TextView) itemView.findViewById(R.id.layout_item_account_show_title_orig);
            ratingView = (RateCustomView) itemView.findViewById(R.id.layout_item_account_rate);

        }
    }
}
