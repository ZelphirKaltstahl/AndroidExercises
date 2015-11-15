package com.example.xiaolong.exercises.a4.subactivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xiaolong.exercises.R;

import java.text.SimpleDateFormat;

public class a4_showdate_showtime_showtime extends Activity {

    private TextView showtime_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a4_showdate_showtime_showtime);

        showtime_textview = (TextView) findViewById(R.id.a4_showdate_showtime_showtime_textview);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        showtime_textview.setText(date);
    }
}
