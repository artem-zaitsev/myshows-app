package com.nikart.soon_episodes;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikart.myshows.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by Artem
 */

public class MonthViewHolder extends GroupViewHolder {

    public TextView monthTitleTextView;
    public TextView episodeCountTextView;
    public ImageView arrow;

    public MonthViewHolder(View itemView) {
        super(itemView);
        monthTitleTextView = (TextView) itemView.findViewById(R.id.layout_group_month_title);
        arrow = (ImageView) itemView.findViewById(R.id.layout_group_month_arrow);
        episodeCountTextView = (TextView) itemView.findViewById(R.id.layout_group_month_ep_count);

    }

    public void setMonthTitle(ExpandableGroup group) {
        monthTitleTextView.setText(group.getTitle());
    }

    public void setEpisodeCount(ExpandableGroup group) {
        episodeCountTextView.setText(String.valueOf(group.getItemCount()));
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(0, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }


    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 0, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

}
