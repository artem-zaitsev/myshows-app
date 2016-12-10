package com.nikart.auth.signup;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nikart.myshows.R;

import org.w3c.dom.Text;

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
        inflate(context, attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "view_layout", -1), this);
    }
}
