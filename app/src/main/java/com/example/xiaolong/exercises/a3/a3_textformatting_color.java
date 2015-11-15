package com.example.xiaolong.exercises.a3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.xiaolong.exercises.R;

public class a3_textformatting_color extends Activity {

    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3_textformatting_color);
    }

    public void on_red(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("color", a3_textformatting_color.RED);
        setResult(RESULT_OK, response_intent);
        finish();
    }

    public void on_green(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("color", a3_textformatting_color.GREEN);
        setResult(RESULT_OK, response_intent);
        finish();
    }

    public void on_blue(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("color", a3_textformatting_color.BLUE);
        setResult(RESULT_OK, response_intent);
        finish();
    }
}
