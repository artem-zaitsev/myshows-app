package com.nikart.myshows;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * Created by Artem Zaitsev on 15.11.2016.
 */

public class WelcomeFragment extends Fragment {
    static final String ARG_KEY = "welcome";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        Bundle bundle = getArguments();
        int num_of_page = bundle.getInt(ARG_KEY);
        switch (num_of_page) {
            case 0: {
                rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
                break;
            }
            case 1: {
                rootView = inflater.inflate(R.layout.fragment_promo, container, false);
                break;
            }
            default:
                rootView = inflater.inflate(R.layout.fragment_promo, container, false);
        }
        return rootView;
    }
}
