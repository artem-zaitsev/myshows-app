package com.nikart.screens.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.nikart.myshows.R;

/**
 * Created by Artem
 */

public class RateDialog extends DialogFragment
        implements RateCustomView.OnRateCustomViewClickListener {

    public int rate;
    private RateCustomView rateCustomView;
    private RateDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (RateDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement RateDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_rate, null))
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    ///do something
                    listener.onRateDialogPositiveClick(RateDialog.this);
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
        return builder.create();
    }

    @Override
    public void onClick(int rate) {
        this.rate = rate;
    }

    public interface RateDialogListener {
        public void onRateDialogPositiveClick(DialogFragment dialog);
    }
}
