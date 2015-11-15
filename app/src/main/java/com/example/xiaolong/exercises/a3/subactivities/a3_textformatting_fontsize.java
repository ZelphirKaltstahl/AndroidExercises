package com.example.xiaolong.exercises.a3.subactivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.xiaolong.exercises.R;

public class a3_textformatting_fontsize extends Activity {

    private static final int SMALL = 10;
    private static final int MEDIUM = 20;
    private static final int LARGE = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3_textformatting_fontsize);
    }

    public void on_small(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("fontsize", a3_textformatting_fontsize.SMALL);
        setResult(RESULT_OK, response_intent);
        finish();
    }

    public void on_medium(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("fontsize", a3_textformatting_fontsize.MEDIUM);
        setResult(RESULT_OK, response_intent);
        finish();
    }

    public void on_large(View view) {
        Intent response_intent = new Intent();
        response_intent.putExtra("fontsize", a3_textformatting_fontsize.LARGE);
        setResult(RESULT_OK, response_intent);
        finish();
    }
}
