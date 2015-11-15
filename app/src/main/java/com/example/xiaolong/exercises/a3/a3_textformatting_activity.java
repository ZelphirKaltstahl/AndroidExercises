package com.example.xiaolong.exercises.a3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaolong.exercises.MyActivity;
import com.example.xiaolong.exercises.R;
import com.example.xiaolong.exercises.a3.subactivities.a3_textformatting_alignment;
import com.example.xiaolong.exercises.a3.subactivities.a3_textformatting_color;
import com.example.xiaolong.exercises.a3.subactivities.a3_textformatting_fontsize;
import com.example.xiaolong.exercises.a3.subactivities.a3_textformatting_test;

public class a3_textformatting_activity extends Activity {

    private Intent open_alignment_activity_intent;
    private Intent open_color_activity_intent;
    private Intent open_fontsize_activity_intent;
    private Intent open_test_activity_intent;

    public static final String LOG_TAG_MAIN_ACTIVITY = "MAIN_ACTIVITY";

    private TextView formatted_textview;
    private EditText test_value_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3_textformatting);

        formatted_textview = (TextView) findViewById(R.id.a3_formated_textview);
        test_value_edittext = (EditText) findViewById(R.id.a3_test_edittext);
    }

    public void on_change_alignment(View view) {
        if(open_alignment_activity_intent == null) {
            open_alignment_activity_intent = new Intent(this, a3_textformatting_alignment.class);
        }
        startActivityForResult(open_alignment_activity_intent, MyActivity.CHOOSE_ALIGNMENT.ordinal());
    }

    public void on_change_color(View view) {
        if(open_color_activity_intent == null) {
            open_color_activity_intent = new Intent(this, a3_textformatting_color.class);
        }
        startActivityForResult(open_color_activity_intent, MyActivity.CHOOSE_COLOR.ordinal());
    }

    public void on_change_fontsize(View view) {
        if(open_fontsize_activity_intent == null) {
            open_fontsize_activity_intent = new Intent(this, a3_textformatting_fontsize.class);
        }
        startActivityForResult(open_fontsize_activity_intent, MyActivity.CHOOSE_FONTSIZE.ordinal());
    }

    public void on_test(View view) {
        if(open_test_activity_intent == null) {
            open_test_activity_intent = new Intent(this, a3_textformatting_test.class);
        }
        open_test_activity_intent.putExtra("test_value", test_value_edittext.getText().toString());
        startActivityForResult(open_test_activity_intent, MyActivity.CHOOSE_TEST.ordinal());
    }

    @Override
    protected void onActivityResult(int request_code, int response_code, Intent intent_with_data) {
        Log.d(a3_textformatting_activity.LOG_TAG_MAIN_ACTIVITY, Integer.toString(request_code));
        Log.d(a3_textformatting_activity.LOG_TAG_MAIN_ACTIVITY, "RETURNED");
        if(intent_with_data == null) {
            return;
        }

        if(response_code == RESULT_OK) {
            if(request_code == MyActivity.CHOOSE_ALIGNMENT.ordinal()) {
                Log.d(a3_textformatting_activity.LOG_TAG_MAIN_ACTIVITY, "ALIGNMENT ACTIVITY " +
                        "RETURNED");
                change_alignment(intent_with_data);
            } else if(request_code == MyActivity.CHOOSE_COLOR.ordinal()) {
                Log.d(a3_textformatting_activity.LOG_TAG_MAIN_ACTIVITY, "COLOR ACTIVITY " +
                        "RETURNED");
                change_color(intent_with_data);
            } else if(request_code == MyActivity.CHOOSE_FONTSIZE.ordinal()) {
                Log.d(a3_textformatting_activity.LOG_TAG_MAIN_ACTIVITY, "FONTSIZE ACTIVITY " +
                        "RETURNED");
                change_fontsize(intent_with_data);
            } else if(request_code == MyActivity.CHOOSE_TEST.ordinal()) {
                Log.d(a3_textformatting_activity.LOG_TAG_MAIN_ACTIVITY, "FONTSIZE ACTIVITY " +
                        "RETURNED");
                show_test_result(intent_with_data);
            }
        }
    }

    private void change_alignment(Intent intent_with_data) {
        int alignment = intent_with_data.getIntExtra("gravity", Gravity.CENTER);
        formatted_textview.setGravity(alignment);
    }

    private void change_color(Intent intent_with_data) {
        int color = intent_with_data.getIntExtra("color", Color.BLACK);
        formatted_textview.setTextColor(color);
    }

    private void change_fontsize(Intent intent_with_data) {
        int default_fontsize = 10;
        int fontsize = intent_with_data.getIntExtra("fontsize", default_fontsize);
        formatted_textview.setTextSize(fontsize);
    }

    private void show_test_result(Intent intent_with_data) {
        if(intent_with_data.hasExtra("is_empty")) {
            boolean is_empty = intent_with_data.getBooleanExtra("is_empty", false);
            if (is_empty) {
                Toast.makeText(this, "The text is empty!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "The text is not empty!", Toast.LENGTH_SHORT).show();
            }
        } else {
            boolean is_number = intent_with_data.getBooleanExtra("is_number", false);
            if (is_number) {
                Toast.makeText(this, "The text is a number!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "The text is not a number!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
