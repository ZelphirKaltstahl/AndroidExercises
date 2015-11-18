package com.example.xiaolong.exercises.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xiaolong.exercises.MyActivity;
import com.example.xiaolong.exercises.R;

import java.util.ArrayList;

public class a6_listview_activity extends Activity {

    private static final String LISTVIEW_ACTIVITY_LOG_TAG = "LISTVIEW";

    private ListView equations_listview;
    private ArrayAdapter<String> listview_adapter;
    private ArrayList<String> list_of_equation_strings = new ArrayList<>();
    private Intent open_calculator_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a6_listview_activity);

        equations_listview = (ListView) findViewById(R.id.a6_listview_listview);
        equations_listview.setAdapter(listview_adapter);
        listview_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_of_equation_strings);
    }

    public void on_add(View view) {
        if (open_calculator_intent == null) {
            open_calculator_intent = new Intent(this, a_calculator.class);
        }
        startActivityForResult(open_calculator_intent, MyActivity.ENTER_EQUATION.ordinal());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent_with_data) {
        if (resultCode == RESULT_OK) {
            if (intent_with_data == null) {
                Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "intent didn't carry data");
            } else {
                if(requestCode == MyActivity.ENTER_EQUATION.ordinal()) {
                    if(intent_with_data.hasExtra(a_calculator.EQUATION_EXTRA_STRING)) {
                        listview_adapter.add(intent_with_data.getStringExtra(a_calculator.EQUATION_EXTRA_STRING));
                    }
                }
            }
        }
    }
}
