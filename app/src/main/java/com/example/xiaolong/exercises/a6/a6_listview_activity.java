package com.example.xiaolong.exercises.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiaolong.exercises.MyActivity;
import com.example.xiaolong.exercises.R;

import java.util.ArrayList;
import java.util.HashMap;

public class a6_listview_activity extends Activity {

    private static final String LISTVIEW_ACTIVITY_LOG_TAG = "LISTVIEW";

    private HashMap<String, Boolean> existing_map = new HashMap<>();
    private ArrayList<String> list_of_strings = new ArrayList<>();
    private ListView equations_listview;
    private ArrayAdapter<String> listview_adapter;
    private Intent open_calculator_intent;


    // WORKAROUND BECAUSE OF ANDROID BUGS
    private TextView selected_listview_item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a6_listview_activity);

        list_of_strings.add("Test Entry");
        listview_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_of_strings);
        equations_listview = (ListView) findViewById(R.id.a6_listview_listview);
        equations_listview.setAdapter(listview_adapter);

        registerForContextMenu(equations_listview);

        equations_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "ListItem " + view + " at " + position + " clicked!");
                toggle_selection(view);
                selected_listview_item = (TextView) view;
            }
        });
    }

    /**
     * One day this function might just work!
     * @param view
     */
    private void toggle_selection(View view) {
        if(view.isSelected()) {
            view.setSelected(false);
            equations_listview.clearChoices();
        } else {
            view.setSelected(true);
        }
    }

    public void on_calc(View view) {
        if (selected_listview_item != null) {
            Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "Selected View is: " + equations_listview.getSelectedView());
            Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "Selected View is: " + selected_listview_item);
            String equation_string = selected_listview_item.getText().toString();
            Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "String in selected view is: " + selected_listview_item.getText().toString());

            Intent open_calculator_with_equation_intent = new Intent(this, a_calculator.class);
            open_calculator_with_equation_intent.putExtra(a_calculator.SINGLE_EQUATION_EXTRA_STRING, equation_string);

            startActivityForResult(open_calculator_with_equation_intent, MyActivity.ENTER_EQUATION.ordinal());
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.a6_listview_context_menu_clear_list_item:
                listview_adapter.clear();
                return true;
            case R.id.a6_listview_context_menu_do_nothing_item:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    public void on_add(View view) {
        open_calculator_activity();
    }

    private void open_calculator_activity() {
        if (open_calculator_intent == null) {
            open_calculator_intent = new Intent(this, a_calculator.class);
        }
        startActivityForResult(open_calculator_intent, MyActivity.ENTER_EQUATION.ordinal());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent_with_data) {
        if (resultCode == RESULT_OK) {
            if (intent_with_data == null) {
                Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "intent was null");
            } else {
                Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "checking who responded");
                if(requestCode == MyActivity.ENTER_EQUATION.ordinal()) {
                    Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "calculator responded");
                    if(intent_with_data.hasExtra(a_calculator.EQUATIONS_EXTRA_STRING)) {
                        Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "intent has required extra, now adding it to listview adapter");
                        String[] equations_data = intent_with_data.getStringArrayExtra(a_calculator.EQUATIONS_EXTRA_STRING);
                        if (equations_data != null) {
                            for (String equation : intent_with_data.getStringArrayExtra(a_calculator.EQUATIONS_EXTRA_STRING)) {
                                Log.d(a6_listview_activity.LISTVIEW_ACTIVITY_LOG_TAG, "received:" + equation);
                                add_listview_item(equation);
                                selected_listview_item = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private void add_listview_item(String item) {
        if(!existing_map.containsKey(item)) {
            while (listview_adapter.getCount() >= 15) {
                String last_item = listview_adapter.getItem(listview_adapter.getCount() - 1);
                listview_adapter.remove(last_item);
            }
            list_of_strings.add(0, item);
            existing_map.put(item, new Boolean(true));
            listview_adapter.notifyDataSetChanged();
        }
    }
}
