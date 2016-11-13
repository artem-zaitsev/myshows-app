package com.nikart.myshows;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signinButton;

    static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signinButton = (Button) findViewById(R.id.sign_in_btn);
        signinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MainActivity.start(this);
    }
}
