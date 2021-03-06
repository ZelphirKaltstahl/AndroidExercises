package com.example.xiaolong.exercises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.xiaolong.exercises.a3.a3_textformatting_activity;
import com.example.xiaolong.exercises.a4.a4_showtime_showdate_activity;
import com.example.xiaolong.exercises.a5.a5_horizontal_vertical_layout_activity;
import com.example.xiaolong.exercises.a6.a6_listview_activity;

public class MainActivity extends Activity {

    private Intent open_exercise_3_intent;
    private Intent open_exercise_4_intent;
    private Intent open_exercise_5_intent;
    private Intent open_exercise_6_intent;
    private Intent open_exercise_7_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void on_exercise_3(View view) {
        Toast.makeText(this, "Exercise 3 clicked!", Toast.LENGTH_SHORT).show();
        if(open_exercise_3_intent == null) {
            open_exercise_3_intent = new Intent(this, a3_textformatting_activity.class);
        }
        startActivity(open_exercise_3_intent);
    }

    public void on_exercise_4(View view) {
        Toast.makeText(this, "Exercise 4 clicked!", Toast.LENGTH_SHORT).show();
        if(open_exercise_4_intent == null) {
            open_exercise_4_intent = new Intent(this, a4_showtime_showdate_activity.class);
        }
        startActivity(open_exercise_4_intent);
    }

    public void on_exercise_5(View view) {
        Toast.makeText(this, "Exercise 5 clicked!", Toast.LENGTH_SHORT).show();
        if(open_exercise_5_intent == null) {
            open_exercise_5_intent = new Intent(this, a5_horizontal_vertical_layout_activity.class);
        }
        startActivity(open_exercise_5_intent);
    }

    public void on_exercise_6(View view) {
        Toast.makeText(this, "Exercise 6 clicked!", Toast.LENGTH_SHORT).show();
        if(open_exercise_6_intent == null) {
            open_exercise_6_intent = new Intent(this, a6_listview_activity.class);
        }
        startActivity(open_exercise_6_intent);
    }

    public void on_exercise_7(View view) {
        Toast.makeText(this, "Exercise 7 clicked!", Toast.LENGTH_SHORT).show();
    }
}
