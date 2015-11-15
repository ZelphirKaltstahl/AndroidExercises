package com.example.xiaolong.exercises.a4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.xiaolong.exercises.R;

public class a4_showtime_showdate_activity extends Activity {

    private static final String SHOWDATE_ACTION_STRING = "com.example.xiaolong.exercises.intent.showdate";
    private static final String SHOWTIME_ACTION_STRING = "com.example.xiaolong.exercises.intent.showtime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a4_showtime_showdate_activity);
    }

    public void on_show_time(View view) {
        Intent open_showtime_intent = new Intent();
        open_showtime_intent.setAction(a4_showtime_showdate_activity.SHOWTIME_ACTION_STRING);
        if(open_showtime_intent.resolveActivity(getPackageManager()) != null) {
            startActivity(open_showtime_intent);
        } else {
            Toast.makeText(this, "Intent cannot be handled by any activity.", Toast.LENGTH_SHORT).show();
        }
    }

    public void on_show_date(View view) {
        Intent open_showdate_intent = new Intent();
        open_showdate_intent.setAction(a4_showtime_showdate_activity.SHOWDATE_ACTION_STRING);
        if(open_showdate_intent.resolveActivity(getPackageManager()) != null) {
            startActivity(open_showdate_intent);
        } else {
            Toast.makeText(this, "Intent cannot be handled by any activity.", Toast.LENGTH_SHORT).show();
        }
    }
}
