package com.nikart.screens.auth.signup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Nikita on 09.12.2016.
 */

public class SignUpView extends LinearLayout {

    public SignUpView(Context context) {
        this(context, null);
    }

    public SignUpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        inflate(context,
                attributeSet
                        .getAttributeResourceValue(
                                "http://schemas.android.com/apk/res-auto",
                                "view_layout", -1),
                this);
    }
}
