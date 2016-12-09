package com.nikart.auth.signup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nikart.main.MainActivity;
import com.nikart.myshows.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signUpButton, nextButton;
    private SignUpView firstSignUpView, secondSignUpView;

    public static void start(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpButton = (Button) findViewById(R.id.sign_up_btn);
        signUpButton.setOnClickListener(this);
        nextButton = (Button) findViewById(R.id.sign_up_next_btn);
        nextButton.setOnClickListener(this);

        firstSignUpView  = (SignUpView) findViewById(R.id.first_sign_up_view);
        secondSignUpView = (SignUpView) findViewById(R.id.second_sign_up_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_btn: {
                MainActivity.start(this);
                break;
            }
            case R.id.sign_up_next_btn: {
                firstSignUpView.setVisibility(View.INVISIBLE);
                secondSignUpView.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.INVISIBLE);
                break;
            }
        }
    }
}
