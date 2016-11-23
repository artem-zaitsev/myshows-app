package com.nikart.auth.signin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nikart.main.MainActivity;
import com.nikart.myshows.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signInButton;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = (Button) findViewById(R.id.sign_in_btn);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MainActivity.start(this);
    }
}
