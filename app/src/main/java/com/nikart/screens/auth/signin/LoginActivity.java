package com.nikart.screens.auth.signin;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nikart.myshows.R;
import com.nikart.presenter.DaggerPresenterComponent;
import com.nikart.presenter.login.LoginPresenter;
import com.nikart.screens.BaseActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    public LoginPresenter presenter;
    private Button signInButton;
    private EditText loginEditText;
    private EditText passwordEditText;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void injectPresenter() {
        DaggerPresenterComponent.create().inject(this);
        presenter.onCreate(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        setPresenter(presenter);
        getPresenter().loadData();
    }

    @Override
    protected void initActivity() {
        signInButton = (Button) findViewById(R.id.sign_in_btn);
        loginEditText = (EditText) findViewById(R.id.login_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        signInButton.setOnClickListener(this);
    }

    public void startActivityIfSignedIn(Boolean isSuccessful) {
        PreferencesWorker.getInstance().saveSignedIn(isSuccessful);
        MainActivity.start(LoginActivity.this);
    }

    public String getLogin() {
        return loginEditText.getText().toString();
    }

    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public <T> void showData(T data) {
        startActivityIfSignedIn((Boolean) data);
    }

    @Override
    public void showError(Throwable t) {

    }
}
