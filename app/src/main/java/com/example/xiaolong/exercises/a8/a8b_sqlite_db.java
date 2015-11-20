package com.example.xiaolong.exercises.a8;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.xiaolong.exercises.R;

public class a8b_sqlite_db extends Activity {

    ExpandableListView a8b_expanablelistview;
    DBHelper db_helper;
    SQLiteDatabase sqlite_database;

    private static final String LOG_TAG = "SQLite DB Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a8b_sqlite_db);

        db_helper = new DBHelper(this);
        sqlite_database = db_helper.getWritableDatabase();

        a8b_expanablelistview = (ExpandableListView) findViewById(R.id.a8b_expandablelistview);
    }

    private void load_data() {

    }
}
