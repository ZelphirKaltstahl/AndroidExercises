package com.example.xiaolong.exercises.a6;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.xiaolong.exercises.R;

public class a6_listview_activity extends Activity {

    private ListView equations_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a6_listview_activity);

        equations_listview = (ListView) findViewById(R.id.a6_listview_listview);
    }


}
