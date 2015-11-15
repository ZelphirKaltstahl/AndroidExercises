package com.example.xiaolong.exercises.a3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.xiaolong.exercises.MyActivity;
import com.example.xiaolong.exercises.R;

public class a3_textformatting_activity extends Activity {

    private Intent open_alignment_activity_intent;
    private Intent open_color_activity_intent;
    private Intent open_fontsize_activity_intent;

    public static final String LOG_TAG_MAIN_ACTIVITY = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3_textformatting);
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
                //TODO
            } else if(request_code == MyActivity.CHOOSE_COLOR.ordinal()) {
                Log.d(a3_textformatting_activity.LOG_TAG_MAIN_ACTIVITY, "COLOR ACTIVITY " +
                        "RETURNED");
                //TODO
            } else if(request_code == MyActivity.CHOOSE_FONTSIZE.ordinal()) {
                Log.d(a3_textformatting_activity.LOG_TAG_MAIN_ACTIVITY, "FONTSIZE ACTIVITY " +
                        "RETURNED");
                //TODO
            }
        }
    }

    private void change_alignment(String alignment) {

    }

    private void change_color(String color) {

    }

    private void change_fontsize(int fontsize) {

    }
}
