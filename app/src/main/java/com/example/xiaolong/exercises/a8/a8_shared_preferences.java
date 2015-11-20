package com.example.xiaolong.exercises.a8;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaolong.exercises.R;

public class a8_shared_preferences extends Activity {

    SharedPreferences shared_preferences;
    private static final String SAVED_TEXT_KEY = "saved_text";

    EditText a8_shared_preferences_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a8_shared_preferences);

        a8_shared_preferences_edit_text = (EditText) findViewById(R.id.a8_shared_preferences_edittext);
    }

    public void on_save(View view) {
        shared_preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_preferences.edit();
        String entered_text = a8_shared_preferences_edit_text.getText().toString();
        editor.putString(a8_shared_preferences.SAVED_TEXT_KEY, entered_text);
        editor.commit();
        Toast.makeText(this, "Text saved!", Toast.LENGTH_SHORT).show();
    }

    public void on_load(View view) {
        shared_preferences = getPreferences(MODE_PRIVATE);
        String loaded_text = shared_preferences.getString(a8_shared_preferences.SAVED_TEXT_KEY, "");
        a8_shared_preferences_edit_text.setText(loaded_text);
        Toast.makeText(this, "Text loaded!", Toast.LENGTH_SHORT).show();
    }
}
