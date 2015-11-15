package com.example.xiaolong.exercises.a4.subactivities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xiaolong.exercises.R;

import java.text.SimpleDateFormat;

public class a4_showdate_showtime_showdateextended extends Activity {

    private TextView showdateextended_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a4_showdate_showtime_showdateextended);

        showdateextended_textview = (TextView) findViewById(R.id.a4_showdate_showtime_showdateextended_textview);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        showdateextended_textview.setText(date);
    }
}
