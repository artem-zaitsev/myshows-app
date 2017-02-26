package com.nikart.util;

import com.nikart.dto.Episode;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by key on 26.02.2017.
 */

public class Month extends ExpandableGroup<Episode> {

    public Month(String title, List<Episode> items) {
        super(title, items);
    }
}
