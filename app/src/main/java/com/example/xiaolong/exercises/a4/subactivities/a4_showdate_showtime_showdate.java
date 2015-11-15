package com.example.xiaolong.exercises.a4.subactivities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xiaolong.exercises.R;

import java.text.SimpleDateFormat;

public class a4_showdate_showtime_showdate extends Activity {

    private TextView showdate_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a4_showdate_showtime_showdate);

        showdate_textview = (TextView) findViewById(R.id.a4_showdate_showtime_showdate_textview);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        showdate_textview.setText(date);
    }
}
