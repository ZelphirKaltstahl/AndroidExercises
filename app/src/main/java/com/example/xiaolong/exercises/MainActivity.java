package com.example.xiaolong.exercises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.xiaolong.exercises.a3.a3_textformatting_activity;

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
        if(open_exercise_3_intent == null) {
            open_exercise_3_intent = new Intent(this, a3_textformatting_activity.class);
            if(open_exercise_3_intent.resolveActivity(getPackageManager()) != null) {
                startActivity(open_exercise_3_intent);
            }
        }
    }

    public void on_exercise_4(View view) {

    }

    public void on_exercise_5(View view) {

    }

    public void on_exercise_6(View view) {

    }

    public void on_exercise_7(View view) {

    }
}
