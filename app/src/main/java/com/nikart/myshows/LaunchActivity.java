package com.nikart.myshows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        registerButton = (Button)findViewById(R.id.register_btn);
        loginButton = (Button)findViewById(R.id.login_btn);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        /*switch (view){
            case registerButton : {
                break;
            }
        }*/

        LoginActivity.start(this);
    }
}
