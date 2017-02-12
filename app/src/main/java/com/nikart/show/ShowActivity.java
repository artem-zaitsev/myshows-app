package com.nikart.show;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.nikart.myshows.R;

public class ShowActivity extends AppCompatActivity {

    private String title;
    private TextView titleTextView;

    public static void start(Context context, String title){
        Intent intent = new Intent(context,ShowActivity.class);
        intent.putExtra("TITLE",title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent =  getIntent();
        title = intent.getStringExtra("TITLE");

        titleTextView = (TextView) findViewById(R.id.activity_show_toolbar_title_textview);
        titleTextView.setText(title);

    }
}
