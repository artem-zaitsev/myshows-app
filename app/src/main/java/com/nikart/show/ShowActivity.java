package com.nikart.show;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nikart.myshows.R;

public class ShowActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent = new Intent(context,ShowActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
    }
}
