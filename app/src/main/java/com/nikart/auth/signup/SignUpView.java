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

    private TypedArray typedArray;
    private int layoutId = 1;

    public SignUpView(Context context) {
        this(context, null);
    }

    public SignUpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SignUpView, 0, 0);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        try {
            layoutId = typedArray.getInt(R.styleable.SignUpView_view_layout, 1);
        } finally {
            typedArray.recycle();
        }

        switch (layoutId) {
            case 1: {
                inflate(context, R.layout.fragment_signup_first, this);
                break;
            }
            case 2: {
                inflate(context, R.layout.fragment_signup_second, this);
                break;
            }
            default:
                inflate(context, R.layout.fragment_signup_first, this);
        }

    }
}
