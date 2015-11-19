package com.example.xiaolong.exercises.a7;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.widget.Toast;

import com.example.xiaolong.exercises.R;

import java.util.ArrayList;
import java.util.List;

public class a7_intents extends Activity {

    private static final String LOG_TAG = "Intents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a7_intents);
    }

    public void on_google(View view) {
        Intent open_google_intent = new Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com")
        );
        startActivity(open_google_intent);
    }

    public void on_google_with_query(View view) {
        Intent open_google_with_query_intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.de/search?q=th+wildau")
        );
        startActivity(open_google_with_query_intent);
    }

    public void on_google_maps_with_query(View view) {
        Intent open_google_maps_with_query_intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.de/maps/place/University+of+Applied+Sciences+Wildau,+Hochschulring+1,+15745+Wildau/@52.319655,13.6276173,17z")
        );
        startActivity(open_google_maps_with_query_intent);
    }

    public void on_show_contact(View view) {
        List<String> contact_ids = new ArrayList<>();
        List<String> contact_names = new ArrayList<>();
        List<String> contact_lookup_keys = new ArrayList<>();

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                contact_ids.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID)));
                contact_names.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                contact_lookup_keys.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)));
            }
        }

        for(int i = 0; i < contact_ids.size(); i++) {
            Log.d(a7_intents.LOG_TAG,
                "Contact Name: " + contact_names.get(i) +
                " Contact ID: " + contact_ids.get(i) +
                " LookupKey " + contact_lookup_keys.get(i)
            );
        }

        Intent show_contact_intent = new Intent(
            Intent.ACTION_VIEW,
            //Uri.parse("content://contacts/people/" + Uri.encode("2"))
            Uri.parse("content://contacts/people/" + contact_ids.get(1))
        );

        startActivity(show_contact_intent);
    }

    public void list_contacts() {
        final List<String> contact_ids = new ArrayList<>();
        final List<String> contact_names = new ArrayList<>();
        final List<String> contact_lookup_keys = new ArrayList<>();

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                contact_ids.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID)));
                contact_names.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                contact_lookup_keys.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)));
            }
        }
    }

    public void add_contact(View view) {
        ContentResolver cr = getContentResolver();
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,"accountname@gmail.com")
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "com.google")
                .build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                .build());


        try {
            cr.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (OperationApplicationException ex) {
            ex.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void on_edit_contact(View view) {
        ContentResolver content_resolver = getContentResolver();
        Cursor contacts_cursor = content_resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (contacts_cursor.getCount() > 0) {
            while (contacts_cursor.moveToNext()) {

                String id = contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if (Integer.parseInt(contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                    Cursor phone_cursor = content_resolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                        new String[]{id},
                        null
                    );

                    while (phone_cursor.moveToNext()) {
                        String phoneNo = phone_cursor.getString(phone_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Toast.makeText(getApplicationContext(), "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
                    }
                    phone_cursor.close();
                }

            }
        }
    }

    public void on_show_phone_number(View view) {
        final String TELEPHONE_NUMBER = "015125965178";
        Intent call_number_intent = new Intent();
        call_number_intent.setAction(Intent.ACTION_DIAL);
        call_number_intent.setData(Uri.parse("tel:" + TELEPHONE_NUMBER));

        if (call_number_intent.resolveActivity(getPackageManager()) != null) {
            startActivity(call_number_intent);
        }
    }

    public void on_call_phone_number(View view) {

    }

    public void on_set_alarm(View view) {

    }

    public void on_create_calendar_event(View view) {

    }

    public void on_send_email(View view) {

    }

    public void on_send_sms(View view) {

    }

    public void on_inspect_other_activity(View view) {
        // TODO: ???
    }
}
