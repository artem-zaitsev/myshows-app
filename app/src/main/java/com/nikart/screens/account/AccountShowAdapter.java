package com.nikart.screens.account;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nikart.app.App;
import com.nikart.model.dto.Show;
import com.nikart.myshows.R;
import com.nikart.screens.show.ShowActivity;
import com.nikart.screens.util.RateCustomView;

import java.util.List;

/**
 * Created by key on 02.03.2017.
 */

public class AccountShowAdapter extends RecyclerView.Adapter<AccountShowAdapter.ShowViewHolder> {

    private List<Show> showList;
    private RateShowChangedListener listener;
    private Context context;

    public AccountShowAdapter(List list, RateShowChangedListener listener) {
        this.showList = list;
        this.listener = listener;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_account_show,
                parent, false);
        context = parent.getContext();
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        final Show show = showList.get(position);

        holder.showTitle.setText(show.getTitle());
        holder.root.setOnClickListener(view -> {
            ShowActivity.start(context, show.getId());
        });
        holder.showTitleOriginal.setText(show.getTitleOriginal());
        holder.ratingView.setRate(Math.round(show.getRating()));
        holder.ratingView.setClickListener(rate -> {
            show.setRating(rate);
            if (listener != null) listener.rateUpdate(show.getId(), rate);
        });
    }


    @Override
    public int getItemCount() {
        return showList.size();
    }

    public interface RateShowChangedListener {
        public void rateUpdate(int showId, int rate);
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout root;
        public TextView showTitle;
        public TextView showTitleOriginal;
        public RateCustomView ratingView;

        public ShowViewHolder(View itemView) {
            super(itemView);
            root = (LinearLayout) itemView.findViewById(R.id.layout_item_account_root);
            showTitle = (TextView) itemView.findViewById(R.id.layout_item_account_show_title);
            showTitleOriginal =
                    (TextView) itemView.findViewById(R.id.layout_item_account_show_title_orig);
            ratingView = (RateCustomView) itemView.findViewById(R.id.layout_item_account_rate);

        }
    }
}
