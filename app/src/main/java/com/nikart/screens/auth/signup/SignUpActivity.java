package com.nikart.screens.auth.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nikart.myshows.R;
import com.nikart.screens.BaseActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    private Button signUpButton, nextButton, backButton;
    private SignUpView firstSignUpView, secondSignUpView;
    private LinearLayout linearLayoutWithButtons;

    public static void start(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void injectPresenter() {
        //do nothing
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initActivity() {
        signUpButton = (Button) findViewById(R.id.sign_up_btn);
        signUpButton.setOnClickListener(this);
        nextButton = (Button) findViewById(R.id.sign_up_next_btn);
        nextButton.setOnClickListener(this);
        backButton = (Button) findViewById(R.id.sign_up_back_btn);
        backButton.setOnClickListener(this);

        firstSignUpView = (SignUpView) findViewById(R.id.first_sign_up_view);
        secondSignUpView = (SignUpView) findViewById(R.id.second_sign_up_view);
        linearLayoutWithButtons = (LinearLayout) findViewById(R.id.sign_up_bttns);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_btn: {
                PreferencesWorker.getInstance().saveSignedIn(true);
                MainActivity.start(this);
                break;
            }
            case R.id.sign_up_next_btn: {
                firstSignUpView.setVisibility(View.INVISIBLE);
                secondSignUpView.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.INVISIBLE);
                linearLayoutWithButtons.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.sign_up_back_btn: {
                firstSignUpView.setVisibility(View.VISIBLE);
                secondSignUpView.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                linearLayoutWithButtons.setVisibility(View.INVISIBLE);
                break;
            }
        }
    }

    @Override
    public <T> void showData(T data) {

    }

    @Override
    public void showError(Throwable t) {

    }
}
