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
import com.example.xiaolong.exercises.a7.a7_intents;
import com.example.xiaolong.exercises.a8.a8a_shared_preferences;
import com.example.xiaolong.exercises.a8.a8b_sqlite_db;

public class MainActivity extends Activity {

    private Intent open_exercise_3_intent;
    private Intent open_exercise_4_intent;
    private Intent open_exercise_5_intent;
    private Intent open_exercise_6_intent;
    private Intent open_exercise_7_intent;
    private Intent open_exercise_8a_shared_preferences_intent;
    private Intent open_exercise_8b_sqlite_db_intent;

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
        if(open_exercise_7_intent == null) {
            open_exercise_7_intent = new Intent(this, a7_intents.class);
        }
        startActivity(open_exercise_7_intent);
    }

    public void on_exercise_8a(View view) {
        Toast.makeText(this, "Exercise 8 clicked!", Toast.LENGTH_SHORT).show();
        if(open_exercise_8a_shared_preferences_intent == null) {
            open_exercise_8a_shared_preferences_intent = new Intent(this, a8a_shared_preferences.class);
        }
        startActivity(open_exercise_8a_shared_preferences_intent);
    }

    public void on_exercise_8b(View view) {
        Toast.makeText(this, "Exercise 8 clicked!", Toast.LENGTH_SHORT).show();
        if(open_exercise_8b_sqlite_db_intent == null) {
            open_exercise_8b_sqlite_db_intent = new Intent(this, a8b_sqlite_db.class);
        }
        startActivity(open_exercise_8b_sqlite_db_intent);
    }
}
