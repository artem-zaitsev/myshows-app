package com.nikart.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.nikart.myshows.R;

/**
 * Created by Artem on 05.03.2017.
 */

public class LayoutSwitcherDialog extends DialogFragment {

    private LayoutSwitcherDialogListener listener;

    public interface LayoutSwitcherDialogListener {
        public void OnItemClickListener(int i);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (LayoutSwitcherDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement LayoutSwtcherDialogListener!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.menu_layout_switch)
                .setItems(R.array.layouts_variants, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.OnItemClickListener(i);
                    }
                });
        return builder.create();
    }
}
