package com.example.xiaolong.exercises.a3.subactivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.example.xiaolong.exercises.R;

public class a3_textformatting_alignment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3_textformatting_alignment);
    }

    public void on_left(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("gravity", Gravity.START);
        setResult(RESULT_OK, response_intent);
        finish();
    }

    public void on_center(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("gravity", Gravity.CENTER_HORIZONTAL);
        setResult(RESULT_OK, response_intent);
        finish();
    }

    public void on_right(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("gravity", Gravity.END);
        setResult(RESULT_OK, response_intent);
        finish();
    }
}
