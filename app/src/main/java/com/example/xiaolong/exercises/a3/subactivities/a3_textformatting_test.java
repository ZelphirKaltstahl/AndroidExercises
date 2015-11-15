package com.example.xiaolong.exercises.a3.subactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.xiaolong.exercises.R;

public class a3_textformatting_test extends Activity {

    private Intent initiation_intent_with_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3_textformatting_test);
        initiation_intent_with_data = getIntent();
    }

    public void on_is_empty(View view) {
        String message = initiation_intent_with_data.getStringExtra("test_value");
        Intent result_intent = new Intent();
        result_intent.putExtra("is_empty", message.isEmpty());
        setResult(RESULT_OK, result_intent);
        finish();
    }

    public void on_is_number(View view) {
        String message = initiation_intent_with_data.getStringExtra("test_value");
        Intent result_intent = new Intent();

        boolean result = true;
        try {
            Double.parseDouble(message);
        } catch (Exception ex) {
            result = false;
        }

        result_intent.putExtra("is_number", result);
        setResult(RESULT_OK, result_intent);
        finish();
    }
}
