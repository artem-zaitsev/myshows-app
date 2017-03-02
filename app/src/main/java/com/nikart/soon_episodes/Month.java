package com.nikart.soon_episodes;

import com.nikart.dto.Episode;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Artem
 */

public class Month extends ExpandableGroup<Episode> {

    public Month(String title, List<Episode> items) {
        super(title, items);
    }
}
