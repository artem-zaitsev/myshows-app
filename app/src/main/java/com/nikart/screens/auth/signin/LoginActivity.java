package com.nikart.screens.auth.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nikart.myshows.R;
import com.nikart.presenter.login.LoginPresenter;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signInButton;
    private EditText loginEditText;
    private EditText passwordEditText;
    private LoginPresenter presenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        initActivity();
    }

    @Override
    public void onClick(View view) {
        presenter.signIn(loginEditText.getText().toString(), passwordEditText.getText().toString());
    }

    private void initActivity() {
        signInButton = (Button) findViewById(R.id.sign_in_btn);
        loginEditText = (EditText) findViewById(R.id.login_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        signInButton.setOnClickListener(this);
    }

    public void startActivityIfSignedIn(Boolean isSuccessful) {
        PreferencesWorker.getInstance().saveSignedIn(isSuccessful);
        MainActivity.start(LoginActivity.this);
    }
}
