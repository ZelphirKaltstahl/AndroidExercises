package com.example.xiaolong.exercises.a8;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 20.11.15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String PERSON_TABLE_NAME = "Persons";
    private static final String PERSON_ID_ATTRIBUTE_NAME = "PersonID";
    private static final String PERSON_FIRST_NAME_ATTRIBUTE_NAME = "FirstName";
    private static final String PERSON_LAST_NAME_ATTRIBUTE_NAME = "LastName";
    private static final String PERSON_MOBILE_PHONE_NUMBER_ATTRIBUTE_NAME = "MobilePhoneNumber";

    private static final String ROOM_TABLE_NAME = "Rooms";
    private static final String ROOM_ID_ATTRIBUTE_NAME = "RoomID";
    private static final String ROOM_STATIONARY_PHONE_NUMBER_ATTRIBUTE_NAME = "StationaryPhoneNumber";

    private static final String LOG_TAG = "DBHelper";
    private SQLiteDatabase sqlite_database;

    public DBHelper(Context context) {
        super(context, "myDB1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DBHelper.LOG_TAG, "--- onCreate Database ---");
        sqlite_database = db;
        create_initial_data();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // not yet implemented, necessary?
    }

    private void create_initial_data() {
        try {
            sqlite_database.execSQL(
                    "CREATE TABLE " + DBHelper.PERSON_TABLE_NAME + " (" +
                            DBHelper.PERSON_ID_ATTRIBUTE_NAME + " int PRIMARY KEY, " +
                            DBHelper.PERSON_FIRST_NAME_ATTRIBUTE_NAME + " varchar(255) NOT NULL, " +
                            DBHelper.PERSON_LAST_NAME_ATTRIBUTE_NAME + " varchar(255) NOT NULL, " +
                            DBHelper.PERSON_MOBILE_PHONE_NUMBER_ATTRIBUTE_NAME + " varchar(64), " +
                            DBHelper.ROOM_ID_ATTRIBUTE_NAME + " varchar(255) FOREIGN KEY" +
                    ");"
            );
            sqlite_database.execSQL(
                    "CREATE TABLE " + DBHelper.ROOM_TABLE_NAME + " (" +
                            DBHelper.ROOM_ID_ATTRIBUTE_NAME + " int PRIMARY KEY, " +
                            DBHelper.ROOM_STATIONARY_PHONE_NUMBER_ATTRIBUTE_NAME + " varchar(64) NOT NULL" +
                    ");"
            );
        } catch (SQLException ex) {
            Log.d(DBHelper.LOG_TAG, "Failed to create a database table.");
        }


//        List<String> person_names = new ArrayList<>();
//        person_names.addAll();
//
//        // insert persons
//        for(int i = 0; i < 10; i++) {
//            sqlite_database.execSQL(
//                    "INSERT INTO " + DBHelper.PERSON_TABLE_NAME + " (" +
//                            DBHelper.PERSON_ID_ATTRIBUTE_NAME + ", " +
//                            DBHelper.PERSON_FIRST_NAME_ATTRIBUTE_NAME + ", " +
//                            DBHelper.PERSON_LAST_NAME_ATTRIBUTE_NAME + ", " +
//                            DBHelper.PERSON_MOBILE_PHONE_NUMBER_ATTRIBUTE_NAME + ", " +
//                            DBHelper.ROOM_ID_ATTRIBUTE_NAME +
//                    ")" +
//                            "VALUES (value1,value2,value3,...);");
//        }
    }
}
